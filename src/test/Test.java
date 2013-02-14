package test;

import java.util.ArrayList;

import data.DataProvider;
import entities.Entry;
import entities.User;


public class Test {
    
    public void testUser() {
        User u = new User();
        
        //set password
        String pwPlain = "herpderp";
        u.setPassword(pwPlain);
        
        //make sure stored password isn't the plaintext
        System.out.println("pw: " + (u.getPassword().compareTo(pwPlain) != 0));
        
        //test authentication
        System.out.println("auth: " + u.authenticate(pwPlain));
    }
    
    public void testDataProvider () {
        User u = new User();
        u.setID("1-TESTID-1");
        u.setEmail("herp@derp.com");
        DataProvider dp = new DataProvider();
        
        //test save user
        dp.saveUser(u);
        
        //test get all users
        ArrayList<User> list = dp.getAllUsers();
        
        //test get user by id
        System.out.println("get by id: " + (dp.getUserById(u.getID()).getID().compareTo(u.getID()) == 0));
        
        //test get user by email
        System.out.println("get by email: " + (dp.getUserByEmail(u.getEmail()).getID().compareTo(u.getID()) == 0));
        
        //test update user
        User u2a = new User();
        u2a.setEmail("test@email.com");
        dp.saveUser(u2a);
        User u2b = dp.getUserByEmail("test@email.com");
        u2b.setEmail("changed@email.com");
        dp.saveUser(u2b);
        u2b = dp.getUserByEmail("test@email.com");
        System.out.println("check null after update: " + (u2b == null));
        u2b = dp.getUserByEmail("changed@email.com");
        System.out.println("check changed after update: " + (u2b != null));
        
        Entry e = new Entry();
        e.setOtherDetails("details");
        e.setUserID(u.getID());
        
        dp.saveEntry(e);
        
        ArrayList<Entry> entryList = dp.getAllEntries();
        
        Entry e2 = entryList.get(entryList.size() - 1);
        
        //test save entry
        System.out.println("check save entry: " + (e2.getID().compareTo(e.getID()) == 0));
        
        //test get entry by ID
        System.out.println("check get entry by ID: " + (dp.getEntryById(e.getID()).getID().compareTo(e.getID()) == 0));
        
        //test get entry by UserID
        ArrayList<Entry> userEntryList = dp.getEntriesByUserId(u.getID());
        System.out.println("check get entry by UserId: " + (userEntryList.get(userEntryList.size() - 1).getID().compareTo(e2.getID()) == 0));
    }

    public Test() {
        this.testUser();
        this.testDataProvider();
    }
    
    public static void main(String[] args) {
        new Test();
    }

}
