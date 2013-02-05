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

    public User getUserById(String id) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public Entry getEntryById(String id) {
        return null;
    }

    public ArrayList<Entry> getEntriesByUserId(String id) {
        return null;
    }

    public ArrayList<Entry> getEntriesByCategoryId(String id) {
        return null;
    }

    public ArrayList<Entry> getWinningEntries() {
        return null;
    }

    public Category getCategoryById(String id) {
        return null;
    }

    public Contest getContestById(String id) {
        return null;
    }

}
