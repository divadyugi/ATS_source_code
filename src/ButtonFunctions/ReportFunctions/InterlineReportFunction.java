package ButtonFunctions.ReportFunctions;

import Database.IndividualInterlineReportDatabaseFunctions;
import Database.InterlineDatabaseFunctions;
import Entities.SaleReport;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InterlineReportFunction {

    private static String locationPath = null;

    public static void createInterlineReport(MainPage ui){
        ui.getCenter().getChildren().clear();
        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date: ");
        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date: ");
        Button generate = new Button("Generate");
        generate.setOnAction(e->{
            LocalDate localStartDate = startDate.getValue();
            Instant instantStartDate = Instant.from(localStartDate.atStartOfDay(ZoneId.systemDefault()));
            Date startDateTime = Date.from(instantStartDate);
            LocalDate localEndDate = endDate.getValue();
            Instant instantEndDate = Instant.from(localEndDate.atStartOfDay(ZoneId.systemDefault()));
            Date endDateTime = Date.from(instantEndDate);
            generateReport(ui,startDateTime,endDateTime);

        });

        ui.getCenter().getChildren().addAll(startDate,endDate,generate);


    }

    private static void writeBlank(SaleReport report, Row row){
        Cell cell = row.createCell(1);
        cell.setCellValue(report.getEmployeeID());

        cell = row.createCell(2);
        cell.setCellValue(report.getSoldCount());

        cell = row.createCell(3);
        cell.setCellValue(report.getPrice());

        cell = row.createCell(4);
        cell.setCellValue(report.getTaxes());

        cell = row.createCell(5);
        cell.setCellValue(report.getTotalPrice());

    }

    private static void writeCash(SaleReport report, Row row){
        Cell cell = row.createCell(6);
        cell.setCellValue(report.getTotalPrice());
    }

    private static void writeCard(SaleReport report, Row row){
        Cell cell = row.createCell(7);
        cell.setCellValue(report.getCardNumber());

        cell = row.createCell(8);
        cell.setCellValue(report.getUSDprice());

        cell = row.createCell(9);
        cell.setCellValue(report.getTotalPrice());
    }

    private static void writeTotal(SaleReport report, Row row){
        Cell cell = row.createCell(10);
        cell.setCellValue(report.getTotalPrice());
    }

    private static void writeCommission(SaleReport report, Row row){
        Cell cell = row.createCell(11);
        cell.setCellValue(report.getPrice());

        cell = row.createCell(12);
        cell.setCellValue(report.getCommissionAmount());

        cell = row.createCell(13);
        cell.setCellValue(report.getTaxes());
    }


    public static void generateReport(MainPage ui, Date startDate, Date endDate){
        Stage stage = new Stage();
        stage.setTitle("Individual interline report");

        BorderPane layout = new BorderPane();


        //Top
        GridPane top = new GridPane();
        top.setPadding(new Insets(10,10,10,10));
        top.setVgap(8);
        top.setHgap(10);
        top.setAlignment(Pos.TOP_CENTER);

        Label welcomeMessage = new Label("Global Interline Sales Report \n (INTERLINE - By Advisor) ");

        top.add(welcomeMessage,0,0,1,1);

        layout.setTop(top);

        //Export
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Global Interline sales report");

        int rowCount = 1;
        String [] headers;
        headers = new String[]{"Advisor Number","Blanks Sold","Fare Price: £","Tax price: £","Total price: £","Cash: £","Card Number","Price: $","Price: £", "Total Amounts Sold: £","Assessable Amounts: £","Commission %", "Non-Assesable Amounts: £"};

        Row headerRows = sheet.createRow(1);
        for(int i =0; i<headers.length; i++){
            Cell cell = headerRows.createCell(i+1);
            cell.setCellValue(headers[i]);
            sheet.autoSizeColumn(i+1);
        }

        //Center with table

        GridPane center = CreateLayout.createGridPane();
        center.setAlignment(Pos.TOP_CENTER);

        TableView<SaleReport> AirViaDocuments;

        //EmployeeID
        TableColumn<SaleReport, Integer> employeeColumn = new TableColumn<>("Advisor number");
        employeeColumn.setMinWidth(75);
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        //doc numbers
        TableColumn<SaleReport, Integer> blanksSold = new TableColumn<>("Blanks sold: ");
        blanksSold.setMinWidth(50);
        blanksSold.setCellValueFactory(new PropertyValueFactory<>("soldCount"));

        //fare price
        TableColumn<SaleReport, Float> farePrice = new TableColumn<>("Fare Price");
        farePrice.setMinWidth(50);
        farePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //tax price
        TableColumn<SaleReport, Float> taxPrice = new TableColumn<>("Tax price");
        taxPrice.setMinWidth(50);
        taxPrice.setCellValueFactory(new PropertyValueFactory<>("taxes"));

        //total price
        TableColumn<SaleReport, Float> totalPriceDocuments = new TableColumn<>("Total price");
        totalPriceDocuments.setMinWidth(50);
        totalPriceDocuments.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        AirViaDocuments = new TableView<>();
        AirViaDocuments.getColumns().addAll(employeeColumn,blanksSold,farePrice,taxPrice,totalPriceDocuments);
        AirViaDocuments.setItems(InterlineDatabaseFunctions.getDocumentInformation(startDate,endDate));
        AirViaDocuments.setMaxSize(450, 300);


        //export airvia documents
        for(SaleReport report: AirViaDocuments.getItems()){
            Row row = sheet.createRow(++rowCount);
            writeBlank(report,row);

        }

        Label totalTickets = new Label();
        float totalTicketCount = 0;
        for(SaleReport report : AirViaDocuments.getItems() ){
            totalTicketCount += report.getSoldCount();
        }

        Label totalGBPPrice = new Label("");
        float totalGBPPriceCount = 0;
        for(SaleReport report: AirViaDocuments.getItems()){
            totalGBPPriceCount += Float.parseFloat(report.getPrice());
        }

        Label totalTaxPrice = new Label("");
        float totalTaxPriceCount = 0;
        for(SaleReport report: AirViaDocuments.getItems()){
            totalTaxPriceCount += Float.parseFloat(report.getTaxes());
        }

        Label totalTicketPrice = new Label();
        float totalTicketPriceCount = 0;
        for(SaleReport report: AirViaDocuments.getItems()){
            totalTicketPriceCount += Float.parseFloat(report.getTotalPrice());
        }
        totalTickets.setText("Total blanks Used: "+totalTicketCount);
        totalGBPPrice.setText("Total price £: "+totalGBPPriceCount);
        totalTaxPrice.setText("Total Tax: "+totalTaxPriceCount);
        totalTicketPrice.setText("Total Price+Tax £: "+totalTicketPriceCount);

        center.add(AirViaDocuments,0,0,7,1);
        center.add(totalTickets,2,1,1,1);
        center.add(totalGBPPrice,4,1,1,1);
        center.add(totalTaxPrice,5,1,1,1);
        center.add(totalTicketPrice,6,1,1,1);

        //cASH TABLE
        TableView <SaleReport> cashTable;

        TableColumn<SaleReport, Float> cashPrice = new TableColumn<>("Cash: £");
        cashPrice.setMinWidth(50);
        cashPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        cashTable = new TableView<>();
        cashTable.setItems(InterlineDatabaseFunctions.getCashInformation(startDate,endDate));
        cashTable.setMaxSize(100,300);
        cashTable.getColumns().addAll(cashPrice);


        //wiret cash
        rowCount= 2;
        for(SaleReport report: cashTable.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeCash(report,row);
            rowCount++;
        }

        Label totalCashPrice = new Label();
        float totalCashPriceCount = 0f;
        for(SaleReport report: cashTable.getItems()){
            totalCashPriceCount += Float.parseFloat(report.getTotalPrice());
        }
        totalCashPrice.setText("Total cash: £"+totalCashPriceCount);


        center.add(cashTable, 8,0,1,1);
        center.add(totalCashPrice,8,1,1,1);

        //CREDIT CARD TABLE

        TableView<SaleReport> cardTable;

        //Card number
        TableColumn<SaleReport, String> cardNumber = new TableColumn<>("Card Number:");
        cardNumber.setMinWidth(125);
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));

        //USD
        TableColumn<SaleReport, Float> USDCard = new TableColumn<>("Cash: $");
        USDCard.setMinWidth(50);
        USDCard.setCellValueFactory(new PropertyValueFactory<>("USDprice"));
        //GBP
        TableColumn<SaleReport, Float> GBPCard = new TableColumn<>("Cash: £");
        GBPCard.setMinWidth(50);
        GBPCard.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        cardTable = new TableView<>();
        cardTable.setItems(InterlineDatabaseFunctions.getCardInformation(startDate,endDate));
        cardTable.setMaxSize(275,300);
        cardTable.getColumns().addAll(cardNumber,USDCard,GBPCard);


        //Could change these to take, just a table, a method, and a rowcount
        //write credit card
        rowCount= 2;
        for(SaleReport report: cardTable.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeCard(report,row);
            rowCount++;
        }

        Label totalUSDCardPrice = new Label();
        float totalUSDCardPriceCount = 0f;
        for(SaleReport report: cardTable.getItems()){
            totalUSDCardPriceCount += Float.parseFloat(report.getUSDprice());
        }

        Label totalGBPCardPrice = new Label("");
        float totalGBPCardPriceCount = 0f;
        for(SaleReport report: cardTable.getItems()){
            totalGBPCardPriceCount += Float.parseFloat(report.getTotalPrice());
        }

        totalUSDCardPrice.setText("Total card price $: "+String.format("%.2f", totalUSDCardPriceCount));
        totalGBPCardPrice.setText("Total card price £: "+String.format("%.2f",totalGBPCardPriceCount));

        center.add(cardTable,10,0,3,1);
        center.add(totalUSDCardPrice,11,1,1,1);
        center.add(totalGBPCardPrice,12,1,1,1);

        //TOTAL AMOUNTS PAID TABLE
        TableView<SaleReport> totalTable;

        TableColumn<SaleReport, Float> totalPriceColumn = new TableColumn<>("Total Amounts Paid:");
        totalPriceColumn.setMinWidth(100);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        totalTable = new TableView<>();
        totalTable.setItems(InterlineDatabaseFunctions.getTotalPaid(startDate,endDate));
        totalTable.setMaxSize(125,300);
        totalTable.getColumns().addAll(totalPriceColumn);

        Label totalPricePaid = new Label();
        float totalPricePaidCount = 0f;
        for(SaleReport report: cashTable.getItems()){
            totalPricePaidCount += Float.parseFloat(report.getTotalPrice());
        }
        totalPricePaid.setText("Total Paid: £"+totalPricePaidCount);

        center.add(totalTable, 14,0,1,1);
        center.add(totalPricePaid,14,1,1,1);

        //write total amounts paid
        rowCount= 2;
        for(SaleReport report: totalTable.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeTotal(report,row);
            rowCount++;
        }

        //COMMISSION TABLE
        TableView<SaleReport> commissionTable;

        //assessable amount
        TableColumn<SaleReport, Float> assessableAmount = new TableColumn<>("Assessable Amount: ");
        assessableAmount.setMinWidth(100);
        assessableAmount.setCellValueFactory(new PropertyValueFactory<>("price"));

        //commission amount
        TableColumn<SaleReport, Float> commissionAmount = new TableColumn<>("Commission % ");
        commissionAmount.setMinWidth(50);
        commissionAmount.setCellValueFactory(new PropertyValueFactory<>("commissionAmount"));

        //non-assessable amounts
        TableColumn<SaleReport, Float> nonAssessableAmount = new TableColumn<>("Non-Assessable Amount: ");
        nonAssessableAmount.setMinWidth(100);
        nonAssessableAmount.setCellValueFactory(new PropertyValueFactory<>("taxes"));

        commissionTable = new TableView<>();
        commissionTable.setItems(InterlineDatabaseFunctions.getCommissionInformation(startDate,endDate));
        commissionTable.setMaxSize(350,300);
        commissionTable.getColumns().addAll(assessableAmount,commissionAmount,nonAssessableAmount);


        Label totalNonAssess = new Label();
        float totalNonAssessCount = 0f;
        for(SaleReport report: commissionTable.getItems()){
            totalNonAssessCount += Float.parseFloat(report.getTaxes());
        }
        totalNonAssess.setText("Total Non Assessable: £"+totalNonAssessCount);

        Label totalAsses = new Label();
        float totalAssessCount = 0f;
        for(SaleReport report: commissionTable.getItems()){
            totalAssessCount += Float.parseFloat(report.getPrice());
        }
        totalAsses.setText("Total Assessable: £"+totalAssessCount);

        //label total commission amounts
        Label totalCommission = new Label();
        float totalCommissionCount = Float.parseFloat(InterlineDatabaseFunctions.totalCommissionable(startDate,endDate));
        totalCommission.setText("Total commission amounts: £"+totalCommissionCount);


        //net amounts for agent's debit
        Label agentNet = new Label();
        float agentNetCount = totalAssessCount-totalCommissionCount;
        agentNet.setText("Net amounts for Agent's Debit: £"+agentNetCount);

        //label total price - commission amount
        Label totalNet = new Label();
        float totalNetCount = agentNetCount+totalNonAssessCount;
        totalNet.setText("Total Net Amount for bank remittence to AIR VIA: £"+totalNetCount);

        center.add(commissionTable,15,0,4,1);
        center.add(totalAsses, 16,1,1,1);
        center.add(totalNonAssess,17,1,1,1);
        center.add(totalCommission,16,3,1,1);
        center.add(agentNet,16,4,1,1);
        center.add(totalNet,16,5,1,1);

        rowCount= 2;
        for(SaleReport report: commissionTable.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeCommission(report,row);
            rowCount++;
        }

        layout.setCenter(center);


        //Adding labels
        Row labelRow = sheet.createRow(sheet.getLastRowNum()+1);
        Label [] labels = new Label[] {totalTickets,totalGBPPrice,totalTaxPrice,totalTicketPrice,totalCashPrice,totalUSDCardPrice,totalGBPCardPrice,totalPricePaid,totalAsses,totalNonAssess};
        int [] cells = new int[] {2,3,4,5,6,8,9,10,11,13};

        for(int i =0; i<cells.length; i++){
            Cell cell = labelRow.createCell(cells[i]);
            cell.setCellValue(labels[i].getText());
            sheet.autoSizeColumn(cells[i]);
        }

        //cell 13, row = lastRowNumb+1;
        Label [] totalPayLabels = new Label[] {totalCommission,agentNet,totalNet};
        for(int i =0; i<totalPayLabels.length; i++){
            Row totalRow = sheet.createRow(sheet.getLastRowNum()+1);
            Cell cell = totalRow.createCell(12);
            cell.setCellValue(totalPayLabels[i].getText());
            sheet.autoSizeColumn(12);
        }

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button location = new Button("Location");
        location.setOnAction(e->{locationPath = saveFile(ui,startDate,"Global Individual Report");});

        Button generate = new Button("Generate");
        generate.setOnAction(e->{
            try{

                FileOutputStream outputStream = new FileOutputStream(locationPath);
                workbook.write(outputStream);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        bottom.getChildren().addAll(location,generate);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static String saveFile(MainPage ui, Date startDate, String baseName){
        java.sql.Date fileDate = new java.sql.Date(startDate.getTime());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));

        //to open up a file dialog, we need an access to the main window
        Window stage = ui.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName(baseName+"-"+fileDate);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel spreadsheet", "*.xls"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
            // fileChooser.setInitialDirectory(file.getParentFile());
            //TODO actually save file
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
