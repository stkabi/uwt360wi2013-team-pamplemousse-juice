package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entities.Category;
import entities.Contest;
import entities.Entry;
import entities.User;

public class DataProvider {

    private static String DATAPATH = "AppData";
    private static String USERPATH = "Users";
    private static String ENTRYPATH = "Entries";
    private static String CATEGORYPATH = "Categories";
    private static String CONTESTPATH = "Contests";
    private static String DELIMITER = "|";
    
    HashMap<String, ArrayList<String[]>> cache = new HashMap<String, ArrayList<String[]>>();

    public DataProvider() { }

    private ArrayList<String[]> getData(String filePath) {
        
        //cache only single type of data at a time
        if (cache.containsKey(filePath)) {
            return cache.get(filePath);
        } else {
            cache.clear();
        }
        
        ArrayList<String[]> items = new ArrayList<String[]>();
        try {
            //get/create base data dir
            File file = new File(DATAPATH + "/" + filePath);
            if (!file.exists()) { file.mkdirs(); }
            
            //get/create db file
            file = new File(DATAPATH + "/" + filePath + "/" + filePath.toLowerCase() + ".txt");
            if (!file.exists()) { file.createNewFile(); }
            
            //read all lines
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                items.add(DataProvider.splitString(scanner.nextLine()));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //cache it
        cache.put(filePath, items);
        
        return items;
    }
    
    private void saveData(String filePath, String data) {
        //get/create db file
        File file = new File(DATAPATH + "/" + filePath + "/" + filePath.toLowerCase() + ".txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //clear the cache if we've got this data type cached.
        if (this.cache.containsKey(filePath)) {
            this.cache.clear();
        }
    }
    
    public ArrayList<User> getAllUsers() {
        ArrayList<String[]> data = this.getData(USERPATH);
        ArrayList<User> users = new ArrayList<User>();
        for (String[] s : data) {
            users.add(User.deserialize(s));
        }
        return users;
    }

    public User getUserById(String id) {
        ArrayList<User> allUsers = this.getAllUsers();
        for (User u : allUsers) {
            if (u.getID().compareTo(id) == 0) {
                return u;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        ArrayList<User> allUsers = this.getAllUsers();
        for (User u : allUsers) {
            if (u.getEmail().compareTo(email) == 0) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Get all entries
     * @return List of all entries
     */
    public ArrayList<Entry> getAllEntries() { 
        ArrayList<String[]> data = this.getData(ENTRYPATH);
        ArrayList<Entry> entries = new ArrayList<Entry>();
        Entry e;
        for (String[] s : data) {
            e = new Entry();
            e.deserialize(s);
            entries.add(e);
        }
        return entries;
    }

    /**
     * Get an entry by an id
     * @param id Entry id
     * @return entry 
     */
    public Entry getEntryById(String id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        for (Entry e : allEntries) {
            if (e.getID().compareTo(id) == 0) {
                return e;
            }
        }
        return null;
    }

    /**
     * Get entries by user id
     * @param id User id
     * @return Entries for user id
     */
    public ArrayList<Entry> getEntriesByUserId(String id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        ArrayList<Entry> resultList = new ArrayList<Entry>();
        for (Entry e : allEntries) {
            if (e.getUserID().compareTo(id) == 0) {
                resultList.add(e);
            }
        }
        return resultList;
    }

    /**
     * Get all entries for a category
     * @param id Category id
     * @return entries for category
     */
    public ArrayList<Entry> getEntriesByCategoryId(String id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        ArrayList<Entry> resultList = new ArrayList<Entry>();
        for (Entry e : allEntries) {
            if (e.getCategoryID().compareTo(id) == 0) {
                resultList.add(e);
            }
        }
        return resultList;
    }

    /**
     * Get all winning entries.
     * @return Winning entries
     */
    public ArrayList<Entry> getWinningEntries() {
        ArrayList<Entry> allEntries = this.getAllEntries();
        ArrayList<Entry> resultList = new ArrayList<Entry>();
        for (Entry e : allEntries) {
            if (e.isWinner()) {
                resultList.add(e);
            }
        }
        return resultList;
    }

    public Category getCategoryById(String id) {
        //TODO: Implement
        return null;
    }

    public Contest getContestById(String id) {
        //TODO: Implement
        return null;
    }
    
    /**
     * Creates or updates a user object. 
     * @param user User to save.
     * @return set of all users
     */
    public ArrayList<User> saveUser(User user) {
        ArrayList<User> users = this.getAllUsers();
        String data = "";
        boolean update = false;
        for (User u : users) {
            if (u.getID().compareTo(user.getID()) == 0) {
                data += user.serialize() + "\n";
                update = true;
                break;
            } else {
                data += u.serialize() + "\n";
            }
        }
        if (!update) {
            users.add(user);
            data += user.serialize();
        }
        this.saveData(USERPATH, data);
        return users;
    }
    
    /**
     * Serialize an arraylist of properties to a string with a delimiter
     * @param properties
     * @return
     */
    public static String serialize(ArrayList<Object> properties) {
        String data = "";
        for (Object p : properties) {
            if (p != null) {
                data += p.toString();
            }
            data += DELIMITER;
        }
        return data;
    }
    
    /**
     * Splits a string into a string array based on delimiter.
     * @param data String to split
     * @return string array
     */
    public static String[] splitString(String data) {
        return data.split("\\" + DELIMITER, -1);
    }

}
