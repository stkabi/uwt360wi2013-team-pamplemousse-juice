package test;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import ui.LiteTable;
import data.DataProvider;
import entities.Category;
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
        u.setName("John Doe");
        u.setAddress("4102 Herp Derp, Tacoma, Washington");
        u.setPhoneNumber("360-990-3815");
        u.setEmail("herp@derp.com");
        DataProvider dp = new DataProvider();
        
        Category c = new Category();
        c.setName("CoolWeavez~~");
        ArrayList<String> judgeIDs = new ArrayList<String>();
        judgeIDs.add(u.getID());
        c.setJudgeIDs(judgeIDs);
        dp.saveItem(c);

        // test save user
        dp.saveItem(u);
        
        //test get all users
        ArrayList<User> list = dp.getAllUsers();
        System.out.println("get all users: " + (list.size() > 0));
        
        //test get user by id
        System.out.println("get by id: " + (dp.getUserById(u.getID()).getID().compareTo(u.getID()) == 0));
        
        //test get user by email
        System.out.println("get by email: " + (dp.getUserByEmail(u.getEmail()).getID().compareTo(u.getID()) == 0));
        
        
        //test update user
        User u2a = new User();
        u2a.setEmail("test@email.com");
        dp.saveItem(u2a);
        User u2b = dp.getUserByEmail("test@email.com");
        u2b.setEmail("changed@email.com");
        dp.saveItem(u2b);
        u2b = dp.getUserByEmail("test@email.com");
        System.out.println("check null after update: " + (u2b == null));
        u2b = dp.getUserByEmail("changed@email.com");
        System.out.println("check changed after update: " + (u2b != null));
        
        dp.removeItem(u2b);
        
        Entry e = new Entry();
        e.setOtherDetails("details");
        e.setUserID(u.getID());
        e.setCategoryID(c.getID());
        
        dp.saveItem(e);
        
        ArrayList<Entry> entryList = dp.getAllEntries();
        
        Entry e2 = entryList.get(entryList.size() - 1);
        
        //test save entry
        System.out.println("check save entry: " + (e2.getID().compareTo(e.getID()) == 0));
        
        //test get entry by ID
        System.out.println("check get entry by ID: " + (dp.getEntryById(e.getID()).getID().compareTo(e.getID()) == 0));
        
        //test get entry by UserID
        ArrayList<Entry> userEntryList = dp.getEntriesByUserId(u.getID());
        System.out.println("check get entry by UserId: " + (userEntryList.get(userEntryList.size() - 1).getID().compareTo(e2.getID()) == 0));
        
        dp.removeItem(e);
        dp.removeItem(u);
        dp.removeItem(c);
    }
    
    private void testTable() {
        JFrame frame = new JFrame("Table test");
        DataProvider dp = new DataProvider();
        ArrayList<User> users = dp.getAllUsers();
        Object[][] data = new Object[users.size()][6];
        
        for (int i = 0; i < users.size(); i += 1) {
            data[i][0] = users.get(i).getID();
            data[i][1] = users.get(i).getEmail();
            data[i][2] = users.get(i).getName();
            data[i][3] = users.get(i).getRole();
            data[i][4] = users.get(i).getAddress();
            data[i][5] = users.get(i).getPhoneNumber();
        }
        
        LiteTable table = new LiteTable(data, new Object[] {"UserID", "Email", "Name", "Role", "Address", "Phone"});
        frame.setLayout(new BorderLayout());
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // center
        frame.setVisible(true);
    }
    
    private void createMockUsers() {
        DataProvider dp = new DataProvider();
        
        User organizer = dp.getUserByEmail("organizer@gmail.com");
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
        
        User judge = dp.getUserByEmail("judge@gmail.com");
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
        
        User contestant = dp.getUserByEmail("contestant@gmail.com");
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

    public Test() {
        this.createMockUsers();
        this.testUser();
        this.testDataProvider();
        this.testTable();
    }
    
    public static void main(String[] args) {
        new Test();
    }

}
