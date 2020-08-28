package ButtonFunctions.MaintenanceFunctions.Discount;

import Database.DiscountDatabaseFunctions;
import Database.FixedDiscountDatabaseFunction;
import Entities.Discount;
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

public class DiscountPlans {

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
        center.setAlignment(Pos.CENTER);

        TableView<Discount> discountTable;

        //ID column
        TableColumn<Discount, Integer> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("discountID"));

        //type column
        TableColumn<Discount,String> typeColumn = new TableColumn<>("Type: ");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        discountTable = new TableView<>();
        discountTable.setMinSize(175, 300);
        discountTable.setItems(DiscountDatabaseFunctions.getDiscountInformation());
        discountTable.getColumns().addAll(IDColumn, typeColumn);

        center.add(discountTable, 0, 0, 1, 1);
        layout.setCenter(center);

        //Bottom with buttons
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setSpacing(20);


        Button addNewPlan = new Button("Add plan");
        addNewPlan.setOnAction(e->{
            DiscountDatabaseFunctions.createDiscount();
            discountTable.setItems(DiscountDatabaseFunctions.getDiscountInformation());
        });

        Button changeFlexible = new Button("Change to flexible");
        changeFlexible.setOnAction(e->{
            Discount discount = discountTable.getSelectionModel().getSelectedItem();
            if(discount.getType().equals("null")) {
                DiscountDatabaseFunctions.updateType(discount,"Flexible");
                CreateFlexibleDiscount.createFlexibleDiscount(discount);
            }
            discountTable.setItems(DiscountDatabaseFunctions.getDiscountInformation());
        });

        Button changeFixed = new Button("Change to fixed");
        changeFixed.setOnAction(e->{
            Discount discount = discountTable.getSelectionModel().getSelectedItem();
            if(discount.getType().equals("null")){
                UpdateFixedDiscount.updateFixedDiscount(ui,discount,"New","Discount");
                stage.close();
            }else{
                System.out.println(discount.getType());
            }
            discountTable.setItems(DiscountDatabaseFunctions.getDiscountInformation());
        });



        Button delete = new Button("Delete");
        delete.setOnAction(e->{
        Discount discount = discountTable.getSelectionModel().getSelectedItem();
        if(discount.getType().equals("Fixed")){
            FixedDiscountDatabaseFunction.deleteFixedDiscountPlan(discount);
            DiscountDatabaseFunctions.deleteDiscount(discount);

        }else{
            DiscountDatabaseFunctions.deleteDiscount(discount);
        }
            discountTable.setItems(DiscountDatabaseFunctions.getDiscountInformation());
        });

        Button updatePlan = new Button("Update plan");
        updatePlan.setOnAction(e->{
            Discount discount = discountTable.getSelectionModel().getSelectedItem();
            if(discount.getType().equals("Fixed")){
                UpdateFixedDiscount.updateFixedDiscount(ui, discount, "Update","Discount");
                stage.close();
            }else if(discount.getType().equals("Flexible")){
                CreateFlexibleDiscount.createFlexibleDiscount(discount);

            }
        });

        bottom.getChildren().addAll(addNewPlan,changeFixed,changeFlexible,delete,updatePlan);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
