package cl.experti.admission.webapp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Francisco
 */
@FacesValidator("cl.experti.admission.webapp.validator.RutValidator")
public class RutValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String rut = (String) o;
        if (StringUtils.isNotBlank(rut) && StringUtils.contains(rut, "-")) {
            String[] r = StringUtils.split(rut, "-");
            if (!ValidarRut(r[0], r[1])) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rut no valido", "Rut no valido"));
            }
        } else {
            if (!StringUtils.contains(rut, "-")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rut debe ser con guion", "Rut debe ser con guion"));
            } else if (StringUtils.length(rut) < 10) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato invalido", "formato invalido"));
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay data para validar", "Ingrese rut valido para validacion"));
            }
        }

    }

    private boolean ValidarRut(String rut2, String dv2) {

        int rut = Integer.parseInt(rut2);
        char dv = dv2.charAt(0);
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }

}
