package ButtonFunctions.SalesFunctions;

import ButtonFunctions.MaintenanceFunctions.CustomerFunction.CustomerFunctions;
import ButtonFunctions.SearchFunction;
import Database.BlankDatabaseFunctions;
import Database.CustomerDatabaseFunctions;
import Database.SalesDatabaseFunction;
import Entities.*;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class NewSale {

    private static TextField taxes;
    private static TextField price;
    private static  ExchangeRate exchangeRate;
    private static Commission commission;
    private static ArrayList<ExchangeRate> exchangeRates;
    private static TextField exchangeAmount;


    //creates an interline sales page for selling an interline ticket to a new customer
    public static void createNewInterlineSalesPage(MainPage ui, Customer customer, String blankType){
       // exchangeRate = SalesDatabaseFunction.getCurrentExchangeRate();
        exchangeRate = null;
        exchangeRates = SalesDatabaseFunction.getCurrentExchangeRate();
        Price originalPrice = new Price(0,0,exchangeRate);
        Price currentPrice = new Price(0,0,exchangeRate);
        Price[] prices ={currentPrice,originalPrice};
        commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType));

        GridPane center = new GridPane();


        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        exchangeAmount = new TextField();
        exchangeAmount.setPromptText("Exchange amount: ");

        ComboBox exchangeRateChoice = new ComboBox();
        exchangeRateChoice.setPromptText("Exchange Rate: ");
        exchangeRateChoice.getItems().clear();
        for(int i =0; i<exchangeRates.size(); i++){
            exchangeRateChoice.getItems().add(exchangeRates.get(i).getExchangeAmount());
        }
        exchangeRateChoice.getItems().add("New");
        exchangeRateChoice.setOnAction(e->{
            if(!(exchangeRateChoice.getValue()==null)) {
                if (!(exchangeRateChoice.getValue().equals("New"))) {
                    for (int i = 0; i < exchangeRateChoice.getItems().size(); i++) {
                        if (exchangeRateChoice.getItems().get(i) == exchangeRateChoice.getValue()) {
                            exchangeRate = exchangeRates.get(i);
                            prices[0].setExchangeRate(exchangeRate);
                            prices[1].setExchangeRate(exchangeRate);
                        }
                    }
                } else {
                    if (exchangeAmount.getText().isEmpty()) {
                        AlertWindow.showInformationAlert("No exchange rate entered", "Please first enter an exchange rate");
                    }
                    exchangeRate = new ExchangeRate(new java.util.Date(), Float.parseFloat(exchangeAmount.getText()));
                    prices[0].setExchangeRate(exchangeRate);
                    prices[1].setExchangeRate(exchangeRate);
                    //exchangeAmount.setEditable(false);
                }
            }
        });

        //since all the customers are new customer, they have no option to pay later, and thus only cash and card are the only payment options
        ComboBox paymentMethod = new ComboBox();
        paymentMethod.setPromptText("Payment Method:");
        paymentMethod.getItems().addAll("Cash","Card");

        Label localTotalPrice = new Label("Total price:   GBP");

        Label USDTotalPrice = new Label("Total Price:   USD");

        DatePicker date = new DatePicker();
        date.setPromptText("Sales date:");
        date.setOnAction(e->{
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date receiveDate = Date.from(instant);
           // exchangeRate = SalesDatabaseFunction.getCurrentExchangeRate(receiveDate);
            exchangeRates = SalesDatabaseFunction.getCurrentExchangeRate(receiveDate);
            exchangeRateChoice.setValue(null);
            exchangeRateChoice.getItems().clear();
            for(int i =0; i<exchangeRates.size(); i++){
                exchangeRateChoice.getItems().add(exchangeRates.get(i).getExchangeAmount());
            }
            exchangeRateChoice.getItems().add("New");
            ExistingSale.changeTotalInterlinePrice(price,taxes ,prices,localTotalPrice,USDTotalPrice);
            ExistingSale.changeTotalInterlineTaxes(taxes,price, prices,localTotalPrice,USDTotalPrice);
            commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType),receiveDate);
        });

        Button pay = new Button("Pay");
        pay.setOnAction(e->{
            int blankID = SalesDatabaseFunction.getBlankID(ui.getProfile().getEmployeeID(),blankType);
            Sale sale = new Sale(String.valueOf(paymentMethod.getValue()),
                    prices[0],blankID,ui.getProfile().getEmployeeID(),
                    customer.getID(),commission.getCommissionID(),new Date());
            sale.setBlankType(Integer.parseInt(blankType));
            if(!(date.getValue()==null)) {
                LocalDate localDate = date.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date receiveDate = Date.from(instant);
                sale.setSalesDate(receiveDate);
            }
            if(String.valueOf(paymentMethod.getValue()).equals("Cash")){
                if(exchangeRateChoice.getValue().equals("New")){
                    exchangeRate.setID(SalesDatabaseFunction.addNewExchangeRate(Float.parseFloat(exchangeAmount.getText())));
                    sale.setPrice(prices[0]);
                }
                CustomerDatabaseFunctions.addCustomer(customer);
                sale.setCustomerID(SalesDatabaseFunction.getLatestCustomer());
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Interline");
                }
                SalesDatabaseFunction.createInterlineSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID,Integer.parseInt(blankType), "Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }else if(String.valueOf(paymentMethod.getValue()).equals("Card")){
                if(exchangeRateChoice.getValue().equals("New")){
                    exchangeRate.setID(SalesDatabaseFunction.addNewExchangeRate(Float.parseFloat(exchangeAmount.getText())));
                    sale.setPrice(prices[0]);
                }
                CustomerDatabaseFunctions.addCustomer(customer);
                sale.setCustomerID(SalesDatabaseFunction.getLatestCustomer());
                customer.setID(SalesDatabaseFunction.getLatestCustomer());
                CardPayment.cardPaymentWindow(ui,blankType, sale,"New","Interline",customer);
            }else if(String.valueOf(paymentMethod.getValue()).equals("Late payment")){
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Interline");
                }
                SalesDatabaseFunction.createInterlineSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            SearchFunction.saleBlankTypeSearch(ui,customer,"New");
        });

        price = new TextField();
        price.setPromptText("Price: ");
        price.setOnKeyReleased(e->{
            ExistingSale.changeTotalInterlinePrice(price,taxes ,prices,localTotalPrice,USDTotalPrice);

        });

        taxes = new TextField();
        taxes.setPromptText("Taxes: ");
        taxes.setOnKeyReleased(e->{
            ExistingSale.changeTotalInterlineTaxes(taxes,price, prices,localTotalPrice,USDTotalPrice);
        });

        center.add(exchangeRateChoice,0,0,3,1);
        center.add(price,0,1,3,1);
        center.add(taxes,0,2,3,1);
        center.add(exchangeAmount,0,3,3,1);
        center.add(paymentMethod,0,4,3,1);
        center.add(localTotalPrice,0,5,3,1);
        center.add(USDTotalPrice,0,6,3,1);
        center.add(date,0,7,3,1);
        center.add(back,0,8,1,1);
        center.add(pay,1,8,1,1);

        ui.getLayout().setCenter(center);

    }


    //creates a domestic sales page for a new customer
    //the same as interline with the exception of not being able to choose exchange rate for the sale
    public static void createNewDomesticSale(MainPage ui, Customer customer, String blankType){
        Price originalPrice = new Price(0,0);
        Price currentPrice = new Price(0,0);
        Price[] prices ={currentPrice,originalPrice};
        commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType));

        GridPane center = new GridPane();


        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);



        ComboBox paymentMethod = new ComboBox();
        paymentMethod.setPromptText("Payment Method:");
        paymentMethod.getItems().addAll("Cash","Card");

        Label localTotalPrice = new Label("Total price:   GBP");

        DatePicker date = new DatePicker();
        date.setPromptText("Sales date: ");
        date.setOnAction(e->{
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date receiveDate = Date.from(instant);
            commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType),receiveDate);
        });

        Button pay = new Button("Pay");
        pay.setOnAction(e->{
            int blankID = SalesDatabaseFunction.getBlankID(ui.getProfile().getEmployeeID(),blankType);
            Sale sale = new Sale(String.valueOf(paymentMethod.getValue()),
                    prices[0],blankID,ui.getProfile().getEmployeeID(),
                    customer.getID(),commission.getCommissionID(),new Date());
            sale.setBlankType(Integer.parseInt(blankType));
            if(!(date.getValue()==null)) {
                LocalDate localDate = date.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date receiveDate = Date.from(instant);
                sale.setSalesDate(receiveDate);
            }
            if(String.valueOf(paymentMethod.getValue()).equals("Cash")){
                CustomerDatabaseFunctions.addCustomer(customer);
                sale.setCustomerID(SalesDatabaseFunction.getLatestCustomer());
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Domestic");
                }
                SalesDatabaseFunction.createDomesticSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
                ui.getLayout().setCenter(ui.getCenter());
                ui.createSalesPage();
            }else if(String.valueOf(paymentMethod.getValue()).equals("Card")){
                CustomerDatabaseFunctions.addCustomer(customer);
                sale.setCustomerID(SalesDatabaseFunction.getLatestCustomer());
                customer.setID(SalesDatabaseFunction.getLatestCustomer());
                CardPayment.cardPaymentWindow(ui, blankType,sale,"New","Domestic",customer);
            }else if(String.valueOf(paymentMethod.getValue()).equals("Late payment")){
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Domestic");
                }
                SalesDatabaseFunction.createDomesticSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            SearchFunction.saleBlankTypeSearch(ui,customer,"New");
        });

        price = new TextField();
        price.setPromptText("Price: ");
        price.setOnKeyReleased(e->{
            ExistingSale.changeTotalDomesticPrice(price,taxes ,prices,localTotalPrice);

        });

        taxes = new TextField();
        taxes.setPromptText("Taxes: ");
        taxes.setOnKeyReleased(e->{
            ExistingSale.changeTotalDomesticTaxes(taxes,price, prices,localTotalPrice);
        });

        center.add(price,0,1,3,1);
        center.add(taxes,0,2,3,1);
        center.add(paymentMethod,0,3,3,1);
        center.add(localTotalPrice,0,4,3,1);
        center.add(date, 0, 5, 3,1);
        center.add(back,0,6,1,1);
        center.add(pay,1,6,1,1);

        ui.getLayout().setCenter(center);

    }
}
