package ButtonFunctions.StockFunctions;

import Database.BlankDatabaseFunctions;
import Database.EmployeeDatabaseFunctions;
import Entities.Blank;
import Entities.Employee;
import UI.AlertWindow;
import UI.CreateLayout;
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

public class ReAllocateFunction {

    public static void createReallocationTable(String employeeID, MainPage ui){
        Stage stage = new Stage();
        stage.setTitle("Reallocation");

        BorderPane layout = new BorderPane();

        //Top with welcome text;
        GridPane top = new GridPane();
        top.setPadding(new Insets(10,10,10,10));
        top.setAlignment(Pos.BOTTOM_CENTER);

        Label title = new Label("Blank functions");


        TextField search = new TextField();
        search.setPromptText("Blank ID:");
        ComboBox type = new ComboBox();
        type.setPromptText("Blank Type");
        type.getItems().clear();
        type.getItems().addAll("444","440","420","201","101","451","452");
        Button searchButton = new Button("Search");

        top.add(title,0,0,2,1);
        top.add(search,1,1,2,1);
        top.add(type, 0,1,1,1);
        top.add(searchButton,1,2,1,1);
        layout.setTop(top);

        //Center with two tables

        GridPane center = CreateLayout.createGridPane();


        //create table1 with employeeID's blanks
        TableView<Blank> blankTable;

        //ID column
        TableColumn<Blank, Integer> IDcolumn = new TableColumn<>("ID");
        IDcolumn.setMinWidth(50);
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

        String searchQuery = "SELECT * FROM ats.blanks WHERE employeesIDBlanks = "+employeeID;

        blankTable = new TableView<>();
        blankTable.setMaxSize(700, 300);
        blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
        blankTable.getColumns().addAll(IDcolumn,TypeColumn,StatusColumn,ReceiveColumn,AssignedColumn,EmployeeIDColumn);
        blankTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //create table2 with all employee IDs;
        TableView<Employee> employeeTable;

        //ID column
        TableColumn<Employee, Integer> employeesIDcolumn = new TableColumn<>("employee ID");
        employeesIDcolumn.setMinWidth(50);
        employeesIDcolumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        //firstName column
        TableColumn<Employee, String> employeeFirstName = new TableColumn<>("First Name");
        employeeFirstName.setMinWidth(50);
        employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //lastName column
        TableColumn<Employee, String> employeeLastName = new TableColumn<>("Last name");
        employeeLastName.setMinWidth(50);
        employeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //email column
        TableColumn<Employee, String> employeeEmail = new TableColumn<>("email");
        employeeEmail.setMinWidth(50);
        employeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        //password column
        TableColumn<Employee, String> employeePassword = new TableColumn<>("password");
        employeePassword.setMinWidth(50);
        employeePassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        //role column
        TableColumn<Employee, String> employeeRole = new TableColumn<>("role");
        employeeRole.setMinWidth(50);
        employeeRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        String employeeSearchQuery = "SELECT * FROM employees WHERE ID !="+employeeID;

        employeeTable = new TableView<>();
        employeeTable.setMaxSize(700,300);
        employeeTable.setItems(EmployeeDatabaseFunctions.getEmployeesInfo(employeeSearchQuery));
        employeeTable.getColumns().addAll(employeesIDcolumn,employeeFirstName,employeeLastName,employeeEmail,employeePassword,employeeRole);

        center.add(blankTable,0,1,1,1);
        center.add(employeeTable,1,1,1,1);
        layout.setCenter(center);


        //bottom with buttons
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button reAllocate = new Button("Reallocate blanks");
        reAllocate.setOnAction(e->{
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            ObservableList<Blank> blanks = blankTable.getSelectionModel().getSelectedItems();

            for(Blank blank: blanks) {
                if (blank.getStatus().equals("Assigned")) {
                    if (!employee.getRole().equals("System Administrator")) {
                        BlankDatabaseFunctions.reAssignBlank(blank, employee);


                    } else {
                        AlertWindow.showInformationAlert("Wrong employee role", "Please select a travel advisor or an office manager to allocate the blanks to");
                        return;
                    }
                } else {
                    AlertWindow.showInformationAlert("Wrong blank status", "Please select a blank that has not been sold, only assigned blanks can be reallocated");
                    return;
                }
            }
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
            AlertWindow.showInformationAlert("Success!", "The blanks  have been reallocated to: " + employee.getEmployeeID());
        });

        searchButton.setOnAction(e-> {
            if (!(search.getText().equals(""))) {
                if(!(type.getValue() == null)) {
                    String singleSearchQuery = "SELECT * FROM ats.blanks WHERE blankID = " + Integer.parseInt(search.getText())+" AND Type = "+Integer.parseInt(type.getValue().toString())+" AND employeesIDBlanks = "+employeeID;
                    blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(singleSearchQuery));
                }else {
                    String singleSearchQuery = "SELECT * FROM ats.blanks WHERE blankID = " + Integer.parseInt(search.getText())+" AND employeesIDBlanks = "+employeeID;;
                    blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(singleSearchQuery));
                }
            } else if (!(type.getValue() == null)) {
                String singleSearchQuery = "SELECT * FROM ats.blanks WHERE Type = " + Integer.parseInt(type.getValue().toString())+" AND employeesIDBlanks = "+employeeID;;
                blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(singleSearchQuery));
            }else{
                    blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
                }
        });

        Button deAllocate = new Button("deAllocate");

        deAllocate.setOnAction(e->{
            //removes the blanks from the employee, and changes them to be received, will also  update the table, removing them from the table
            ObservableList<Blank> blanks = blankTable.getSelectionModel().getSelectedItems();

            for(Blank blank: blanks) {
                if (blank.getStatus().equals("Assigned")) {
                    BlankDatabaseFunctions.deAllocate(blank);
                    BlankDatabaseFunctions.changeStatus(blank.getBlankID(),blank.getBlankType(),"Received");
                } else {
                    AlertWindow.showInformationAlert("Wrong blank status", "Please select a blank that has not been sold, only assigned blanks can be reallocated");
                    return;
                }
                }
            blankTable.setItems(BlankDatabaseFunctions.getBlankInformation(searchQuery));
            AlertWindow.showInformationAlert("Success!", "The blanks  have been deallocated");
        });

        bottom.getChildren().addAll(reAllocate, deAllocate);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
