package ButtonFunctions.MaintenanceFunctions.CustomerFunction;

import ButtonFunctions.MaintenanceFunctions.CustomerFunction.CustomerFunctions;
import Database.CustomerDatabaseFunctions;
import Entities.Customer;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UpdateCustomer {

    public static void updateCustomer(MainPage ui, Customer customer) {

        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);

        TextField firstName = new TextField();
        firstName.setPromptText("First name");
        firstName.setText(customer.getFirstName());

        TextField lastName = new TextField();
        lastName.setPromptText("Last name: ");
        lastName.setText(customer.getLastName());

        TextField email = new TextField();
        email.setPromptText("Email: ");
        email.setText(customer.getEmail());

        ComboBox type = new ComboBox();
        type.getItems().clear();
        type.getItems().addAll("Customer", "Regular", "Valued");
        type.setPromptText("Type:");
        type.setValue(customer.getType());

        Button update = new Button("Update");
        update.setOnAction(e->{
            Customer newCustomer = new Customer(firstName.getText(),
                    lastName.getText(),
                    String.valueOf(type.getValue()),
                    email.getText());
            CustomerDatabaseFunctions.updateCustomer(customer,newCustomer);
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
            CustomerFunctions.createTable(ui);
        });

        Button back = new Button("Back");
        back.setOnAction(e -> {
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(firstName, 0, 2, 2, 1);
        center.add(lastName, 0, 3, 2, 1);
        center.add(email, 0, 4, 2, 1);
        center.add(type, 0, 5, 2, 1);
        center.add(back, 0, 6, 1, 1);
        center.add(update, 1, 6, 1, 1);

        ui.getLayout().setCenter(center);

    }
}
