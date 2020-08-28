package ButtonFunctions.StockFunctions;

import Database.BlankDatabaseFunctions;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AllocateFunction {

    public static void allocateBlanks(MainPage ui){

        GridPane center = new GridPane();
        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);

        ComboBox blankType = new ComboBox();
        blankType.getItems().clear();
        blankType.setPromptText("Blank Type:");
        blankType.getItems().addAll("444","440","420","201","101","451","452");

        TextField blankID = new TextField();
        blankID.setPromptText("Start from: ");

        TextField amount = new TextField();
        amount.setPromptText("Amount: ");

        TextField employeeID = new TextField();
        employeeID.setPromptText("Employee ID: ");

        DatePicker date = new DatePicker();
        date.setPromptText("Date allocated");

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createStockPage();
        });

        Button allocate = new Button("Allocate");
        allocate.setOnAction(e->{
            if(date.getValue()==null) {
                if(blankID.getText().equals("")) {
                    BlankDatabaseFunctions.allocateBlank(Integer.parseInt(String.valueOf(blankType.getValue())),Integer.parseInt(amount.getText()), Integer.parseInt(employeeID.getText()), new Date(), 0);
                }else{
                    BlankDatabaseFunctions.allocateBlank(Integer.parseInt(String.valueOf(blankType.getValue())),Integer.parseInt(amount.getText()), Integer.parseInt(employeeID.getText()), new Date(), Integer.parseInt(blankID.getText()));
                }
            }else{
                LocalDate localDate = date.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date assignDate = Date.from(instant);
                if(blankID.getText().equals("")) {
                    BlankDatabaseFunctions.allocateBlank( Integer.parseInt(String.valueOf(blankType.getValue())),Integer.parseInt(amount.getText()),Integer.parseInt(employeeID.getText()), assignDate, 0);
                }else{
                    BlankDatabaseFunctions.allocateBlank( Integer.parseInt(String.valueOf(blankType.getValue())),Integer.parseInt(amount.getText()),Integer.parseInt(employeeID.getText()), assignDate, Integer.parseInt(blankID.getText()));
                }
            }
            if(amount.getText().equals("")){
                AlertWindow.showInformationAlert("Empty field","Please enter the amount");
            }
        });

        center.add(blankType,0,0,2,1);
        center.add(blankID,0,1,2,1);
        center.add(amount,0,2,2,1);
        center.add(employeeID,0,3,2,1);
        center.add(date,0,4,2,1);
        center.add(back,0,5,1,1);
        center.add(allocate,1,5,1,1);

        ui.getLayout().setCenter(center);

    }
}
