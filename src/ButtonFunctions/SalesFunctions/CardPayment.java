package ButtonFunctions.SalesFunctions;

import Database.BlankDatabaseFunctions;
import Database.SalesDatabaseFunction;
import Entities.CreditCard;
import Entities.Customer;
import Entities.Sale;
import UI.AlertWindow;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CardPayment {

    public static void cardPaymentWindow(MainPage ui, String blankType, Sale sale, String type, String saleType, Customer customer) {
        Stage stage = new Stage();
        stage.setTitle("Card payment");

        BorderPane layout = new BorderPane();

        //Table on the left;

        //shows all the cards associated with the selected customer
        TableView<CreditCard> creditCardTable;

        //cardNumber column
        TableColumn<CreditCard, Integer> numberColumn = new TableColumn<>("Card number:");
        numberColumn.setMinWidth(50);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));

        //expiryDate column
        TableColumn<CreditCard, Date> expiryDateColumn = new TableColumn<>("expiry Date:");
        expiryDateColumn.setMinWidth(50);
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        //cvv column
        TableColumn<CreditCard, Integer> cvvColumn = new TableColumn<>("CVV:");
        cvvColumn.setMinWidth(50);
        cvvColumn.setCellValueFactory(new PropertyValueFactory<>("cvv"));

        //firstName column
        TableColumn<CreditCard, String> firstNameColumn = new TableColumn<>("First Name:");
        firstNameColumn.setMinWidth(50);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //lastName column
        TableColumn<CreditCard, String> lastNameColumn = new TableColumn<>("Last Name:");
        lastNameColumn.setMinWidth(50);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        creditCardTable = new TableView<>();
        creditCardTable.setMaxSize(400, 300);
        creditCardTable.setItems(SalesDatabaseFunction.getCreditCardInfo(customer));
        creditCardTable.getColumns().addAll(numberColumn, expiryDateColumn, cvvColumn, firstNameColumn, lastNameColumn);

        layout.setLeft(creditCardTable);

        GridPane center = new GridPane();
        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        TextField cardNumber = new TextField();
        cardNumber.setPromptText("Card number: ");

        DatePicker expiryDate = new DatePicker(); //could change to two comboboxes, one for month and another for year
        expiryDate.setPromptText("Expiry Date: ");
        TextField firstName = new TextField();
        firstName.setPromptText("First name: ");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name: ");

        TextField cvvNumber = new TextField();
        cvvNumber.setPromptText("CVV: ");

        Button back = new Button("Back");

        back.setOnAction(e-> stage.close());
        Button pay  = new Button("Pay");
        pay.setOnAction(e->{
            //makes sure that a credit card is selected for the purchase
            if(!creditCardTable.getSelectionModel().getSelectedItem().equals(null)) {
                CreditCard creditCard = creditCardTable.getSelectionModel().getSelectedItem();
                sale.setCardNumber(creditCard.getCardNumber());
                int blankID = SalesDatabaseFunction.getBlankID(ui.getProfile().getEmployeeID(),blankType);


                if(saleType.equals("Interline")) {
                    if(blankType.equals("451")||blankType.equals("452")) {
                        sale.setMCOtype("Interline");
                    }
                    SalesDatabaseFunction.createInterlineSale(sale);
                    BlankDatabaseFunctions.changeStatus(blankID,Integer.parseInt(blankType), "Sold");
                }else{
                    if(blankType.equals("451")||blankType.equals("452")) {
                        sale.setMCOtype("Domestic");
                    }
                    SalesDatabaseFunction.createDomesticSale(sale);
                    BlankDatabaseFunctions.changeStatus(blankID,Integer.parseInt(blankType), "Sold");
                }
                AlertWindow.showInformationAlert("Success!", "Sale has been successfully made\n The ID of the ticket is: "+SalesDatabaseFunction.getSoldTicketID());

                stage.close();
            }
        });

        Button add = new Button("Add");
        add.setOnAction(e->{
            //adds the credit card to the database, so that it could be used for the sale
            LocalDate localDate = expiryDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            //create credit card info
            CreditCard creditCard = new CreditCard(cardNumber.getText(),date,Integer.parseInt(cvvNumber.getText()),firstName.getText(),lastName.getText());
            if(!SalesDatabaseFunction.cardInSystem(creditCard)) {
                SalesDatabaseFunction.storeCreditCard(creditCard, sale);
            }
            creditCardTable.setItems(SalesDatabaseFunction.getCreditCardInfo(customer));
        });

        center.add(cardNumber,0,0,2,1);
        center.add(expiryDate,0,1,2,1);
        center.add(firstName,0,2,2,1);
        center.add(lastName,0,3,2,1);
        center.add(cvvNumber,0,4,2,1);
        center.add(back,0,5,1,1);
        center.add(pay,1,5,1,1);
        center.add(add, 2,5,1,1);

        layout.setCenter(center);

        Scene scene = new Scene(layout,700,400);
        stage.setScene(scene);
        stage.show();
    }

    }
