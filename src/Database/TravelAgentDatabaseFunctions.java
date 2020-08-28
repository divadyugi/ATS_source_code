package Database;

import Entities.Agency;
import Entities.Employee;
import UI.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelAgentDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<Agency> getAgencyInfo(){
        ObservableList<Agency> agencies = FXCollections.observableArrayList();
        try{
            String query=  "SELECT * FROM travelagent";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Agency agency = new Agency(rs.getString(2),rs.getString(3));
                agency.setID(rs.getInt(1));
                agencies.add(agency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agencies;
    }

    public static void addAgency(Agency agency){
        try{
            String query;
            if(agency.getID()==0) {
                query = "INSERT INTO `ats`.`travelagent` (`name`, `address`) VALUES ('"+agency.getName()+"', '"+agency.getAddress()+"');";
            }else{
                query = "INSERT INTO `ats`.`travelagent` (`ID`,`name`, `address`) VALUES ('"+agency.getID()+"', '"+agency.getName()+"', '"+agency.getAddress()+"');";
            }

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
        } catch (SQLException e) {
            AlertWindow.showInformationAlert("Duplicate ID","An agency with that ID already exits");
        }
    }

    public static void updateAgency(Agency oldAgency, Agency newAgency){
        try{
            String query = "UPDATE `ats`.`travelagent` SET `name` = '"+newAgency.getName()+"', `address` = '"+newAgency.getAddress()+"' WHERE (`ID` = '"+oldAgency.getID()+"');";

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
