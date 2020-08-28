package Database;


import Entities.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<Discount> getDiscountInformation(){
        ObservableList<Discount> discountData = FXCollections.observableArrayList();

        try{
            String search = "SELECT * FROM ats.discount ";

            PreparedStatement stm = con.prepareStatement(search);

           ResultSet rs = stm.executeQuery(search);

            while(rs.next()){
                Discount discount = new Discount(rs.getString(2));
                discount.setDiscountID(rs.getInt(1));
                discountData.add(discount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discountData;
    }

    public static void deleteDiscount(Discount discount){

        try {
            String deleteDiscount = "DELETE FROM `ats`.`discount` WHERE discountID = '" + discount.getDiscountID() + "'";
            PreparedStatement discountStm = con.prepareStatement(deleteDiscount);
            discountStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDiscount(){
        try{
            String insert = "INSERT INTO `ats`.`discount` (`Type`) VALUES ('null');";

            PreparedStatement stm = con.prepareStatement(insert);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateType(Discount discount, String type){
        try{
            String updateDiscount = "UPDATE `ats`.`discount` SET `Type` = '"+type+"' WHERE (`discountID` = '"+discount.getDiscountID()+"');";
            PreparedStatement updateStm = con.prepareStatement(updateDiscount);
            updateStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
