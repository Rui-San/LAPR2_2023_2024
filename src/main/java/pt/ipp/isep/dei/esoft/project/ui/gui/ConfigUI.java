package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCheckupController;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.io.*;

public class ConfigUI implements Initializable {

    @FXML
    private ComboBox cbSortingAlgorithm, cbEmailService;
    @FXML
    private TextField txtUsername;

    public ConfigUI() {

    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        cbSortingAlgorithm.getItems().addAll(ApplicationSession.SORTING_ALGORITHMS_OPTIONS);
        cbSortingAlgorithm.getSelectionModel().select(ApplicationSession.getInstance().getProperties().get(ApplicationSession.SORTING_ALGORITHM));
        ApplicationSession.EMAIL_SERVICES_OPTIONS.forEach((k, v) -> cbEmailService.getItems().add(v));
        cbEmailService.getSelectionModel().select(ApplicationSession.EMAIL_SERVICES_OPTIONS.get(ApplicationSession.getInstance().getProperties().getProperty(ApplicationSession.EMAIL_SERVICE)));
        txtUsername.setText(ApplicationSession.getInstance().getProperties().get(ApplicationSession.EMAIL_NOREPLY).toString());

    }



    @FXML
    private void changeSorting() throws FileNotFoundException {
        ApplicationSession.getInstance().getProperties().setProperty(ApplicationSession.SORTING_ALGORITHM, cbSortingAlgorithm.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void changeService(){
        ApplicationSession.EMAIL_SERVICES_OPTIONS.forEach((k, v) -> {
            if(v.equals(cbEmailService.getSelectionModel().getSelectedItem())){
                ApplicationSession.getInstance().getProperties().setProperty(ApplicationSession.EMAIL_SERVICE, k);
            }
        });
    }


}