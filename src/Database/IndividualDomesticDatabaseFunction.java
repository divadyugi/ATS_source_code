package Database;

import Entities.SaleReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class IndividualDomesticDatabaseFunction {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<SaleReport> getDocumentInformation(int employeeID, Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.Type, b.blankID, s.price, s.taxes, (s.price+s.taxes)\n" +
                    "FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND salesDate>= '" + sqlStartDate + "'\n" +
                    "AND salesDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    " AND b.Type = s.blankType " +
                    "AND s.employeeIDSale = " + employeeID + ";\n";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SaleReport saleReport = new SaleReport(String.valueOf(rs.getInt(1)),
                        String.valueOf(rs.getInt(2)),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getFloat(5));
                salesData.add(saleReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }


    public static ObservableList<SaleReport> getCashInformation(int employeeID, Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT(s.price+s.taxes)\n" +
                    "FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND salesDate>= '" + sqlStartDate + "'\n" +
                    "AND salesDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = " + employeeID + "\n" +
                    "AND s.type = 'Cash'" +
                    " AND latePaymentStatus != 'Failed To Pay'  " +
                    " AND b.Type = s.blankType ;";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SaleReport saleReport = new SaleReport();
                saleReport.setTotalPrice(rs.getFloat(1));
                salesData.add(saleReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }


    public static ObservableList<SaleReport> getCardInformation(int employeeID, Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT s.cardDetailsID, (s.price+s.taxes)\n" +
                    "FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND salesDate>= '" + sqlStartDate + "'\n" +
                    "AND salesDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = " + employeeID + "\n" +
                    "AND s.type = 'Card'" +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    " AND b.Type = s.blankType ";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SaleReport saleReport = new SaleReport();
                saleReport.setCardNumber(rs.getString(1));
                saleReport.setTotalPrice(rs.getFloat(2));
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

            String query = "SELECT(s.price+s.taxes)\n" +
                    "FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND salesDate>= '" + sqlStartDate + "'\n" +
                    "AND salesDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = " + employeeID + "" +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    " AND b.Type = s.blankType \n";

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

    public static ObservableList<SaleReport> getCommissionableInformation(int employeeID, Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT s.price, c.amount, s.taxes FROM sales s, commission c, blanks b\n" +
                    "WHERE s.exchangeRateID IS NULL\n" +
                    "AND s.commissionIDSale = c.commissionID\n" +
                    "AND salesDate>= '"+sqlStartDate+"'\n" +
                    "AND salesDate <= '"+sqlEndDate+"'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.employeeIDSale = "+employeeID+"" +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    " AND b.Type = s.blankType ;";

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
                    "WHERE s.exchangeRateID IS NULL\n" +
                    "AND s.commissionIDSale = c.commissionID\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND (b.BlankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND salesDate>= '"+sqlStartDate+" '\n" +
                    "AND salesDate <= '"+sqlEndDate+" '\n" +
                    "AND s.employeeIDSale = "+employeeID+"" +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    " AND b.Type = s.blankType ;\n";

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
