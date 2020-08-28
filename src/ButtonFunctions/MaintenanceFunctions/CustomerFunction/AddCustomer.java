package ButtonFunctions.MaintenanceFunctions.CustomerFunction;

import ButtonFunctions.MaintenanceFunctions.CustomerFunction.CustomerFunctions;
import ButtonFunctions.SearchFunction;
import Database.CustomerDatabaseFunctions;
import Database.SalesDatabaseFunction;
import Entities.Customer;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddCustomer {

    private static Button add;

    public static void addCustomer(MainPage ui, String addType) {

        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);


        TextField ID = new TextField();
        ID.setPromptText("Customer ID: ");
        if(addType.equals("Sale")) {
            ID.setEditable(false);
            ID.setText(String.valueOf(SalesDatabaseFunction.getLatestCustomer()+1));
        }

        TextField firstName = new TextField();
        firstName.setPromptText("First name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name: ");

        TextField email = new TextField();
        email.setPromptText("Email: ");

        ComboBox type = new ComboBox();
        type.getItems().clear();
        type.getItems().addAll("Customer", "Regular", "Valued");
        type.setPromptText("Type:");

        add = new Button("Add");
        add.setOnAction(e->{
            if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getValue()).isEmpty()) {
                //creates an error if any of the required details are missing
                AlertWindow.showInformationAlert("Missing fields","Please fill out all the required fields");
                return;
            }
            if(addType.equals("Sale")){
                //if the new customer is used for sale, then the customer is not added to the database yet, just created
                Customer customer = new Customer(firstName.getText(),
                        lastName.getText(),
                        "Customer",
                        email.getText());
                if(ID.getText().equals("")){
                    customer.setID(0);
                }else{
                    customer.setID(Integer.parseInt(ID.getText()));
                }
                SearchFunction.saleBlankTypeSearch(ui,customer,"New");
            }else{
                //creates and adds customer to database
                Customer customer = new Customer(firstName.getText(),
                        lastName.getText(),
                        String.valueOf(type.getValue()),
                        email.getText());
                if(ID.getText().equals("")){
                    customer.setID(0);
                }else{
                    customer.setID(Integer.parseInt(ID.getText()));
                }
                CustomerDatabaseFunctions.addCustomer(customer);
                ui.getLayout().setCenter(ui.getCenter());
                ui.createMaintenancePage();
                CustomerFunctions.createTable(ui);
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(ID,0,1,2,1);
        center.add(firstName,0,2,2,1);
        center.add(lastName,0,3,2,1);
        center.add(email,0,4,2,1);
        if(!addType.equals("Sale")) {
            center.add(type, 0, 5, 2, 1);
        }
        center.add(back,0,6,1,1);
        center.add(add,1,6,1,1);

        ui.getLayout().setCenter(center);

    }





}
