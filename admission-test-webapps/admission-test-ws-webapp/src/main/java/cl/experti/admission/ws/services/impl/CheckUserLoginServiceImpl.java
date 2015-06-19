package cl.experti.admission.ws.services.impl;

import cl.experti.admission.val.ValidUtil;
import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;
import cl.experti.admission.ws.services.CheckUserLoginService;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

@Service("checkLoginService")
public class CheckUserLoginServiceImpl implements CheckUserLoginService {

    @Resource(name = "userNames")
    private Properties users;
    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(CheckUserLoginServiceImpl.class);
    }

    @Override
    public LoginResponse checkLoginService(LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        boolean existsUser = users.containsKey(request.getUserName());
        logger.debug("Exists user? {}", existsUser);

        if (existsUser) {
            String pass = request.getPassword();
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.update(pass.getBytes());
                boolean passwordMatches = StringUtils.equals(new BASE64Encoder().encode(digest.digest()), (String) users.get(request.getUserName()));
                logger.debug("Password matches? {}", passwordMatches);

                if (passwordMatches) {
                    if (ValidUtil.validateExistingEmployee(request.getUserName())) {
                        loginResponse.setLoginResponseCode(new BigDecimal(0));
                        loginResponse.setLoginResponseMessage("Logged successfully");
                        List<String> userRoles = new ArrayList<>();
                        userRoles.add("AdmissionWebapp");
                        //userRoles.add("RolNoValido");
                        loginResponse.setUserRoles(userRoles);
                    } else {
                        loginResponse.setLoginResponseCode(new BigDecimal(3));
                        loginResponse.setLoginResponseMessage("Usuario no es parte de la empresa");
                    }
                } else {
                    loginResponse.setLoginResponseCode(new BigDecimal(2));
                    loginResponse.setLoginResponseMessage("Password do not matches");
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }

        } else {
            loginResponse.setLoginResponseCode(new BigDecimal(1));
            loginResponse.setLoginResponseMessage("User not found");
        }

        return loginResponse;
    }

}
