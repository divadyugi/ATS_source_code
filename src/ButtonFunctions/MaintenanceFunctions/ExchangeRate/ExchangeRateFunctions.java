package ButtonFunctions.MaintenanceFunctions.ExchangeRate;

import ButtonFunctions.MaintenanceFunctions.ExchangeRate.AddExchangeRates;
import Database.ExchangeRateDatabaseFunctions;
import Entities.ExchangeRate;
import UI.AlertWindow;
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

import java.util.Date;

public class ExchangeRateFunctions {

        public static void createTable(MainPage ui){
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

            TableView<ExchangeRate> exchangeRateTable;

            //ID column
            TableColumn<ExchangeRate, Integer> IDColumn = new TableColumn<>("ID");
            IDColumn.setMinWidth(50);
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            //start Date column
            TableColumn<ExchangeRate, Date> startDateColumn = new TableColumn<>("start Date");
            startDateColumn.setMinWidth(100);
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

            //endDate column
            TableColumn<ExchangeRate, Date> endDateColumn = new TableColumn<>("end Date");
            endDateColumn.setMinWidth(100);
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

            //amount column
            TableColumn<ExchangeRate, Float> amountColumn = new TableColumn<>("Exchange amount");
            amountColumn.setMinWidth(100);
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("exchangeAmount"));

            exchangeRateTable = new TableView<>();
            exchangeRateTable.setMinSize(375,300);
            exchangeRateTable.setItems(ExchangeRateDatabaseFunctions.getExchangeInformation());
            exchangeRateTable.getColumns().addAll(IDColumn,startDateColumn,endDateColumn,amountColumn);

            center.add(exchangeRateTable,0,0,1,1);
            layout.setCenter(center);

            //Bottom with buttons

            HBox bottom = new HBox();
            bottom.setPadding(new Insets(10, 10, 10, 10));
            bottom.setSpacing(20);

            Button add = new Button("Add");
            add.setOnAction(e->{
                stage.close();
                AddExchangeRates.addExchangeRate(ui);
            });

            Button delete = new Button("Delete");
            delete.setOnAction(e->{
                ExchangeRate exchangeRate = exchangeRateTable.getSelectionModel().getSelectedItem();
                ExchangeRateDatabaseFunctions.removeExchangeRate(exchangeRate);
                exchangeRateTable.setItems(ExchangeRateDatabaseFunctions.getExchangeInformation());
            });

            Button close = new Button("End exchange Rate");
            close.setOnAction(e->{
                ExchangeRate exchangeRate =exchangeRateTable.getSelectionModel().getSelectedItem();
                if(exchangeRate.getEndDate()==null) {
                    ExchangeRateDatabaseFunctions.endExchangeRate(exchangeRate);
                    exchangeRateTable.setItems(ExchangeRateDatabaseFunctions.getExchangeInformation());
                    AlertWindow.showInformationAlert("Success!", "ExchangeRate successfully finished");
                }else{
                    AlertWindow.showInformationAlert("Wrong exchange rate", "This exchange rate has already been closed, please select a different exchange rate");
                }
            });

            bottom.getChildren().addAll(add,delete,close);
            bottom.setAlignment(Pos.BOTTOM_CENTER);

            layout.setBottom(bottom);

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();


        }


}
