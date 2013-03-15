package entities;
/**
 * 
 * @author Justin Prentice
 *
 */
public class Entry extends BaseEntity {
	private static final long serialVersionUID = -3191322136179845133L;
	private String userID;
	private String categoryID;
	private boolean isWinner = false;
	private String weavingPattern; // we will probably need to build our own
									// class for this eventually
	private String fibersInWeave;
	private String otherDetails;
	private String imagePath;

	/**
	 * Parameterized Constructor
	 * 
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
	public Entry(final String userID, final String categoryID,
			final boolean isWinner, final String weavingPattern,
			final String fibersInWeave, final String otherDetails,
			final String imagePath) {
		this.userID = userID;
		this.categoryID = categoryID;
		this.isWinner = isWinner;
		this.weavingPattern = weavingPattern;
		this.fibersInWeave = fibersInWeave;
		this.otherDetails = otherDetails;
		this.imagePath = imagePath;
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

	public String getFibersInWeave() {
		return fibersInWeave;
	}

	public void setFibersInWeave(final String fibersInWeave) {
		this.fibersInWeave = fibersInWeave;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(final String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}
}
