package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUI {

    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }

        if (alertType == Alert.AlertType.ERROR) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        }

        if (alertType == Alert.AlertType.INFORMATION) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        }

        if (alertType == Alert.AlertType.WARNING) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        }

        return alert;
    }

}
