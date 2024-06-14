package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.tools.TextManipulator;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuUI implements Initializable {

    @FXML
    private StackPane contentArea;
    @FXML
    private Label profileItemName, profileItemRole;
    @FXML
    private VBox vbMenuButtonsHolder;
    @FXML
    private Control mbJobs, mbSkills, mbCollaborators, mbVehicles, mbTeams, mbGreenSpaces, mbTasks, mbLogout, mbMyAgenda, mbUpdateVehicleKm, mbRoutesToOpen;

    private List<Control> allOptions = new ArrayList<>();

    private Stage loginPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allOptions.addAll(List.of(mbJobs, mbSkills, mbCollaborators, mbVehicles, mbTeams, mbGreenSpaces, mbTasks, mbMyAgenda, mbUpdateVehicleKm, mbRoutesToOpen));

        UserSession session = ApplicationSession.getInstance().getCurrentSession();

        profileItemName.setText(Repositories.getInstance().getAuthenticationRepository().getAuthenticationFacade().getUser(session.getUserId().getEmail()).get().getName());
        profileItemRole.setText(TextManipulator.capitalizeWords(session.getUserRoles().get(0).getId()));

        List<Control> sessionOptions = new ArrayList<>();

        for (UserRoleDTO role : session.getUserRoles()) {
            switch (role.getId()) {
                case "HUMAN RESOURCES MANAGER":
                    sessionOptions.addAll(List.of(mbJobs, mbSkills, mbCollaborators, mbTeams));
                    break;
                case "VEHICLE FLEET MANAGER":
                    sessionOptions.add(mbVehicles);
                    break;
                case "GREEN SPACE MANAGER":
                    sessionOptions.addAll(List.of(mbGreenSpaces, mbTasks, mbRoutesToOpen));
                    break;
                case "ADMINISTRATOR":
                    sessionOptions.addAll(allOptions);
                    sessionOptions.remove(mbMyAgenda);
                    break;
                case "COLLABORATOR":
                    sessionOptions.addAll(List.of(mbMyAgenda, mbUpdateVehicleKm));
                    break;
            }

        }

        for (Control option : allOptions) {
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
            loginPage.setTitle("Login");
            loginPage.setResizable(false);
            Image image = new Image("file:MS_logo.png");
            loginPage.getIcons().add(image);
            loginPage.setScene(scene);

        } catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error.", ex.getMessage());
        }
    }

    public void setMenuSelected(Control selected) {
        for (Control option : allOptions) {
            if (option.equals(selected)) {
                if(!option.getStyleClass().contains("menu-btn-selected")){
                    option.getStyleClass().add("menu-btn-selected");
                }
            } else {
                option.getStyleClass().remove("menu-btn-selected");
            }
        }
    }

    @FXML
    public void RegisterCollaborator() throws IOException {
        setMenuSelected(mbCollaborators);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterCollaboratorScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void ListCollaborators() throws IOException {
        setMenuSelected(mbCollaborators);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ListCollaboratorsScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterGreenSpace() throws IOException {
        setMenuSelected(mbGreenSpaces);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterGreenSpaceScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void ListGreenSpaces() throws IOException {
        setMenuSelected(mbGreenSpaces);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ListGreenSpacesScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void ListVehiclesNeedingCheckup() throws IOException {
        setMenuSelected(mbVehicles);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ListVehiclesNeedingCheckupScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RoutesToOpen() throws IOException {
        setMenuSelected(mbRoutesToOpen);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ObtainRoutesScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void ListCollaboratorTasks() throws IOException {
        setMenuSelected(mbMyAgenda);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ListCollaboratorTasksScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterSkill() throws IOException {
        setMenuSelected(mbSkills);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterSkillScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterVehicle() throws IOException {
        setMenuSelected(mbVehicles);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterVehicleScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterCheckup() throws IOException {
        setMenuSelected(mbVehicles);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterCheckupScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void RegisterJob() throws IOException {
        setMenuSelected(mbJobs);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/RegisterJobScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void AssignSkill() throws IOException {
        setMenuSelected(mbCollaborators);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AssignSkillScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void GenerateTeamProposal() throws IOException {
        setMenuSelected(mbTeams);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/GenerateTeamProposalScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void NewEntryToDo() throws IOException {
        setMenuSelected(mbTasks);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AddNewEntryToDo.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void NewEntryAgenda() throws IOException {
        setMenuSelected(mbTasks);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AddNewEntryAgendaScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void OpenAgenda() throws IOException {
        setMenuSelected(mbTasks);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AgendaScene.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void UpdateVehicleKm() throws IOException {
        setMenuSelected(mbUpdateVehicleKm);
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/UpdateVehicleKmScene.fxml"));
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

    @FXML
    public void openConfig() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/ConfigUI.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void logout(){
        Stage stage = (Stage) contentArea.getScene().getWindow();
        ApplicationSession.getInstance().getCurrentSession().doLogout();
        stage.close();
        setLoginPage();
        loginPage.show();
    }



}
