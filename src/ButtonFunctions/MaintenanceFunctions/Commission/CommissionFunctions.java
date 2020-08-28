package ButtonFunctions.MaintenanceFunctions.Commission;

import Database.CommissionDatabaseFunctions;
import Entities.Commission;
import UI.AlertWindow;
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

import java.util.Date;

public class CommissionFunctions {

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

        GridPane center = new GridPane();

        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        //creates a table with all the commissions
        TableView<Commission> commissionTable;

        //ID column
        TableColumn<Commission, Integer> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(50);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("commissionID"));

        //amount column
        TableColumn<Commission, Float> amountColumn = new TableColumn<>("Amount:");
        amountColumn.setMinWidth(50);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        //startDate column
        TableColumn<Commission, Date> startDateColumn = new TableColumn<>("Start date: ");
        startDateColumn.setMinWidth(100);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("commissionDate"));

        //endDate column
        TableColumn<Commission, Date> endDateColumn = new TableColumn<>("End Date: ");
        endDateColumn.setMinWidth(100);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("commissionEndDate"));

        //BlankType column
        TableColumn<Commission, Integer> blankTypeColumn = new TableColumn<>("Blank Type:");
        blankTypeColumn.setMinWidth(50);
        blankTypeColumn.setCellValueFactory(new PropertyValueFactory<>("blankType"));


        commissionTable = new TableView<>();
        commissionTable.setMinSize(425, 300);
        //sets the data for the table, with data retrieved from the database
        commissionTable.setItems(CommissionDatabaseFunctions.getCommissionInformation());
        commissionTable.getColumns().addAll(IDColumn, amountColumn, startDateColumn, endDateColumn, blankTypeColumn);

        center.add(commissionTable, 0, 0, 1, 1);
        layout.setCenter(center);

        //Bottom with buttons

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setSpacing(20);

        //opens a new stage to type in details of the new commissions
        Button add = new Button("Add");
        add.setOnAction(e->{
            stage.close();
            AddCommission.addCommission(ui);
        });


        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            Commission commission = commissionTable.getSelectionModel().getSelectedItem();
            CommissionDatabaseFunctions.deleteCommission(commission);
            commissionTable.setItems(CommissionDatabaseFunctions.getCommissionInformation());
            AlertWindow.showInformationAlert("Success!","Commission successfully deleted");
        });

        Button update = new Button("Update");
        update.setOnAction(e->{
            Commission commission = commissionTable.getSelectionModel().getSelectedItem();
            UpdateCommission.updateCommission(ui,commission);
            stage.close();
        });

        bottom.getChildren().addAll(add,delete,update);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
