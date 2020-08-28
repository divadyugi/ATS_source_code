package ButtonFunctions.MaintenanceFunctions.Discount;

import ButtonFunctions.MaintenanceFunctions.CustomerFunction.CustomerFunctions;
import Database.CustomerDatabaseFunctions;
import Database.DiscountDatabaseFunctions;
import Database.FixedDiscountDatabaseFunction;
import Entities.Discount;
import Entities.FixedDiscount;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class  UpdateFixedDiscount {

    public static void updateFixedDiscount(MainPage ui, Discount discount, String type, String additionType){

        FixedDiscount fixedDiscount = FixedDiscountDatabaseFunction.getFixedDiscount(discount);

        GridPane center = CreateLayout.createGridPane();

        TextField amount = new TextField();
        amount.setPromptText("Discount amount: ");
        if(type.equals("Update")) {
            amount.setText(String.valueOf(fixedDiscount.getAmount()));
        }

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.createMaintenancePage();
        });

        Button update = new Button("Update");
        update.setOnAction(e-> {
            if(!(amount.getText().isEmpty())){
            if (type.equals("Update")) {
                FixedDiscountDatabaseFunction.updateDiscountPlan(fixedDiscount, Float.parseFloat(amount.getText()));
            } else if (type.equals("New")) {
                FixedDiscountDatabaseFunction.createFixedDiscountPlan(discount, Float.parseFloat(amount.getText()));
                DiscountDatabaseFunctions.updateType(discount, "Fixed");
            }
            ui.createMaintenancePage();
            if (additionType.equals("Discount")) {
                DiscountPlans.createTable(ui);
            } else {
                CustomerFunctions.createTable(ui);
            }
        }else{
                AlertWindow.showInformationAlert("No amount given","Please enter the amount of discount given to the custoemr");
            }
        });

        center.add(amount,0,1,2,1);
        center.add(back,0,2,1,1);
        center.add(update,1,2,1,1);

        ui.getLayout().setCenter(center);
    }
}
