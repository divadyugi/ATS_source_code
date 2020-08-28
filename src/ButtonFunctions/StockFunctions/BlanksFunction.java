package ButtonFunctions.StockFunctions;

import Database.BlankDatabaseFunctions;
import Entities.Blank;
import UI.AlertWindow;
import UI.MainPage;
import javafx.collections.ObservableList;
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

public class BlanksFunction {

    public static void createChoices(String blankType, MainPage ui){

        Stage stage = new Stage();
        stage.setTitle("Blank functions");

        BorderPane layout = new BorderPane();

        //Top with text
        VBox top = new VBox();
        top.setPadding(new Insets(10,10,10,10));
        top.setSpacing(20);
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Blank functions");

        top.getChildren().addAll(title);

        TextField search = new TextField();
        search.setPromptText("Blank ID:");
        Button searchButton = new Button("Search");

        top.getChildren().addAll(search,searchButton);
        layout.setTop(top);

        //Center with table
        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_CENTER);

        //create table here

        TableView<Blank> blankTable;

        //ID column
        TableColumn<Blank, Integer> IDcolumn = new TableColumn<>("ID");
        IDcolumn.setMinWidth(50);
        //for this column, we say that we are going to use the ID value from our object
        IDcolumn.setCellValueFactory(new PropertyValueFactory<>("blankID"));

        //Type column
        TableColumn<Blank, Integer> TypeColumn = new TableColumn<>("Type");
        TypeColumn.setMinWidth(50);
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("blankType"));

        //Status column
        TableColumn<Blank, Integer> StatusColumn = new TableColumn<>("Status");
        StatusColumn.setMinWidth(50);
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        //receiveDate column
        TableColumn<Blank, Integer> ReceiveColumn = new TableColumn<>("Receive Date");
        ReceiveColumn.setMinWidth(50);
        ReceiveColumn.setCellValueFactory(new PropertyValueFactory<>("receiveDate"));

        //assignedDate column
        TableColumn<Blank, Integer> AssignedColumn = new TableColumn<>("Assigned Date: ");
        AssignedColumn.setMinWidth(50);

        AssignedColumn.setCellValueFactory(new PropertyValueFactory<>("assignedDate"));

        //employeeID column
        TableColumn<Blank, Integer> EmployeeIDColumn = new TableColumn<>("employee ID:");
        EmployeeIDColumn.setMinWidth(50);

        EmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));


        String searchQuery = "SELECT * FROM ats.blanks WHERE Type = "+Integer.parseInt(blankType);

        blankTable = new TableView<>();
        blankTable.setMaxSize(700  ,300);
        if(ui.getProfile().getRole().equals("Travel advisor")) {
            String singleSearchQuery = "SELECT * FROM ats.blanks WHERE Type = "+Integer.parseInt(blankType)+" AND employeesIDBlanks = "+ui.getProfile().getEmployeeID();
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(singleSearchQuery));
        }else {
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
        }
        blankTable.getColumns().addAll(IDcolumn,TypeColumn,StatusColumn,ReceiveColumn,AssignedColumn,EmployeeIDColumn);

        center.setConstraints(blankTable,0,1);

        center.getChildren().add(blankTable);
        layout.setCenter(center);
        blankTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        blankTable.setOnMouseClicked(e->{
            System.out.println(blankTable.getSelectionModel().getSelectedItems());
        });




        searchButton.setOnAction(e->{
            if(!(search.getText().equals(""))) {
                String singleSearchQuery ="SELECT * FROM ats.blanks WHERE blankID = "+Integer.parseInt(search.getText())+" AND Type = "+blankType;
                blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(singleSearchQuery));
            }else{
                blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
            }
        });

        //bottom with buttons

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button refund = new Button("Refund blank");
        refund.setOnAction(e->{
            Blank blank = blankTable.getSelectionModel().getSelectedItem();
            if(blank.getStatus().equals("Sold")||blank.getStatus().equals("Valid")) {
                BlankDatabaseFunctions.changeStatus(blank.getBlankID(),blank.getBlankType(), "Refunded");
                blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
                AlertWindow.showInformationAlert("Success!", "The blank has been successfully Refunded");
            }else{
                AlertWindow.showInformationAlert("Wrong blank", "Sorry, you can only refund blanks that have already been sold or validated");
            }
        });
        Button voidBlank = new Button("Void blank");
        voidBlank.setOnAction(e->{
            ObservableList<Blank>  blanks = blankTable.getSelectionModel().getSelectedItems();
            boolean success = false;
            for(Blank blank: blanks) {
                if (blank.getStatus().equals("Sold") || blank.getStatus().equals("Valid")) {
                    BlankDatabaseFunctions.changeStatus(blank.getBlankID(),blank.getBlankType(), "Void");
                    success = true;
                } else {
                    AlertWindow.showInformationAlert("Wrong blank", "Sorry, you can only void blanks that have already been sold or validated");
                }
            }
            if(success) {
                AlertWindow.showInformationAlert("Success!", "The blank has been successfully Voided");
            }
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));

        });
        Button validBlank = new Button("Validate blank");
        validBlank.setOnAction(e->{
            Blank blank = blankTable.getSelectionModel().getSelectedItem();
            if(blank.getStatus().equals("Sold")||blank.getStatus().equals("Valid")) {
                stage.close();
                ValidateFunction.validateBlank(blank, ui);
            }else{
                AlertWindow.showInformationAlert("Wrong blank Status", "Only blanks that have been sold can be validated");
            }
        });

        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            boolean removed = true;
            ObservableList<Blank> blanks = blankTable.getSelectionModel().getSelectedItems();
            for(Blank blank: blanks) {
                if(!BlankDatabaseFunctions.deleteBlank(blank)){
                    removed = false;
                };
            }
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
            if(removed){
                AlertWindow.showInformationAlert("Success!", "Blank successfully delete");
            }
        });




        bottom.getChildren().addAll(refund, voidBlank, validBlank, delete);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();



    }
}
