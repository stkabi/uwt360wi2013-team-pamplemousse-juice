package entities;

import java.util.ArrayList;

public class Category extends BaseEntity {
    private static final long serialVersionUID = -5468875646426363744L;
    private String name;
    private ArrayList<String> judgeIDs;

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
    
    public ArrayList<String> getJudgeIDs() {
        return this.judgeIDs;
    }
}