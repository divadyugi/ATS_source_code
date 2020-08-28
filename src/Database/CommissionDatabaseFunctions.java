package Database;

import Entities.Commission;
import UI.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class CommissionDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<Commission> getCommissionInformation() {
        ObservableList<Commission> commissionData = FXCollections.observableArrayList();
        try {

            Statement stm = con.createStatement();

            String query = "SELECT * FROM commission";

            ResultSet commissionDataSet = stm.executeQuery(query);

            while(commissionDataSet.next()){
                Commission commission = new Commission(
                        commissionDataSet.getFloat(2),
                        commissionDataSet.getDate(3),
                        commissionDataSet.getDate(4),
                        commissionDataSet.getInt(5)
                );
                commission.setCommissionID(commissionDataSet.getInt(1));
                commissionData.add(commission);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commissionData;
    }

    public static void createCommission(Commission commission){
        try{


        java.sql.Date sqlDate = new java.sql.Date(commission.getCommissionDate().getTime());
        String addQuery = "INSERT INTO `ats`.`commission` (`amount`, `commissionDate`, `BlankTypeCommission`) VALUES ('"+commission.getAmount()+"', '"+sqlDate+"' ,'"+commission.getBlankType()+"');";


            PreparedStatement stm = con.prepareStatement(addQuery);


        //only do this if previous commission of that type has expired

            if(checkIfExpired(commission.getBlankType())) {
                stm.executeUpdate();
            }else{
                AlertWindow.showInformationAlert("Already exists", "A commission for that blank type already exists, you can only add a new commission for it once the current one expires");
            }

    } catch (SQLException e) {
        e.printStackTrace();
    }}

    public static void updateCommission(Commission commission){
        try{


            Statement stm = con.createStatement();

            java.sql.Date sqlDate = new java.sql.Date(commission.getCommissionEndDate().getTime());

            String updateQuery = "UPDATE `ats`.`commission` SET `amount` = '"+commission.getAmount()+"', `commissionEndDate` = '"+sqlDate+"' WHERE (`commissionID` = '"+commission.getCommissionID()+"');\n";

            stm.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCommission(Commission commission){
        try{

            String statement = "DELETE FROM commission WHERE commissionID = "+commission.getCommissionID();

            PreparedStatement stm = con.prepareStatement(statement);



            stm.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean checkIfExpired(int type){
        ArrayList<java.util.Date> dates = new ArrayList<>();
        try{
            //check if any of the dates are null, if yes, then return false;
            //if not, then find the one with the highest date, if the highest date, is more than today's date, then return false;
            String statement = "SELECT commissionEndDate FROM ats.commission WHERE BlankTypeCommission = "+type;
            PreparedStatement stm = con.prepareStatement(statement);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                dates.add(rs.getDate(1));
            }

            Long todayDate = new java.util.Date().getTime();

            for(int i =0; i<dates.size(); i++){
                if(dates.get(i)==null){
                    return false;
                }else if(dates.get(i).getTime()>=todayDate){
                    return false;
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
