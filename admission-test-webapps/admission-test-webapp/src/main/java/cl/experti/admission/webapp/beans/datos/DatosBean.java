package cl.experti.admission.webapp.beans.datos;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Francisco
 */
@Component
@Scope("request")
public class DatosBean {

    private String rut;
    private String valorCargo;

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(DatosBean.class);
    }

    /**
     * @return the valorCargo
     */
    public String getValorCargo() {
        return valorCargo;
    }

    /**
     * @param valorCargo the valorCargo to set
     */
    public void setValorCargo(String valorCargo) {
        this.valorCargo = valorCargo;
    }

    public static class Cargo {

        public String nombreCargo;
        public String valor;

        public Cargo(String cargoLabel, String cargoValor) {
            this.nombreCargo = cargoLabel;
            this.valor = cargoValor;
        }

        public String getCargoLabel() {
            return nombreCargo;
        }

        public String getCargoValor() {
            return valor;
        }
    }

    public Cargo[] listaCargo;

    public Cargo[] getCargos() {
        listaCargo = new Cargo[3];
        listaCargo[0] = new Cargo("Jefe de Poectos", "1");
        listaCargo[1] = new Cargo("QA", "2");
        listaCargo[2] = new Cargo("Coder", "3");
        return listaCargo;
        
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    public String finalizar() {
        logger.debug("finalizando prueba");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:\\finalizacion.txt"), "utf-8"))) {
            writer.write("Rut: " + rut + " Cargo: " + valorCargo);
        } catch (Exception e) {
            logger.debug(e.toString());
        }

        return "terminar";
    }

}
