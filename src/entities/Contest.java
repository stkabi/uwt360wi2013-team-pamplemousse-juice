package entities;

import java.util.Date;

public class Contest extends BaseEntity {
    private int contestID;
    private String name;
    private Date startDate;
    private Date endDate;

    /**
     * Default Constructor.
     */
    public Contest() {
        this(0, "", new Date(), new Date());
    }

    /**
     * Parameterized constructor
     * 
     * @param contestID
     *            The contest's ID
     * @param name
     *            The name of the contest
     * @param startDate
     *            The starting date
     * @param endDate
     *            The ending date
     */
    public Contest(final int contestID, final String name, final Date startDate, final Date endDate) {
        super();
        this.contestID = contestID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getContestID() {
        return contestID;
    }

    public void setContestID(final int contestID) {
        this.contestID = contestID;
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

}
