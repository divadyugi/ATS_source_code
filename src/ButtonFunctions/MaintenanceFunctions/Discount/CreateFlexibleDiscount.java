package ButtonFunctions.MaintenanceFunctions.Discount;

import Database.FlexibleDiscountDatabaseFunctions;
import Database.FlexibleRangeDatabaseFunctions;
import Entities.Discount;
import Entities.FlexibleDiscount;
import Entities.FlexibleRange;
import UI.AlertWindow;
import UI.CreateLayout;
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

public class CreateFlexibleDiscount {

    public static void createFlexibleDiscount(Discount discount){

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
        center.setAlignment(Pos.CENTER);

        //table with all the ranges
        TableView<FlexibleRange> rangeTable;

        //ID column
        TableColumn<FlexibleRange, Integer> rangeIDColumn = new TableColumn<>("ID");
        rangeIDColumn.setMinWidth(100);
        rangeIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        //from column
        TableColumn<FlexibleRange, Integer> rangeFromColumn = new TableColumn<>("amount From:");
        rangeFromColumn.setMinWidth(100);
        rangeFromColumn.setCellValueFactory(new PropertyValueFactory<>("amountFrom"));

        //to column
        TableColumn<FlexibleRange, Integer> rangeToColumn = new TableColumn<>("amount To:");
        rangeToColumn.setMinWidth(100);
        rangeToColumn.setCellValueFactory(new PropertyValueFactory<>("amountTo"));

        rangeTable = new TableView<>();
        rangeTable.setMinSize(300,300);
        rangeTable.setItems(FlexibleRangeDatabaseFunctions.getFlexibleRangeInformation());
        rangeTable.getColumns().addAll(rangeIDColumn,rangeFromColumn,rangeToColumn);

        //table with all the ranges of the current flexible discount
        TableView<FlexibleDiscount> discountTable;

        //ID column
        TableColumn<FlexibleDiscount, Integer> flexibleIDColumn = new TableColumn<>("ID");
        flexibleIDColumn.setMinWidth(100);
        flexibleIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        //from column
        TableColumn<FlexibleDiscount, Float> flexibleFromColumn = new TableColumn<>("Amount From:");
        flexibleFromColumn.setMinWidth(100);
        flexibleFromColumn.setCellValueFactory(new PropertyValueFactory<>("amountFrom"));

        //to column
        TableColumn<FlexibleDiscount, Float> flexibleToColumn = new TableColumn<>("amount To:");
        flexibleToColumn.setMinWidth(100);
        flexibleToColumn.setCellValueFactory(new PropertyValueFactory<>("amountTo"));

        //amount column
        TableColumn<FlexibleDiscount, Float> flexibleAmountColumn = new TableColumn<>("discount Amount");
        flexibleAmountColumn.setMinWidth(100);
        flexibleAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        discountTable = new TableView<>();
        discountTable.setMinSize(300,300);
        discountTable.setItems(FlexibleDiscountDatabaseFunctions.getFlexibleDiscountInformation(discount));
        discountTable.getColumns().addAll(flexibleIDColumn,flexibleFromColumn,flexibleToColumn,flexibleAmountColumn);

        //Buttons and textfields between the two tables

        TextField amount = new TextField();
        amount.setPromptText("Discount amount: ");

        Button addToDiscount = new Button("Add to discount");
        center.setMargin(addToDiscount,new Insets(0,0,30,0));
        addToDiscount.setOnAction(e->{
            FlexibleRange flexibleRange = rangeTable.getSelectionModel().getSelectedItem();
            if(!(amount.getText().isEmpty())) {
                FlexibleDiscount flexibleDiscount = new FlexibleDiscount(flexibleRange, Float.parseFloat(amount.getText()), discount.getDiscountID());
                if (!(flexibleRange == null)) {
                    FlexibleDiscountDatabaseFunctions.addFlexibleDiscount(flexibleDiscount);
                    discountTable.setItems(FlexibleDiscountDatabaseFunctions.getFlexibleDiscountInformation(discount));
                    amount.setText(null);
                } else {
                    AlertWindow.showInformationAlert("No range", "Please select a range for the discount");
                }
            }else{
                AlertWindow.showInformationAlert("No amount","Please eneter the amount of discount the customer gets for the selected range");
            }
        });

        TextField from = new TextField();
        from.setPromptText("Amount from: ");

        TextField to = new TextField();
        to.setPromptText("Amount To:");

        Button addToRange = new Button("Add to range");
        addToRange.setOnAction(e->{
            if(!from.getText().isEmpty()) {
                if (to.getText().equals("")) {
                    FlexibleRangeDatabaseFunctions.addRange(Float.parseFloat(from.getText()));
                } else if (Float.parseFloat(to.getText()) > Float.parseFloat(from.getText())) {
                    FlexibleRangeDatabaseFunctions.addRange(Float.parseFloat(from.getText()), Float.parseFloat(to.getText()));
                }
                rangeTable.setItems(FlexibleRangeDatabaseFunctions.getFlexibleRangeInformation());
                from.setText(null);
                to.setText(null);
            }else{
                AlertWindow.showInformationAlert("Missing fields","Please enter the value for where you want the range to start from");
            }
        });


        Button deleteDiscount = new Button("Delete from Discount");
        center.setMargin(deleteDiscount,new Insets(0,0,30,0));
        deleteDiscount.setOnAction(e->{
            FlexibleDiscount flexibleDiscount = discountTable.getSelectionModel().getSelectedItem();
            FlexibleDiscountDatabaseFunctions.deleteDiscount(flexibleDiscount);
            discountTable.setItems(FlexibleDiscountDatabaseFunctions.getFlexibleDiscountInformation(discount));
        });


        center.add(rangeTable,0,1,1,7);
        center.add(amount,1,1,2,1);
        center.add(addToDiscount,1,2,1,1);
        center.add(deleteDiscount,2,2,1,1);
        center.add(from,1,4,2,1);
        center.add(to,1,5,2,1);
        center.add(addToRange,1,6,2,1);
        center.add(discountTable,3,1, 1, 7);

        layout.setCenter(center);


        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
