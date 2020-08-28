package ButtonFunctions.MaintenanceFunctions.Employees;

import Database.EmployeeDatabaseFunctions;
import Entities.Employee;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddEmployee {

    public static void addEmployee(MainPage ui){

        GridPane center = CreateLayout.createGridPane();

        TextField employeeID = new TextField();
        employeeID.setPromptText("Employee ID:");

        TextField firstName = new TextField();
        firstName.setPromptText("First name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name: ");

        TextField email = new TextField();
        email.setPromptText("Email: ");

        PasswordField password = new PasswordField();
        password.setPromptText("Password: ");

        ComboBox role = new ComboBox();
        role.getItems().clear();
        role.getItems().addAll("Travel advisor","System Administrator", "Office Manager");
        role.setPromptText("Role:");

        Button add = new Button("Add");
        add.setOnAction(e->{
            if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||password.getText().isEmpty()||String.valueOf(role.getValue()).isEmpty()) {
                AlertWindow.showInformationAlert("Missing details","Please fill out all the required fields");
                return;
            }
            Employee employee = new Employee(firstName.getText(),
                    lastName.getText()
                    ,email.getText()
                    ,password.getText()
                    ,String.valueOf(role.getValue()));
            if(employeeID.getText().equals("")){
                employee.setEmployeeID(0);
            }else{
                employee.setEmployeeID(Integer.parseInt(employeeID.getText()));
            }

            EmployeeDatabaseFunctions.addEmployee(employee);

            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
            EmployeeFunctions.createTable(ui);
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

        center.add(employeeID,0,1,2,1);
        center.add(firstName,0,2,2,1);
        center.add(lastName,0,3,2,1);
        center.add(email,0,4,2,1);
        center.add(password,0,5,2,1);
        center.add(role,0,6,2,1);
        center.add(back,0,7,1,1);
        center.add(add,1,7,1,1);

        ui.getLayout().setCenter(center);
    }
}
