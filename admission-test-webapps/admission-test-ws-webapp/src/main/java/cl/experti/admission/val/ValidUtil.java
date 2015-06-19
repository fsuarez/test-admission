package cl.experti.admission.val;

import cl.experti.admission.ws.login.config.LoginServiceRequiredFieldProcessor;
import cl.experti.admission.ws.login.impl.LoginServiceImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Francisco
 */
public class ValidUtil {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(ValidUtil.class);
    }

    public static boolean validateExistingEmployee(String employeeName) {
        boolean reTurn = Boolean.FALSE;
        try {
            BufferedReader br = new BufferedReader(new FileReader(LoginServiceRequiredFieldProcessor.RUTA_EMP));
            String sCurrentLine;
            String empleados = StringUtils.EMPTY;

            while ((sCurrentLine = br.readLine()) != null) {
                empleados = sCurrentLine;
            }

            List<String> empleadosExperti = new ArrayList<>();
            String[] trab = empleados.split(",");
            for (int i = 0; i < 13; i++) {
                empleadosExperti.add(trab[i]);
            }

            reTurn = empleadosExperti.contains(employeeName);

        } catch (IOException e) {
            logger.debug("Ocurrio un problema al validar la existencia del usuario en los trabajadores de experti: " + e);
        }
        return reTurn;
    }
}
