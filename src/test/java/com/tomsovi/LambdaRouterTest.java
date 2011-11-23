/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsovi;

import junit.framework.TestCase;

/**
 *
 * @author P3400177
 */
public class LambdaRouterTest extends TestCase {
    private State state1;
    private State state2;
    private State state3;
    
    public LambdaRouterTest(String testName) {
        super(testName);
    }
    
    /**
     * Set up three states with routes from each to each other 
     * 
     * +-----+        +------+            +-----+
     * |     | --A--> |      |            |     |
     * |First|        |Second| ---num---> |Third|
     * |     | <--B-- |      |            |     |
     * +-----+        +------+            +-----+
     *
     * @throws Exception 
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        state1 = new State("First state");
        state2 = new State("Second state");
        state3 = new State("Third state");
        state1.setRouter( new Router() {
                    public State route(String signal) {
                        if ("a".equalsIgnoreCase(signal))
                            return state2;
                        else 
                            return state1.getDefaultRoute();
                    }
                });
        state2.setRouter( new Router() {
                    public State route(String signal) {
                        if ("b".equalsIgnoreCase(signal))
                            return state1;
                        else if (signal.matches("\\d+"))
                            return state3;
                        else
                            return state2.getDefaultRoute();                            
                    }
                } );
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test routing from First to Second state
     */
    public void testRouteFromFirstState() {
        State target = state1.route("A");
        assertEquals(state2,target);
    }

    public void testDefaultRouteFromFirstState() {
        State target = state1.route("x");
        assertEquals(state1,target);
    }

    public void testRouteFromSecondStateToFirst() {
        State target = state2.route("B");
        assertEquals(state1,target);
    }

    public void testRouteFromSecondStateToThird() {
        State target = state2.route("1");
        assertEquals(state3,target);
    }

    public void testRouteFromSecondStateToThird2() {
        State target = state2.route("1234567");
        assertEquals(state3,target);
    }
}
