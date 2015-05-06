
package cl.experti.admission.ws.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para userLogin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="userLogin"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dto.login.ws.admission.experti.cl}loginRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userLogin", propOrder = {
    "loginRequest"
})
public class UserLogin {

    @XmlElement(namespace = "http://dto.login.ws.admission.experti.cl")
    protected LoginRequest loginRequest;

    /**
     * Obtiene el valor de la propiedad loginRequest.
     * 
     * @return
     *     possible object is
     *     {@link LoginRequest }
     *     
     */
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    /**
     * Define el valor de la propiedad loginRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginRequest }
     *     
     */
    public void setLoginRequest(LoginRequest value) {
        this.loginRequest = value;
    }

}
