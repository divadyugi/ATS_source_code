package Database;

import Entities.Customer;
import Entities.TicketReportBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TurnoverReportDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<TicketReportBundle> getReceivedAgentStock(Date startDate, Date endDate){
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT Type, MIN(blankID), MAX(blankID), COUNT(blankID) " +
                    "FROM blanks WHERE receiveDate<= '" + sqlEndDate  +
                    "' AND receiveDate>= '" + sqlStartDate + "' GROUP BY receiveDate, Type;";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TicketReportBundle ticket = new TicketReportBundle(rs.getInt(2),rs.getInt(3),rs.getInt(4));
                ticket.setType(rs.getInt(1));
                ticketData.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketData;
    }

    public static ObservableList<TicketReportBundle> getAssignedSubAgentsStock(Date startDate, Date endDate){
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.ID, b.Type, MIN(blankID), MAX(blankID), COUNT(blankID)" +
                    "FROM employees e, blanks b " +
                    " WHERE e.ID = b.employeesIDBlanks AND assignedDate <= '"+sqlEndDate+"' AND assignedDate >= '"+sqlStartDate+"' " +
                    "AND BlankStatus != 'UnAssigned'\n" +
                    "GROUP BY assignedDate, e.ID";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TicketReportBundle ticket = new TicketReportBundle(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                ticket.setType(rs.getInt(2));
                ticketData.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketData;

    }

    public static ObservableList<TicketReportBundle> getNewlyAssignedBlanks(Date startDate){
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();
        try{
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

            String query = "SELECT e.ID, b.Type, MIN(b.blankID), MAX(b.blankID), COUNT(b.blankID) " +
                    "FROM employees e, blanks b " +
                    "WHERE e.ID = b.employeesIDBlanks " +
                    "AND b.receiveDate< '"+sqlStartDate+"' " +
                    "AND b.blankStatus !='UnAssigned' " +
                    "GROUP BY e.ID, b.Type, b.assignedDate; ";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TicketReportBundle ticket = new TicketReportBundle(
                        rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                ticket.setType(rs.getInt(2));
                System.out.println("Employee: "+rs.getInt(1));
                ticketData.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketData;
    }

    public static ObservableList<TicketReportBundle> getUsedBlanks(Date startDate, Date endDate) {
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();

        try{

            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.Type, MIN(blankID),MAX(blankID), COUNT(blankID) FROM employees e, blanks b, sales s " +
                    "WHERE e.id = b.employeesIDBlanks " +
                    "AND s.blankIDSale = b.blankID " +
                    "AND b.BlankStatus != 'Assigned' AND b.BlankStatus != 'UnAssigned' " +
                    "AND s.salesDate >= '"+sqlStartDate+"' AND s.salesDate<= '"+sqlEndDate +"' "+
                    "GROUP BY e.ID, b.Type, b.assignedDate ;";

            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                TicketReportBundle ticket = new TicketReportBundle(rs.getInt(2),rs.getInt(3),rs.getInt(4));
                ticket.setType(rs.getInt(1));
                ticketData.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketData;
    }

    public static ObservableList<TicketReportBundle> getFinalAgentAmounts( Date endDate) {
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();
    try{
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        String query = "SELECT b.Type, MIN(blankID), MAX(blankID), COUNT(blankID) FROM blanks b\n" +
                "WHERE b.receiveDate <= '"+sqlEndDate+"'\n" +
                "AND (b.BlankStatus = 'Assigned' OR b.BlankStatus = 'Received')\n" +
                "GROUP BY b.Type, b.BlankStatus, b.receiveDate; ";
        PreparedStatement stm = con.prepareStatement(query);

        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            TicketReportBundle ticket = new TicketReportBundle(rs.getInt(2),rs.getInt(3),rs.getInt(4));
            ticket.setType(rs.getInt(1));
            ticketData.add(ticket);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ticketData;
    }

    public static ObservableList<TicketReportBundle> getFinalSubAgentAmounts(Date endDate){
        ObservableList<TicketReportBundle> ticketData = FXCollections.observableArrayList();
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        try{
            String statement = "SELECT e.ID, b.Type, MIN(blankID), MAX(blankID), COUNT(blankID) FROM blanks b, employees e\n" +
                    "WHERE b.receiveDate <= '"+sqlEndDate+"'\n" +
                    "AND b.employeesIDBlanks = e.ID\n" +
                    "AND b.BlankStatus = 'Assigned'\n" +
                    "GROUP BY e.ID, b.Type, b.BlankStatus, b.receiveDate;";

            PreparedStatement stm = con.prepareStatement(statement);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TicketReportBundle ticket = new TicketReportBundle(rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                ticket.setType(rs.getInt(2));
                ticketData.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketData;
    }
}
