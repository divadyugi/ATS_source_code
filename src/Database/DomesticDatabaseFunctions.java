package Database;

import Entities.SaleReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DomesticDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static ObservableList<SaleReport> getDocumentInformation(Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();
        try {

            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.ID, COUNT(s.salesID), SUM(s.price), SUM(s.taxes), SUM(s.price)+SUM(s.taxes)\n" +
                    "FROM employees e, sales s, blanks b\n" +
                    "WHERE e.ID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND salesDate>='" + sqlStartDate + "'\n" +
                    "AND salesDate<= '" + sqlEndDate + "'\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    "GROUP BY e.ID;\n";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SaleReport saleReport = new SaleReport();
                saleReport.setEmployeeID(rs.getInt(1));
                saleReport.setSoldCount(rs.getInt(2));
                saleReport.setPrice(rs.getFloat(3));
                saleReport.setTaxes(rs.getFloat(4));
                saleReport.setTotalPrice(rs.getFloat(5));
                salesData.add(saleReport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static ObservableList<SaleReport> getCashInformation(Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.ID, SUM(s.price)+SUM(s.taxes)\n" +
                    "FROM employees e, sales s, blanks b\n" +
                    "WHERE e.ID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND salesDate>='" + sqlStartDate + "'\n" +
                    "AND salesDate<= '" + sqlEndDate + "'\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND s.type = 'Cash'\n " +
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    "GROUP BY e.ID;\n";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SaleReport saleReport = new SaleReport();
                saleReport.setTotalPrice(rs.getFloat(2));
                salesData.add(saleReport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static ObservableList<SaleReport> getCardInformation(Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT s.cardDetailsID, SUM(s.price)+SUM(s.taxes)\n" +
                    "FROM employees e, sales s, blanks b, exchangerate ex\n" +
                    "WHERE e.ID = s.employeeIDSale\n" +
                    "AND ex.exchangeID = s.exchangeRateID\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND salesDate>='" + sqlStartDate + "'\n" +
                    "AND salesDate<= '" + sqlEndDate + "'\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    "AND s.type = 'Card'\n" +
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    "GROUP BY e.ID;\n";

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

    public static ObservableList<SaleReport> getTotalPaid(Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.price)+SUM(s.taxes)\n" +
                    "FROM employees e, sales s, blanks b, exchangerate ex\n" +
                    "WHERE e.ID = s.employeeIDSale\n" +
                    "AND ex.exchangeID = s.exchangeRateID\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND salesDate>='" + sqlStartDate + "'\n" +
                    "AND salesDate<= '" + sqlEndDate + "'\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    "GROUP BY e.ID;\n";

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

    public static ObservableList<SaleReport> getCommissionInformation(Date startDate, Date endDate) {
        ObservableList<SaleReport> salesData = FXCollections.observableArrayList();
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT c.amount, SUM(s.price), SUM(s.taxes)\n" +
                    "FROM sales s, employees e, commission c, blanks b\n" +
                    "WHERE e.ID = s.employeeIDSale\n" +
                    "AND c.commissionID = s.commissionIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND salesDate>= '"+sqlStartDate+"'\n" +
                    "AND salesDate<= '"+sqlEndDate+"'\n" +
                    "AND (b.blankStatus = 'Sold' OR b.blankStatus = 'Valid')\n" +
                    "AND s.exchangeRateID IS NULL\n" +
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' " +
                    "GROUP BY e.ID, c.commissionID;";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                SaleReport saleReport = new SaleReport();
                saleReport.setCommissionAmount(rs.getFloat(1));
                saleReport.setPrice(rs.getFloat(2));
                saleReport.setTaxes(rs.getFloat(3));

                salesData.add(saleReport);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesData;
    }

    public static String totalCommissionable(Date startDate, Date endDate){
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
                    " AND b.Type = s.blankType " +
                    " AND latePaymentStatus != 'Failed To Pay' ";

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
