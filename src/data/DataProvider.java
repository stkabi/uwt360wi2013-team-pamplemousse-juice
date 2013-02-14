package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import entities.BaseEntity;
import entities.Category;
import entities.Contest;
import entities.Entry;
import entities.User;

public class DataProvider {

    private static String DATAPATH = "AppData";
    private static String USERPATH = "Users";
    private static String ENTRYPATH = "Entries";
    private static String CATEGORYPATH = "Categories";
//    private static String CONTESTPATH = "Contests";
    
    HashMap<String, ArrayList<? extends BaseEntity>> cache = new HashMap<String, ArrayList<? extends BaseEntity>>();

    public DataProvider() { }
    
    /**
     * Get all users
     * @return list of users
     */
    @SuppressWarnings("unchecked")
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = (ArrayList<User>)this.getData(USERPATH);
        return users;
    }
    
    /**
     * Get all entries 
     * @return List of entries
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Entry> getAllEntries() { 
        ArrayList<Entry> entries = (ArrayList<Entry>)this.getData(ENTRYPATH);
        return entries;
    }
    
    /**
     * Get all categories
     * @return List of categories
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Category> getAllCategories() { 
        ArrayList<Category> categories = (ArrayList<Category>)this.getData(CATEGORYPATH);
        return categories;
    }

    /**
     * Get a user by id
     * @param id id of user
     * @return User or null
     */
    public User getUserById(String id) {
        ArrayList<User> allUsers = this.getAllUsers();
        for (User u : allUsers) {
            if (u.getID().compareTo(id) == 0) {
                return u;
            }
        }
        return null;
    }

    /**
     * Get a user by a user's email address
     * @param email email address
     * @return User or null
     */
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

    /**
     * Get a category by id
     * @param id id of category
     * @return Category or null
     */
    public Category getCategoryById(String id) {
        //TODO: Implement
        return null;
    }

    /**
     * Get a contest by id
     * @param id id of contest
     * @return Contest or null
     */
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
        boolean update = false;
        for (User u : users) {
            if (u.getID().compareTo(user.getID()) == 0) {
                u = user;
                update = true;
                break;
            }
        }
        if (!update) {
            users.add(user);
        }
        this.saveData(USERPATH, users);
        return users;
    }
    
    /**
     * Creates or updates an Entry object. 
     * @param entry Entry to save.
     * @return set of all entries
     */
    public ArrayList<Entry> saveEntry(Entry entry) {
        ArrayList<Entry> entries = this.getAllEntries();
        boolean update = false;
        for (Entry e : entries) {
            if (e.getID().compareTo(entry.getID()) == 0) {
                e = entry;
                update = true;
                break;
            }
        }
        if (!update) {
            entries.add(entry);
        }
        this.saveData(ENTRYPATH, entries);
        return entries;
    }

    /**
     * Get all data stored in a text file
     * @param filePath file of text
     * @return List of lines
     */
    @SuppressWarnings("unchecked")
    private ArrayList<? extends BaseEntity> getData(String filePath) {
        
        //cache only single type of data at a time
        if (cache.containsKey(filePath)) {
            return cache.get(filePath);
        } else {
            cache.clear();
        }
        
        ArrayList<? extends BaseEntity> items = new ArrayList<BaseEntity>();
        
        try {
            //get/create base data dir
            File file = new File(DATAPATH + "/" + filePath);
            if (!file.exists()) { file.mkdirs(); }
            
            //get/create db file
            file = new File(DATAPATH + "/" + filePath + "/" + filePath.toLowerCase() + ".txt");
            if (!file.exists()) { 
                file.createNewFile(); 
                return items;
            }
            
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            items = (ArrayList<BaseEntity>) in.readObject();
            in.close();
            fileIn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //cache it
        cache.put(filePath, items);
        
        return items;
    }
    
    /**
     * Save data to a file
     * @param filePath file of text
     * @param data data to save
     */
    private void saveData(String filePath, ArrayList<? extends BaseEntity> data) {
        //get/create db file
        File file = new File(DATAPATH + "/" + filePath + "/" + filePath.toLowerCase() + ".txt");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //clear the cache if we've got this data type cached.
        if (this.cache.containsKey(filePath)) {
            this.cache.clear();
        }
        
        //cache it
        cache.put(filePath, data);
    }

}
