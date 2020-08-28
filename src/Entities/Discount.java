package Entities;

public class Discount {

    //change discount plan type, will bring a new stage, prompting selection of fixed or flexible.

    private int discountID;
    private String type;

    public Discount(String type){
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }
}
