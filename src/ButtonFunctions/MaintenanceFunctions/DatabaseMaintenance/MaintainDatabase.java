package ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance;

import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MaintainDatabase {
    private static String location;

    private static void setupFunction(){
        try {
            File file = new File("src\\DatabaseInformation.txt");
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            file.createNewFile();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setupDatabase(){
        Stage stage = new Stage();
        stage.setTitle("Setup Database");

        GridPane center = CreateLayout.createGridPane();

        TextField hostName = new TextField();
        hostName.setPromptText("Host Name:");

        TextField port = new TextField();
        port.setPromptText("Port:");

        TextField userName = new TextField();
        userName.setPromptText("userName:");

        TextField password = new TextField();
        password.setPromptText("password:");

        Button dumpLocation = new Button("MySql Dump location");
        dumpLocation.setOnAction(e->{
            location = savePath(stage);
        });

        Button back = new Button("Back");

        Button setup = new Button("Setup");
        setup.setOnAction(e->{
            if(hostName.getText().isEmpty()||port.getText().isEmpty()||userName.getText().isEmpty()||password.getText().isEmpty()){
                AlertWindow.showInformationAlert("Missing details","Please fill out all the fields");
                return;
            }
            ArrayList<String> conNames = new ArrayList<>();
            conNames.add(hostName.getText());
            conNames.add(port.getText());
            conNames.add(userName.getText());
            conNames.add(password.getText());
            conNames.add(location);
            setupFunction();
            System.out.println(conNames);
            try {
                File file = new File("src\\DatabaseInformation.txt");
                BufferedWriter writer =new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < conNames.size(); i++) {
                    writer.write(conNames.get(i));
                    writer.newLine();
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AlertWindow.showInformationAlert("Database created", "Database Created, Login with \n ID: 1 \nPassword: ChangeMe");
            Restore.restoreDatabase("F:\\programming\\ats.v4.2\\ATS.v4\\src\\Backup\\BackupBasic.sql");
        });

        center.add(hostName,0,1,2,1);
        center.add(port,0,2,2,1);
        center.add(userName,0,3,2,1);
        center.add(password,0,4,2,1);
        center.add(dumpLocation,0,5,2,1);
        center.add(back,0,6,1,1);
        center.add(setup,1,6,1,1);

        Scene scene = new Scene(center);

        stage.setScene(scene);
        stage.show();

    }

    public static String savePath(Stage ui){
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(new File("src\\Backup"));

        //to open up a file dialog, we need an access to the main window
        Window stage = ui.getScene().getWindow();
        directoryChooser.setTitle("MySQL dump");
        directoryChooser.setInitialFileName("mysql.exe");
        directoryChooser.getExtensionFilters().clear();
        directoryChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MySQL exe", "*.exe"));
        try {
            File file = directoryChooser.showOpenDialog(stage);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
            // fileChooser.setInitialDirectory(file.getParentFile());
            //TODO actually save file
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
