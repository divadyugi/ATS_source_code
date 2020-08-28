package ButtonFunctions.ReportFunctions;

import Database.TurnoverReportDatabaseFunctions;
import Entities.TicketReportBundle;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TicketReportFunctions {

    private static String locationPath = null;

    public static void createTicketReportsPage(MainPage ui){
        ui.getCenter().getChildren().clear();
        DatePicker startDate = new DatePicker();
        startDate.setPromptText("start Date");
        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End date:");
        Button generate = new Button("Generate Report");
        generate.setOnAction(e->{
            LocalDate localStartDate = startDate.getValue();
            Instant instantStartDate = Instant.from(localStartDate.atStartOfDay(ZoneId.systemDefault()));
            Date startDateTime = Date.from(instantStartDate);
            LocalDate localEndDate = endDate.getValue();
            Instant instantEndDate = Instant.from(localEndDate.atStartOfDay(ZoneId.systemDefault()));
            Date endDateTime = Date.from(instantEndDate);
            generateReport(startDateTime, endDateTime, ui);
        });
        ui.getCenter().getChildren().addAll(startDate,endDate,generate);
    }

    private static void writeNewAgent(TicketReportBundle bundle, Row row){
            Cell cell = row.createCell(2);
            cell.setCellValue(bundle.getType());

            cell = row.createCell(3);
            cell.setCellValue(bundle.getFromBlank());

            cell = row.createCell(4);
            cell.setCellValue(bundle.getToBlank());

            cell = row.createCell(5);
            cell.setCellValue(bundle.getBlankCount());
    }

    private static void writeNewSubAgent(TicketReportBundle bundle, Row row){
        Cell cell = row.createCell(6);
        cell.setCellValue(bundle.getEmployeeID());

        cell = row.createCell(7);
        cell.setCellValue(bundle.getType());

        cell = row.createCell(8);
        cell.setCellValue(bundle.getFromBlank());

        cell = row.createCell(9);
        cell.setCellValue(bundle.getToBlank());

        cell = row.createCell(10);
        cell.setCellValue(bundle.getBlankCount());

    }

    private static void writeAssignedSub(TicketReportBundle bundle, Row row){
        Cell cell = row.createCell(11);
        cell.setCellValue(bundle.getEmployeeID());

        cell = row.createCell(12);
        cell.setCellValue(bundle.getType());

        cell = row.createCell(13);
        cell.setCellValue(bundle.getFromBlank());

        cell = row.createCell(14);
        cell.setCellValue(bundle.getToBlank());

        cell = row.createCell(15);
        cell.setCellValue(bundle.getBlankCount());
    }

    private static void writeUsedSub(TicketReportBundle bundle, Row row){
        Cell cell = row.createCell(16);
        cell.setCellValue(bundle.getType());

        cell = row.createCell(17);
        cell.setCellValue(bundle.getFromBlank());

        cell = row.createCell(18);
        cell.setCellValue(bundle.getToBlank());

        cell = row.createCell(19);
        cell.setCellValue(bundle.getBlankCount());

    }

    private static void writeFinalAgents(TicketReportBundle bundle, Row row){
        Cell cell = row.createCell(20);
        cell.setCellValue(bundle.getType());

        cell = row.createCell(21);
        cell.setCellValue(bundle.getFromBlank());

        cell = row.createCell(22);
        cell.setCellValue(bundle.getToBlank());

        cell = row.createCell(23);
        cell.setCellValue(bundle.getBlankCount());
    }

    private static void writeFinalSubAgents(TicketReportBundle bundle, Row row){
        Cell cell = row.createCell(24);
        cell.setCellValue(bundle.getEmployeeID());

        cell = row.createCell(25);
        cell.setCellValue(bundle.getType());

        cell = row.createCell(26);
        cell.setCellValue(bundle.getFromBlank());

        cell = row.createCell(27);
        cell.setCellValue(bundle.getToBlank());

        cell = row.createCell(28);
        cell.setCellValue(bundle.getBlankCount());
    }

    public static void generateReport(Date startDate,Date endDate,MainPage ui){
        Stage stage = new Stage();
        stage.setTitle("Employee details");

        BorderPane layout = new BorderPane();

        //Top layer with welcome text
        VBox top = new VBox();
        top.setPadding(new Insets(10, 10, 10, 10));
        top.setSpacing(20);
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Employee functions");

        top.getChildren().addAll(title);

        layout.setTop(top);

        //Center with table

        GridPane center = CreateLayout.createGridPane();
        center.setAlignment(Pos.TOP_CENTER);


        //Export:
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Ticket Turnover Report");

        int rowCount = 1;
        String[] headers1;
        headers1 = new String[]{"Received Blanks","Final Amounts"};
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(2);
        cell.setCellValue(headers1[0]);
        //Merging cells by providing cell index
        sheet.addMergedRegion(new CellRangeAddress(1,1,2,10));

        cell = row.createCell(20);
        cell.setCellValue(headers1[1]);

        sheet.addMergedRegion(new CellRangeAddress(1,1,20,28));

        String[] headers2;
        headers2 = new String[]{"Agents' Stock","SUB AGENTS' STOCK","SUB AGENTS' USED STOCK","SUB AGENTS' ASSIGNED STOCK", "AGENTS' AMOUNT", "SUB AGENTS' AMOUNT"};
        int[] cellPositions;
        cellPositions = new int[]{2,6,16,11,20,24};

        Row headerRows2 = sheet.createRow(2);
        for(int i =0 ;i<headers2.length;i++){
            Cell cell2 = headerRows2.createCell(cellPositions[i]);
            cell2.setCellValue(headers2[i]);
            if(i%2==0){
                sheet.addMergedRegion(new CellRangeAddress(2,2,cellPositions[i],cellPositions[i]+3));
            }else{
                sheet.addMergedRegion(new CellRangeAddress(2,2,cellPositions[i],cellPositions[i]+4));
            }
        }

        String[] headers3;
        headers3 = new String[]{"Type","From","To","Amount"};

        String[] headers4;
        headers4 = new String[]{"Code:","Type:","From:","To:","Amount:"};

        Row headerRows3 = sheet.createRow(3);
        for(int i=0; i<cellPositions.length;i++){
            if(i%2==0) {
                for (int j = 0; j < headers3.length; j++) {
                    Cell cell2 = headerRows3.createCell(cellPositions[i]+j);
                    cell2.setCellValue(headers3[j]);
                }
            }else{
                for(int j = 0; j<headers4.length; j++){
                    Cell cell2 = headerRows3.createCell(cellPositions[i]+j);
                    cell2.setCellValue(headers4[j]);
                }
            }
        }



        TableView<TicketReportBundle> TicketReport;

        //change this to a label
       // TableColumn ReceivedBlanks = new TableColumn("Received blanks");
        Label ReceivedBlanks = new Label("Received Blanks");
        center.add(ReceivedBlanks,0,0,2,1);
        TableColumn newAgentsStock = new TableColumn("Agents' Stock");


        //Type:
        TableColumn<TicketReportBundle, Integer> newTicketTypeColumn = new TableColumn<>("Type:");
        newTicketTypeColumn.setMinWidth(50);
        newTicketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> newTicketFromColumn = new TableColumn<>("From");
        newTicketFromColumn.setMinWidth(50);
        newTicketFromColumn.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> newTicketToColumn = new TableColumn<>("To:");
        newTicketToColumn.setMinWidth(50);
        newTicketToColumn.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> newTicketAmount = new TableColumn<>("Amount:");
        newTicketAmount.setMinWidth(50);
        newTicketAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));

        TicketReport = new TableView<>();
        TicketReport.setMaxSize(250,300);
        TicketReport.setItems(TurnoverReportDatabaseFunctions.getReceivedAgentStock(startDate,endDate));
        newAgentsStock.getColumns().addAll(newTicketTypeColumn,newTicketFromColumn,newTicketToColumn,newTicketAmount);
        //ReceivedBlanks.getColumns().add(newAgentsStock);
        TicketReport.getColumns().addAll(newAgentsStock);

        rowCount = 3;
        for(TicketReportBundle bundle:TicketReport.getItems()){
            Row row1 = sheet.createRow(++rowCount);
            writeNewAgent(bundle,row1);
        }

        Label totalnewAgents = new Label();
        float totalnewAgentsAmounts = 0;
        for(TicketReportBundle bundle: TicketReport.getItems()){
            totalnewAgentsAmounts += bundle.getBlankCount();
        }
        totalnewAgents.setText("Total: "+totalnewAgentsAmounts);
        totalnewAgents.setPadding(new Insets(0,0,0,200));
        center.add(totalnewAgents,0,2,1,1);

        //newly assigned agents amounts
        TableView<TicketReportBundle> newAgentsTicketReport;

        TableColumn newSubAgentsStock = new TableColumn("SUB AGENTS' STOCK");

        //employee ID:
        TableColumn<TicketReportBundle, Integer> newAgentIDColumn = new TableColumn<>("Code: ");
        newAgentIDColumn.setMinWidth(50);
        newAgentIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        //Type:
        TableColumn<TicketReportBundle, Integer> newAgentTicketTypeColumn = new TableColumn<>("Type:");
        newAgentTicketTypeColumn.setMinWidth(50);
        newAgentTicketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> newAgentTicketFromColumn = new TableColumn<>("From");
        newAgentTicketFromColumn.setMinWidth(50);
        newAgentTicketFromColumn.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> newAgentTicketToColumn = new TableColumn<>("To:");
        newAgentTicketToColumn.setMinWidth(50);
        newAgentTicketToColumn.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> newAgentTicketAmount = new TableColumn<>("Amount:");
        newAgentTicketAmount.setMinWidth(50);
        newAgentTicketAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));

        newSubAgentsStock.getColumns().addAll(newAgentIDColumn,newAgentTicketTypeColumn,newAgentTicketFromColumn,newAgentTicketToColumn,newAgentTicketAmount);
        //ReceivedBlanks.getColumns().add(newSubAgentsStock);
        newAgentsTicketReport = new TableView<>();
        newAgentsTicketReport.setMaxSize(350,300);
        newAgentsTicketReport.setItems(TurnoverReportDatabaseFunctions.getAssignedSubAgentsStock(startDate,endDate));
        newAgentsTicketReport.getColumns().addAll(newSubAgentsStock);

        rowCount = 4;
        for(TicketReportBundle bundle: newAgentsTicketReport.getItems()){
            Row row1;
            if(sheet.getRow(rowCount)==null) {
                row1 = sheet.createRow(rowCount);
            }else{
                row1 = sheet.getRow(rowCount);
            }
            writeNewSubAgent(bundle,row1);
            rowCount++;
        }

        Label totalNewAgentsTickets = new Label();
        float totalNewAgentsTicketAmounts = 0;
        for(TicketReportBundle bundle: newAgentsTicketReport.getItems()){
            totalNewAgentsTicketAmounts += bundle.getBlankCount();
        }
        totalNewAgentsTickets.setText("Total: "+totalNewAgentsTicketAmounts);
        totalNewAgentsTickets.setPadding(new Insets(0,0,0,200));
        center.add(totalNewAgentsTickets,1,2,1,1);

        center.add(newAgentsTicketReport,1,1,1,1);

        //Assigned/Used Blanks

        Label assignedUsed = new Label("Assigned/used blanks");

        //TableColumn AssignedUsedBlanks = new TableColumn("Assigned/Used blanks");
        TableColumn SubAgentsAssignedStock = new TableColumn("Sub Agents' Stock");

        TableView<TicketReportBundle> newlyAssignedBlanksTable;

        //employee ID:
        TableColumn<TicketReportBundle, Integer> newlyAssignedEmployeeID = new TableColumn<>("Code: ");
        newlyAssignedEmployeeID.setMinWidth(50);
        newlyAssignedEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        //Type:
        TableColumn<TicketReportBundle, Integer> newlyAssignedType = new TableColumn<>("Type:");
        newlyAssignedType.setMinWidth(50);
        newlyAssignedType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> newlyAssignedFrom = new TableColumn<>("From");
        newlyAssignedFrom.setMinWidth(50);
        newlyAssignedFrom.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> newlyAssignedTo = new TableColumn<>("To:");
        newlyAssignedTo.setMinWidth(50);
        newlyAssignedTo.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> newlyAssignedAmount = new TableColumn<>("Amount:");
        newlyAssignedAmount.setMinWidth(50);
        newlyAssignedAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));


        SubAgentsAssignedStock.getColumns().addAll(newlyAssignedEmployeeID,newlyAssignedType,newlyAssignedFrom,newlyAssignedTo,newlyAssignedAmount);
        newlyAssignedBlanksTable = new TableView<TicketReportBundle>();
        newlyAssignedBlanksTable.setMaxSize(350,300);
        newlyAssignedBlanksTable.setItems(TurnoverReportDatabaseFunctions.getNewlyAssignedBlanks(startDate));
        newlyAssignedBlanksTable.getColumns().addAll(SubAgentsAssignedStock);

        rowCount = 4;
        for(TicketReportBundle bundle: newlyAssignedBlanksTable.getItems()){
            Row row1;
            if(sheet.getRow(rowCount)==null) {
                row1 = sheet.createRow(rowCount);
            }else{
                row1 = sheet.getRow(rowCount);
            }
            writeAssignedSub(bundle,row1);
            rowCount++;
        }

        Label totalAssignedBlanks = new Label();
        float totalAssignedBlankAmounts = 0;
        for(TicketReportBundle bundle: newlyAssignedBlanksTable.getItems()){
            totalAssignedBlankAmounts += bundle.getBlankCount();
        }
        totalAssignedBlanks.setText("Total: "+totalAssignedBlankAmounts);
        totalAssignedBlanks.setPadding(new Insets(0,0,0,300));
        center.add(totalAssignedBlanks,2,2,1,1);

        center.add(assignedUsed,2,0,1,1);
        center.add(newlyAssignedBlanksTable,2,1,1,1);


        //Sub agents' used tickets
        TableView <TicketReportBundle> usedTickets;
        TableColumn SubAgentsUsed = new TableColumn("Sub agents' used tickets");


        //Type:
        TableColumn<TicketReportBundle, Integer> usedType = new TableColumn<>("Type:");
        usedType.setMinWidth(50);
        usedType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> usedFrom = new TableColumn<>("From");
        usedFrom.setMinWidth(50);
        usedFrom.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> usedTo = new TableColumn<>("To:");
        usedTo.setMinWidth(50);
        usedTo.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> usedAmount = new TableColumn<>("Amount:");
        usedAmount.setMinWidth(50);
        usedAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));

        SubAgentsUsed.getColumns().addAll(usedType,usedFrom,usedTo,usedAmount);
        usedTickets = new TableView<>();
        usedTickets.setMaxSize(250,300);
        usedTickets.setItems(TurnoverReportDatabaseFunctions.getUsedBlanks(startDate,endDate));
        usedTickets.getColumns().add(SubAgentsUsed);

        rowCount = 4;
        for(TicketReportBundle bundle: usedTickets.getItems()){
            Row row1;
            if(sheet.getRow(rowCount)==null) {
                row1 = sheet.createRow(rowCount);
            }else{
                row1 = sheet.getRow(rowCount);
            }
            writeUsedSub(bundle,row1);
            rowCount++;
        }
        Label totalUsedTickets = new Label();
        float totalUsedTicketAmounts = 0;
        for(TicketReportBundle bundle: usedTickets.getItems()){
            totalUsedTicketAmounts += bundle.getBlankCount();
        }
        totalUsedTickets.setText("Total: "+totalUsedTicketAmounts);
        totalUsedTickets.setPadding(new Insets(0,0,0,200));
        center.add(totalUsedTickets,3,2,1,1);

        center.add(usedTickets,3,1,1,1);


        //Final amounts

        Label finalAmounts = new Label("Final amounts");


        //Agent's amounts

        TableView <TicketReportBundle>finalAgentAmounts;
        TableColumn AgentsFinalAmount = new TableColumn("AGENT'S AMOUNT");

        //Type:
        TableColumn<TicketReportBundle, Integer> finalAgentType = new TableColumn<>("Type:");
        finalAgentType.setMinWidth(50);
        finalAgentType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> finalAgentFrom = new TableColumn<>("From");
        finalAgentFrom.setMinWidth(50);
        finalAgentFrom.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> finalAgentTo = new TableColumn<>("To:");
        finalAgentTo.setMinWidth(50);
        finalAgentTo.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> finalAgentAmount = new TableColumn<>("Amount:");
        finalAgentAmount.setMinWidth(50);
        finalAgentAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));

        AgentsFinalAmount.getColumns().addAll(finalAgentType,finalAgentFrom,finalAgentTo,finalAgentAmount);
        finalAgentAmounts = new TableView<>();
        finalAgentAmounts.setMaxSize(250,300);
        finalAgentAmounts.setItems(TurnoverReportDatabaseFunctions.getFinalAgentAmounts(endDate));
        finalAgentAmounts.getColumns().add(AgentsFinalAmount);

        rowCount = 4;
        for(TicketReportBundle bundle: finalAgentAmounts.getItems()){
            Row row1;
            if(sheet.getRow(rowCount)==null) {
                row1 = sheet.createRow(rowCount);
            }else{
                row1 = sheet.getRow(rowCount);
            }
            writeFinalAgents(bundle,row1);
            rowCount++;
        }


        Label totalFinalAgents = new Label();
        float totalFinalAgentAmounts = 0;
        for(TicketReportBundle bundle: finalAgentAmounts.getItems()){
            totalFinalAgentAmounts += bundle.getBlankCount();
        }
        totalFinalAgents.setText("Total: "+totalFinalAgentAmounts);
        totalFinalAgents.setPadding(new Insets(0,0,0,200));
        center.add(totalFinalAgents,4,2,1,1);


        center.add(finalAmounts,4,0,1,1);
        center.add(finalAgentAmounts,4,1,1,1);


        //Final amounts
        //Sub agents' amounts
        TableView <TicketReportBundle>finalSubAgentsAmounts;
        TableColumn SubAgentsFinalAmounts = new TableColumn("Sub AGENTS' AMOUNTS");

        //Employee ID
        TableColumn<TicketReportFunctions, Integer> finalSubAgentID = new TableColumn<>("Employee ID:");
        finalSubAgentID.setMinWidth(50);
        finalSubAgentID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        //Type:
        TableColumn<TicketReportBundle, Integer> finalSubAgentType = new TableColumn<>("Type:");
        finalSubAgentType.setMinWidth(50);
        finalSubAgentType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //newly received from column
        TableColumn<TicketReportBundle, String> finalSubAgentFrom = new TableColumn<>("From");
        finalSubAgentFrom.setMinWidth(50);
        finalSubAgentFrom.setCellValueFactory(new PropertyValueFactory<>("fromBlank"));

        //newly received to column
        TableColumn<TicketReportBundle, String> finalSubAgentTo = new TableColumn<>("To:");
        finalSubAgentTo.setMinWidth(50);
        finalSubAgentTo.setCellValueFactory(new PropertyValueFactory<>("toBlank"));

        //ticketAmount column
        TableColumn<TicketReportBundle, Integer> finalSubAgentAmount = new TableColumn<>("Amount:");
        finalSubAgentAmount.setMinWidth(50);
        finalSubAgentAmount.setCellValueFactory(new PropertyValueFactory<>("blankCount"));

        SubAgentsFinalAmounts.getColumns().addAll(finalSubAgentID,finalSubAgentType,finalSubAgentFrom,finalSubAgentTo,finalSubAgentAmount);
        finalSubAgentsAmounts = new TableView<>();
        finalSubAgentsAmounts.setMaxSize(350,300);
        finalSubAgentsAmounts.setItems(TurnoverReportDatabaseFunctions.getFinalSubAgentAmounts(endDate));
        finalSubAgentsAmounts.getColumns().add(SubAgentsFinalAmounts);

        rowCount = 4;
        for(TicketReportBundle bundle: finalSubAgentsAmounts.getItems()){
            Row row1;
            if(sheet.getRow(rowCount)==null) {
                row1 = sheet.createRow(rowCount);
            }else{
                row1 = sheet.getRow(rowCount);
            }
            writeFinalSubAgents(bundle,row1);
            rowCount++;
        }

        center.add(finalSubAgentsAmounts,5,1,1,1);

        Label totalFinalSubAgents = new Label();
        float totalFinalSubAgentAmounts = 0;
        for(TicketReportBundle bundle: finalSubAgentsAmounts.getItems()){
            totalFinalSubAgentAmounts += bundle.getBlankCount();
        }
        totalFinalSubAgents.setText("Total: "+totalFinalSubAgentAmounts);
        totalFinalSubAgents.setPadding(new Insets(0,0,0,300));
        center.add(totalFinalSubAgents,5,2,1,1);

        center.add(TicketReport,0,1,1,1);
        layout.setCenter(center);

        //Add labels
        Row labelRow = sheet.createRow(sheet.getLastRowNum()+1);
        Label[] labels = new Label[] {totalnewAgents,totalNewAgentsTickets,totalAssignedBlanks,totalUsedTickets,totalFinalAgents,totalFinalSubAgents};

        int[] cells = new int[]{5,10,15,19,23,28};

        for(int i =0; i<cells.length; i++){
            Cell cell2 = labelRow.createCell(cells[i]);
            cell2.setCellValue(labels[i].getText());
            sheet.autoSizeColumn(cells[i]);
        }

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button location = new Button("Location");
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
        location.setOnAction(e->{
            locationPath = InterlineReportFunction.saveFile(ui,startDate,"Ticket Stock Turnover Report");
        });
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }


}
