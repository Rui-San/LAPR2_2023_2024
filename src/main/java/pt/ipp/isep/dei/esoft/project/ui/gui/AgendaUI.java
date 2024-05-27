package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.controller.CancelEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaUI implements Initializable {

    private final AgendaController controller = new AgendaController();

    private final CancelEntryAgendaController cancelEntryAgendaController = new CancelEntryAgendaController();

    @FXML
    private TableView<AgendaTaskDTO> tbTasks;
    @FXML
    private TableColumn<AgendaTaskDTO, String> tcTitle, tcType, tcStatus, tcUrgency, tcTeamsAssigned, tcVehiclesAssigned;

    @FXML
    private Label lblError;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title));
        tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().taskType.toString()));
        tcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status.toString()));
        tcUrgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urgency.toString()));
        tcTeamsAssigned.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().teamsAssigned)));
        tcVehiclesAssigned.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().vehiclesAssigned)));
        fillTaskTable();
    }

    private void fillTaskTable() {
        tbTasks.getItems().addAll(controller.getAgendaTaskDTOList());
    }

    @FXML
    public void btnCancelTask() {

        AgendaTaskDTO selectedTask = tbTasks.getSelectionModel().getSelectedItem();

        if (validateSelectedTaskToCancel(selectedTask)) {

            Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Cancel Entry on Agenda", "Confirm the operation", "Do you wish to cancel the selected Entry?");
            if (alertConfirmation.showAndWait().get() == ButtonType.OK) {

                if (cancelEntryAgendaController.cancelTaskAgenda(selectedTask).isPresent()) {

                    AlertUI.createAlert(Alert.AlertType.INFORMATION, "Agenda", "Confirmation of operation", "Selected task successfully canceled").show();
                    lblError.setText("");
                    lblError.setVisible(false);
                    updateTableView();
                } else {
                    AlertUI.createAlert(Alert.AlertType.ERROR, "Agenda", "Error occurred", "Task couldn't be canceled").show();
                    lblError.setText("");
                    lblError.setVisible(false);
                }

            }
        }
    }

    private void updateTableView() {
        tbTasks.getItems().clear();

        for (AgendaTaskDTO task : controller.getAgendaTaskDTOList()) {
            tbTasks.getItems().add(task);
        }
    }

    public boolean validateSelectedTaskToCancel(AgendaTaskDTO selectedTask) {

        if (selectedTask == null) {
            lblError.setText("Select one task from Agenda");
            lblError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.DONE) {
            lblError.setText("The selected task is already done");
            lblError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.CANCELED) {
            lblError.setText("The selected task is already canceled");
            lblError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.POSTPONED || selectedTask.status == Status.PLANNED) {
            lblError.setText("");
            lblError.setVisible(true);
            return true;
        }
        return true;
    }

}

