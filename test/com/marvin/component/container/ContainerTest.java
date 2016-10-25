package com.marvin.component.container;

import java.util.concurrent.ConcurrentMap;
import junit.framework.TestCase;

public class ContainerTest extends TestCase {
    
    public ContainerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of set method, of class Container.
     */
    public void testSet() {
        System.out.println("set");
        String id = "";
        Object service = null;
        Container instance = new Container();
        instance.set(id, service);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Container.
     */
    public void testGet_String() {
        System.out.println("get");
        String id = "";
        Container instance = new Container();
        Object expResult = null;
        Object result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Container.
     */
    public void testGet_String_Class() {
        System.out.println("get");
        Container instance = new Container();
        Object expResult = null;
        Object result = instance.get(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains() {
        System.out.println("contains");
        String id = "";
        Container instance = new Container();
        boolean expResult = false;
        boolean result = instance.contains(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAliases method, of class Container.
     */
    public void testGetAliases() {
        System.out.println("getAliases");
        Container instance = new Container();
        ConcurrentMap<String, String> expResult = null;
        ConcurrentMap<String, String> result = instance.getAliases();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAliases method, of class Container.
     */
    public void testSetAliases() {
        System.out.println("setAliases");
        ConcurrentMap<String, String> aliases = null;
        Container instance = new Container();
        instance.setAliases(aliases);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAlias method, of class Container.
     */
    public void testAddAlias() {
        System.out.println("addAlias");
        String id = "";
        String alias = "";
        Container instance = new Container();
        instance.addAlias(id, alias);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServices method, of class Container.
     */
    public void testGetServices() {
        System.out.println("getServices");
        Container instance = new Container();
        ConcurrentMap<String, Object> expResult = null;
        ConcurrentMap<String, Object> result = instance.getServices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServices method, of class Container.
     */
    public void testSetServices() {
        System.out.println("setServices");
        ConcurrentMap<String, Object> services = null;
        Container instance = new Container();
        instance.setServices(services);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameter method, of class Container.
     */
    public void testGetParameter_String_Object() {
        System.out.println("getParameter");
        String key = "";
        Object def = null;
        Container instance = new Container();
        Object expResult = null;
        Object result = instance.getParameter(key, def);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameter method, of class Container.
     */
    public void testGetParameter_String() {
        System.out.println("getParameter");
        String key = "";
        Container instance = new Container();
        Object expResult = null;
        Object result = instance.getParameter(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParameter method, of class Container.
     */
    public void testSetParameter() {
        System.out.println("setParameter");
        String key = "";
        Object value = null;
        Container instance = new Container();
        instance.setParameter(key, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameters method, of class Container.
     */
    public void testGetParameters() {
        System.out.println("getParameters");
        Container instance = new Container();
        ConcurrentMap<String, Object> expResult = null;
        ConcurrentMap<String, Object> result = instance.getParameters();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParameters method, of class Container.
     */
    public void testSetParameters() {
        System.out.println("setParameters");
        ConcurrentMap<String, Object> parameters = null;
        Container instance = new Container();
        instance.setParameters(parameters);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Container.
     */
    public void testToString() {
        System.out.println("toString");
        Container instance = new Container();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
