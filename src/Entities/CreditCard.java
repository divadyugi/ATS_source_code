package Entities;

import java.util.Date;

public class CreditCard {

    private String cardNumber;
    private Date expiryDate;
    private int cvv;
    private String firstName;
    private String lastName;
    private int customerID;
    private int salesID;

    public CreditCard(String cardNumber, Date expiryDate, int cvv, String firstName, String lastName){
        this.cardNumber= cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.firstName= firstName;
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }
}
