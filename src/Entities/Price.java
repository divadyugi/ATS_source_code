package Entities;

import java.util.ArrayList;

public class Price {

    private float price;
    private float taxes;
    private ExchangeRate exchangeRate;

    public Price(float price, float taxes){
        this.price =price;
        this.taxes = taxes;
    }

    public Price(float price, float taxes, ExchangeRate exchangeRate){
        this.price = price;
        this.taxes = taxes;
        this.exchangeRate = exchangeRate;
    }

    //calculates the USD price
    public float calculateExchangedPrice(){
        return price*exchangeRate.getExchangeAmount();
    }

    //calculates the USD tax price
    public float calculateExchangedTax(){
        return taxes*exchangeRate.getExchangeAmount();
    }

    //calculates the total USD price of tax+price
    public float calculateTotalExchangedPrice(){
        return calculateExchangedPrice()+calculateExchangedTax();
    }

    //the total price of tax+price
    public float calculateTotalPrice(){
        return price+taxes;
    }

    //calculates the fixed discount amount given the discount used
    public float calculateFixedDiscountPrice(FixedDiscount discount){
        return price*(1-discount.getAmount()/100);
    }

    //given the flexible discounts used, it calculates the flexible discount price of the sale
    public float calculateFlexibleDiscountPrice(ArrayList<FlexibleDiscount> flexibleDiscounts){
        for(int i =0; i<flexibleDiscounts.size(); i++){
            if(flexibleDiscounts.get(i).getAmountTo()!=0){
                if (price > flexibleDiscounts.get(i).getAmountFrom() && price <= flexibleDiscounts.get(i).getAmountTo()) {
                    return price*(1-flexibleDiscounts.get(i).getAmount()/100);
                }

            }else if(price>flexibleDiscounts.get(i).getAmountFrom()){
                return price*(1-flexibleDiscounts.get(i).getAmount()/100);
            }
        }
        return price;
    }

    public float calculateCommission(Commission commission){
        return price*(commission.getAmount()/100);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTaxes() {
        return taxes;
    }

    public void setTaxes(float taxes) {
        this.taxes = taxes;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getExchangeRateID(){
        return exchangeRate.getID();
    }
}
