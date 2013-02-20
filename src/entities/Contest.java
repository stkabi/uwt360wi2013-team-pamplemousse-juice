package entities;

import java.util.Date;

public class Contest extends BaseEntity {
    private static final long serialVersionUID = 38532368642593698L;
    private String name;
    private Date startDate;
    private Date endDate;

    /**
     * Parameterized constructor
     * 
     * @param name
     *            The name of the contest
     * @param startDate
     *            The starting date
     * @param endDate
     *            The ending date
     */
    public Contest(final String name, final Date startDate, final Date endDate) {
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

}
