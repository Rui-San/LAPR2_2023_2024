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
import pt.ipp.isep.dei.esoft.project.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.controller.AssignTeamToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.controller.AssignVehiclesToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.controller.CancelEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AgendaUI implements Initializable {

    private final AgendaController controller = new AgendaController();

    private final CancelEntryAgendaController cancelEntryAgendaController = new CancelEntryAgendaController();

    private final AssignTeamToEntryAgendaController assignTeamToEntryAgendaController = new AssignTeamToEntryAgendaController();

    private final AssignVehiclesToEntryAgendaController assignVehiclesToEntryAgendaController = new AssignVehiclesToEntryAgendaController();

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
        tbTasks.getItems().addAll(controller.getAgendaTaskDTOManagerList());
    }

    @FXML
    public void btnCancelTask() {

        AgendaTaskDTO selectedTask = tbTasks.getSelectionModel().getSelectedItem();

        if (validateSelectedTask(selectedTask)) {

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

    @FXML
    public void btnAssignTeam() {
        AgendaTaskDTO selectedTask = tbTasks.getSelectionModel().getSelectedItem();

        if (validateSelectedTask(selectedTask)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectTeamPopupScene.fxml"));
            Parent root;
            try {
                root = loader.load();
                SelectTeamPopupUI selectTeamPopupUI = loader.getController();
                selectTeamPopupUI.fillTeamTable();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));

                stage.showAndWait();


                TeamDTO selectedTeam = selectTeamPopupUI.getTeamSelected();

                if (selectedTeam != null) {

                    if (assignTeamToEntryAgendaController.assignTeamToTaskAgenda(selectedTask, selectedTeam).isPresent()) {
                        lblError.setText("");
                        lblError.setVisible(false);
                        updateTableView();

                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Assign Team to Task", "Confirmation of operation", "Team successfully assigned to task").show();
                        lblError.setVisible(false);
                    }else{
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Task", "Error occurred", "Not possible to assign this team to the task").show();
                        lblError.setVisible(false);
                    }
                }
            } catch (IOException e) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "An error occurred", e.getMessage()).show();

            } catch (IllegalArgumentException exception){
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Error assigning team to the task", exception.getMessage()).show();

            }
        }
    }

    @FXML
    private void btnAssignVehicles(){
        AgendaTaskDTO selectedTask = tbTasks.getSelectionModel().getSelectedItem();

        if (validateSelectedTask(selectedTask)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectVehiclesPopupScene.fxml"));
            Parent root;
            try {
                root = loader.load();
                SelectVehiclesPopupUI selectVehiclesPopupUI = loader.getController();
                selectVehiclesPopupUI.fillVehicleTable();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));

                stage.showAndWait();


                List<VehicleDTO> selectedVehicles = selectVehiclesPopupUI.getVehiclesSelected();

                if (!selectedVehicles.isEmpty()) {

                    if (assignVehiclesToEntryAgendaController.assignVehiclesToTaskAgenda(selectedTask, selectedVehicles).isPresent()) {
                        lblError.setText("");
                        lblError.setVisible(false);
                        updateTableView();

                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Assign Vehicle to Task", "Confirmation of operation", "Vehicle successfully assigned to task").show();
                        lblError.setVisible(false);
                    }else{
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Vehicle to Task", "Error occurred", "Not possible to assign one or more selected vehicles to the task").show();
                        lblError.setVisible(false);
                    }
                }
            } catch (IOException e) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "An error occurred", e.getMessage()).show();

            } catch (IllegalArgumentException exception){
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Error assigning vehicles to the task", exception.getMessage()).show();

            }
        }
    }


    private void updateTableView() {
        tbTasks.getItems().clear();

        for (AgendaTaskDTO task : controller.getAgendaTaskDTOManagerList()) {
            tbTasks.getItems().add(task);
        }
    }


    public boolean validateSelectedTask(AgendaTaskDTO selectedTask) {

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

