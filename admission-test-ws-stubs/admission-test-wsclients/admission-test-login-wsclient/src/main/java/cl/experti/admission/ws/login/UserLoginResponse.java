
package cl.experti.admission.ws.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para userLoginResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="userLoginResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dto.login.ws.admission.experti.cl}loginResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userLoginResponse", propOrder = {
    "loginResponse"
})
public class UserLoginResponse {

    @XmlElement(namespace = "http://dto.login.ws.admission.experti.cl")
    protected LoginResponse loginResponse;

    /**
     * Obtiene el valor de la propiedad loginResponse.
     * 
     * @return
     *     possible object is
     *     {@link LoginResponse }
     *     
     */
    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    /**
     * Define el valor de la propiedad loginResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginResponse }
     *     
     */
    public void setLoginResponse(LoginResponse value) {
        this.loginResponse = value;
    }

}
