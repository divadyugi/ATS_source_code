package ButtonFunctions.SalesFunctions;

import ButtonFunctions.SearchFunction;
import Database.*;
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

public class ExistingSale {

    private static TextField taxes;
    private static TextField price;
    private static Boolean discounted= false;
    private static FixedDiscount fixedDiscount;
    private static Discount discount;
    private static ArrayList<FlexibleDiscount> flexibleDiscounts;
    private static ExchangeRate exchangeRate;
    private static Commission commission;
    private static ArrayList<ExchangeRate>exchangeRates;
    private static TextField exchangeAmount;

    //method to calculate total USD and GBP price for an interline sale
    public static void changeTotalInterlinePrice(TextField price, TextField taxes, Price[] prices, Label localTotalPrice, Label USDTotalPrice){
        if(!price.getText().isEmpty()) {
            prices[0].setPrice(Float.parseFloat(price.getText()));
            prices[1].setPrice(Float.parseFloat(price.getText()));
        }else{
            prices[0].setPrice(0);
            prices[1].setPrice(0);
        }
        if (!taxes.getText().isEmpty()) {
            localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
            //set usd price to total exchangedprice
            USDTotalPrice.setText("Total price: "+prices[0].calculateTotalExchangedPrice()+" USD");
        } else {
            localTotalPrice.setText("Total price: " + prices[0].getPrice()+" GBP");
            //set usd to total exchangedprice
            USDTotalPrice.setText("Total price: "+prices[0].calculateExchangedPrice()+" USD");
        }
    }

    //method to calculate total GBP price in domestic sale
    public static void changeTotalDomesticPrice(TextField price, TextField taxes, Price[] prices, Label localTotalPrice){
        if(!price.getText().isEmpty()) {
            prices[0].setPrice(Float.parseFloat(price.getText()));
            prices[1].setPrice(Float.parseFloat(price.getText()));
        }else{
            prices[0].setPrice(0);
            prices[1].setPrice(0);
        }

        if (!taxes.getText().isEmpty()) {
            localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
        } else {
            localTotalPrice.setText("Total price: " + prices[0].getPrice() + " GBP");
        }
    }

    //method to calculate totalInterlineTax for USD and GBP
    public static void changeTotalInterlineTaxes(TextField taxes, TextField price,Price[] prices, Label localTotalPrice, Label USDTotalPrice){
        if(!taxes.getText().isEmpty()) {
            prices[0].setTaxes(Float.parseFloat(taxes.getText()));
            prices[1].setTaxes(Float.parseFloat(taxes.getText()));
        }else{
            prices[0].setTaxes(0);
            prices[1].setTaxes(0);
        }
        if (!price.getText().isEmpty()) {
            localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
            USDTotalPrice.setText("Total price: "+prices[0].calculateTotalExchangedPrice()+" USD");
        } else {
            localTotalPrice.setText("Total price: " + prices[0].getTaxes()+" GBP");
            //set usd to total exchanged tax price;
            USDTotalPrice.setText("Total price: "+prices[0].calculateExchangedTax()+" USD");
        }

    }

    //used to calculate total taxes for a domestic flight
    public static void changeTotalDomesticTaxes(TextField taxes,TextField price, Price[] prices, Label localTotalPrice){
        if(!taxes.getText().isEmpty()) {
            prices[0].setTaxes(Float.parseFloat(taxes.getText()));
            prices[1].setTaxes(Float.parseFloat(taxes.getText()));
        }else{
            prices[0].setTaxes(0);
            prices[1].setTaxes(0);
        }
        if (!price.getText().isEmpty()) {
            localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
        } else {
            localTotalPrice.setText("Total price: " + prices[0].getTaxes() + " GBP");
        }
    }

    //calculates the discounted price in both USD and GBP for an interline sale

    private static void calculateInterlineDiscountedPrice(TextField price,Price[] prices, Label localTotalPrice, Label USDTotalPrice, Button applyDiscount){
        if(!discounted){
            if(!price.getText().isEmpty()){
                if(discount.getType().equals("Fixed")) {
                    prices[0].setPrice(prices[0].calculateFixedDiscountPrice(fixedDiscount));
                }else{
                    prices[0].setPrice(prices[0].calculateFlexibleDiscountPrice(flexibleDiscounts));
                }
                price.setText(String.valueOf(prices[0].getPrice()));
                if (!taxes.getText().isEmpty()) {
                    localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
                    //set usd price to total exchangedprice
                    USDTotalPrice.setText("Total price: "+prices[0].calculateTotalExchangedPrice()+" USD");
                } else {
                    localTotalPrice.setText("Total price: " + prices[0].getPrice()+" GBP");
                    //set usd to total exchangedprice
                    USDTotalPrice.setText("Total price: "+prices[0].calculateExchangedPrice()+" USD");
                }
                applyDiscount.setText("Cancel discount");
                discounted = true;
            }
        }else{
            prices[0].setPrice(prices[1].getPrice());
            price.setText(String.valueOf(prices[0].getPrice()));
            changeTotalInterlinePrice(price, taxes, prices,localTotalPrice,USDTotalPrice);
            applyDiscount.setText("Apply discount");
            discounted = false;
        }
    }

