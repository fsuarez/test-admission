
package cl.experti.admission.ws.login;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.experti.admission.ws.login package. 
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

    private final static QName _UserLogin_QNAME = new QName("http://login.ws.admission.experti.cl", "userLogin");
    private final static QName _UserLoginResponse_QNAME = new QName("http://login.ws.admission.experti.cl", "userLoginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.experti.admission.ws.login
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserLogin }
     * 
     */
    public UserLogin createUserLogin() {
        return new UserLogin();
    }

    /**
     * Create an instance of {@link UserLoginResponse }
     * 
     */
    public UserLoginResponse createUserLoginResponse() {
        return new UserLoginResponse();
    }

    /**
     * Create an instance of {@link LoginRequest }
     * 
     */
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.ws.admission.experti.cl", name = "userLogin")
    public JAXBElement<UserLogin> createUserLogin(UserLogin value) {
        return new JAXBElement<UserLogin>(_UserLogin_QNAME, UserLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.ws.admission.experti.cl", name = "userLoginResponse")
    public JAXBElement<UserLoginResponse> createUserLoginResponse(UserLoginResponse value) {
        return new JAXBElement<UserLoginResponse>(_UserLoginResponse_QNAME, UserLoginResponse.class, null, value);
    }

}
