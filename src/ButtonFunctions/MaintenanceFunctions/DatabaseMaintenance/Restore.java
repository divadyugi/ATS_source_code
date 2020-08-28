package ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance;

import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;

public class Restore {

    private static String locationPath = null;

    public static void createPage(MainPage ui){
        ui.getCenter().getChildren().clear();

        Button location = new Button("Location");
        location.setOnAction(e->{
            locationPath = saveFile(ui);
        });

        Button generate = new Button("Restore");
        generate.setOnAction(e->{
            if(!(locationPath==null)) {
                restoreDatabase(locationPath);
            }else{
                AlertWindow.showInformationAlert("Missing file","Please select a file to restore the database from");
            }});

        ui.getCenter().getChildren().addAll(location,generate);
    }

    public static void createNewPage(){
        Stage stage = new Stage();
        GridPane center = CreateLayout.createGridPane();

        center.setAlignment(Pos.CENTER);

        Label tag = new Label("Restore Database");
        Button location = new Button("Location");
        location.setOnAction(e->{
            locationPath = saveFileNew(stage);
        });

        Button generate = new Button("Restore");
        generate.setOnAction(e->{
            if(!(locationPath==null)) {
                restoreDatabase(locationPath);
            }else{
                AlertWindow.showInformationAlert("Missing file","Please select a file to restore the database from");
            }});

        center.add(tag,0,0,2,1);
        center.add(location,0,1,2,1);
        center.add(generate,0,2,2,1);

        Scene scene = new Scene(center);
        stage.setScene(scene);
        stage.show();

    }

    public static String saveFileNew(Stage ui){
        java.sql.Date fileDate = new java.sql.Date(new java.util.Date().getTime());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\Backup"));

        //to open up a file dialog, we need an access to the main window
        Window stage = ui.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName("Backup -"+fileDate);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Sql Database", "*.sql"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
            // fileChooser.setInitialDirectory(file.getParentFile());
            //TODO actually save file
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String saveFile(MainPage ui){
        java.sql.Date fileDate = new java.sql.Date(new java.util.Date().getTime());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src\\Backup"));

        //to open up a file dialog, we need an access to the main window
        Window stage = ui.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");

        fileChooser.setInitialFileName("Backup -"+fileDate);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Sql Database", "*.sql"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
            // fileChooser.setInitialDirectory(file.getParentFile());
            //TODO actually save file
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void restoreDatabase(String locationPath){

        try {

            File file = new File("src\\DatabaseInformation.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> connectionName = new ArrayList<>();
            String temp;
            while ((temp = reader.readLine()) != null) {
                connectionName.add(temp);
            }

            CodeSource codeSource = MainPage.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            String dbName = "ats";
            String dbUser = connectionName.get(2);
            String dbPassword = connectionName.get(3);


            String restorePath =locationPath;

            String[] executeCmd = new String[]{connectionName.get(4), dbName, "-u" + dbUser, "-p" + dbPassword, "-e", " source " + restorePath};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                AlertWindow.showInformationAlert("Success", "Successfully restored database");
            } else {
                AlertWindow.showInformationAlert("UnsuccessFul!", "Unable to restore database, please make sure to select the correct file");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }


}