    //calculates discounted price for domestic sale in GBP
    private static void calculateDomesticDiscountedPrice(Price[] prices, Label localTotalPrice, Button applyDiscount) {
        if (!discounted) {
            if (!price.getText().isEmpty()) {
                if (discount.getType().equals("Fixed")) {
                    prices[0].setPrice(prices[0].calculateFixedDiscountPrice(fixedDiscount));
                } else {
                    prices[0].setPrice(prices[0].calculateFlexibleDiscountPrice(flexibleDiscounts));
                }
                price.setText(String.valueOf(prices[0].getPrice()));
                if (!taxes.getText().isEmpty()) {
                    localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
                } else {
                    localTotalPrice.setText("Total price: " + prices[0].getPrice() + " GBP");
                }
                applyDiscount.setText("Cancel discount");
                discounted = true;
            }
            } else {
                prices[0].setPrice(prices[1].getPrice());
                price.setText(String.valueOf(prices[0].getPrice()));
                if (!taxes.getText().isEmpty()) {
                    localTotalPrice.setText("Total price: " + prices[0].calculateTotalPrice() + " GBP");
                } else {
                    localTotalPrice.setText("Total price: " + prices[0].getPrice() + " GBP");
                }
                applyDiscount.setText("Apply discount");
                discounted = false;
            }
        }


