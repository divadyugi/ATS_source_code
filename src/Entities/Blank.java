package Entities;

import java.util.Date;

public class Blank {

    private int blankID;
    private int blankType;
    private String status;
    private Date receiveDate;
    private Date assignedDate;
    private int employeeID;

    public Blank(int blankType, String status, Date receiveDate){
        this.blankType = blankType;
        this.status = status;
        this.receiveDate = receiveDate;
    }

    public Blank(int blankType, String status, Date receiveDate, Date assignedDate, int employeeID){
        this.blankType = blankType;
        this.status = status;
        this.receiveDate = receiveDate;
        this.assignedDate = assignedDate;
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBlankType() {
        return blankType;
    }

    public void setBlankType(int blankType) {
        this.blankType = blankType;
    }

    public int getBlankID() {
        return blankID;
    }

    public void setBlankID(int blankID) {
        this.blankID = blankID;
    }
}
