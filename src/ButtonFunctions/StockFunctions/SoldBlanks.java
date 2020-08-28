package ButtonFunctions.StockFunctions;

import Database.BlankDatabaseFunctions;
import Database.SoldDatabaseFunctions;
import Entities.Blank;
import Entities.Coupon;
import Entities.Price;
import Entities.Sale;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SoldBlanks {

    //show sold blanks on table on left,

    //when blank is clicked show it's coupons on the right, along with destination
    //delete will delete both sale and coupon, and blank
    //button to delete coupon
    //refund
    //check late payment? maybe
    public static void salesTable(MainPage ui){

        Stage stage = new Stage();
        stage.setTitle("Reallocation");

        BorderPane layout = new BorderPane();

        //Top with welcome text;
        VBox top = new VBox();
        top.setPadding(new Insets(10,10,10,10));
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Sold blanks");

        top.getChildren().addAll(title);

        GridPane center = CreateLayout.createGridPane();

        TableView<Sale> saleTable;

        //type
        TableColumn<Sale, Integer> typeColumn = new TableColumn<>("SaleType");
        typeColumn.setMinWidth(50);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        //price
        TableColumn<Sale, Float> priceColumn = new TableColumn<>("Price £:");
        priceColumn.setMinWidth(50);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("FloatPrice"));
        //taxes
        TableColumn<Sale, Float> taxesColumn = new TableColumn<>("Taxes £:");
        taxesColumn.setMinWidth(50);
        taxesColumn.setCellValueFactory(new PropertyValueFactory<>("taxes"));
        //blankType
        TableColumn<Sale, Integer> blankTypeColumn = new TableColumn<>("Blank type");
        blankTypeColumn.setMinWidth(50);
        blankTypeColumn.setCellValueFactory(new PropertyValueFactory<>("blankType"));
        //blankID
        TableColumn<Sale, String> blankIDColumn = new TableColumn<>("blankID");
        blankIDColumn.setMinWidth(50);
        blankIDColumn.setCellValueFactory(new PropertyValueFactory<>("blankID"));
        //customerID
        TableColumn<Sale, Integer> customerIDColumn = new TableColumn<>("customer ID");
        customerIDColumn.setMinWidth(50);
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        //employeeID
        TableColumn<Sale, Integer> employeeIDcolumn = new TableColumn<>("employeeID");
        employeeIDcolumn.setMinWidth(50);
        employeeIDcolumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        String searchQuery = null;
        if(ui.getProfile().getRole().equals("Travel advisor")){
            searchQuery = "SELECT s.type,s.price,s.taxes,s.blankType,s.blankIDSale,s.customerIDSale,s.employeeIDSale FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND s.blankType = b.Type\n" +
                    "AND (b.BlankStatus = 'Valid' OR b.BlankStatus = 'Sold')" +
                    "AND employeeIDSale = "+ui.getProfile().getEmployeeID()+"" +
                    "  AND latePaymentStatus != 'Failed To Pay' ";
        }else{
            searchQuery = "SELECT s.type,s.price,s.taxes,s.blankType,s.blankIDSale,s.customerIDSale,s.employeeIDSale FROM sales s, blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND s.blankType = b.Type\n" +
                    "AND (b.BlankStatus = 'Valid' OR b.BlankStatus = 'Sold' OR b.BlankStatus = 'Refunded')" +
                    " AND latePaymentStatus != 'Failed To Pay' ";
        }

        saleTable = new TableView<>();
        saleTable.setMaxSize(650,300);
        saleTable.setItems(SoldDatabaseFunctions.getSoldInformation(searchQuery));
        saleTable.getColumns().addAll(typeColumn,priceColumn,taxesColumn,blankTypeColumn,blankIDColumn,customerIDColumn,employeeIDcolumn);

        //Coupon tables
        TableView<Coupon> couponTable;

        //price
        TableColumn<Coupon, String> couponType = new TableColumn<>("Coupon type");
        couponType.setMinWidth(50);
        couponType.setCellValueFactory(new PropertyValueFactory<>("couponType"));
        //taxes
        TableColumn<Coupon, String> destinationTo = new TableColumn<>("Destination to:");
        destinationTo.setMinWidth(100);
        destinationTo.setCellValueFactory(new PropertyValueFactory<>("destinationTo"));
        //blankType
        TableColumn<Coupon, String> destinationFrom = new TableColumn<>("Destinaton from");
        destinationFrom.setMinWidth(100);
        destinationFrom.setCellValueFactory(new PropertyValueFactory<>("destinationFrom"));

        couponTable = new TableView<>();
        couponTable.setMaxSize(700,300);
        couponTable.getColumns().addAll(couponType,destinationTo,destinationFrom);

        saleTable.setOnMouseClicked(e->{
            Sale sale = saleTable.getSelectionModel().getSelectedItem();
            couponTable.setItems(SoldDatabaseFunctions.getCouponInformation(sale.blankIDToInteger(),sale.getBlankType()));
        });



        center.add(saleTable,0,1,1,1);
        center.add(couponTable,1,1,1,1);
        layout.setCenter(center);

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);


        Button deleteCoupon = new Button("Delete Coupon");
        deleteCoupon.setOnAction(e->{
            Sale sale = saleTable.getSelectionModel().getSelectedItem();
            Coupon coupon =  couponTable.getSelectionModel().getSelectedItem();
            SoldDatabaseFunctions.deleteCoupon(coupon,sale);
            couponTable.setItems(SoldDatabaseFunctions.getCouponInformation(sale.blankIDToInteger(),sale.getBlankType()));
        });

        Button refund = new Button("Refund");
        refund.setOnAction(e->{
            Sale sale = saleTable.getSelectionModel().getSelectedItem();
            SoldDatabaseFunctions.changeRefunded(sale);
            String query = null;
            if(ui.getProfile().getRole().equals("Travel advisor")){
                query = "SELECT s.type,s.price,s.taxes,s.blankType,s.blankIDSale,s.customerIDSale,s.employeeIDSale FROM sales s, blanks b\n" +
                        "WHERE b.blankID = s.blankIDSale\n" +
                        "AND s.blankType = b.Type\n" +
                        "AND (b.BlankStatus = 'Valid' OR b.BlankStatus = 'Sold')" +
                        "AND employeeIDSale = "+ui.getProfile().getEmployeeID()+" " +
                    " AND latePaymentStatus != 'Failed To Pay' ";
            }else{
                query = "SELECT s.type,s.price,s.taxes,s.blankType,s.blankIDSale,s.customerIDSale,s.employeeIDSale FROM sales s, blanks b\n" +
                        "WHERE b.blankID = s.blankIDSale\n" +
                        "AND s.blankType = b.Type\n" +
                        "AND (b.BlankStatus = 'Valid' OR b.BlankStatus = 'Sold')" +
                        "  AND latePaymentStatus != 'Failed To Pay' ";
            }

            saleTable.setItems(SoldDatabaseFunctions.getSoldInformation(query));

        });





        bottom.getChildren().addAll(deleteCoupon,refund);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }
}
