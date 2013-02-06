package entities;

public class Entry extends BaseEntity {
    private String userID;
    private String categoryID;
    private boolean isWinner;
    private String weavingPattern; // we will probably need to build our own
                                   // class for this eventually
    private int fibersInWeave;
    private String otherDetails;

    /**
     * Parameterized Constructor
     * 
     * @param id
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
    public Entry(final String id, final String userID, final String categoryID, final boolean isWinner, final String weavingPattern, final int fibersInWeave, final String otherDetails) {
        this.id = id;
        this.userID = userID;
        this.categoryID = categoryID;
        this.isWinner = isWinner;
        this.weavingPattern = weavingPattern;
        this.fibersInWeave = fibersInWeave;
        this.otherDetails = otherDetails;
    }

    public Entry() {
        super();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(final String userID) {
        this.userID = userID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(final String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(final boolean isWinner) {
        this.isWinner = isWinner;
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

    public Entry deserialize(String[] data) {
        return null;
    }

    public String serialize() {
        return null;
    }
}
