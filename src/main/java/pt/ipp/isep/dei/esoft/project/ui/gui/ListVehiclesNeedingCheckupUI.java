package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.controller.ListVehiclesNeedingCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleNeedingCheckup;

import java.util.List;

public class ListVehiclesNeedingCheckupUI implements Initializable {

    private final ListVehiclesNeedingCheckupController controller;

    @FXML
    private TableView<VehicleNeedingCheckup> tbNeddingCheckup;
    @FXML
    private TableColumn<VehicleNeedingCheckup, String> plateId;
    @FXML
    private TableColumn<VehicleNeedingCheckup, Integer> currentKm;
    @FXML
    private TableColumn<VehicleNeedingCheckup, Integer> checkupFrequencyKms;
    @FXML
    private TableColumn<VehicleNeedingCheckup, Integer> lastCheckupKm;
    @FXML
    private TableColumn<VehicleNeedingCheckup, Integer> optimalNextCheckupKm;

    /**
     * Creates an instance of ListVehiclesNeedingCheckupUI.
     */
    public ListVehiclesNeedingCheckupUI() {
        controller = new ListVehiclesNeedingCheckupController();
    }

    /**
     * Gets the controller.
     * @return controller
     */
    private ListVehiclesNeedingCheckupController getController() {
        return controller;
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        plateId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehicle().getPlateId()));
        currentKm.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVehicle().getCurrentKm()).asObject());
        checkupFrequencyKms.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVehicle().getCheckupFrequencyKms()).asObject());
        lastCheckupKm.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLastCheckupKm()).asObject());
        optimalNextCheckupKm.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOptimalNextCheckupKm()).asObject());

        showData();
    }

    /**
     * Method that shows the data.
     */
    private void showData() {

        List<VehicleNeedingCheckup> vehiclesNeedingCheckupList = getController().getVehiclesNeedingCheckup();

        for (VehicleNeedingCheckup vehicleNeedingCheckup : vehiclesNeedingCheckupList){
            tbNeddingCheckup.getItems().add(vehicleNeedingCheckup);
        }
    }

}
