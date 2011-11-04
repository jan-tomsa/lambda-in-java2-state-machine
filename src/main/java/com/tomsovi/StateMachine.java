
package com.tomsovi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author P3400177
 */
public class StateMachine {
    private List<State> states;
    private State currentState;
    
    public StateMachine() {
        states = new ArrayList();
        currentState = null; //TODO
    }

    public State getCurrentState() {
        return currentState;
    }
    
    public String getCurrentMessage() {
        return currentState.getMessage();
    }
    
    public void addState( State state ) {
        states.add(state);
    }
    
    public void processSignal( String signal ) {
        currentState = currentState.route(signal);
    }
}
