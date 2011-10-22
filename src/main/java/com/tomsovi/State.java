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
    private State defaultRoute = null;
    
    /** 
     * Initialize state.
     * defaultRoute set to self by default
     * @param message - message to display when state is reached
     */
    public State(String message) {
        this.message = message;
        routes = new HashMap<String,State>();
    }
    
    public void setDefaultRoute( State target ) {
        defaultRoute = target;
    }
    
    public void addRoute(String signal, State target) {
        routes.put(signal, target);
    }

    /**
     * Route from this state to another based on signal
     * @param signal = received signal
     * @return State determined by signal. 
     *      If received signal matches exactly to routing pattern, relevant route is used.
     *      Otherwise match routing pattern to received signal - route if matches.
     */
    public State route(String signal) {
        if (routes.containsKey(signal))
            return routes.get(signal);
        else {
            for (String key : routes.keySet()) {
                if (signal.matches(key))
                    return routes.get(key);
            }
            return getDefaultRoute();
        }
    }
    
    public String getMessage() {
        return message;
    }

    private State getDefaultRoute() {
        if (defaultRoute == null)
            return this;
        else
            return defaultRoute;
    }
}
