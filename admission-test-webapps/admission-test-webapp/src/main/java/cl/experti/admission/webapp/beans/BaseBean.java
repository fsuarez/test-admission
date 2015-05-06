package cl.experti.admission.webapp.beans;

import java.util.Arrays;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.experti.admission.webapp.security.AdmissionUser;

@SuppressWarnings("serial")
public class BaseBean implements java.io.Serializable {

    protected AdmissionUser getUsuarioSesion() {
        AdmissionUser user = (AdmissionUser) getPrincipal();
        return user;
    }

    protected String getRequestParameter(String name) {
        Map<String, String> requestParameterMap = getExternalContext().getRequestParameterMap();
        return requestParameterMap.get(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSessionParameter(String name) {
        HttpSession httpSession = getHttpSession();
        return (T) httpSession.getAttribute(name);
    }

    protected Object getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getFlashParameter(String name) {
        Flash flash = getExternalContext().getFlash();
        return (T) flash.get(name);
    }

    protected <T> void setFlashParameter(String name, T value) {
        getExternalContext().getFlash().put(name, value);
    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected void addInfoMessage(String messageFor, String message) {
        getFacesContext().addMessage(messageFor, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    protected void addErrorMessage(String messageFor, String message) {
        getFacesContext().addMessage(messageFor, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected void executeViewScriptsAndUpdate(String[] toExecute, String[] toUpdate) {
        for (String script : toExecute) {
            getRequestContext().execute(script);
        }
        getRequestContext().update(Arrays.asList(toUpdate));
    }

    protected HttpSession getHttpSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(true);
    }
}