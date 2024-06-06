package pt.ipp.isep.dei.esoft.project.ui.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import pt.ipp.isep.dei.esoft.project.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AdminUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class AuthenticationUI implements Initializable {
    private final AuthenticationController ctrl;
    private Stage landingPage;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Text lblError;

    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLandingPage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SideMenuScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            landingPage = new Stage();
            landingPage.initModality(Modality.APPLICATION_MODAL);
            landingPage.setTitle("Musgo Sublime - Green Space Management");
            Image image = new Image("file:MS_logo.png");
            landingPage.getIcons().add(image);
            landingPage.setResizable(false);
            landingPage.setScene(scene);

        } catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Erro.", ex.getMessage());
        }
    }

    private void displayErrorLayout(Control controlObject, Text labelToShowError, String errorMessage) {
        controlObject.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLayoutErrors(Control controlObject, Text labelWithError) {
        controlObject.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    @FXML
    public void btnLoginAction() {
        boolean success = false;

        System.out.println("Username: " + txtUsername.getText().trim());
        System.out.println("Password: " + txtPassword.getText().trim());
        success = ctrl.doLogin(txtUsername.getText().trim(), txtPassword.getText().trim());
        System.out.println("Login successful: " + success);

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Error", "No roles available for this user.", "Please contact the system administrator.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    setLandingPage();
                    landingPage.show();
                    Stage currentStage = (Stage) txtUsername.getScene().getWindow();
                    currentStage.close();
                } else {
                    System.out.println("No role selected.");
                }
            }
        }else{
            displayErrorLayout(txtUsername, lblError, "Invalid username or password.");
            displayErrorLayout(txtPassword, lblError, "Invalid username or password.");
            this.logout();
        }
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI()));
        return rolesUI;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }


    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

}