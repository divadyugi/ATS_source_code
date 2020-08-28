package ButtonFunctions.ReportFunctions;

import Entities.SaleReport;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Objects;

public class ReportWriter {

    //implements basic functions used  by sale report generation classes
    //TODO: implement reportwriter in every sale report generation class
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private ReportGeneration reportGeneration;

    public ReportWriter(HSSFWorkbook workbook, HSSFSheet sheet, ReportGeneration reportGeneration){
        this.workbook = workbook;
        this.sheet = sheet;
        this.reportGeneration = reportGeneration;
    }

    public  void headerExport(int rowCount, String[] headers){
        Row headerRows = sheet.createRow(1);
        for(int i =0; i<headers.length; i++){
            Cell cell = headerRows.createCell(i+1);
            cell.setCellValue(headers[i]);
            sheet.autoSizeColumn(i+1);
        }
    }

    public  void exportDocuments(int rowCount, TableView<SaleReport> table){
        for(SaleReport report: table.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            reportGeneration.writeBlank(report,row);
            rowCount++;
        }
    }

    public  void exportCash(int rowCount, TableView<SaleReport> table){
        for(SaleReport report: table.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            reportGeneration.writeCash(report,row);
            rowCount++;
        }
    }

    public  void exportCard(int rowCount, TableView<SaleReport> table){
        for(SaleReport report: table.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            reportGeneration.writeCard(report,row);
            rowCount++;
        }
    }

    public  void exportTotal(int rowCount, TableView<SaleReport> table){
        for(SaleReport report: table.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            reportGeneration.writeTotal(report,row);
            rowCount++;
        }
    }

    public  void exportCommission(int rowCount, TableView<SaleReport> table){
        for(SaleReport report: table.getItems()){
            Row row;
            if(sheet.getRow(rowCount)==null) {
                row = sheet.createRow(rowCount);
            }else{
                row = sheet.getRow(rowCount);
            }
            reportGeneration.writeCommission(report,row);
            rowCount++;
        }
    }

    public void exportLabels(Label[] labels, int[] cells){
        Row labelRow = sheet.createRow(sheet.getLastRowNum()+1);
        for(int i =0; i<cells.length; i++){
            Cell cell = labelRow.createCell(cells[i]);
            cell.setCellValue(labels[i].getText());
            sheet.autoSizeColumn(cells[i]);
        }
    }
}
