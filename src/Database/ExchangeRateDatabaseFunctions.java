package Database;


import Entities.ExchangeRate;
import UI.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ExchangeRateDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<ExchangeRate> getExchangeInformation(){
        ObservableList<ExchangeRate> exchangeRateData = FXCollections.observableArrayList();

        try{

            String searchQuery = "SELECT * FROM ats.exchangerate";
            PreparedStatement stm = con.prepareStatement(searchQuery);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                ExchangeRate exchangeRate = new ExchangeRate(rs.getDate(2),
                        rs.getDate(3),
                        rs.getFloat(4));

                exchangeRate.setID(rs.getInt(1));

                exchangeRateData.add(exchangeRate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeRateData;
    }

    private static boolean checkIfExpired() {
        ArrayList<Date> dates = new ArrayList<>();
        try {
            String statement = "SELECT endDate FROM ats.exchangerate";
            PreparedStatement stm = con.prepareStatement(statement);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dates.add(rs.getDate(1));
            }

            Long todayDate = new java.util.Date().getTime();

            for (int i = 0; i < dates.size(); i++) {
                if (dates.get(i) == null) {
                    return false;
                } else if (dates.get(i).getTime() >= todayDate) {
                    return false;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

        public static void addExchangeRate(ExchangeRate exchangeRate){
        try{
            PreparedStatement stm;
            java.sql.Date sqlEndDate = null;
            java.sql.Date sqlDate = new java.sql.Date(exchangeRate.getStartDate().getTime());
            if(!(exchangeRate.getEndDate()==null)) {
                sqlEndDate = new java.sql.Date(exchangeRate.getEndDate().getTime());
            }

            String addQuery = "INSERT INTO `ats`.`exchangerate` (`startDate`, `exchangeAmount`) VALUES ('"+sqlDate+"', '"+exchangeRate.getExchangeAmount()+"');";
            String addEndDateQuery = "INSERT INTO `ats`.`exchangerate` (`startDate`,`endDate`, `exchangeAmount`) VALUES ('"+sqlDate+"', '"+sqlEndDate+"', '"+exchangeRate.getExchangeAmount()+"');";

            if(!(exchangeRate.getEndDate()==null)) {
                stm = con.prepareStatement(addEndDateQuery);
            }else{
                stm = con.prepareStatement(addQuery);
            }

            if(checkIfExpired()) {
                stm.executeUpdate();
            }else{
                AlertWindow.showInformationAlert("Already exists", "There is already an exchange rate set for today, please end the current exchange rate before adding a new exchange rate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        }



    public static void removeExchangeRate(ExchangeRate exchangeRate){
        try{
            String deletion = "DELETE FROM `ats`.`exchangerate` WHERE (`exchangeID` = '"+exchangeRate.getID()+"');";

            PreparedStatement stm = con.prepareStatement(deletion);

            stm.executeUpdate();
            AlertWindow.showInformationAlert("Success!", "Exchange rate successfully delete");
        } catch (SQLException e) {
            AlertWindow.showInformationAlert("Used exchange rate", "You can't delete exchange rates that have been used in sales previously");
        }
    }


    public static void endExchangeRate(ExchangeRate exchangeRate){
        try{


            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

            String update = "UPDATE `ats`.`exchangerate` SET `endDate` = '"+sqlDate+"' WHERE (`exchangeID` = '"+exchangeRate.getID()+"');";

            PreparedStatement stm = con.prepareStatement(update);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
