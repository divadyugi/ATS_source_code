package ButtonFunctions.MaintenanceFunctions.ExchangeRate;


import Database.ExchangeRateDatabaseFunctions;
import Entities.ExchangeRate;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AddExchangeRates {

    public static void addExchangeRate(MainPage ui){
        GridPane center = CreateLayout.createGridPane();

        TextField amount = new TextField();
        amount.setPromptText("Exchange amount: ");

        java.util.Date date = new java.util.Date();
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Start Date:");
        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End date:");

        Button add = new Button("Add");
        add.setOnAction(e->{
            if(!amount.getText().isEmpty()) {
                ExchangeRate exchangeRate = new ExchangeRate(date, Float.parseFloat(amount.getText()));
                if (!(datePicker.getValue() == null)) {
                    LocalDate localDate = datePicker.getValue();
                    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                    Date receiveDate = Date.from(instant);
                    exchangeRate.setStartDate(receiveDate);
                }
                if (!(endDate.getValue() == null)) {
                    LocalDate localDate = endDate.getValue();
                    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                    Date endingDate = Date.from(instant);
                    exchangeRate.setEndDate(endingDate);
                }
                ExchangeRateDatabaseFunctions.addExchangeRate(exchangeRate);
                ui.getLayout().setCenter(ui.getCenter());
                ui.createMaintenancePage();
                ExchangeRateFunctions.createTable(ui);
            }else{
                AlertWindow.showInformationAlert("Missing amount","Please type in amount");
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(amount,0,1,2,1);
        center.add(datePicker,0,2,2,1);
        center.add(endDate,0,3,2,1);
        center.add(add,0,4,1,1);
        center.add(back,1,4,1,1);

        ui.getLayout().setCenter(center);
    }


}
