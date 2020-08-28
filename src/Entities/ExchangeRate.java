package Entities;

import java.util.Date;

public class ExchangeRate {

    private int ID;
    private Date startDate;
    private Date endDate;
    private float exchangeAmount;

    public ExchangeRate (Date startDate, float exchangeAmount){
        this.startDate = startDate;
        this.exchangeAmount = exchangeAmount;
    }

    public ExchangeRate(Date startDate, Date endDate, float exchangeAmount){
        this.startDate = startDate;
        this.endDate = endDate;
        this.exchangeAmount = exchangeAmount;
    }

    public float getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(float exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
