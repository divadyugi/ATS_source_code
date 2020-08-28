package ButtonFunctions.MaintenanceFunctions.TravelAgent;

import Database.TravelAgentDatabaseFunctions;
import Entities.Agency;
import Entities.Employee;
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

public class AgentFunctions {

    public static void createTable(MainPage ui) {
        Stage stage = new Stage();

        stage.setTitle("Employee details");

        BorderPane layout = new BorderPane();

        //Top layer with welcome text
        VBox top = new VBox();
        top.setPadding(new Insets(10,10,10,10));
        top.setSpacing(20);
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Employee functions");

        top.getChildren().addAll(title);

        layout.setTop(top);


        //Center with table

        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        //create table here

        TableView<Agency> agencyTable;

        //ID
        TableColumn<Agency, Integer> IDColumn = new TableColumn<>("ID: ");
        IDColumn.setMinWidth(50);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        //name
        TableColumn<Agency, Integer> nameColumn = new TableColumn<>("Name: ");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //address
        TableColumn<Agency, Integer> addressColumn = new TableColumn<>("Address: ");
        addressColumn.setMinWidth(50);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        agencyTable = new TableView<>();
        agencyTable.setMaxSize(275,300);
        agencyTable.setItems(TravelAgentDatabaseFunctions.getAgencyInfo());
        agencyTable.getColumns().addAll(IDColumn,nameColumn,addressColumn);


        center.add(agencyTable,0,1,1,1);
        layout.setCenter(center);

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button add = new Button("Add");
        add.setOnAction(e->{
            AddAgency.addAgency(ui,"Add",null);
            stage.close();
        });
        Button delete = new Button("Delete");

        Button update = new Button("Update");
        update.setOnAction(e->{
            Agency oldAgency = agencyTable.getSelectionModel().getSelectedItem();
            AddAgency.addAgency(ui,"Update",oldAgency);
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
