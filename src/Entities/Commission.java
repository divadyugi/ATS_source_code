package Entities;


import java.util.Date;

public class Commission {

    private int commissionID;
    private float amount;
    private Date commissionDate;
    private Date commissionEndDate;
    private int blankType;

    public Commission(float amount, Date commissionDate, int blankType){
        this.amount = amount;
        this.commissionDate = commissionDate;
        this.blankType = blankType;
    }

    public Commission(float amount, Date commissionDate, Date commissionEndDate, int blankType){
        this.amount = amount;
        this.commissionDate = commissionDate;
        this.commissionEndDate = commissionEndDate;
        this.blankType = blankType;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    public Date getCommissionEndDate() {
        return commissionEndDate;
    }

    public void setCommissionEndDate(Date commissionEndDate) {
        this.commissionEndDate = commissionEndDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCommissionID() {
        return commissionID;
    }

    public void setCommissionID(int commissionID) {
        this.commissionID = commissionID;
    }

    public int getBlankType() {
        return blankType;
    }

    public void setBlankType(int blankType) {
        this.blankType = blankType;
    }
}
