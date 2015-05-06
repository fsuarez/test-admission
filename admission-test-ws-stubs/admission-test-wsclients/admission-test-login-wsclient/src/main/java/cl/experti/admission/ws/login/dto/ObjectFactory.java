
package cl.experti.admission.ws.login.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import cl.experti.admission.ws.login.LoginRequest;
import cl.experti.admission.ws.login.LoginResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.experti.admission.ws.login.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginRequest_QNAME = new QName("http://dto.login.ws.admission.experti.cl", "loginRequest");
    private final static QName _LoginResponse_QNAME = new QName("http://dto.login.ws.admission.experti.cl", "loginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.experti.admission.ws.login.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.login.ws.admission.experti.cl", name = "loginRequest")
    public JAXBElement<LoginRequest> createLoginRequest(LoginRequest value) {
        return new JAXBElement<LoginRequest>(_LoginRequest_QNAME, LoginRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.login.ws.admission.experti.cl", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

}
