package cl.experti.admission.ws.services;

import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;

public interface CheckUserLoginService {
    /**
     * Verifica si el usuario se encuentra enrolado y devuelve respuesta.
     * 
     * @param request
     *            Datos del usuario a enrolar.
     * @return respuesta a chequeo.
     */
    public LoginResponse checkLoginService(LoginRequest request);
}