package com.tomsovi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author P3400177
 */
public class RegExRouter implements Router {
    private State owningState;
    private Map<String,State> routes;

    public RegExRouter(State owningState) {
        this.routes = new HashMap<String,State>();
        this.owningState = owningState;
    }

    public void addRoute(String signal, State target) {
        routes.put(signal, target);
    }
    
    public State route(String signal) {
        if (routes.containsKey(signal))
            return routes.get(signal);
        else {
            for (String key : routes.keySet()) {
                if (signal.matches(key))
                    return routes.get(key);
            }
            return owningState.getDefaultRoute();
        }
    }
    
}
