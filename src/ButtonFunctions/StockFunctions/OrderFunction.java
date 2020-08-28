package ButtonFunctions.StockFunctions;

import Database.BlankDatabaseFunctions;
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

public class OrderFunction {

    public static void orderBlanks(MainPage ui){

        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);

        ComboBox blankType = new ComboBox();
        blankType.getItems().clear();
        blankType.setPromptText("Blank Type:");
        blankType.getItems().addAll("444","440","420","201","101","451","452");

        TextField blankID = new TextField();
        blankID.setPromptText("ID to start the blanks from");

        TextField amount = new TextField();
        amount.setPromptText("Amount: ");

        DatePicker date = new DatePicker();
        date.setPromptText("Date received");

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createStockPage();
        });
        Button order = new Button("Add blanks");
        order.setOnAction(e->{
            if(amount.getText().equals("")){
                AlertWindow.showInformationAlert("Empty field", "Please enter the amount of blanks you want to order");
                return;
            }
            if(date.getValue()==null) {
                //leaving the date empty defaults the date to today's date
                if(blankID.getText().equals("")) {
                    //leaving the blankID empty will default it to the highest possible free blankID of that type
                    BlankDatabaseFunctions.orderBlanks(Integer.parseInt(amount.getText()), Integer.parseInt(String.valueOf(blankType.getValue())));
                }else{
                    BlankDatabaseFunctions.orderBlanks(Integer.parseInt(amount.getText()), Integer.parseInt(String.valueOf(blankType.getValue())), new Date(), Integer.parseInt(blankID.getText()));
                }
            }else{
                LocalDate localDate = date.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date receiveDate = Date.from(instant);
                if(blankID.getText().equals("")) {
                    BlankDatabaseFunctions.orderBlanks(Integer.parseInt(amount.getText()), Integer.parseInt(String.valueOf(blankType.getValue())), receiveDate);
                }else{
                    BlankDatabaseFunctions.orderBlanks(Integer.parseInt(amount.getText()), Integer.parseInt(String.valueOf(blankType.getValue())), new Date(), Integer.parseInt(blankID.getText()));
                }


            }

            AlertWindow.showInformationAlert("Success!","The blanks have been ordered ");

        });


        center.add(blankType,0,0,2,1);
        center.add(blankID,0,1,2,1);
        center.add(amount,0,2,2,1);
        center.add(date,0,3,2,1);
        center.add(back,0,4,1,1);
        center.add(order,1,4,1,1);

        ui.getLayout().setCenter(center);

    }
}
