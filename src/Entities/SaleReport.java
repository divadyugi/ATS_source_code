package Entities;

public class SaleReport {
    //the type of blank
    private String blankType;
    //the blankID of the blank used for the sale
    private String blankNumber;
    //the price of the sale
    private float price;
    //the exchange rate used for the sale
    private float exchangeAmount;
    //the USD price from price*exchangeRate
    private float USDprice;
    //the taxes in the sale
    private float taxes;
    //the total price of price+taxes used for the sale
    private float totalPrice;
    //the employee who made the sale
    private int employeeID;
    //the card number used for the sale
    private String cardNumber;
    //the commission percentage used for the sale
    private float commissionAmount;
    //the number sales that were made during that time
    private int soldCount;

    public SaleReport(){}

    //Use for first part of individual interline report
    public SaleReport(String blankType, String blankNumber, float USDprice, float exchangeAmount, float price, float taxes, float totalPrice){
        this.blankType = blankType;
        this.blankNumber = String.format("%08d", Integer.parseInt(blankNumber));
        this.price = price;
        this.exchangeAmount= exchangeAmount;
        this.USDprice = USDprice;
        this.taxes= taxes;
        this.totalPrice = totalPrice;
    }

    //Use for first part of individual domestic report
    public SaleReport(String blankType, String blankNumber, float price, float taxes, float totalPrice){
        this.blankType = blankType;
        this.blankNumber = String.format("%08d", Integer.parseInt(blankNumber));
        this.price = price;
        this.taxes = taxes;
        this.totalPrice = totalPrice;
    }

    public String getTotalPrice() {
        return String.format("%.2f",totalPrice);
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTaxes() {
        return String.format("%.2f",taxes);
    }

    public void setTaxes(float taxes) {
        this.taxes = taxes;
    }

    public String getUSDprice() {
        return String.format("%.2f",USDprice);
    }

    public void setUSDprice(float USDprice) {
        this.USDprice = USDprice;
    }

    public String getExchangeAmount() {
        return String.format("%.2f",exchangeAmount);
    }

    public void setExchangeAmount(float exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBlankNumber() {
        return blankNumber;
    }

    public void setBlankNumber(String blankNumber) {
        this.blankNumber = String.format("%08d", Integer.parseInt(blankNumber));
    }

    public String getBlankType() {
        return blankType;
    }

    public void setBlankType(String blankType) {
        this.blankType = blankType;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCommissionAmount() {
        return String.format("%.2f",commissionAmount);
    }

    public void setCommissionAmount(float commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }
}
