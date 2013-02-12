package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
    private static String DELIMITER = "|";
    
    HashMap<String, ArrayList<String>> cache = new HashMap<String, ArrayList<String>>();

    public DataProvider() { }
    
    /**
     * Get all users
     * @return list of users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<String> data = this.getData(USERPATH);
        ArrayList<User> users = new ArrayList<User>();
        for (String s : data) {
            users.add((User)DataProvider.deserialize(s, User.class));
        }
        return users;
    }
    
    /**
     * Get all entries 
     * @return List of entries
     */
    public ArrayList<Entry> getAllEntries() { 
        ArrayList<String> data = this.getData(ENTRYPATH);
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (String s : data) {
            entries.add((Entry)DataProvider.deserialize(s, Entry.class));
        }
        return entries;
    }
    
    /**
     * Get all categories
     * @return List of categories
     */
    public ArrayList<Category> getAllCategories() { 
        ArrayList<String> data = this.getData(CATEGORYPATH);
        ArrayList<Category> categories = new ArrayList<Category>();
        for (String s : data) {
            categories.add((Category)DataProvider.deserialize(s, Category.class));
        }
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
        String data = "";
        boolean update = false;
        for (User u : users) {
            if (u.getID().compareTo(user.getID()) == 0) {
                data += DataProvider.serialize(user) + "\n";
                update = true;
                break;
            } else {
                data += DataProvider.serialize(u) + "\n";
            }
        }
        if (!update) {
            users.add(user);
            data += DataProvider.serialize(user);
        }
        this.saveData(USERPATH, data);
        return users;
    }
    
    /**
     * Creates or updates an Entry object. 
     * @param entry Entry to save.
     * @return set of all entries
     */
    public ArrayList<Entry> saveEntry(Entry entry) {
        ArrayList<Entry> entries = this.getAllEntries();
        String data = "";
        boolean update = false;
        for (Entry e : entries) {
            if (e.getID().compareTo(entry.getID()) == 0) {
                data += DataProvider.serialize(entry) + "\n";
                update = true;
                break;
            } else {
                data += DataProvider.serialize(e) + "\n";
            }
        }
        if (!update) {
            entries.add(entry);
            data += DataProvider.serialize(entry);
        }
        this.saveData(ENTRYPATH, data);
        return entries;
    }
    
    /**
     * Serialize an arraylist of properties to a string with a delimiter
     * @param properties
     * @return
     */
    public static String serialize(BaseEntity obj) {
        String data = "";
        Class<? extends BaseEntity> cls = obj.getClass();
        try {
            ArrayList<Field> fields = DataProvider.getAllFields(cls);
            for (Field f : fields) {
                f.setAccessible(true);
                Object val = f.get(obj);
                if (val != null) {
                    data += val.toString();
                }
                data += DELIMITER;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    /**
     * Deserialize an object from a delimiter separated string to an instance of the class 
     * @param data String to parse
     * @param cls Class to construct
     * @return instance of a class
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object deserialize(String data, Class<? extends BaseEntity> cls) {
        String[] args = DataProvider.splitString(data);
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        
        try {
            ArrayList<Field> fields = DataProvider.getAllFields(cls);
            int index = 0;
            for (Field f : fields) {
                f.setAccessible(true);
                Class<?> type = f.getType();
                if (type.isEnum()) {
                    f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), args[index]));
                } else if (type.equals(boolean.class)) {
                    f.set(obj, Boolean.valueOf(args[index]));
                } else if (type.equals(int.class)) {
                    f.set(obj, Integer.valueOf(args[index]));
                } else {
                    f.set(obj, args[index]);
                }
                index += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    /**
     * Get all fields on a class
     * @param type Class
     * @return List of fields
     */
    private static ArrayList<Field> getAllFields(Class<?> type) {
        ArrayList<Field> result = new ArrayList<Field>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            result.addAll(Arrays.asList(i.getDeclaredFields()));
            i = i.getSuperclass();
        }

        return result;
    }

    /**
     * Get all data stored in a text file
     * @param filePath file of text
     * @return List of lines
     */
    private ArrayList<String> getData(String filePath) {
        
        //cache only single type of data at a time
        if (cache.containsKey(filePath)) {
            return cache.get(filePath);
        } else {
            cache.clear();
        }
        
        ArrayList<String> items = new ArrayList<String>();
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
                items.add(scanner.nextLine());
            }
            
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
    
    /**
     * Splits a string into a string array based on delimiter.
     * @param data String to split
     * @return string array
     */
    public static String[] splitString(String data) {
        return data.split("\\" + DELIMITER, -1);
    }

}
