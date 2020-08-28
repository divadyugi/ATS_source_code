package ButtonFunctions.MaintenanceFunctions.Employees;

import Database.EmployeeDatabaseFunctions;
import Entities.Employee;
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


public class EmployeeFunctions {

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

        String employeeSearchQuery = "SELECT * FROM employees";

        employeeTable = new TableView<>();
        employeeTable.setMinSize(550,300);
        employeeTable.setItems(EmployeeDatabaseFunctions.getEmployeesInfo(employeeSearchQuery));
        employeeTable.getColumns().addAll(employeesIDcolumn,employeeFirstName,employeeLastName,employeeEmail,employeePassword,employeeRole);

        center.add(employeeTable,0,1,1,1);
        layout.setCenter(center);

        //Bottom with buttons
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10,10,10,10));
        bottom.setSpacing(20);

        Button add = new Button("Add");
        add.setOnAction(e->{
            stage.close();
            AddEmployee.addEmployee(ui);
        });

        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            if(!(employee == null)) {
                EmployeeDatabaseFunctions.removeEmployee(employee);
                employeeTable.setItems(EmployeeDatabaseFunctions.getEmployeesInfo(employeeSearchQuery));
                AlertWindow.showInformationAlert("Success!", "Employee successfully removed");
            }else{
                AlertWindow.showInformationAlert("Employee not selected","Please select an employee");
            }
        });

        Button update = new Button("Update");
        update.setOnAction(e->{
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            if(!(employee == null)) {
                stage.close();
                UpdateEmployee.updateEmployee(ui, employee);
            }else{
                AlertWindow.showInformationAlert("Employee not selected","Please select an employee");
            }
        });

        bottom.getChildren().addAll(add,delete,update);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
