package Database;

import Entities.Employee;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeDatabaseFunctions {


    public static void removeEmployee(Employee employee){
        try{
            Connection con = DatabaseConnection.getConnection();

            String statement = "DELETE FROM `ats`.`employees` WHERE (`ID` = "+employee.getEmployeeID()+");";

            PreparedStatement stm = con.prepareStatement(statement);
            stm.executeUpdate();



            stm.executeUpdate(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(Employee employee){
        try{
            PreparedStatement stm;
            Connection con = DatabaseConnection.getConnection();


            if(employee.getEmployeeID()==0) {
                stm = con.prepareStatement("INSERT INTO `ats`.`employees` " +
                        "(`firstName`, `lastName`, `email`, `password`, `role`) " +
                        "VALUES (?, ?, ?, ?, ?)");
                stm.setString(1, employee.getFirstName());
                stm.setString(2, employee.getLastName());
                stm.setString(3, employee.getEmail());
                stm.setString(4, employee.getPassword());
                stm.setString(5, employee.getRole());
            }else {
                stm = con.prepareStatement("INSERT INTO `ats`.`employees` " +
                        "(`ID`,`firstName`, `lastName`, `email`, `password`, `role`) " +
                        "VALUES (?,?, ?, ?, ?, ?)");
                stm.setInt(1,employee.getEmployeeID());
                stm.setString(2, employee.getFirstName());
                stm.setString(3, employee.getLastName());
                stm.setString(4, employee.getEmail());
                stm.setString(5, employee.getPassword());
                stm.setString(6, employee.getRole());
            }


            stm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Employee> getEmployeesInfo(String searchQuery){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try{
            Connection con = DatabaseConnection.getConnection();


            Statement stm = con.createStatement();

            ResultSet employeeDataSet = stm.executeQuery(searchQuery);

            while(employeeDataSet.next()){
                Employee employee = new Employee(
                        employeeDataSet.getString(2),
                        employeeDataSet.getString(3),
                        employeeDataSet.getString(4),
                        employeeDataSet.getString(5),
                        employeeDataSet.getString(6));
                employee.setEmployeeID(employeeDataSet.getInt(1));
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void updateEmployee(Employee employeeUpdate, Employee newEmployeeData){
        try{

            Connection con = DatabaseConnection.getConnection();


            String query = "UPDATE employees SET `firstName` = ?, `lastName` = ?, `email` = ?, `password` = ?, `role` = ? WHERE (`ID` = ?);";

            PreparedStatement stm = con.prepareStatement(query);

            stm.setString(1,newEmployeeData.getFirstName());
            stm.setString(2,newEmployeeData.getLastName());
            stm.setString(3,newEmployeeData.getEmail());
            stm.setString(4,newEmployeeData.getPassword());
            stm.setString(5,newEmployeeData.getRole());
            stm.setInt(6, employeeUpdate.getEmployeeID());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
