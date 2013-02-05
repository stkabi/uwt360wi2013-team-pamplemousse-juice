package data;

public class DataProvider {
    
    private enum DataType {
        USER,
        ENTRY,
        CATEGORY,
        CONTEST
    };
    private static String DATAPATH = "/Data/";
    private static String USERPATH = DATAPATH + "/Users/users.txt";
    private static String ENTRYPATH = DATAPATH + "/Entries/entires.txt";
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
    
    public void getUserById(String id) {
        
    }
    
    public void getUserByEmail(String email) {
        
    }
    
    public void getEntryById(String id) {
        
    }
    
    public void getEntriesByUserId(String id) {
        
    }
    
    public void getEntriesByCategoryId(String id) {
        
    }
    
    public void getWinningEntries() {
        
    }
    
    public void getCategoryById(String id) {
        
    }
    
    public void getContestById(String id) {
        
    }

}
