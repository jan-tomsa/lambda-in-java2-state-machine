package com.tomsovi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author P3400177
 */
public class State {
    private String message;
    private Map<String,State> routes;
    private State defaultRoute;
    
    /** 
     * Initialize state.
     * defaultRoute set to self by default
     * @param message - message to display when state is reached
     */
    public State(String message) {
        this.message = message;
        routes = new HashMap<String,State>();
        defaultRoute = this;
    }
    
    public void setDefaultRoute( State target ) {
        defaultRoute = target;
    }
    
    public void addRoute(String signal, State target) {
        routes.put(signal, target);
    }
    
    public State route(String signal) {
        if (routes.containsKey(signal))
            return routes.get(signal);
        else
            return defaultRoute;
    }
    
    public String getMessage() {
        return message;
    }
}
