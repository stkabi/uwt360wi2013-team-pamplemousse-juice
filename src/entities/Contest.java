package entities;

import java.util.Date;

public class Contest extends BaseEntity {
    private String name;
    private Date startDate;
    private Date endDate;
    
    /**
     * Parameterized constructor
     * @param name
     *            The name of the contest
     * @param startDate
     *            The starting date
     * @param endDate
     *            The ending date
     */
    public Contest(final String id, final String name, final Date startDate, final Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Contest() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
        
    public Contest deserialize(String data) {
        return null;
    }

    public String serialize() {
        return null;
    }

}
