package Database;

import Entities.Employee;
import UI.AlertWindow;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabaseFunction {

    public static boolean loginFunction(int ID, String password) {
        try {
            Connection con = DatabaseConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEES WHERE password = " + '"' + password + '"');
            while (rs.next()) {
                int name = rs.getInt("ID");
                if (ID == name) {
                    return true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        AlertWindow.showInformationAlert("Wrong details","Please make sure that the ID and the password are both correct!");
        return false;
    }

    public static Employee loggedInProfile(int ID, String password){

        try {
            Connection con = DatabaseConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEES WHERE password = " + '"' + password + '"'+"AND ID = "+ID);
            while (rs.next()) {
                Employee employee = new Employee(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                employee.setEmployeeID(rs.getInt(1));
                return employee;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
