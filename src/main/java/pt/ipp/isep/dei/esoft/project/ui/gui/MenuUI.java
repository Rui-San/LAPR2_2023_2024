package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuUI implements Initializable {

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void RegisterCollaborator() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterCollaboratorScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterGreenSpace() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterGreenSpaceScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void ListVehiclesNeedingCheckup() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ListVehiclesNeedingCheckupScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterSkill() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterSkillScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterVehicle() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterVehicleScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterJob() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterJobScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

}
