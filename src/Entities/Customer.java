package Entities;

public class Customer {

    private int ID;
    private String firstName;
    private String lastName;
    private String type;
    private String email;
    private int discountID;

    public Customer(String firsName, String lastName, String type, String email){
        this.firstName = firsName;
        this.lastName = lastName;
        this.type =type;
        this.email = email;
    }

    public Customer(String firsName, String lastName, String type, String email, int discountID){
        this.firstName = firsName;
        this.lastName = lastName;
        this.type =type;
        this.email = email;
        this.discountID = discountID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
