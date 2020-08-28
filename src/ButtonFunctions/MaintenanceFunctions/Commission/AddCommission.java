package ButtonFunctions.MaintenanceFunctions.Commission;

import ButtonFunctions.MaintenanceFunctions.Commission.CommissionFunctions;
import Database.CommissionDatabaseFunctions;
import Entities.Commission;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddCommission {


    //used to add commissions to database
    public static void addCommission(MainPage ui) {
        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);

        TextField amount = new TextField();
        amount.setPromptText("Commission amount:");

/*        TextField employeeID = new TextField();
        employeeID.setPromptText("Employee's ID:");*/

        ComboBox blankType = new ComboBox();
        blankType.setPromptText("Blank Type");
        blankType.getItems().addAll("444","440","420","201","101","451","452");



        DatePicker date = new DatePicker();
        date.setPromptText("Start date: ");


        Button add = new Button("Add");
        add.setOnAction(e->{
            if(!amount.getText().isEmpty() || String.valueOf(blankType.getValue()).isEmpty()) {
                Commission commission = new Commission(Float.parseFloat(amount.getText()), new java.util.Date(), Integer.parseInt(String.valueOf(blankType.getValue())));
                //this will determine whether to use today's date or the date selected
                if (!(date.getValue() == null)) {
                    LocalDate localDate = date.getValue();
                    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                    Date receiveDate = Date.from(instant);
                    commission.setCommissionDate(receiveDate);
                }
                CommissionDatabaseFunctions.createCommission(commission);
                ui.getLayout().setCenter(ui.getCenter());
                ui.createMaintenancePage();
                CommissionFunctions.createTable(ui);
            }else{
                //this will give an error message if any of the required details are missing
                AlertWindow.showInformationAlert("Missing details","Please fill out all the required fields");
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(amount,0,0,2,1);
        center.add(blankType,0,1,2,1);
        center.add(date,0,2,2,1);
        center.add(add,0,3,1,1);
        center.add(back,1,3,1,1);

        ui.getLayout().setCenter(center);

    }
}
