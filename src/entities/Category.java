package entities;

import java.util.ArrayList;

public class Category extends BaseEntity {
	private static final long serialVersionUID = -5468875646426363744L;
	private String name;
	private ArrayList<String> judgeIDs = new ArrayList<String>();

	public Category(final String name, ArrayList<String> judgeIDs) {
		this.name = name;
		this.judgeIDs = judgeIDs;
	}

	public Category() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setJudgeIDs(final ArrayList<String> judgeIDs) {
		this.judgeIDs = judgeIDs;
	}

	public void addJudgeID(String id) {
		this.judgeIDs.add(id);
	}

	public void removeJudgeID(String id) {
		// TODO: Untested
		this.judgeIDs.remove(id);
	}

	public ArrayList<String> getJudgeIDs() {
		return this.judgeIDs;
	}

	/**
	 * Overridden method providing the object string representation. Used to
	 * setup the category JCombobox
	 * 
	 * @return the string representation of the object
	 */
	@Override
	public String toString() {
		return this.name;
	}
}