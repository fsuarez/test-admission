package cl.experti.admission.ws.login.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.experti.admission.ws.login.LoginService;
import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private static Logger logger;
    
    static{
	logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    }

    @Override
    public LoginResponse userLogin(LoginRequest loginRequest) {
	logger.debug("Realizing login with following data: {}", ReflectionToStringBuilder.reflectionToString(loginRequest, ToStringStyle.MULTI_LINE_STYLE));
	
	LoginResponse loginResponse = new LoginResponse();
	loginResponse.setLoginResponseCode(new BigDecimal(0));
	loginResponse.setLoginResponseMessage("Logged successfully");
	
	logger.debug("Returning following login response: {}", ReflectionToStringBuilder.reflectionToString(loginResponse, ToStringStyle.MULTI_LINE_STYLE));
	return loginResponse;
    }
}