package Database;

import Entities.Customer;
import Entities.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();
    public static ObservableList<Customer> getCustomerInformation() {
        ObservableList<Customer> customerData = FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM  ats.customer";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Customer customer;
                if(rs.getInt(6)==0){
                    customer = new Customer(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));

                }else{
                    customer = new Customer(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6));
                }
                customer.setID(rs.getInt(1));
                customerData.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerData;
    }

    public static void addCustomer(Customer customer){
        //if discountID = -1, then use statement without creating discountID
        try{
            String query = null;
            if(customer.getID()==0) {
                query = "INSERT INTO `ats`.`customer` (`firstName`, `lastName`, `customerType`, `email`) VALUES ('" + customer.getFirstName() + "', '" + customer.getLastName()
                        + "', '" + customer.getType() + "', '" + customer.getEmail() + "')";
            }else{
                query = "INSERT INTO `ats`.`customer` (`customerID`,`firstName`, `lastName`, `customerType`, `email`) VALUES ('"+customer.getID()+"', '" + customer.getFirstName() + "', '" + customer.getLastName()
                        + "', '" + customer.getType() + "', '" + customer.getEmail() + "')";
            }

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(Customer oldCustomer, Customer newCustomer){
        try{
            String statement = "UPDATE `ats`.`customer` SET `firstName` = '"+newCustomer.getFirstName()+"', `lastName` = '"+newCustomer.getLastName()+"', `customerType` = '"+newCustomer.getType()+"', `email` = '"+newCustomer.getEmail()+"' WHERE `customerID` = '"+oldCustomer.getID()+"';";

            PreparedStatement stm = con.prepareStatement(statement);

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(Customer customer){
        try{
            String statement = "DELETE FROM customer WHERE customerID = "+customer.getID();

            PreparedStatement stm = con.prepareStatement(statement);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectDiscountPlan(Customer customer){}


    public static  Discount  showDiscountPlan(int discountID){
        Discount discount = null;
        try{
            String statement = "SELECT * FROM ats.discount WHERE discountID = "+discountID;

            PreparedStatement stm = con.prepareStatement(statement);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                discount = new Discount (rs.getString(2));
                discount.setDiscountID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    public static void changePlan(Discount discount, Customer customer){
        try{
           String statement = " UPDATE `ats`.`customer` SET `discountID` = '"+discount.getDiscountID()+"' WHERE (`customerID` = '"+customer.getID()+"')";

           PreparedStatement stm = con.prepareStatement(statement);

           stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
