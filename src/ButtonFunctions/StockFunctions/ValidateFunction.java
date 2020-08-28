package ButtonFunctions.StockFunctions;

import ButtonFunctions.SearchFunction;
import Database.BlankDatabaseFunctions;
import Entities.Blank;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ValidateFunction {

    public static void validateBlank(Blank blank, MainPage ui){

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(10);

        TextField destFrom = new TextField();
        destFrom.setPromptText("Destination From: ");

        TextField destTo = new TextField();
        destTo.setPromptText("Destination To: ");

        Button back = new Button("Back");
        back.setOnAction(e->{
            SearchFunction.createSearchWindow(ui);
        });

        Button auditor = new Button("Auditor Coupon");
        auditor.setOnAction(e->{
            BlankDatabaseFunctions.auditorBlank(blank);
        });


        Button validate = new Button ("Validate");
        validate.setOnAction(e->{
            //if the destinations are not given, an error will be shown
            if(destFrom.equals("")||destTo.equals("")){
                AlertWindow.showInformationAlert("Empty fields","Please fill out all the fields");
                return;
            }else {
                BlankDatabaseFunctions.validateBlank(blank, destTo.getText(), destFrom.getText());
                AlertWindow.showInformationAlert("Success!", "The flight coupon has been added to the blank");
            }
        });


        layout.add(destFrom,0,0,3,1);
        layout.add(destTo, 0,1,3,1);
        layout.add(back,0,2,1,1);
        layout.add(auditor,1,2,1,1);
        layout.add(validate,2,2,1,1);

        ui.getLayout().setCenter(layout);
    }
}
