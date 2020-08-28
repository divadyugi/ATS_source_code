package Database;

import Entities.Sale;
import Entities.SaleReport;
import Entities.TicketReportBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class IndividualInterlineReportDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static void setEmployeeComboBox(ComboBox employees){
        try{
            String select = "SELECT * FROM employees WHERE role = 'Travel Advisor'";

            PreparedStatement stm = con.prepareStatement(select);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                employees.getItems().add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static ObservableList<SaleReport> getDocumentInformation(int employeeID, Date startDate, Date endDate){
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try{
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.Type, b.blankID, (s.price*e.exchangeAmount), e.exchangeAmount, s.price, s.taxes, (s.price+s.taxes)\n" +
                    "FROM sales s, blanks b, exchangerate e\n" +
                    "WHERE e.exchangeID = s.exchangeRateID \n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.BlankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = "+employeeID +"\n"+
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND salesDate>= '"+sqlStartDate+"' \n" +
                    "AND salesDate <= '"+sqlEndDate+"' " +
                    "AND b.Type = s.blankType\n" +
                    "AND latePaymentStatus != 'Failed To Pay'";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                SaleReport saleReport = new SaleReport(String.valueOf(rs.getInt(1)),
                        String.valueOf(rs.getInt(2)),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6),
                        rs.getFloat(7));
                salesData.add(saleReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static ObservableList<SaleReport> getCashInformation(int employeeID, Date startDate, Date endDate){
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT (s.price+s.taxes) FROM sales s, blanks b WHERE s.type = 'Cash' AND !(exchangeRateID IS NULL)\n" +
                    "AND salesDate >= '"+sqlStartDate+"'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND salesDate <= '"+sqlEndDate+ "'" +
                    "AND s.employeeIDSale = "+employeeID+" " +
                    " AND b.Type = s.blankType " +
                    "AND latePaymentStatus != 'Failed To Pay';";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                SaleReport saleReport = new SaleReport();
                saleReport.setTotalPrice(rs.getFloat(1));
                salesData.add(saleReport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesData;
    }

    public static ObservableList<SaleReport> getCardInformation(int employeeID, Date startDate, Date endDate){
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try{
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        String query = "SELECT s.cardDetailsID, (s.price*e.exchangeAmount), (s.price+s.taxes) from sales s, exchangerate e, blanks b\n" +
                "WHERE e.exchangeID = s.exchangeRateID\n" +
                "AND s.type = 'Card'\n" +
                "AND salesDate>= '"+sqlStartDate+"' \n" +
                "AND salesDate <= '"+sqlEndDate+"' \n" +
                "AND b.blankID = s.blankIDSale\n" +
                "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                "AND s.employeeIDSale = "+employeeID+"" +
                " AND b.Type = s.blankType " +
                "AND latePaymentStatus != 'Failed To Pay';\n";

        PreparedStatement stm = con.prepareStatement(query);

        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            SaleReport saleReport = new SaleReport();
            saleReport.setCardNumber(rs.getString(1));
            saleReport.setUSDprice(rs.getFloat(2));
            saleReport.setTotalPrice(rs.getFloat(3));
            salesData.add(saleReport);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }
    public static ObservableList<SaleReport> getTotalPaid(int employeeID, Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();
        try{
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT (s.price+s.taxes) FROM sales s, blanks b\n" +
                    "WHERE !(s.exchangeRateID IS NULL)\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND salesDate>= '"+sqlStartDate+"'\n" +
                    "AND salesDate <= '"+sqlEndDate+"'\n" +
                    "AND s.employeeIDSale = "+employeeID+" " +
                    " AND b.Type = s.blankType\n " +
                    "AND latePaymentStatus != 'Failed To Pay'";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                SaleReport saleReport = new SaleReport();
                saleReport.setTotalPrice(rs.getFloat(1));
                salesData.add(saleReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }
    public static ObservableList<SaleReport> getCommissionableInformation(int employeeID, Date startDate, Date endDate){
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query ="SELECT s.price, c.amount, s.taxes FROM sales s, commission c, blanks b\n" +
                    "WHERE !(s.exchangeRateID IS NULL)\n" +
                    "AND s.commissionIDSale = c.commissionID\n" +
                    "AND salesDate>= '"+sqlStartDate+"'\n" +
                    "AND salesDate <= '"+sqlEndDate+"'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = "+employeeID+" " +
                    " AND b.Type = s.blankType" +
                    " AND latePaymentStatus != 'Failed To Pay'";

            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                SaleReport saleReport = new SaleReport();
                saleReport.setPrice(rs.getFloat(1));
                saleReport.setCommissionAmount(rs.getFloat(2));
                saleReport.setTaxes(rs.getFloat(3));
                salesData.add(saleReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static String totalCommissionable(int employeeID, Date startDate, Date endDate){
        String commissionAmount = null;
        float commissionTotal = 0f;
        try{
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.price*(c.amount/100)) FROM sales s, commission c, blanks b\n" +
                    "WHERE !(s.exchangeRateID IS NULL)\n" +
                    "AND s.commissionIDSale = c.commissionID\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND salesDate>= '"+sqlStartDate+" '\n" +
                    "AND salesDate <= '"+sqlEndDate+" '\n" +
                    "AND s.employeeIDSale = "+employeeID+"" +
                    " AND b.Type = s.blankType " +
                    "AND latePaymentStatus != 'Failed To Pay';\n";

            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                commissionTotal= rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        commissionAmount = String.format("%.2f",commissionTotal);
        return commissionAmount;
    }

}
