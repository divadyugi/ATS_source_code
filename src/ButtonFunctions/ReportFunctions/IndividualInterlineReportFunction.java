package ButtonFunctions.ReportFunctions;

import Database.IndividualInterlineReportDatabaseFunctions;
import Entities.SaleReport;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
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
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class IndividualInterlineReportFunction {

    private static String locationPath = null;

    public static void createIndividualInterlineReportFunction(MainPage ui){
        ui.getCenter().getChildren().clear();
        DatePicker startDate = new DatePicker();
        startDate.setPromptText("start Date");
        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End date:");
        ComboBox employees = new ComboBox();
        employees.setPromptText("Employee ID:");
        employees.getItems().clear();
        IndividualInterlineReportDatabaseFunctions.setEmployeeComboBox(employees);
        Button generate = new Button("Generate Report");
        generate.setOnAction(e->{
            LocalDate localStartDate = startDate.getValue();
            Instant instantStartDate = Instant.from(localStartDate.atStartOfDay(ZoneId.systemDefault()));
            Date startDateTime = Date.from(instantStartDate);
            LocalDate localEndDate = endDate.getValue();
            Instant instantEndDate = Instant.from(localEndDate.atStartOfDay(ZoneId.systemDefault()));
            Date endDateTime = Date.from(instantEndDate);
            if(ui.getProfile().getRole().equals("Travel advisor")){
                createReport(ui,ui.getProfile().getEmployeeID(),startDateTime,endDateTime);
            }else{
                createReport(ui,Integer.parseInt(String.valueOf(employees.getValue())),startDateTime,endDateTime);
            }
        });
        ui.getCenter().getChildren().addAll(startDate,endDate);
        if(ui.getProfile().getRole().equals("Travel advisor")){
           ui.getCenter().getChildren().add(generate);
        }else{
            ui.getCenter().getChildren().addAll(employees,generate);
        }

    }

    private static void writeBlank(SaleReport report, Row row){
        Cell cell = row.createCell(1);
        cell.setCellValue(report.getBlankType());

        cell = row.createCell(2);
        cell.setCellValue(report.getBlankNumber());

        cell = row.createCell(3);
        cell.setCellValue(report.getUSDprice());

        cell = row.createCell(4);
        cell.setCellValue(report.getExchangeAmount());

        cell = row.createCell(5);
        cell.setCellValue(report.getPrice());

        cell = row.createCell(6);
        cell.setCellValue(report.getTaxes());

        cell = row.createCell(7);
        cell.setCellValue(report.getTotalPrice());

    }

    private static void writeCash(SaleReport report, Row row){
        Cell cell2 = row.createCell(8);
        cell2.setCellValue(report.getTotalPrice());
    }

    private static void writeCard(SaleReport report, Row row){
        Cell cell = row.createCell(9);
        cell.setCellValue(report.getCardNumber());

        cell = row.createCell(10);
        cell.setCellValue(report.getUSDprice());

        cell = row.createCell(11);
        cell.setCellValue(report.getTotalPrice());
    }

    private static void writeTotal(SaleReport report, Row row){
        Cell cell = row.createCell(12);
        cell.setCellValue(report.getTotalPrice());
    }

    private static void writeCommission(SaleReport report, Row row){
        Cell cell = row.createCell(13);
        cell.setCellValue(report.getPrice());

        cell = row.createCell(14);
        cell.setCellValue(report.getCommissionAmount());

        cell = row.createCell(15);
        cell.setCellValue(report.getTaxes());

    }
    public static void createReport(MainPage ui, int employeeID, Date startDate, Date endDate){
        Stage stage = new Stage();
        stage.setTitle("Individual interline report");

        BorderPane layout = new BorderPane();


        //Top
        GridPane top = new GridPane();
        top.setPadding(new Insets(10,10,10,10));
        top.setVgap(8);
        top.setHgap(10);
        top.setAlignment(Pos.TOP_CENTER);

        Label welcomeMessage = new Label("Individual Interline Sales Report \n Employee: "+employeeID);

        top.add(welcomeMessage,0,0,1,1);

        layout.setTop(top);

        //Center with table

        //Export
        //Use XSSF as that is the one for 2007 and later
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Individual interline sales report");

        int rowCount = 1;
        String [] headers;
        headers = new String[]{"Type","ID","USD","GBP/USD","GBP","Taxes","Total Price/GBP", "Cash","CC number","USD","GBP","TotalPaid","Assessable Amount","Commission %","Non-Assessable Amount"};

        Row headerRows = sheet.createRow(1);
        for(int i =0; i<headers.length; i++){
            Cell cell = headerRows.createCell(i+1);
            cell.setCellValue(headers[i]);
            sheet.autoSizeColumn(i+1);
        }

        GridPane center = CreateLayout.createGridPane();
        center.setAlignment(Pos.TOP_CENTER);

        TableView<SaleReport> AirViaDocuments;

        TableColumn AirViaDocumentsColumn = new TableColumn("AIR VIA DOCUMENTS");

        TableColumn blankNumber = new TableColumn("Blank Used for Sale");

        //Blank Type:
        TableColumn<SaleReport, String> blankType = new TableColumn<>("Type: ");
        blankType.setMinWidth(50);
        blankType.setCellValueFactory(new PropertyValueFactory<>("blankType"));

        //Blank ID
        TableColumn<SaleReport, String> blankID = new TableColumn<>("ID: ");
        blankID.setMinWidth(50);
        blankID.setCellValueFactory(new PropertyValueFactory<>("blankNumber"));

        blankNumber.getColumns().addAll(blankType,blankID);

        TableColumn fareAmounts = new TableColumn("Fare amounts");
        TableColumn<SaleReport, Float> USDDocumentColumn = new TableColumn<>("USD: ");
        USDDocumentColumn.setMinWidth(50);
        USDDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("USDprice"));

        //Exchange rate
        TableColumn<SaleReport, Float> exchangeAmount = new TableColumn<>("GBP/USD: ");
        exchangeAmount.setMinWidth(50);
        exchangeAmount.setCellValueFactory(new PropertyValueFactory<>("exchangeAmount"));


        //GBP
        TableColumn<SaleReport, Float> GBPDocumentColumn = new TableColumn<>("GBP: ");
        GBPDocumentColumn.setMinWidth(50);
        GBPDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        fareAmounts.getColumns().addAll(USDDocumentColumn,exchangeAmount,GBPDocumentColumn);

        //taxes
        TableColumn<SaleReport, Float> taxesDocumentsColumn = new TableColumn<>("Taxes: " );
        taxesDocumentsColumn.setMinWidth(50);
        taxesDocumentsColumn.setCellValueFactory(new PropertyValueFactory<>("taxes"));

        //TotalPrice
        TableColumn<SaleReport, Float> totalPriceDocumentsColumn = new TableColumn<>("Total price/GBP: ");
        totalPriceDocumentsColumn.setMinWidth(50);
        totalPriceDocumentsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        AirViaDocumentsColumn.getColumns().addAll(blankNumber,fareAmounts,taxesDocumentsColumn,totalPriceDocumentsColumn);
        AirViaDocuments = new TableView<>();
        AirViaDocuments.setMaxSize(500,300);
        AirViaDocuments.setItems(IndividualInterlineReportDatabaseFunctions.getDocumentInformation(employeeID,startDate,endDate));
        AirViaDocuments.getColumns().add(AirViaDocumentsColumn);


        //export airvia documents
        for(SaleReport report: AirViaDocuments.getItems()){
            Row row = sheet.createRow(++rowCount);
            writeBlank(report,row);

        }

        Label totalTickets = new Label();
        float totalTicketCount = 0;
        for(SaleReport report : AirViaDocuments.getItems() ){
            totalTicketCount +=1;
        }

        Label totalUSDPrice = new Label();
        float totalUSDPriceCount = 0;
        for(SaleReport report: AirViaDocuments.getItems()){
            totalUSDPriceCount += Float.parseFloat(report.getUSDprice());
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

        totalUSDPrice.setText("Total price $: "+totalUSDPriceCount);
        totalTickets.setText("Total blanks Used: "+totalTicketCount);
        totalGBPPrice.setText("Total price £: "+totalGBPPriceCount);
        totalTaxPrice.setText("Total Tax: "+totalTaxPriceCount);
        totalTicketPrice.setText("Total Price+Tax £: "+totalTicketPriceCount);
        center.add(AirViaDocuments,0,0,7,1);
        center.add(totalTickets,1,1,1,1);
        center.add(totalUSDPrice,2,1,1,1);
        center.add(totalGBPPrice,4,1,1,1);
        center.add(totalTaxPrice,5,1,1,1);
        center.add(totalTicketPrice,6,1,1,1);


        TableView<SaleReport> cashTable;

        TableColumn<SaleReport, Float> cashTotalColumn = new TableColumn<>("Cash: ");
        cashTotalColumn.setMinWidth(50);
        cashTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        cashTable = new TableView<>();
        cashTable.setItems(IndividualInterlineReportDatabaseFunctions.getCashInformation(employeeID,startDate,endDate));
        cashTable.setMaxSize(75,300);
        cashTable.getColumns().add(cashTotalColumn);

        Label totalCashPrice = new Label();
        float totalCashPriceCount = 0f;
        for(SaleReport report: cashTable.getItems()){
            totalCashPriceCount += Float.parseFloat(report.getTotalPrice());
        }
        totalCashPrice.setText("Total cash: £"+totalCashPriceCount);

        center.add(cashTable,7,0,2,1);
        center.add(totalCashPrice,8,1,1,1);
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

        //Credit card table
        TableView<SaleReport> creditCardTable;

        TableColumn<SaleReport, String> cardNumberColumn = new TableColumn<>("CC number: ");
        cardNumberColumn.setMinWidth(50);
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));

        //USD price:
        TableColumn<SaleReport, Float> USDCardPrice = new TableColumn<>("USD: ");
        USDCardPrice.setMinWidth(50);
        USDCardPrice.setCellValueFactory(new PropertyValueFactory<>("USDprice"));

        //GBP
        TableColumn<SaleReport, Float> GBPCardPrice = new TableColumn<>("GBP: ");
        GBPCardPrice.setMinWidth(50);
        GBPCardPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        creditCardTable = new TableView<>();
        creditCardTable.setMaxSize(250,300);
        creditCardTable.setItems(IndividualInterlineReportDatabaseFunctions.getCardInformation(employeeID,startDate,endDate));
        creditCardTable.getColumns().addAll(cardNumberColumn,USDCardPrice,GBPCardPrice);

        //write credit card
        rowCount= 2;
        for(SaleReport report: creditCardTable.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeCard(report,row);
            rowCount++;
        }

        //USD total label
        //GBP total label;
        Label totalUSDCardPrice = new Label();
        float totalUSDCardPriceCount = 0f;
        for(SaleReport report: creditCardTable.getItems()){
            totalUSDCardPriceCount += Float.parseFloat(report.getUSDprice());
        }

        Label totalGBPCardPrice = new Label("");
        float totalGBPCardPriceCount = 0f;
        for(SaleReport report: creditCardTable.getItems()){
            totalGBPCardPriceCount += Float.parseFloat(report.getTotalPrice());
        }

        totalUSDCardPrice.setText("Total card price $: "+String.format("%.2f", totalUSDCardPriceCount));
        totalGBPCardPrice.setText("Total card price £: "+String.format("%.2f",totalGBPCardPriceCount));
        center.add(creditCardTable,9,0,4,1);
        center.add(totalUSDCardPrice,11,1,1,1);
        center.add(totalGBPCardPrice,12,1,1,1);

        //Total amounts paid table
        TableView<SaleReport> totalAmountPaid;

        TableColumn<SaleReport, Float> totalPaidColumn = new TableColumn<>("Total Paid:");
        totalPaidColumn.setMinWidth(50);
        totalPaidColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        totalAmountPaid = new TableView<>();
        totalAmountPaid.setMaxSize(100,300);
        totalAmountPaid.setItems(IndividualInterlineReportDatabaseFunctions.getTotalPaid(employeeID,startDate,endDate));
        totalAmountPaid.getColumns().add(totalPaidColumn);

        Label totalPricePaid = new Label();
        float totalPricePaidCount = 0f;
        for(SaleReport report: cashTable.getItems()){
            totalPricePaidCount += Float.parseFloat(report.getTotalPrice());
        }
        totalPricePaid.setText("Total Paid: £"+totalPricePaidCount);

        //label
        center.add(totalAmountPaid,13,0,2,1);
        center.add(totalPricePaid,14,1,1,1);

        //write total amounts paid
        rowCount= 2;
        for(SaleReport report: totalAmountPaid.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
           writeTotal(report,row);
            rowCount++;
        }

        TableView<SaleReport> commissionPrice;

        //price column
        TableColumn<SaleReport, Float> commissionPriceColumn = new TableColumn<>("Assessable Amount:");
        commissionPriceColumn.setMinWidth(50);
        commissionPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //percent column
        TableColumn<SaleReport, Float> commissionAmountColumn = new TableColumn<>("Commission %: ");
        commissionAmountColumn.setMinWidth(50);
        commissionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("commissionAmount"));

        //non-assessable Amount
        TableColumn<SaleReport, Float> taxesCommissionColumn = new TableColumn<>("Non-Assessable Amount:");
        taxesCommissionColumn.setMinWidth(50);
        taxesCommissionColumn.setCellValueFactory(new PropertyValueFactory<>("taxes"));

        commissionPrice = new TableView<>();
        commissionPrice.setMinWidth(400);
        commissionPrice.setMaxHeight(300);
        commissionPrice.setItems(IndividualInterlineReportDatabaseFunctions.getCommissionableInformation(employeeID,startDate,endDate));
        commissionPrice.getColumns().addAll(commissionPriceColumn,commissionAmountColumn,taxesCommissionColumn);

        //write commission paid
        rowCount= 2;
        for(SaleReport report: commissionPrice.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            writeCommission(report,row);
            rowCount++;
        }

        //label total non-assessable amounts
        Label totalNonAssess = new Label();
        float totalNonAssessCount = 0f;
        for(SaleReport report: commissionPrice.getItems()){
            totalNonAssessCount += Float.parseFloat(report.getTaxes());
        }
        totalNonAssess.setText("Total Non Assessable: £"+totalNonAssessCount);

        //label total assessable amounts

        Label totalAsses = new Label();
        float totalAssessCount = 0f;
        for(SaleReport report: commissionPrice.getItems()){
            totalAssessCount += Float.parseFloat(report.getPrice());
        }
        totalAsses.setText("Total Assessable: £"+totalAssessCount);

        //label total commission amounts
        Label totalCommission = new Label();
        float totalCommissionCount = Float.parseFloat(IndividualInterlineReportDatabaseFunctions.totalCommissionable(employeeID,startDate,endDate));
        totalCommission.setText("Total commission amounts: £"+totalCommissionCount);


        //net amounts for agent's debit
        Label agentNet = new Label();
        float agentNetCount = totalAssessCount-totalCommissionCount;
        agentNet.setText("Net amounts for Agent's Debit: £"+agentNetCount);

        //label total price - commission amount
        Label totalNet = new Label();
        float totalNetCount = agentNetCount+totalNonAssessCount;
        totalNet.setText("Total Net Amount for bank remittence to AIR VIA: £"+totalNetCount);

        center.add(commissionPrice, 15,0,5,1);
        center.add(totalAsses, 18,1,1,1);
        center.add(totalNonAssess,19,1,1,1);
        center.add(totalCommission,18,2,1,1);
        center.add(agentNet,18,3,1,1);
        center.add(totalNet,18,4,1,1);
        layout.setCenter(center);

        //Adding labels
        Row labelRow = sheet.createRow(sheet.getLastRowNum()+1);
        Label [] labels = new Label[] {totalTickets,totalUSDPrice,totalGBPPrice,totalTaxPrice,totalTicketPrice,totalCashPrice,totalUSDCardPrice,totalGBPCardPrice,totalPricePaid,totalAsses,totalNonAssess};
        int [] cells = new int[] {2,3,5,6,7,8,10,11,12,13,15};

        for(int i =0; i<cells.length; i++){
            Cell cell = labelRow.createCell(cells[i]);
            cell.setCellValue(labels[i].getText());
            sheet.autoSizeColumn(cells[i]);
        }

        //cell 13, row = lastRowNumb+1;
        Label [] totalPayLabels = new Label[] {totalCommission,agentNet,totalNet};
        for(int i =0; i<totalPayLabels.length; i++){
            Row totalRow = sheet.createRow(sheet.getLastRowNum()+1);
            Cell cell = totalRow.createCell(13);
            cell.setCellValue(totalPayLabels[i].getText());
            sheet.autoSizeColumn(13);
        }


        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);


        Button location = new Button("Location");
        location.setOnAction(e->{locationPath = saveFile(ui,startDate);});
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

    public static String saveFile(MainPage ui, Date startDate){
        java.sql.Date fileDate = new java.sql.Date(startDate.getTime());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));

        //to open up a file dialog, we need an access to the main window
        Window stage = ui.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName("Individual Interline Report-"+fileDate);
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
