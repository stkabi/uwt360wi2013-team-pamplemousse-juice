package test;

import java.util.ArrayList;

import data.DataProvider;
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
        
        //test serialization
        String data;
        data = u.serialize();
        
        //test deserialization
        User u2 = User.deserialize(DataProvider.splitString(data));
        System.out.println("deserialize: " + (u2.getID().compareTo(u.getID()) == 0));
    }
    
    public void testDataProvider() {
        User u = new User();
        u.setID("1-TESTID-1");
        u.setEmail("herp@derp.com");
        DataProvider dp = new DataProvider();
        
        //test save user
        dp.saveUser(u);
        
        //test get all users
        ArrayList<User> list = dp.getAllUsers();
        
        //last user should be our new one, check id
        System.out.println("add new: " + (list.get(list.size() - 1).getID().compareTo(u.getID()) == 0));
        
        //test get user by id
        System.out.println("get by id: " + (dp.getUserById(u.getID()).getID().compareTo(u.getID()) == 0));
        
        //test get user by email
        System.out.println("get by email: " + (dp.getUserByEmail(u.getEmail()).getID().compareTo(u.getID()) == 0));
    }

    public Test() {
        this.testUser();
        this.testDataProvider();
    }
    
    public static void main(String[] args) {
        new Test();
    }

}
