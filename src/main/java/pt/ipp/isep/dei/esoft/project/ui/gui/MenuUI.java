package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.authorization.AuthenticationUI;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuUI implements Initializable {

    @FXML
    private StackPane contentArea;
    @FXML
    private VBox vbMenuButtonsHolder;
    @FXML
    private MenuButton mbJobs, mbSkills, mbCollaborators, mbVehicles, mbTeams, mbGreenSpaces, mbTasks, mbAdmin;

    private List<MenuButton> allOptions = new ArrayList<>();

    private Stage loginPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allOptions.addAll(List.of(mbJobs, mbSkills, mbCollaborators, mbVehicles, mbTeams, mbGreenSpaces, mbTasks, mbAdmin));

        List<UserRoleDTO> sessionRoles = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserRoles();

        List<MenuButton> sessionOptions = new ArrayList<>();

        for (UserRoleDTO role : sessionRoles) {
            switch (role.getId()) {
                case "HUMAN RESOURCES MANAGER":
                    sessionOptions.addAll(List.of(mbJobs, mbSkills, mbCollaborators, mbTeams));
                    break;
                case "VEHICLE FLEET MANAGER":
                    sessionOptions.addAll(List.of(mbVehicles));
                    break;
                case "GREEN SPACE MANAGER":
                    sessionOptions.addAll(List.of(mbGreenSpaces, mbTasks));
                    break;
                case "ADMIN":
                    sessionOptions.addAll(List.of(mbAdmin));
                    break;
            }
        }

        for (MenuButton option : allOptions) {
            if (!sessionOptions.contains(option)) {
                vbMenuButtonsHolder.getChildren().remove(option);
            }
        }


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
            logout();
        }
    }

    public void logout(){
        Stage stage = (Stage) contentArea.getScene().getWindow();
        stage.close();
        setLoginPage();
        loginPage.show();
    }



}
