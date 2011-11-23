package com.tomsovi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author P3400177
 */
public class State {
    private String message;
    private Router router;
    private State defaultRoute = null;
    
    /** 
     * Initialize state.
     * defaultRoute set to self by default
     * @param message - message to display when state is reached
     */
    public State(String message) {
        this.message = message;
        router = new RegExRouter(this);
    }
    
    public void setDefaultRoute( State target ) {
        defaultRoute = target;
    }
    
    public void setRouter( Router newRouter ) {
        this.router = newRouter;
    }

    /**
     * Route from this state to another based on signal
     * @param signal = received signal
     * @return State determined by signal. 
     *      If received signal matches exactly to routing pattern, relevant route is used.
     *      Otherwise match routing pattern to received signal - route if matches.
     */
    public State route(String signal) {
        return router.route(signal);
    }
    
    public String getMessage() {
        return message;
    }

    public State getDefaultRoute() {
        if (defaultRoute == null)
            return this;
        else
            return defaultRoute;
    }
}
