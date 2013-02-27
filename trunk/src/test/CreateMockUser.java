package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.*;
import data.DataProvider;

public class CreateMockUser {
	
	static DataProvider dp = new DataProvider();
	static User organizer = dp.getUserByEmail("organizer@gmail.com");
	static User judge = dp.getUserByEmail("judge@gmail.com");
	static User contestant = dp.getUserByEmail("contestant@gmail.com");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
        if (organizer == null) {
            organizer = new User();
            organizer.setName("Joshua Organizer");
            organizer.setRole(User.Role.ORGANIZER);
            organizer.setAddress("3970 Harris Rd, Silverdale, WA");
            organizer.setPassword("pass");
            organizer.setEmail("organizer@gmail.com");
            organizer.setPhoneNumber("(408) 227-3610");
            dp.saveItem(organizer);
        }
        
        if (judge == null) {
            judge = new User();
            judge.setName("Jonathan Judge");
            judge.setRole(User.Role.JUDGE);
            judge.setAddress("2843 Sherman Ave, Camden, NJ");
            judge.setPassword("pass");
            judge.setEmail("judge@gmail.com");
            judge.setPhoneNumber("(510) 522-8058");
            dp.saveItem(judge);
        }
        
        if (contestant == null) {
            contestant = new User();
            contestant.setName("Linda Contestant");
            contestant.setRole(User.Role.CONTESTANT);
            contestant.setAddress("235 E Garvey Ave, Monterey Park, CA");
            contestant.setPassword("pass");
            contestant.setEmail("contestant@gmail.com");
            contestant.setPhoneNumber("(626) 288-8613");
            dp.saveItem(contestant);
        }
        
        if (dp.getAllCategories().size() == 0) {
            Category c1 = new Category();
            c1.setName("Classic");
            Category c2 = new Category();
            c2.setName("Hipster");
            Category c3 = new Category();
            c3.setName("Ancient");
            
            c1.addJudgeID(judge.getID());
            c2.addJudgeID(judge.getID());
            
            dp.saveItem(c1);
            dp.saveItem(c2);
            dp.saveItem(c3);
            
            
            Entry e1 = new Entry();
            e1.setUserID(contestant.getID());
            e1.setCategoryID(c1.getID());
            e1.setOtherDetails("Really cool weave in Classic category.");
            dp.saveItem(e1);
            
            Entry e2 = new Entry();
            e2.setUserID(contestant.getID());
            e2.setCategoryID(c2.getID());
            e2.setOtherDetails("Really cool weave in Hipster category.");
            dp.saveItem(e2);
            
            Entry e3 = new Entry();
            e3.setUserID(contestant.getID());
            e3.setCategoryID(c3.getID());
            e3.setOtherDetails("Really cool weave in Ancient category.");
            dp.saveItem(e3);
        }
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
		public void testOrganizer() {
		//System.out.println(organizer.getName());
		String pwPlain = "pass";
		
		//Checking the stored values such as name, role, address, password
		//email and phone number for the user "organizer"
		assertTrue (organizer.getName().compareTo("Joshua Organizer") == 0);
		assertTrue (organizer.getRole().compareTo(User.Role.ORGANIZER) == 0);
		assertTrue (organizer.getAddress().compareTo("3970 Harris Rd, Silverdale, WA") == 0);
		assertTrue (organizer.authenticate(pwPlain));
		assertTrue (organizer.getEmail().compareTo("organizer@gmail.com") == 0);
		assertTrue (organizer.getPhoneNumber().compareTo("(408) 227-3610") == 0);
		}
	
	@Test
		public void testJudge() {
			
		String pwPlain = "pass";
		
		//Checking the stored values such as name, role, address, password
		//email and phone number for the user "judge"
		assertTrue (judge.getName().compareTo("Jonathan Judge") == 0);
		assertTrue (judge.getRole().compareTo(User.Role.JUDGE) == 0);
		assertTrue (judge.getAddress().compareTo("2843 Sherman Ave, Camden, NJ") == 0);
		assertTrue (judge.authenticate(pwPlain));
		assertTrue (judge.getEmail().compareTo("judge@gmail.com") == 0);
		assertTrue (judge.getPhoneNumber().compareTo("(510) 522-8058") == 0);
		}
	
	@Test
		public void testContestant() {
		
		String pwPlain = "pass";
		
		//Checking the stored values such as name, role, address, password
		//email and phone number for the user "contestant"
		assertTrue (contestant.getName().compareTo("Linda Contestant") == 0);
		assertTrue (contestant.getRole().compareTo(User.Role.CONTESTANT) == 0);
		assertTrue (contestant.getAddress().compareTo("235 E Garvey Ave, Monterey Park, CA") == 0);
		assertTrue (contestant.authenticate(pwPlain));
		assertTrue (contestant.getEmail().compareTo("contestant@gmail.com") == 0);
		assertTrue (contestant.getPhoneNumber().compareTo("(626) 288-8613") == 0);
		}

	// Now I will modify the information of an user.
	
	
	}


