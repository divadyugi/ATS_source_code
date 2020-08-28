package Database;

import Entities.Discount;
import Entities.FixedDiscount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FixedDiscountDatabaseFunction {

    private static Connection con = DatabaseConnection.getConnection();

    public static FixedDiscount getFixedDiscount(Discount discount){
        try{
            String searchQuery = "SELECT fixedID, amount, discountID, Type FROM fixeddiscount f, discount d WHERE d.discountID = f.fixedDiscountID AND d.discountID = "+discount.getDiscountID();

            PreparedStatement stm = con.prepareStatement(searchQuery);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                FixedDiscount fixedDiscount = new FixedDiscount(rs.getInt(3),rs.getFloat(2));
                fixedDiscount.setFixedID(rs.getInt(1));
                return fixedDiscount;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateDiscountPlan(FixedDiscount fixedDiscount, float amount){
        try{
            String query = "UPDATE `ats`.`fixeddiscount` SET `amount` = '"+amount+"' WHERE (`fixedID` = '"+fixedDiscount.getFixedID()+"');";

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFixedDiscountPlan(Discount discount){
        /*DELETE FROM `ats`.`fixeddiscount` WHERE (`fixedID` = '3');
        DELETE FROM `ats`.`discount` WHERE discountID = '1';*/

        try{
            String deleteFixed = "DELETE FROM `ats`.`fixeddiscount` WHERE (`fixedDiscountID` = '"+discount.getDiscountID()+"')";
            PreparedStatement fixedStm = con.prepareStatement(deleteFixed);



            fixedStm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createFixedDiscountPlan(Discount discount, float amount){
        try{
            String insert = "INSERT INTO `ats`.`fixeddiscount` (`fixedDiscountID`, `amount`) VALUES ('"+discount.getDiscountID()+"', '"+amount+"');";


            PreparedStatement stm = con.prepareStatement(insert);


            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
