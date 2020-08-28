package Entities;

public class FixedDiscount extends Discount {

    private int fixedID;
    private float amount;

    public FixedDiscount(int discountID, float amount){
        super("Fixed");
        super.setDiscountID(discountID);
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getFixedID() {
        return fixedID;
    }

    public void setFixedID(int fixedID) {
        this.fixedID = fixedID;
    }
}
