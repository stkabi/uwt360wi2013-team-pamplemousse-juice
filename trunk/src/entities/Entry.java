package entities;

public class Entry extends BaseEntity {
    private int entryID;
    private int userID;
    private int categoryID;
    private boolean winner; // Is this how we flag winners? Perhaps it should be
                            // in our 'database' so it is searchable?
    private String weavingPattern; // we will probably need to build our own
                                   // class for this eventually
    private int fibersInWeave;
    private String otherDetails;

    /**
     * Default constructor
     */
    public Entry() {
        this(0, 0, 0, false, "", 0, "");
    }

    /**
     * Parameterized Constructor
     * 
     * @param entryID
     *            This entry's ID
     * @param userID
     *            The ID of the user who submitted this entry
     * @param categoryID
     *            The ID of the category this entry was submitted in
     * @param winner
     *            Denotes if this entry was a winner (?)
     * @param weavingPattern
     *            The pattern used to create this entry (probably to be changed
     *            later)
     * @param fibersInWeave
     *            The number of fibers in the weave
     * @param otherDetails
     *            User-provided details that don't fit elsewhere
     */
    public Entry(final int entryID, final int userID, final int categoryID, final boolean winner, final String weavingPattern, final int fibersInWeave, final String otherDetails) {
        super();
        this.entryID = entryID;
        this.userID = userID;
        this.categoryID = categoryID;
        this.winner = winner;
        this.weavingPattern = weavingPattern;
        this.fibersInWeave = fibersInWeave;
        this.otherDetails = otherDetails;
    }

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(final int entryID) {
        this.entryID = entryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(final int userID) {
        this.userID = userID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(final int categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(final boolean winner) {
        this.winner = winner;
    }

    public String getWeavingPattern() {
        return weavingPattern;
    }

    public void setWeavingPattern(final String weavingPattern) {
        this.weavingPattern = weavingPattern;
    }

    public int getFibersInWeave() {
        return fibersInWeave;
    }

    public void setFibersInWeave(final int fibersInWeave) {
        this.fibersInWeave = fibersInWeave;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(final String otherDetails) {
        this.otherDetails = otherDetails;
    }
}
