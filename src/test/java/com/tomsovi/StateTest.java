package com.tomsovi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for States.
 */
public class StateTest extends TestCase {
    private State state1;
    private State state2;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StateTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( StateTest.class );
    }

    /**
     * Set up two states with routes from each to each other 
     * 
     * +-----+        +------+
     * |     | --A--> |      |
     * |First|        |Second|
     * |     | <--B-- |      |
     * +-----+        +------+
     *
     * @throws Exception 
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        state1 = new State("First state");
        state2 = new State("Second state");
        state1.addRoute("A", state2);
        state2.addRoute("B", state1);
    }
    
    /**
     * Test routing from First to Second state
     */
    public void testRouteFromState() {
        State target = state1.route("A");
        assertEquals(state2,target);
    }

    public void testDefaultRoute() {
        State target = state1.route("X");
        assertEquals(state1,target);
    }
}
