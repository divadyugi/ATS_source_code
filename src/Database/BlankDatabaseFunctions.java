package Database;

import Entities.Blank;
import Entities.Employee;
import Entities.Price;
import Entities.Sale;
import UI.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BlankDatabaseFunctions {

    private static Connection con = DatabaseConnection.getConnection();

    public static String checkBlankStatus(int blankID, int blankType) {
        try {

            Statement stm = con.createStatement();

            String searchStatement = "SELECT BlankStatus FROM blanks WHERE blankID = " + blankID+" AND Type = "+ blankType;

            ResultSet rs = stm.executeQuery(searchStatement);
            while (rs.next()) {
                return rs.getString(1);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ObservableList<Blank> getBlankInformation(String searchQuery){
        ObservableList<Blank> blankData = FXCollections.observableArrayList();

        try{


            PreparedStatement stm = con.prepareStatement(searchQuery);

            ResultSet blankDataSet = stm.executeQuery();
            while(blankDataSet.next()) {
                if (checkBlankStatus(blankDataSet.getInt(1),blankDataSet.getInt(2)).equals("Assigned") || checkBlankStatus(blankDataSet.getInt(1),blankDataSet.getInt(2)).equals("Sold")) {
                    Blank blank = new Blank(blankDataSet.getInt(2),
                            blankDataSet.getString(3),
                            blankDataSet.getDate(4),
                            blankDataSet.getDate(5),
                            blankDataSet.getInt(6)
                    );
                    blank.setBlankID(blankDataSet.getInt(1));
                    blankData.add(blank);
                }else{
                    Blank blank = new Blank(
                            blankDataSet.getInt(2),
                            blankDataSet.getString(3),
                            blankDataSet.getDate(4)
                    );
                    blank.setBlankID(blankDataSet.getInt(1));
                    blankData.add(blank);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return blankData;
    }

    public static void changeStatus(int blankID, int blankType, String status){
        try {

            String searchBlank = "UPDATE `ats`.`blanks` SET `BlankStatus` = '"+status+"' WHERE `blankID` = "+ blankID+" AND Type = "+ blankType;

            PreparedStatement stm = con.prepareStatement(searchBlank);



            stm.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void auditorBlank(Blank blank){

        try {
            boolean hasAuditor = false;
            String checkAuditor = "SELECT couponID FROM coupons c, blanks b WHERE c.blankIDCoupons = "+blank.getBlankID()+" AND c.blankType = "+blank.getBlankType()+" AND c.blankIDCoupons = b.blankID AND c.couponType = 'Auditor'";

            PreparedStatement stm = con.prepareStatement(checkAuditor);


            ResultSet rs = stm.executeQuery();
            //do autoincrement here?
            while(rs.next()){
                if(rs.getInt("couponID")>0){
                    hasAuditor = true;
                    break;
                }
            }

            String createStatement ="INSERT INTO `ats`.`coupons` (`couponType`, `blankIDCoupons`, `blankType`) VALUES ('Auditor', '"+blank.getBlankID()+ "', '"+blank.getBlankType()+"');";

            PreparedStatement stm2 = con.prepareStatement(createStatement);
            if(!hasAuditor){
                stm.executeUpdate(createStatement);
                AlertWindow.showInformationAlert("Success","An auditor coupon has been added to the blank");
            }else{
                AlertWindow.showInformationAlert("Unsuccessful","The blank already has an auditor blank");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void validateBlank(Blank blank, String destTo, String destFrom){
        try{

        int maxCoupons = 0;
        int actualCoupons = 0;

            switch(blank.getBlankType()){
                case 444:
                    maxCoupons = 4;
                    break;
                case 440:
                    maxCoupons = 4;
                    break;
                case 420:
                    maxCoupons = 2;
                    break;
                case 201:
                    maxCoupons = 2;
                    break;
                case 101:
                    maxCoupons = 1;
                    break;
            }
        //check the blank's type, set maxCoupons according to that
            /*
            max Coupons for each type:
                444,440 = 4
                420 = 2
                201 = 2
                101 = 1;
            */

        //check how many coupons blankNumber has, than assign actualCoupons according to that
        String actualCouponSearch = "SELECT COUNT(couponID) FROM coupons c\n" +
                "WHERE c.blankIDCoupons = "+blank.getBlankID()+"\n" +
                "AND couponType = \"Flight\"\n" +
                "AND c.blankType = "+blank.getBlankType();

        PreparedStatement stm = con.prepareStatement(actualCouponSearch);

        ResultSet couponNumbers = stm.executeQuery(actualCouponSearch);
        while(couponNumbers.next()){
            actualCoupons =couponNumbers.getInt(1);
        }

        //create new coupon, with type flight, destTo and destFrom...

        if(actualCoupons<maxCoupons) {
            String addStatement = "INSERT INTO `ats`.`coupons` (`couponType`, `blankIDCoupons`, `blankType`) VALUES ('Flight', '" + blank.getBlankID() + "','"+blank.getBlankType()+"');";
            stm.executeUpdate(addStatement);

            String highestCoupon = "SELECT MAX(couponID) FROM coupons ";
            ResultSet couponIDs = stm.executeQuery(highestCoupon);
            int couponID = 0;
            while (couponIDs.next()) {
                couponID = couponIDs.getInt(1);
            }

            String addDestination = "INSERT INTO `ats`.`coupondestination` (`destinationTo`, `destinationFrom`, `couponIDDestination`) VALUES ('" + destTo + "', '" + destFrom + "', '" + couponID + "')";

            stm.executeUpdate(addDestination);
            changeStatus(blank.getBlankID(),blank.getBlankType(),"Valid");
        }else{
            AlertWindow.showInformationAlert("Max Coupons", "The blank you are trying to validate has already reached it's maximum coupons");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }}

    public static void orderBlanks(int amount, int type){

        try {
            Connection con = DatabaseConnection.getConnection();

            String searchQuery = "SELECT MAX(blankID) FROM blanks WHERE Type = "+type;
            PreparedStatement highestblankID = con.prepareStatement(searchQuery);


            ResultSet rs = highestblankID.executeQuery(searchQuery);
            int highestBlank = 1;
            while (rs.next()) {
                highestBlank = rs.getInt(1) + 1;
            }

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

            for (int i = 0; i < amount; i++) {
                String addQuery = "INSERT INTO `ats`.`blanks` (`blankID`, `Type`, `BlankStatus`, `receiveDate`) VALUES ('" + (highestBlank + i) + "' , '" + type + "', 'Received', '" + sqlDate + "');";


                highestblankID.executeUpdate(addQuery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void orderBlanks(int amount, int type, java.util.Date receiveDate){

        try {
            Connection con = DatabaseConnection.getConnection();

            String searchQuery = "SELECT MAX(blankID) FROM blanks WHERE Type = "+type;
            PreparedStatement highestblankID = con.prepareStatement(searchQuery);


            ResultSet rs = highestblankID.executeQuery(searchQuery);
            int highestBlank = 1;
            while (rs.next()) {
                highestBlank = rs.getInt(1) + 1;
            }

            java.sql.Date sqlDate = new java.sql.Date(receiveDate.getTime());

            for (int i = 0; i < amount; i++) {
                String addQuery = "INSERT INTO `ats`.`blanks` (`blankID`, `Type`, `BlankStatus`, `receiveDate`) VALUES ('" + (highestBlank + i) + "' , '" + type + "', 'Received', '" + sqlDate + "');";


                highestblankID.executeUpdate(addQuery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void orderBlanks(int amount, int type, java.util.Date receiveDate, int blankID){

        try{

            if(!(checkIfExists(blankID,type)==null)){
                return;
            }else{
                java.sql.Date sqlDate = new java.sql.Date(receiveDate.getTime());

                int highestBlank = blankID;

                String query = "INSERT INTO `ats`.`blanks` (`blankID`, `Type`, `BlankStatus`, `receiveDate`) VALUES ('" + (highestBlank) + "' , '" + type + "', 'Received', '" + sqlDate + "');";
                PreparedStatement stm = con.prepareStatement(query);
                for(int i =0; i<amount; i++){
                    String addQuery = "INSERT INTO `ats`.`blanks` (`blankID`, `Type`, `BlankStatus`, `receiveDate`) VALUES ('" + (highestBlank + i) + "' , '" + type + "', 'Received', '" + sqlDate + "');";

                    stm.executeUpdate(addQuery);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Blank checkIfExists(int blankID, int type){
        try{

            String query = "SELECT * FROM blanks WHERE blankID = "+blankID+" AND Type = "+type;

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Blank blank = new Blank(rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getInt(6));
                blank.setBlankID(rs.getInt(1));
                return blank;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void allocateBlank(int type, int amount, int employeeID,java.util.Date date,int blankID){
        try{

            boolean enoughBlanks = false;

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String amountOfBlanks = "SELECT COUNT(blankID) FROM ats.blanks WHERE Type = "+'"'+type+'"'+" AND BlankStatus='Received';";
            PreparedStatement stm = con.prepareStatement(amountOfBlanks);

            ResultSet count = stm.executeQuery(amountOfBlanks);
            while(count.next()) {
                if (count.getInt(1) < amount) {
                    enoughBlanks = false;
                    AlertWindow.showInformationAlert("Not enough blanks", "Please enter a lower amount, there are not enough blanks of type:"+type+"In the system");
                    return;
                } else {
                    enoughBlanks = true;
                }
            }
            int highestBlank = 1;

            if(blankID == 0){
                String highestBlankID = "SELECT MIN(blankID) FROM ats.blanks WHERE Type = "+type+" AND BlankStatus = \"Received\"";
                ResultSet rs = stm.executeQuery(highestBlankID);


                while(rs.next()){
                    highestBlank = rs.getInt(1);
                    break;
                }

                if(enoughBlanks) {
                    for (int i = 0; i < amount; i++) {
                        Blank blank = checkIfExists(highestBlank+i,type);
                        if(blank.getStatus().equals("Received")) {
                            String addStatement = "UPDATE ats.blanks SET BlankStatus = 'Assigned', assignedDate = " + '"' + sqlDate + '"' + ", employeesIDBlanks = " + '"' + employeeID + '"' + " WHERE blankID = " + (highestBlank + i) + " AND Type = "+type;
                            stm.executeUpdate(addStatement);
                        }else{
                            amount++;
                        }
                    }
                    AlertWindow.showInformationAlert("Success!","The blanks have been allocated");
                }
            }else{
                Blank blank = checkIfExists(blankID,type);
                if(blank.getStatus().equals("Received")){
                    highestBlank = blankID;
                    if(enoughBlanks){
                        for(int i =0; i<amount; i++){
                            Blank blank2 = checkIfExists(highestBlank+i,type);
                            if(blank.getStatus().equals("Received")) {
                                String addStatement = "UPDATE ats.blanks SET BlankStatus = 'Assigned', assignedDate = " + '"' + sqlDate + '"' + ", employeesIDBlanks = " + '"' + employeeID + '"' + " WHERE blankID = " + (highestBlank + i)  + " AND Type = "+type;;
                                stm.executeUpdate(addStatement);
                            }else{
                                amount++;
                            }
                        }
                        AlertWindow.showInformationAlert("Success!","The blanks have been allocated");
                    }
                }else{
                    AlertWindow.showInformationAlert("Error!","These blanks have already been allocated, please select a different blank");
                }
            }






        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void reAssignBlank(Blank blank, Employee employee){

        try{


            String update = "UPDATE `blanks` SET `employeesIDBlanks` = '"+employee.getEmployeeID()+"' WHERE `blankID` = '"+blank.getBlankID()+"' AND `Type` = "+blank.getBlankType();

            PreparedStatement stm = con.prepareStatement(update);

            stm.executeUpdate(update);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteBlank(Blank blank){
        try{

            String delete = "DELETE FROM `ats`.`blanks` WHERE `blankID` = '"+blank.getBlankID()+"' AND `Type` = "+blank.getBlankType() ;

            PreparedStatement stm = con.prepareStatement(delete);

          /*  if(blank.getStatus().equals("Refunded")||blank.getStatus().equals("Void")){
                stm.executeUpdate();
            }else if(blank.getStatus().equals("Sold")){
                AlertWindow.showInformationAlert("Wrong blank status","To delete a sold blank, you must first void it or refund it.");
            }else{
                stm.executeUpdate();
            }*/
          if(blank.getStatus().equals("Received")||blank.getStatus().equals("Allocated")){
                stm.executeUpdate();
          }else{
              AlertWindow.showInformationAlert("Wrong blank status","You can only delete blanks from the system if they were not used");
              return false;
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
       // AlertWindow.showInformationAlert("Success!", "Blank successfully delete");
    }

    public static void deAllocate(Blank blank){
        try{


            String update = "UPDATE `blanks` SET `employeesIDBlanks` = NULL WHERE `blankID` = '"+blank.getBlankID()+"' AND `Type` = "+blank.getBlankType();

            PreparedStatement stm = con.prepareStatement(update);

            stm.executeUpdate(update);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
