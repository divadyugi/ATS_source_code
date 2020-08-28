package ButtonFunctions;

import ButtonFunctions.SalesFunctions.ExistingSale;
import ButtonFunctions.SalesFunctions.NewSale;
import ButtonFunctions.StockFunctions.BlanksFunction;
import ButtonFunctions.StockFunctions.ReAllocateFunction;
import Database.CustomerDatabaseFunctions;
import Database.SalesDatabaseFunction;
import Entities.Customer;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class SearchFunction {

    public static void createSearchWindow(MainPage ui){
        //search window used for searching different blank types for the blankFunctions method
        GridPane center = CreateLayout.createGridPane();

        center.setAlignment(Pos.TOP_CENTER);

        ComboBox blankType = new ComboBox();
        blankType.setPromptText("Blank Type");
        blankType.getItems().addAll("444","440","420","201","101","451","452");


        Button search = new Button("Search");
        search.setOnAction(e->{
            if(!(blankType.getValue().equals(""))) {
                BlanksFunction.createChoices(String.valueOf(blankType.getValue()), ui);
            }else{

                //if a blank is not selected an error message will pop-up
                AlertWindow.showInformationAlert("Select type","Please select the type of blank you want to search for");
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createStockPage();
        });

        center.add(blankType,0,0,2,1);
        center.add(back,0,1,1,1);
        center.add(search,1,1,1,1);

        ui.getLayout().setCenter(center);
    }

    //employee search used for searching the employee that we want to reallocate blanks from
    public static void createEmployeeSearch(MainPage ui){
        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        TextField employeeID = new TextField();
        employeeID.setPromptText("employee ID: ");

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createStockPage();
        });

        Button search = new Button("Search");
        search.setOnAction(e->{
                ReAllocateFunction.createReallocationTable(employeeID.getText(),ui);
        });

        center.add(employeeID,0,0,2,1);
        center.add(back,0,1,1,1);
        center.add(search,1,1,1,1);

        ui.getLayout().setCenter(center);

    }

    public static void createCustomerSearch(MainPage ui){
        //creates a table showing all the customers already in the system, used for selecting the customer buying the ticket for a sale
        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

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
        customerTable.setMinSize(600,300);
        customerTable.setItems(CustomerDatabaseFunctions.getCustomerInformation());
        customerTable.getColumns().addAll(customerIDColumn,customerFirstNameColumn,customerLastNameColumn,customerEmailColumn,customerTypeColumn,customerDiscountColumn);

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createSalesPage();
            ui.getLogin().getStage().setWidth(300);
        });

        Button search = new Button("Search");
        search.setOnAction(e->{
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            if(!(customer==null)) {
                SearchFunction.saleBlankTypeSearch(ui, customer, "Existing");
            }else{
                AlertWindow.showInformationAlert("No customer selected!","Please select a customer");
            }
        });


        center.add(customerTable,0,0,2,1);
        center.add(back,0,1,1,1);
        center.add(search,1,1,1,1);

        ui.getLayout().setCenter(center);
        ui.getLogin().getStage().setWidth(800);
    }

    public static void saleBlankTypeSearch(MainPage ui, Customer customer, String type) {
        //blank type search used for a sale, it will also cause another type
        ui.getLogin().getStage().setWidth(300);

        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        ComboBox blankType = new ComboBox();
        blankType.setPromptText("Blank Type");
        blankType.getItems().addAll("444", "440", "420", "201", "101", "451", "452");


        Button search = new Button("Search");
        search.setOnAction(e->{
            if(blankType.getValue().equals("444")||blankType.getValue().equals("440")||blankType.getValue().equals("420")){
                if(type.equals("Existing")) {
                    ExistingSale.createExistingInterlineSalesPage(ui, customer, String.valueOf(blankType.getValue()));
                }else{
                   NewSale.createNewInterlineSalesPage(ui, customer, String.valueOf(blankType.getValue()));
                }
            }else if(blankType.getValue().equals("201")||blankType.getValue().equals("101")){
                if(type.equals("Existing")) {
                   ExistingSale.createExistingDomesticSale(ui, customer, String.valueOf(blankType.getValue()));
                }else{
                   NewSale.createNewDomesticSale(ui,customer,String.valueOf(blankType.getValue()));
                }
            }else if(blankType.getValue().equals("451")||blankType.getValue().equals("452")){
                if(type.equals("Existing")) {
                    //if the blank type chosen is 451 or 452 (miscellaneous), then it will prompt you to select whether it is interline or domestinc
                    flightType(ui, customer, String.valueOf(blankType.getValue()),"Existing");
                }else{
                    flightType(ui, customer, String.valueOf(blankType.getValue()),"New");
                }
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e -> {
            //if type is existing: searchfunction, else new csutomer createor

            ui.getLayout().setCenter(ui.getCenter());
            SearchFunction.createCustomerSearch(ui);
        });

        center.add(blankType,0,0,2,1);
        center.add(back,0,1,1,1);
        center.add(search,1,1,1,1);

        ui.getLayout().setCenter(center);
    }

    public static void flightType(MainPage ui, Customer customer, String blankType, String type){
        //used to select whether an MCO blank(451 or 452) is for a domestic or interline flight
        GridPane center =CreateLayout.createGridPane();
        center.setAlignment(Pos.TOP_CENTER);

        ComboBox flightType = new ComboBox();
        flightType.setPromptText("Flight Type");
        flightType.getItems().addAll("Interline","Domestic");

        Button search = new Button("Search");
        search.setOnAction(e->{
            boolean interline = flightType.getValue().equals("Interline");
            if(interline&&type.equals("Existing")){
                ExistingSale.createExistingInterlineSalesPage(ui,customer,blankType);
            }else if(!interline && type.equals("Existing")){
                ExistingSale.createExistingDomesticSale(ui,customer,blankType);
            }else if(!interline && type.equals("New")){
                System.out.println("Type: "+type+" Interline: "+interline);
                NewSale.createNewDomesticSale(ui,customer,blankType);
            }else{
                System.out.println("Type: "+type+" Interline: "+interline);
                NewSale.createNewInterlineSalesPage(ui,customer,blankType);
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            saleBlankTypeSearch(ui,customer,type);
        });

        center.add(flightType,0,1,2,1);
        center.add(back,0,2,1,1);
        center.add(search,1,2,1,1);

        ui.getLayout().setCenter(center);
    }
}
