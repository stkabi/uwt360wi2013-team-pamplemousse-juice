package data;

import java.util.ArrayList;

import entities.Category;
import entities.Contest;
import entities.Entry;
import entities.User;

public class DataProvider {

    private enum DataType {
        USER, ENTRY, CATEGORY, CONTEST
    };

    private static String DATAPATH = "/Data/";
    private static String USERPATH = DATAPATH + "/Users/users.txt";
    private static String ENTRYPATH = DATAPATH + "/Entries/entrees.txt";
    private static String CATEGORYPATH = DATAPATH + "/Categories/categories.txt";
    private static String CONTESTPATH = DATAPATH + "/Contests/contests.txt";

    public DataProvider() {
        // TODO Auto-generated constructor stub
    }

    public void getData(DataType type) {
        switch (type) {
        case USER:
            break;
        case ENTRY:
            break;
        case CATEGORY:
            break;
        case CONTEST:
            break;
        }
    }
    
    public ArrayList<User> getAllUsers() {
        return null;
    }

    public User getUserById(String id) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }
    
    /**
     * Get all entries
     * @return List of all entries
     */
    public ArrayList<Entry> getAllEntries() {
        //TODO: Read file, deserialize, return all
        return null;
    }

    /**
     * Get an entry by an id
     * @param id Entry id
     * @return entry 
     */
    public Entry getEntryById(int id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        for (Entry e : allEntries) {
            if (e.getEntryID() == id) {
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
    public ArrayList<Entry> getEntriesByUserId(int id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        ArrayList<Entry> resultList = new ArrayList<Entry>();
        for (Entry e : allEntries) {
            if (e.getUserID() == id) {
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
    public ArrayList<Entry> getEntriesByCategoryId(int id) {
        ArrayList<Entry> allEntries = this.getAllEntries();
        ArrayList<Entry> resultList = new ArrayList<Entry>();
        for (Entry e : allEntries) {
            if (e.getCategoryID() == id) {
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
        return null;
    }

    public Contest getContestById(String id) {
        return null;
    }

}
