package cl.experti.admission.webapp.security;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.experti.admission.ws.login.LoginRequest;
import cl.experti.admission.ws.login.LoginResponse;
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

    @PostConstruct
    public void init() {
        setHideUserNotFoundExceptions(false);
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
                return new AdmissionUser(username, password, true, true, true, true, mapRoles(loginResponse.getLoginResponse()));
            } else {
                AuthenticationException authEx = mapLoginException(loginResponse.getLoginResponse());
                throw authEx;
            }
        } catch (SOAPFaultException e) {
            logger.error("Error en conexion a servicio de login");
            throw new SoapFailedAuthenticationException("Error en conexion a servicio de login", e);
        }
    }

    private Collection<GrantedAuthority> mapRoles(LoginResponse loginResponse) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for (String userRole : loginResponse.getUserRoles()) {
            roles.add(new AdmissionAuthority(userRole));
        }

        return roles;
    }

    public AuthenticationException mapLoginException(LoginResponse loginResponse) {
        AuthenticationException authEx = null;
        BigDecimal responseCode = loginResponse.getResponseCode();
        switch (responseCode.intValue()) {
            case 1:
                authEx = new UsernameNotFoundException("Usuario no encontrado");
                break;
            case 2:
                authEx = new BadCredentialsException("Contrasena no corresponde al usuario ingresado");
                break;
            case 3:
                authEx = new BadCredentialsException("Usuario ingresado no es parte de la empresa");
                break;
            default:
                authEx = new InsufficientAuthenticationException("Fallo no contemplado");
                break;
        }

        throw authEx;
    }
}
