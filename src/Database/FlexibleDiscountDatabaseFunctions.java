package Database;

import Entities.Discount;
import Entities.FlexibleDiscount;
import Entities.FlexibleRange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlexibleDiscountDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<FlexibleDiscount> getFlexibleDiscountInformation(Discount discount) {
        ObservableList<FlexibleDiscount> discountData = FXCollections.observableArrayList();
        try{
            String getData = "SELECT flexibleID, amountFrom, amountTo, discountAmount, r.rangeID\n" +
                    "FROM flexiblediscount flex, flexiblerange r, flexiblediscountranges flexrange, discount d\n" +
                    "WHERE flex.flexibleDiscountID = d.discountID \n" +
                    "AND flex.flexibleID = flexrange.flexibleDiscountID\n" +
                    "AND r.rangeID = flexrange.rangeID\n" +
                    "AND d.discountID = "+discount.getDiscountID();

            PreparedStatement stm = con.prepareStatement(getData);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                FlexibleRange range = new FlexibleRange(rs.getFloat(2),rs.getFloat(3));
                FlexibleDiscount flexibleDiscount = new FlexibleDiscount(range,rs.getFloat(4),discount.getDiscountID());
                flexibleDiscount.setID(rs.getInt(1));
                discountData.add(flexibleDiscount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountData;
    }

    public static int findLatestID(){
        try{
            String maxID = "SELECT MAX(flexibleID) FROM ats.flexiblediscount;";
            PreparedStatement stm = con.prepareStatement(maxID);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addFlexibleDiscount(FlexibleDiscount flexibleDiscount){
        try{
            String addDiscount = "INSERT INTO `ats`.`flexiblediscount` (`flexibleDiscountID`, `discountAmount`) VALUES ('"+flexibleDiscount.getDiscountID()+"', '"+flexibleDiscount.getAmount()+"');\n";



            PreparedStatement discountStm = con.prepareStatement(addDiscount);


            discountStm.executeUpdate();
            flexibleDiscount.setID(findLatestID());
            createConnection(flexibleDiscount.getRange(),flexibleDiscount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createConnection(FlexibleRange range, FlexibleDiscount flexibleDiscount){
        try{
            String addConnection = "INSERT INTO `ats`.`flexiblediscountranges` (`rangeID`, `flexibleDiscountID`) VALUES ('"+range.getID()+"', '"+flexibleDiscount.getID()+"');";

            PreparedStatement connectionStm = con.prepareStatement(addConnection);

            connectionStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDiscount(FlexibleDiscount flexibleDiscount){
        try{


            String deleteDiscount = "DELETE FROM `ats`.`flexiblediscount` WHERE (`flexibleID` = '"+flexibleDiscount.getID()+"');";


            PreparedStatement discountStm = con.prepareStatement(deleteDiscount);

            deleteConnection(flexibleDiscount);
            discountStm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteConnection(FlexibleDiscount flexibleDiscount){
        try{
            String deleteConnection = "DELETE FROM `ats`.`flexiblediscountranges` WHERE (`flexibleDiscountID` = '"+flexibleDiscount.getID()+"');\n";
            PreparedStatement connectionStm = con.prepareStatement(deleteConnection);

            connectionStm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
