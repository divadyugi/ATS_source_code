package ButtonFunctions.MaintenanceFunctions.Employees;

import ButtonFunctions.MaintenanceFunctions.Employees.EmployeeFunctions;
import Database.EmployeeDatabaseFunctions;
import Entities.Employee;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UpdateEmployee {

    public static void updateEmployee(MainPage ui, Employee employee){

        GridPane center = CreateLayout.createGridPane();

        TextField firstName = new TextField(employee.getFirstName());
        firstName.setPromptText("First name");

        TextField lastName = new TextField(employee.getLastName());
        lastName.setPromptText("Last name: ");

        TextField email = new TextField(employee.getEmail());
        email.setPromptText("Email: ");

        PasswordField password = new PasswordField();
        password.setPromptText("Password: ");
        password.setText(employee.getPassword());

        ComboBox role = new ComboBox();
        role.getItems().clear();
        role.getItems().addAll("Travel advisor","System Administrator", "Office Manager");
        role.setPromptText("Role:");

        role.setValue(employee.getRole());

        Button add = new Button("Update");
        add.setOnAction(e->{
            Employee newEmployee = new Employee(firstName.getText(),
                    lastName.getText()
                    ,email.getText()
                    ,password.getText()
                    ,String.valueOf(role.getValue()));

            EmployeeDatabaseFunctions.updateEmployee(employee, newEmployee);

            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
            EmployeeFunctions.createTable(ui);
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });

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
