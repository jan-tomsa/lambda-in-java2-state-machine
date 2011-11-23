package com.tomsovi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * State machine (very very basic parser)
 *
 */
public class App 
{
    List<State> states;
    
    public App() {
        State stateOutside = new State("Outside");
        State stateInParens1 = new State("In parentheses 1");
        State stateInParens2 = new State("In parentheses 2");
        State stateError = new State("Error - should not come here");
        states = new ArrayList<State>(Arrays.asList(stateOutside, stateInParens1, stateInParens2, stateError));
        RegExRouter roOutToIn1 = new RegExRouter(stateOutside);
        roOutToIn1.addRoute("<", stateInParens1);
        roOutToIn1.addRoute(">", stateError);
        stateOutside.setRouter(roOutToIn1);
        RegExRouter roIn1ToIn2 = new RegExRouter(stateInParens1);
        roIn1ToIn2.addRoute("<", stateInParens2);
        roIn1ToIn2.addRoute(">", stateOutside);
        stateInParens1.setRouter(roIn1ToIn2);
        RegExRouter roIn2ToIn1 = new RegExRouter(stateInParens2);
        roIn2ToIn1.addRoute("<", stateError); // too much parantheses
        roIn2ToIn1.addRoute(">", stateInParens1);
        stateInParens2.setRouter(roIn2ToIn1);
    }
    
    public void execute(List<String> signals) {
        State current = states.get(0);
        for(String signal : signals) {
            System.out.println("----------------");
            System.out.println("Current: " + current.getMessage());
            System.out.println("Received: '" + signal + "'");
            current = current.route(signal);
            System.out.println("New: " + current.getMessage());
        }
        System.out.println("----------------");
    }
    
    public static void main( String[] args )
    {
        List<String> signals = new ArrayList<String>( Arrays.asList("<","x",">") );
        App app = new App();
        app.execute( signals );
    }
}
