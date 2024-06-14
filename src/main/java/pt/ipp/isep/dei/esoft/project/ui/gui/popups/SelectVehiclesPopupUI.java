package pt.ipp.isep.dei.esoft.project.ui.gui.popups;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.controller.AssignVehiclesToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.List;

public class SelectVehiclesPopupUI {

    private final AssignVehiclesToEntryAgendaController controller = new AssignVehiclesToEntryAgendaController();

    @FXML
    private TableView<VehicleDTO> tbVehicles;

    @FXML
    private Label lblError;

    @FXML
    private TableColumn<VehicleDTO, String> tcPlateID, tcBrand, tcModel, tcType;

    private List<VehicleDTO> vehiclesSelected;

    public void btnSelectVehicle() {
        List<VehicleDTO> selectedVehicles = tbVehicles.getSelectionModel().getSelectedItems();
        if (selectedVehicles != null) {
            lblError.setText("");
            lblError.setVisible(false);
            vehiclesSelected = selectedVehicles;
            closeUI();
        } else {
            lblError.setText("No Vehicle selected.");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void btnCancelAction() {
        closeUI();
    }

    private void closeUI() {
        lblError.setText("");
        lblError.setVisible(false);
        Stage stage = (Stage) tbVehicles.getScene().getWindow();
        stage.close();
    }

    public List<VehicleDTO> getVehiclesSelected(){
        return vehiclesSelected;
    }

    public void fillVehicleTable() {
        tcPlateID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().plateID));
        tcBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().brand));
        tcModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().model));
        tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type));

        tbVehicles.getItems().addAll(controller.getVehicles());
        tbVehicles.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
    }



}
