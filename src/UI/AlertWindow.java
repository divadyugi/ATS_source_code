package UI;

import javafx.scene.control.Alert;

public class AlertWindow {

    //creates a pop-up window with a message

    public static void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
