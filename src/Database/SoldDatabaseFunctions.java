package Database;

import Entities.Coupon;
import Entities.Price;
import Entities.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<Sale> getSoldInformation(String searchQuery){
        ObservableList<Sale> saleData = FXCollections.observableArrayList();
        try {

            PreparedStatement stm = con.prepareStatement(searchQuery);

            ResultSet saleDataSet = stm.executeQuery();

            while(saleDataSet.next()){
                Sale sale = new Sale();
                sale.setType(saleDataSet.getString(1));
                Price price = new Price(saleDataSet.getFloat(2),saleDataSet.getFloat(3));
                sale.setPrice(price);
                sale.setBlankType(saleDataSet.getInt(4));
                sale.setBlankID(saleDataSet.getInt(5));
                sale.setCustomerID(saleDataSet.getInt(6));
                sale.setEmployeeID(saleDataSet.getInt(7));

                saleData.add(sale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleData;
    }

    public static ObservableList<Coupon> getCouponInformation(int blankID, int blankType){
        ObservableList<Coupon> couponData = FXCollections.observableArrayList();
        try{
            /*String query ="SELECT c.couponID, c.couponType, d.destinationTo, d.destinationFrom\n" +
                    "FROM coupons c, coupondestination d\n" +
                    "WHERE c.couponID = d.couponIDDestination\n" +
                    "AND c.blankIDCoupons = "+blankID+"\n" +
                    "AND c.blankType = "+blankType;*/

            String query = "SELECT c.couponID, c.couponType\n" +
                    "FROM coupons c\n" +
                    "WHERE c.blankIDCoupons = "+blankID+"\n" +
                    "AND c.blankType = "+blankType;

            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Coupon coupon = new Coupon(rs.getInt(1),rs.getString(2));
                if(rs.getString(2).equals("Flight")) {
                    setDestinations(coupon);
                }
                couponData.add(coupon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return couponData;
    }

    public static void setDestinations(Coupon coupon){
        try{
            String query = "SELECT destinationTo, destinationFrom\n" +
                    "FROM coupondestination\n" +
                    "WHERE couponIDDestination = "+coupon.getCouponID();

            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                coupon.setDestinationTo(rs.getString(1));
                coupon.setDestinationFrom(rs.getString(2));
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeRefunded(Sale sale){
        try{
            String query = "UPDATE `ats`.`blanks` SET `BlankStatus` = 'Refunded' WHERE (`blankID` = '"+sale.getBlankID()+"') and (`Type` = '"+sale.getBlankType()+"');";

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteCoupon(Coupon coupon, Sale sale){

        try{
            String query = "DELETE FROM `ats`.`coupons` WHERE (`couponID` = '"+coupon.getCouponID()+"');";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            if(noCoupons(sale)){
                updateBlank(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean noCoupons(Sale sale){
        try{
            String query = "SELECT COUNT(couponID) FROM coupons WHERE blankIDCoupons = "+sale.getBlankID()+" and blankType = "+sale.getBlankType();

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                if(rs.getInt(1)==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateBlank(Sale sale){
        try{
            String query = "UPDATE `ats`.`blanks` SET `BlankStatus` = 'Sold' WHERE (`blankID` = '"+sale.getBlankID()+"') and (`Type` = '"+sale.getBlankType()+"');\n";

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}