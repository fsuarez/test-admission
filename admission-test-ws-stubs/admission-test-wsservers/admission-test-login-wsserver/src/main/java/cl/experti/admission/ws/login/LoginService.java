package cl.experti.admission.ws.login;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;

@WebService(targetNamespace = "http://login.ws.admission.experti.cl")
public interface LoginService {
    /**
     * Verifica el login de usuario y envia respuesta.
     * 
     * @param loginRequest
     *            Peticion de login
     * @return respuesta a peticion de login
     */
    @WebMethod
    @WebResult(name = "loginResponse", targetNamespace = "http://dto.login.ws.admission.experti.cl")
    public LoginResponse userLogin(@WebParam(name="loginRequest", targetNamespace="http://dto.login.ws.admission.experti.cl")LoginRequest loginRequest);
}
