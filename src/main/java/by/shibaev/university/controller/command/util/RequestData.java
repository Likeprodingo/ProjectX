package by.shibaev.university.controller.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class RequestData {
    private Map<String, String[]> parameters;
    private Map<String, Object> attributes;
    private HttpServletRequest request;
    private HttpSession session;

    public RequestData(HttpServletRequest request) {
        parameters = request.getParameterMap();
        attributes = new HashMap<>();
        session = request.getSession();
        this.request = request;
    }

    public Map<String, String[]> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public Optional<String[]> getParameter(String name) {
        Optional<String[]> res = Optional.empty();
        for (String key : parameters.keySet()) {
            if (key.equals(name)) {
                res = Optional.of(parameters.get(key));
            }
        }
        return res;
    }

    public boolean isSessionNew(){
        return session.isNew();
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void setSessionAttribute(String name, Object value) {
        session.setAttribute(name,value);
    }

    public Object getSessionAttribute(String name) {
        return session.getAttribute(name);
    }

    public void restore() {
        for (String key : attributes.keySet()) {
            request.setAttribute(key, attributes.get(key));
        }
    }
}
