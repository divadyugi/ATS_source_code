package ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance;

import UI.AlertWindow;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class Backup {

    private static String locationPath = null;

    public static void createPage(MainPage ui){
        ui.getCenter().getChildren().clear();

        Button location = new Button("Location");
        location.setOnAction(e->{
            locationPath = saveFile(ui);
        });

        Button generate = new Button("Generate");
        generate.setOnAction(e-> {
            if(locationPath == null) {
                AlertWindow.showInformationAlert("No location selected","Please select a location");
            }else{
                backupDatabase(locationPath);
                AlertWindow.showInformationAlert("Success!","The backup has been successfully made");
            }
        });

        ui.getCenter().getChildren().addAll(location,generate);
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
            File file = fileChooser.showSaveDialog(stage);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
            // fileChooser.setInitialDirectory(file.getParentFile());
            //TODO actually save file
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void backupDatabase(String locationPath){
        try{
            CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());

            String dbName = "ats";
            String dbUser = "root";
            String dbPassword = "admin";


            String savePath = "\""+locationPath+"\"";

            String executeCmd = "C:\\mysql-8.0.17-winx64\\bin\\mysqldump -u" + dbUser + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + savePath;

            Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
