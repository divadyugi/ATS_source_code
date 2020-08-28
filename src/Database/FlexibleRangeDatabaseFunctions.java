package Database;


import Entities.FlexibleDiscount;
import Entities.FlexibleRange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlexibleRangeDatabaseFunctions {
    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<FlexibleRange> getFlexibleRangeInformation(){
        ObservableList<FlexibleRange> rangeData = FXCollections.observableArrayList();

        try{
            String search = "SELECT * FROM ats.flexiblerange";

            PreparedStatement stm = con.prepareStatement(search);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                FlexibleRange flexibleRange = new FlexibleRange(rs.getFloat(2),rs.getFloat(3));
                flexibleRange.setID(rs.getInt(1));
                rangeData.add(flexibleRange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rangeData;
    }

    public static void addRange(float from){
        try{
            String addRange = "INSERT INTO `ats`.`flexiblerange` (`amountFrom`) VALUES ('"+from+"');";

            PreparedStatement stm = con.prepareStatement(addRange);

            stm.executeUpdate(addRange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRange(float from, float to){
        try{
            String addRange = "INSERT INTO `ats`.`flexiblerange` (`amountFrom`, `amountTo`) VALUES ('"+from+"', '"+to+"');";

            PreparedStatement stm = con.prepareStatement(addRange);

            stm.executeUpdate(addRange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
