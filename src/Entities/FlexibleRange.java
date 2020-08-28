package Entities;

public class FlexibleRange {

    private int ID;
    private float amountFrom;
    private float amountTo;

    public FlexibleRange(float amountFrom, float amountTo){
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(float amountFrom) {
        this.amountFrom = amountFrom;
    }

    public float getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(float amountTo) {
        this.amountTo = amountTo;
    }
}
