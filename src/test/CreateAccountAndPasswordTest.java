package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.User;

public class CreateAccountAndPasswordTest {

    // create an instance of user and a string
    static User u = new User();
    static String pwPlain = "herpderp";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // set password
        u.setPassword(pwPlain);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPassword() {
        assertTrue(u.getPassword().compareTo(pwPlain) != 0);
    }

    @Test
    public void testAuthenticate() {
        assertTrue(u.authenticate(pwPlain));
    }

}
