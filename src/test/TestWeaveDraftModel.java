package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ui.weavedraft.Model;

public class TestWeaveDraftModel {

    static Model model = new Model(16, 4, 4, 16);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception { }

    @AfterClass
    public static void tearDownAfterClass() throws Exception { }

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void testEverything() {
        model.setTread(0, 0, true);
        model.setTieUp(0, 1, true);
        model.setThread(0, 1, true);
        assertTrue(model.getValue(0,0));
        
        model.setTread(0, 3, true);
        assertTrue(model.getValue(0,3));
        assertFalse(model.getValue(1,3));
        
        model.setTieUp(1, 0, true);
        model.setThread(3, 0, true);
        model.setTread(1, 1, true);
        assertTrue(model.getValue(3,1));
    }
}
