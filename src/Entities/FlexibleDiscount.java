package Entities;

public class FlexibleDiscount extends Discount {

    private int ID;
    private FlexibleRange range;
    private float amount;
    //the range's ID, and the amount used with it.

    public FlexibleDiscount(FlexibleRange range, float amount, int discountID){
        super("Flexible");
        super.setDiscountID(discountID);
        this.range =range;
        this.amount = amount;

    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public FlexibleRange getRange() {
        return range;
    }

    public void setRange(FlexibleRange range) {
        this.range = range;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getAmountFrom() {
        return range.getAmountFrom();
    }

    public float getAmountTo(){
        return range.getAmountTo();
    }
}
