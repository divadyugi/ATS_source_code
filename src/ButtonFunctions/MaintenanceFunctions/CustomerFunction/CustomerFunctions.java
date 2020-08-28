package ButtonFunctions.MaintenanceFunctions.CustomerFunction;

import ButtonFunctions.MaintenanceFunctions.Discount.CreateFlexibleDiscount;
import ButtonFunctions.MaintenanceFunctions.Discount.DiscountPlans;
import ButtonFunctions.MaintenanceFunctions.Discount.UpdateFixedDiscount;
import Database.CustomerDatabaseFunctions;
import Entities.Customer;
import Entities.Discount;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerFunctions {

    public static void createTable(MainPage ui) {
        Stage stage = new Stage();
        stage.setTitle("Employee details");

        BorderPane layout = new BorderPane();

        //Top layer with welcome text
        VBox top = new VBox();
        top.setPadding(new Insets(10, 10, 10, 10));
        top.setSpacing(20);
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Employee functions");

        top.getChildren().addAll(title);

        layout.setTop(top);

        //Center with table

        GridPane center = CreateLayout.createGridPane();
        center.setAlignment(Pos.TOP_CENTER);


        //creates a table with customer details
        TableView<Customer> customerTable;


        //ID column
        TableColumn<Customer, Integer> customerIDColumn = new TableColumn<>("Customer ID:");
        customerIDColumn.setMinWidth(50);
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        //firstname column
        TableColumn<Customer, String> customerFirstNameColumn = new TableColumn<>("First name");
        customerFirstNameColumn.setMinWidth(50);
        customerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //lastName column
        TableColumn<Customer, String> customerLastNameColumn = new TableColumn<>("Last name");
        customerLastNameColumn.setMinWidth(50);
        customerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //customerType column
        TableColumn<Customer, String> customerTypeColumn = new TableColumn<>("Type: ");
        customerTypeColumn.setMinWidth(50);
        customerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //email column
        TableColumn<Customer, String> customerEmailColumn = new TableColumn<>("Email: ");
        customerEmailColumn.setMinWidth(50);
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        //discountID column
        TableColumn<Customer, Integer> customerDiscountColumn = new TableColumn<>("Discount ID:");
        customerDiscountColumn.setMinWidth(50);
        customerDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("discountID"));

        customerTable = new TableView<>();
        customerTable.setMinSize(400,300);
        //fills the table with details retrieved from the database
        customerTable.setItems(CustomerDatabaseFunctions.getCustomerInformation());
        customerTable.getColumns().addAll(customerIDColumn,customerFirstNameColumn,customerLastNameColumn,customerEmailColumn,customerTypeColumn,customerDiscountColumn);

        center.add(customerTable,0,1,1,1);
        layout.setCenter(center);

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button add = new Button("Add");
        add.setOnAction(e-> {
            AddCustomer.addCustomer(ui,"New");
            stage.close();
        });

        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            if(!(customer == null)) {
                CustomerDatabaseFunctions.deleteCustomer(customer);
                customerTable.setItems(CustomerDatabaseFunctions.getCustomerInformation());
                AlertWindow.showInformationAlert("Success!", "Customer successfully deleted");
            }else{
                //if you attempt to delete a customer without selecting one, an error message will pop up
                AlertWindow.showInformationAlert("No customer selected","Please select the customer you want to delete from the system");
            }
        });

        Button update = new Button("Update");
        update.setOnAction(e->{
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            UpdateCustomer.updateCustomer(ui,customer);
            stage.close();
        });

        Button viewDiscount = new Button("View Discount");
        //if discount is flexible, show the createFlexibleDiscount using that discount
        //if it is fixed, use the updateFixedDiscount to show the fixed discount for that
        viewDiscount.setOnAction(e->{
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            if(customer.getType().equals("Valued")) {
                Discount discount = CustomerDatabaseFunctions.showDiscountPlan(customer.getDiscountID());
                if (discount.getType().equals("Flexible")) {
                    CreateFlexibleDiscount.createFlexibleDiscount(discount);
                } else if (discount.getType().equals("Fixed")) {
                    UpdateFixedDiscount.updateFixedDiscount(ui, discount, "Customer", "Update");
                    stage.close();
                }
            }else{
                AlertWindow.showInformationAlert("Wrong customer type","Please select a valued customer, only valued customers can have discounts");
            }
        });

        Button selectPlan = new Button("Select Discount plan");
        selectPlan.setOnAction(e->{
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            if(customer.getType().equals("Valued")) {
                SelectPlan.createTable(ui, customer);
                stage.close();
            }else{
                AlertWindow.showInformationAlert("Wrong customer type", "Only valued customers can have a discount plan, please update the customer's type, or choose a different customer");
            }
        });

        bottom.getChildren().addAll(add,delete,update,viewDiscount,selectPlan);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }
}
