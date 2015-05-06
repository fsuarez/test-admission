package cl.experti.admission.webapp.beans.login;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cl.experti.admission.webapp.beans.BaseBean;
import cl.experti.admission.ws.login.LoginRequest;
import cl.experti.admission.ws.login.LoginService;
import cl.experti.admission.ws.login.UserLogin;
import cl.experti.admission.ws.login.UserLoginResponse;

@Component
@Scope("request")
@SuppressWarnings("serial")
public class LoginBean extends BaseBean {
    private String username;
    private String password;
    @Resource(name = "loginServiceClient")
    private LoginService loginService;
    private static Logger logger;
    
    static {
	logger = LoggerFactory.getLogger(LoginBean.class);
    }

    public String doLogin() throws Exception {
	String resultado = null;

	try {
	    UserLogin userLoginParams = new UserLogin();
	    LoginRequest userParams = new LoginRequest();
	    
	    userParams.setUsername(username);
	    userParams.setPassword(password);
	    userLoginParams.setLoginRequest(userParams);
	    UserLoginResponse loginResponse = loginService.userLogin(userLoginParams);
	    
	    BigDecimal loginResponseCode = loginResponse.getLoginResponse().getResponseCode();
	    boolean authenticated = BigDecimal.ZERO.equals(loginResponseCode);
	    if (authenticated) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		RequestDispatcher dispatcher = null;
		dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(),(ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();
	    } else {
		getFacesContext().addMessage("badPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en verificacion usuario/password", "Error en verificacion usuario/password"));
	    }
	} catch (Exception e) {
	    getFacesContext().addMessage("badPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en verificacion usuario/password", "Error en verificacion usuario/password"));
	    resultado = null;
	}
	return resultado;
    }
    
    public String doLogout() {
	logger.debug("Logout to the system");
	SecurityContextHolder.clearContext();
	return "logoutDone";
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
