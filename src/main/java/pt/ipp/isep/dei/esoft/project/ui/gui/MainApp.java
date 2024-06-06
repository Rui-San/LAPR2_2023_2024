package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;

import java.io.IOException;

public class MainApp extends Application {
    public static final String APP_TITLE = "Login";

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
            scene.getStylesheets().add(css);


            Image image = new Image("file:MS_logo.png");
            stage.getIcons().add(image);

            stage.setTitle(APP_TITLE);
            stage.setScene(scene);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override
                public void handle(WindowEvent event) {
                    Alert closeAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, APP_TITLE,
                            "Confirm action.", "Do you really wish to quit the application?");
                    if (closeAlert.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            stage.show();
        } catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, APP_TITLE,
                    "There was a problem running the application.", ex.getMessage()).show();
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}