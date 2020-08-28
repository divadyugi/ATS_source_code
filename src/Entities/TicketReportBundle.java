package Entities;

public class TicketReportBundle {

    //the ID of the employee who the blank is assigned to
    private int employeeID;
    //the blankID where the bundle of blanks starts from
    private int fromBlank;
    //the blankID where the bundle of blanks end
    private int toBlank;
    //the number of blanks that are in the bundle
    private int blankCount;
    //the total number of blanks in the bundle
    private int totalCount;
    //the type of blank that this bundle is for
    private int type;

    public TicketReportBundle(int fromBlank, int toBlank, int blankCount){
        this.fromBlank = fromBlank;
        this.toBlank = toBlank;
        this.blankCount = blankCount;
    }

    public TicketReportBundle(int employeeID, int fromBlank, int toBlank, int blankCount){
        this.employeeID = employeeID;
        this.fromBlank = fromBlank;
        this.toBlank = toBlank;
        this.blankCount = blankCount;
    }

    public TicketReportBundle(int totalCount){
        this.totalCount = totalCount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFromBlank() {
        return String.format("%08d",fromBlank);
    }

    public void setFromBlank(int fromBlank) {
        this.fromBlank = fromBlank;
    }

    public String getToBlank() {
        return String.format("%08d",toBlank);
    }

    public void setToBlank(int toBlank) {
        this.toBlank = toBlank;
    }

    public int getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(int blankCount) {
        this.blankCount = blankCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
