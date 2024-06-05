package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class UpdateVehicleKmUI implements Initializable {

    private final RegisterCheckupController controller;

    @FXML
    private TableView<Vehicle> tbVehicles;
    @FXML
    private TableColumn<Vehicle, String> tcPlateID;
    @FXML
    private TableColumn<Vehicle, String> tcBrand;
    @FXML
    private TableColumn<Vehicle, String> tcModel;
    @FXML
    private TableColumn<Vehicle, String> tcCurrentKm;
    @FXML
    private Label lblVehicleError, lblKmError;
    @FXML
    private TextField txtKm;


    public UpdateVehicleKmUI() {
        controller = new RegisterCheckupController();
    }

    private RegisterCheckupController getController() {
        return controller;
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        tcPlateID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlateId()));
        tcBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        tcModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        tcCurrentKm.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCurrentKm())));

        tbVehicles.getItems().addAll(getController().getVehicles());
    }


    private void setError(Control field, Label lblError, String message) {
        field.setStyle("-fx-border-color: red;");
        lblError.setText(message);
        lblError.setVisible(true);
    }

    private void clearError(Control field, Label lblError) {
        field.setStyle("-fx-border-color: none;");
        lblError.setText("");
        lblError.setVisible(false);
    }


    private boolean validateKms(String kmToUpdate) {
        try {
            Integer.parseInt(kmToUpdate);
        } catch (Exception e) {
            setError(txtKm, lblKmError, "Km must be a number.");
            return false;
        }

        if (Integer.parseInt(kmToUpdate) < 0) {
            setError(txtKm, lblKmError, "Km must be a positive number.");
            return false;
        } else if (Integer.parseInt(kmToUpdate) < tbVehicles.getSelectionModel().getSelectedItem().getCurrentKm()) {
            setError(txtKm, lblKmError, "The kms to update must be higher than current km of the vehicle.");
            return false;
        }

        clearError(txtKm, lblKmError);
        return true;
    }

    private boolean validateSelectedVehicle() {
        if (tbVehicles.getSelectionModel().getSelectedItem() == null) {
            setError(tbVehicles, lblVehicleError, "You must select a vehicle.");
            return false;
        }

        clearError(tbVehicles, lblVehicleError);
        return true;
    }

    @FXML
    private void btnUpdateKm() {
        try {

            try {

                Vehicle selectedVehicle = tbVehicles.getSelectionModel().getSelectedItem();

                if (validateSelectedVehicle()) {
                    String newKms = txtKm.getText().trim();
                    if (validateKms(newKms)) {

                        if (controller.updateVehicleKms(selectedVehicle.getPlateId().trim(), Integer.parseInt(newKms)).isPresent()) {

                            updateTableView();

                            AlertUI.createAlert(Alert.AlertType.INFORMATION, "Update vehicle kms", "Confirmation of operation", "Vehicle new successfully updated").show();
                            clearError(txtKm,lblKmError);
                            clearError(tbVehicles,lblVehicleError);


                        } else {
                            AlertUI.createAlert(Alert.AlertType.ERROR, "Update vehicle kms", "Error occurred", "WAs not possible to update kms").show();
                            clearError(txtKm,lblKmError);
                            clearError(tbVehicles,lblVehicleError);
                        }

                    }

                }
            } catch (IllegalArgumentException ie) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Add new entry to agenda", ie.getMessage()).show();
            }


        } catch (IllegalArgumentException ie) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Add new entry to agenda", ie.getMessage()).show();
        }
    }

        private void updateTableView() {
            tbVehicles.getItems().clear();
            tbVehicles.getItems().addAll(getController().getVehicles());

        }

        @FXML
        private void btnClearAction () {
            tbVehicles.getSelectionModel().clearSelection();
            clearError(tbVehicles, lblVehicleError);

            clearError(txtKm, lblKmError);
            txtKm.clear();

        }

    }
