package pt.ipp.isep.dei.esoft.project.domain;

public class WorkPeriod {
    private Date startDate;
    private Date endDate;

    public WorkPeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean overlapsWith(Date otherStart, Date otherEnd) {
        return !startDate.isAfter(otherEnd) && !endDate.isBefore(otherStart);
    }

    @Override
    public String toString() {
        return "WorkPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

