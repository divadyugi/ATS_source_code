package ButtonFunctions.MaintenanceFunctions.Commission;

import Database.CommissionDatabaseFunctions;
import Entities.Commission;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UpdateCommission {

    //same as addCommission, however this time it gives option to add endDate, and also fills out details with the previously selected commission's details
    public static void updateCommission (MainPage ui, Commission commission){

        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);

        TextField amount = new TextField();
        amount.setPromptText("Commission amount:");
        amount.setText(String.valueOf(commission.getAmount()));

/*        TextField employeeID = new TextField();
        employeeID.setPromptText("Employee's ID:");
        employeeID.setText(String.valueOf(commission.()));
        employeeID.setEditable(false);*/

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("Commission end Date");
      //  LocalDate commissionEndDate = Instant.ofEpochMilli(commission.getCommissionEndDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
       // endDate.setValue(commissionEndDate);

        Button update = new Button("Update");
        update.setOnAction(e->{
            LocalDate localDate = endDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            Commission newCommission = new Commission(Float.parseFloat(amount.getText()),commission.getCommissionDate(),date,commission.getBlankType());
            newCommission.setCommissionID(commission.getCommissionID());
            CommissionDatabaseFunctions.updateCommission(newCommission);
            ui.createMaintenancePage();
            CommissionFunctions.createTable(ui);
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(amount,0,0,2,1);
        // center.add(employeeID,0,1,2,1);
        center.add(endDate ,0,2,2,1);
        center.add(update,0,3,1,1);
        center.add(back,1,3,1,1);

        ui.getLayout().setCenter(center);
    }
}
