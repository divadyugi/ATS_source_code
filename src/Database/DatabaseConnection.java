package Database;

import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            File file = new File("src\\DatabaseInformation.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> connectionName = new ArrayList<>();
            String temp;
            while ((temp = reader.readLine()) != null) {
                connectionName.add(temp);
            }
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = "jdbc:mysql://+hostName+port+"/ats", userName, password;
            con = DriverManager.getConnection("jdbc:mysql://"+connectionName.get(0)+":"+connectionName.get(1)+"/ats", connectionName.get(2), connectionName.get(3));

            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void setupDatabase(){

    }
}
