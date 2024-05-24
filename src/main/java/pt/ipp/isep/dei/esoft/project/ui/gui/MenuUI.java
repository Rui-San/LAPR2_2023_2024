package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.authorization.AuthenticationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuUI implements Initializable {

    @FXML
    private StackPane contentArea;

    private Stage loginPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            loginPage = new Stage();
            loginPage.initModality(Modality.APPLICATION_MODAL);
            loginPage.setTitle("Programa");
            loginPage.setResizable(false);
            loginPage.setScene(scene);

        } catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Erro.", ex.getMessage());
        }
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
    public void RegisterCheckup() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterCheckupScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterJob() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterJobScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void AssignSkill() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AssignSkillScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void GenerateTeamProposal() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/GenerateTeamProposalScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void NewEntryToDo() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AddNewEntryToDo.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void NewEntryAgenda() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AddNewEntryAgendaScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void logoutAction() throws IOException {
        Alert logoutAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE, "Logout", "Are you sure you want to logout?");
        if(logoutAlert.showAndWait().get().getText().equals("OK")){
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.close();
            setLoginPage();
            loginPage.show();
        }
    }



}
