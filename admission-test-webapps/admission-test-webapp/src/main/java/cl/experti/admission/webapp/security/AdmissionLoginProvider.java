package cl.experti.admission.webapp.security;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cl.experti.admission.ws.login.LoginRequest;
import cl.experti.admission.ws.login.LoginService;
import cl.experti.admission.ws.login.UserLogin;
import cl.experti.admission.ws.login.UserLoginResponse;

@Service("admissionLoginProvider")
public class AdmissionLoginProvider extends AbstractUserDetailsAuthenticationProvider {
    @Resource(name = "loginServiceClient")
    private LoginService loginService;
    private static Logger logger;
    
    static {
	logger = LoggerFactory.getLogger(AdmissionLoginProvider.class);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
	logger.debug("TO-DO Method");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	try {
	    String password = authentication.getCredentials().toString();
	    UserLogin userLoginParams = new UserLogin();
	    LoginRequest userParams = new LoginRequest();
	    
	    userParams.setUsername(username);
	    userParams.setPassword(password);
	    userLoginParams.setLoginRequest(userParams);
	    UserLoginResponse loginResponse = loginService.userLogin(userLoginParams);
	    
	    BigDecimal loginResponseCode = loginResponse.getLoginResponse().getResponseCode();
	    boolean authenticated = BigDecimal.ZERO.equals(loginResponseCode);
	    
	    if (authenticated) {
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new AdmissionAuthority("AdmissionWebapp"));
	        return new AdmissionUser(username, password, true, true, true, true, grantedAuthorities);
	    } else {
	        throw new BadCredentialsException("No coincide par usuario/password");
	    }
	} catch (SOAPFaultException e) {
	    logger.error("Error en conexion a servicio de login");
	    throw new SoapFailedAuthenticationException("Error en conexion a servicio de login", e);
	}
    }
}