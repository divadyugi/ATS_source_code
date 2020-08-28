package Database;

import Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.image.RescaleOp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDatabaseFunction {

    private static Connection con = DatabaseConnection.getConnection();

    public static Customer getCustomer(int customerID){
        try{
            String searchQuery = "SELECT * FROM ats.customer WHERE customerID = "+customerID;
            PreparedStatement stm = con.prepareStatement(searchQuery);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Customer customer = new Customer(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );

                customer.setID(rs.getInt(1));
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ExchangeRate> getCurrentExchangeRate(){
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        try{
            String query = "SELECT * FROM exchangerate WHERE (endDate IS NULL OR endDate >= (SELECT * FROM todaydate));";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                ExchangeRate exchangeRate = new ExchangeRate(rs.getDate(2),
                        rs.getDate(3),
                        rs.getFloat(4));
                exchangeRate.setID(rs.getInt(1));
                exchangeRates.add(exchangeRate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }
    public static ArrayList<ExchangeRate> getCurrentExchangeRate(java.util.Date startDate){
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        try{
            java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());

            String query = "SELECT * FROM exchangerate WHERE startDate <= '"+sqlDate+"' AND (endDate IS NULL OR endDate >= '"+sqlDate+"');";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();



            while(rs.next()){
                ExchangeRate exchangeRate = new ExchangeRate(rs.getDate(2),
                        rs.getDate(3),
                        rs.getFloat(4));
                exchangeRate.setID(rs.getInt(1));
                exchangeRates.add(exchangeRate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }

    public static Discount getDiscountType(Customer customer){
        try{
            String query = "SELECT d.discountID, Type  FROM discount d, customer c WHERE c.discountID = d.discountID AND c.customerID = "+customer.getID();

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Discount discount = new Discount(rs.getString(2));
                discount.setDiscountID(rs.getInt(1));
                return discount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<FlexibleDiscount> getFlexibleDiscounts(Discount discount) {
        ArrayList<FlexibleDiscount> discountData = new ArrayList<>();
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

    public static void createInterlineSale(Sale sale ){
        try{

            java.sql.Date sqlDate = new java.sql.Date(sale.getSalesDate().getTime());

            boolean cardPayment;

            String cashUpdate;
            String cardUpdate;
            if(sale.getMCOtype()==null) {
                cashUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `exchangeRateID`, `salesDate`,`latePaymentStatus`,`blankType` ) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sale.getPrice().getExchangeRateID() + "', '" + sqlDate +"','"+sale.getLatePaymentStatus()+ "', '"+sale.getBlankType()+"');";

                cardUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `exchangeRateID`, `salesDate`,`cardDetailsID`,`latePaymentStatus`,`blankType` ) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sale.getPrice().getExchangeRateID() + "', '" + sqlDate + "', '" + sale.getCardNumber() +"','"+sale.getLatePaymentStatus()+ "', '"+sale.getBlankType()+"');";
            }else{
                cashUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `exchangeRateID`, `salesDate`,`MCOstatus` ,`latePaymentStatus`,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sale.getPrice().getExchangeRateID() + "', '" + sqlDate + "', '"+sale.getMCOtype( )+"','"+sale.getLatePaymentStatus()+"', '"+sale.getBlankType()+"');";

                cardUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `exchangeRateID`, `salesDate`,`cardDetailsID`,`MCOstatus` ,`latePaymentStatus`,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sale.getPrice().getExchangeRateID() + "', '" + sqlDate + "', '" + sale.getCardNumber() + "', '"+sale.getMCOtype( )+"','"+sale.getLatePaymentStatus()+"', '"+sale.getBlankType()+"');";
            }

            if(sale.getCardNumber()==null){
                cardPayment = false;
            }else{
                cardPayment = true;
            }

            if(!cardPayment){
                PreparedStatement stm = con.prepareStatement(cashUpdate);
                stm.executeUpdate();
            }else{
                PreparedStatement stm = con.prepareStatement(cardUpdate);
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDomesticSale(Sale sale){
        try {

            java.sql.Date sqlDate = new java.sql.Date(sale.getSalesDate().getTime());

            boolean cardPayment;
            String cashUpdate;
            String cardUpdate;

            if(sale.getMCOtype()==null) {
                cashUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `salesDate`,`latePaymentStatus`,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sqlDate +"','"+sale.getLatePaymentStatus()+ "', '"+sale.getBlankType()+"');";

                cardUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `salesDate`,`cardDetailsID` ,`latePaymentStatus` ,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sqlDate + "', '" + sale.getCardNumber() +"','"+sale.getLatePaymentStatus() + "', '"+sale.getBlankType()+"');";

            }else{
                cashUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `salesDate`,`MCOstatus` ,`latePaymentStatus`,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sqlDate + "', '"+sale.getMCOtype( )+"','"+sale.getLatePaymentStatus()+ "', '"+sale.getBlankType()+"');";

                cardUpdate = "INSERT INTO `ats`.`sales` (`type`, `price`, `taxes`, `blankIDSale`, `customerIDSale`, `employeeIDSale`, `commissionIDSale`, `salesDate`,`cardDetailsID`,`MCOstatus` ,`latePaymentStatus`,`blankType`) " +
                        "VALUES " +
                        "('" + sale.getType() + "', '" + sale.getPrice().getPrice() + "', '" + sale.getPrice().getTaxes() + "', '" + sale.getBlankID() + "', '" + sale.getCustomerID() + "', '" +
                        "" + sale.getEmployeeID() + "', '" + sale.getCommissionID() + "', '" + sqlDate + "', '" + sale.getCardNumber() + "', '"+sale.getMCOtype()+"','"+sale.getLatePaymentStatus()+ "', '"+sale.getBlankType()+"');";

            }
            if(sale.getCardNumber()==null){
                cardPayment = false;
            }else{
                cardPayment = true;
            }

            if(!cardPayment){
                PreparedStatement stm = con.prepareStatement(cashUpdate);
                stm.executeUpdate();
            }else{
                PreparedStatement stm = con.prepareStatement(cardUpdate);
                stm.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getBlankID(int employeeID, String BlankType){

        try{


            String query = "SELECT MIN(blankID) FROM blanks WHERE employeesIDBlanks = "+employeeID+" AND Type = "+ BlankType+" AND BlankStatus = \"Assigned\";";

            PreparedStatement stm = con.prepareStatement(query);



            ResultSet rs = stm.executeQuery(query);

            while(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public static Commission getSalesCommission(int blankType){

        try{
            Connection con = DatabaseConnection.getConnection();

            Statement stm = con.createStatement();

            String query = "SELECT * FROM commission WHERE BlankTypeCommission = "+blankType+" AND (commissionEndDate IS NULL OR commissionEndDate > (SELECT * FROM TODAYDATE));";

            ResultSet rs = stm.executeQuery(query);

            while(rs.next()){
                Commission commission = new Commission(rs.getFloat(2),rs.getDate(3),rs.getInt(5));
                commission.setCommissionID(rs.getInt(1));
                return commission;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Commission getSalesCommission(int blankType, java.util.Date startDate){

        try{

            Statement stm = con.createStatement();

            java.sql.Date date = new java.sql.Date(startDate.getTime());

            String query = "SELECT * FROM commission WHERE BlankTypeCommission = "+blankType+" AND commissionDate <= '"+date+"'AND (commissionEndDate IS NULL OR commissionEndDate > '"+date+"');";

            ResultSet rs = stm.executeQuery(query);

            while(rs.next()){
                Commission commission = new Commission(rs.getFloat(2),rs.getDate(3),rs.getInt(5));
                commission.setCommissionID(rs.getInt(1));
                return commission;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean cardInSystem(CreditCard creditCard){
        try{
            String query = "SELECT * FROM carddetails WHERE cardNumber = "+creditCard.getCardNumber();

            PreparedStatement stm = con.prepareStatement(query);



            ResultSet rs = stm.executeQuery(query);

            while(rs.next()){
                if(rs.getString(1)==creditCard.getCardNumber()){
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void storeCreditCard(CreditCard creditCard, Sale sale){
        try{

            java.sql.Date sqlDate = new java.sql.Date(creditCard.getExpiryDate().getTime());

            String update = "INSERT INTO `ats`.`carddetails` (`cardNumber`, `expiryDate`, `cvv`, `firstName`, `lastName`, `customerIDCard`) " +
                    "VALUES ('"+creditCard.getCardNumber()+"', '"+sqlDate+"', '"+creditCard.getCvv()+"', " +
                    "'"+creditCard.getFirstName()+"', '"+creditCard.getLastName()+"', '"+sale.getCustomerID()+"');";

            PreparedStatement stm = con.prepareStatement(update);


            stm.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<CreditCard> getCreditCardInfo(Customer customer){
        ObservableList<CreditCard> creditCards = FXCollections.observableArrayList();
        try{
            String cardSearch = "SELECT * FROM carddetails WHERE customerIDCard =  "+customer.getID();

            PreparedStatement stm = con.prepareStatement(cardSearch);



            ResultSet rs = stm.executeQuery(cardSearch);

            while(rs.next()){
                CreditCard creditCard = new CreditCard(rs.getString(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
                creditCards.add(creditCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCards;
    }

    public static int getLatestCustomer(){
        try{
            String query = "SELECT MAX(customerID) from ats.customer";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs =stm.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getSoldTicketID(){
        try{

            String query = "SELECT salesID, blankIDSale from sales WHERE salesID = (SELECT MAX(salesID) FROM sales)";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                return rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<Sale> getLatePayments(){
        ArrayList<Sale> sales = new ArrayList<>();
        try{
            String query = "SELECT * FROM sales WHERE type = 'Late payment'\n" +
                    "AND (SELECT * FROM todaydate)>(DATE_ADD(salesDate, INTERVAL +30 DAY))\n" +
                    "AND latePaymentStatus = 'Not Paid';";

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Price price = new Price(rs.getFloat(3),rs.getFloat(4));
                Sale sale = new Sale();
                sale.setSalesID(rs.getInt(1));
                sale.setPrice(price);
                sale.setBlankID(rs.getInt(5));
                sale.setCustomerID(rs.getInt(6));
                sale.setEmployeeID(rs.getInt(7));
                sale.setLatePaymentStatus(rs.getString(12));
                sale.setSalesDate(rs.getDate(10));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public static void changePaymentStatus(Sale sale, String status){
        try{
            String query;
            java.sql.Date sqlDate;
            if(!(sale.getLatePaymentDate()==null)) {
                sqlDate  = new java.sql.Date(sale.getLatePaymentDate().getTime());
                query = "UPDATE `ats`.`sales` SET `latePaymentStatus` = '"+status+"', `latePaymentDate` = '"+sqlDate+"' WHERE (`salesID` = '"+sale.getSalesID()+"');\n";
            }else{
                sqlDate = null;
                query = "UPDATE `ats`.`sales` SET `latePaymentStatus` = '"+status+"' WHERE (`salesID` = '"+sale.getSalesID()+"');\n";
            }



            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addNewExchangeRate(float amount){
        try{
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            String query = "INSERT INTO `ats`.`exchangerate` (`startDate`, `exchangeAmount`) VALUES ('"+sqlDate+"', '"+amount+"');\n";

            PreparedStatement stm = con.prepareStatement(query);

            stm.executeUpdate();
            String searchQuery = "SELECT MAX(exchangeID) FROM exchangerate";

            PreparedStatement result = con.prepareStatement(searchQuery);
            ResultSet rs = result.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }




}