        //creates the sale page for selling an interline ticket to an already existing customer
    public static void createExistingInterlineSalesPage(MainPage ui, Customer customer, String blankType){

        exchangeRate = null;
        exchangeRates = SalesDatabaseFunction.getCurrentExchangeRate();
        Price originalPrice = new Price(0,0,exchangeRate);
        Price currentPrice = new Price(0,0,exchangeRate);
        Price[] prices ={currentPrice,originalPrice};
        discount = SalesDatabaseFunction.getDiscountType(customer);

        commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType));

        if(customer.getType().equals("Valued")) {
            if (discount.getType().equals("Fixed")) {
                fixedDiscount = FixedDiscountDatabaseFunction.getFixedDiscount(discount);
            } else if (discount.getType().equals("Flexible")) {
                flexibleDiscounts = SalesDatabaseFunction.getFlexibleDiscounts(discount);
            }
        }
        //if discount type = fixed, create a fixed discount
        //otherwise create an array of all the flexible discounts that the customer has


        //GUI elements:

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
        //fills the exchangeRateChoice combobox with exchnagerates that can be used for the sale
        for(int i =0; i<exchangeRates.size(); i++){
            exchangeRateChoice.getItems().add(exchangeRates.get(i).getExchangeAmount());
        }
        exchangeRateChoice.getItems().add("New");
        exchangeRateChoice.setOnAction(e->{
            //when an exchange rate is selected, it will update the exchangeRate used for the sale
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
                    exchangeAmount.setEditable(false);
                }
            }
        });
        ComboBox paymentMethod = new ComboBox();
        paymentMethod.setPromptText("Payment Method:");
        paymentMethod.getItems().addAll("Cash","Card");
        //if the customer that is buying a blank is a regular or valued customer, the late payment option is added to paymentMethod choices
        if(!customer.getType().equals("Customer")){
            paymentMethod.getItems().add("Late payment");
        }

        Label localTotalPrice = new Label("Total price:   GBP");

        Label USDTotalPrice = new Label("Total Price:   USD");

        DatePicker date = new DatePicker();
        date.setPromptText("Sales Date:");
        date.setOnAction(e->{
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date receiveDate = Date.from(instant);
            //exchangeRate = SalesDatabaseFunction.getCurrentExchangeRate(receiveDate);
            exchangeRates = SalesDatabaseFunction.getCurrentExchangeRate(receiveDate);
            exchangeRateChoice.setValue(null);
            exchangeRateChoice.getItems().clear();
            for(int i =0; i<exchangeRates.size(); i++){
                exchangeRateChoice.getItems().add(exchangeRates.get(i).getExchangeAmount());
            }
            exchangeRateChoice.getItems().add("New");
            //prices[0].setExchangeRate(exchangeRate);
            //prices[1].setExchangeRate(exchangeRate);
            ExistingSale.changeTotalInterlinePrice(price,taxes ,prices,localTotalPrice,USDTotalPrice);
            ExistingSale.changeTotalInterlineTaxes(taxes,price, prices,localTotalPrice,USDTotalPrice);
            commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType),receiveDate);
        });

        Button pay = new Button("Pay");
        pay.setOnAction(e->{

            //creates the sale based on the type of payment that was chosen
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
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Interline");
                }
                SalesDatabaseFunction.createInterlineSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }else if(String.valueOf(paymentMethod.getValue()).equals("Card")){
                if(exchangeRateChoice.getValue().equals("New")){
                    exchangeRate.setID(SalesDatabaseFunction.addNewExchangeRate(Float.parseFloat(exchangeAmount.getText())));
                    sale.setPrice(prices[0]);
                }
                CardPayment.cardPaymentWindow(ui, blankType, sale,"Existing","Interline",customer);
            }else if(String.valueOf(paymentMethod.getValue()).equals("Late payment")){
                if(exchangeRateChoice.getValue().equals("New")){
                    exchangeRate.setID(SalesDatabaseFunction.addNewExchangeRate(Float.parseFloat(exchangeAmount.getText())));
                    sale.setPrice(prices[0]);
                }
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Interline");
                }
                sale.setLatePaymentStatus("Not Paid");
                SalesDatabaseFunction.createInterlineSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            SearchFunction.saleBlankTypeSearch(ui,customer,"Existing");
        });



        price = new TextField();
        price.setPromptText("Price: ");
        price.setOnKeyReleased(e->{
        changeTotalInterlinePrice(price,taxes ,prices,localTotalPrice,USDTotalPrice);

        });

        taxes = new TextField();
        taxes.setPromptText("Taxes: ");
        taxes.setOnKeyReleased(e->{
            changeTotalInterlineTaxes(taxes,price, prices,localTotalPrice,USDTotalPrice);
        });

        Button applyDiscount = new Button("Apply Discount");
        applyDiscount.setOnAction(e->{
          calculateInterlineDiscountedPrice(price, prices,localTotalPrice,USDTotalPrice,applyDiscount);
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
        if(customer.getType().equals("Valued")) {
            center.add(applyDiscount, 2, 8, 1, 1);
        }


        ui.getLayout().setCenter(center);

    }

    //creates the sale page for selling a domestic ticket to an already existing customer
    //it is the same as an intelrine sale page, but with the difference of not having the option to select exchange rate
    public static void createExistingDomesticSale(MainPage ui, Customer customer, String blankType){

        Price originalPrice = new Price(0,0);
        Price currentPrice = new Price(0,0);
        Price[] prices ={currentPrice,originalPrice};
        discount = SalesDatabaseFunction.getDiscountType(customer);

        commission = SalesDatabaseFunction.getSalesCommission(Integer.parseInt(blankType));


        if(discount.getType().equals("Fixed")){
            fixedDiscount = FixedDiscountDatabaseFunction.getFixedDiscount(discount);
        }else if(discount.getType().equals("Flexible")){
            flexibleDiscounts = SalesDatabaseFunction.getFlexibleDiscounts(discount);
        }

        GridPane center = new GridPane();


        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);



        ComboBox paymentMethod = new ComboBox();
        paymentMethod.setPromptText("Payment Method:");
        paymentMethod.getItems().addAll("Cash","Card");
        if(!customer.getType().equals("Customer")){
            paymentMethod.getItems().add("Late payment");
        }

        Label localTotalPrice = new Label("Total price:   GBP");

        DatePicker date = new DatePicker();
        date.setPromptText("Sale date: ");
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
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Domestic");
                }
                SalesDatabaseFunction.createDomesticSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID, Integer.parseInt(blankType),"Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }else if(String.valueOf(paymentMethod.getValue()).equals( "Card")){
                CardPayment.cardPaymentWindow(ui, blankType,sale,"Existing","Domestic",customer);
            }else if(String.valueOf(paymentMethod.getValue()).equals("Late payment")){
                sale.setLatePaymentStatus("Not Paid");
                if(blankType.equals("451")||blankType.equals("452")) {
                    sale.setMCOtype("Domestic");
                }
                SalesDatabaseFunction.createDomesticSale(sale);
                BlankDatabaseFunctions.changeStatus(blankID,Integer.parseInt(blankType), "Sold");
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            SearchFunction.saleBlankTypeSearch(ui,customer,"Existing");
        });



        price = new TextField();
        price.setPromptText("Price: ");
        price.setOnKeyReleased(e->{
           changeTotalDomesticPrice(price,taxes, prices,localTotalPrice);

        });

        taxes = new TextField();
        taxes.setPromptText("Taxes: ");
        taxes.setOnKeyReleased(e->{
            changeTotalDomesticTaxes(taxes,price, prices,localTotalPrice);
        });

        Button applyDiscount = new Button("Apply Discount");
        applyDiscount.setOnAction(e->{
            calculateDomesticDiscountedPrice(prices,localTotalPrice,applyDiscount);
        });

        center.add(price,0,1,3,1);
        center.add(taxes,0,2,3,1);
        center.add(paymentMethod,0,3,3,1);
        center.add(localTotalPrice,0,4,3,1);
        center.add(date, 0,5,3,1);
        center.add(back,0,6,1,1);
        center.add(pay,1,6,1,1);
        if(customer.getType().equals("Valued")) {
            center.add(applyDiscount, 2, 6, 1, 1);
        }


        ui.getLayout().setCenter(center);
    }
}
