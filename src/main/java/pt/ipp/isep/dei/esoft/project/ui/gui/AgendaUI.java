package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.controller.*;
import pt.ipp.isep.dei.esoft.project.domain.TaskDuration;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskDurationFormatter;
import pt.ipp.isep.dei.esoft.project.tools.TaskStartDateFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AgendaUI implements Initializable {

    private final AgendaController controller = new AgendaController();

    private final CancelEntryAgendaController cancelEntryAgendaController = new CancelEntryAgendaController();

    private final AssignTeamToEntryAgendaController assignTeamToEntryAgendaController = new AssignTeamToEntryAgendaController();

    private final AssignVehiclesToEntryAgendaController assignVehiclesToEntryAgendaController = new AssignVehiclesToEntryAgendaController();

    private final PostponeTaskController postponeTaskController = new PostponeTaskController();

    @FXML
    private TableView<AgendaTaskDTO> tbTasks;
    @FXML
    private TableColumn<AgendaTaskDTO, String> tcTitle, tcType, tcStatus, tcUrgency, tcTeamsAssigned, tcVehiclesAssigned, tcGreenSpace, tcDate;

    @FXML
    private Label lblError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title));
        tcGreenSpace.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().greenSpaceName));
        tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().taskType.toString()));
        tcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status.toString()));
        tcUrgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urgency.toString()));
        tcTeamsAssigned.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isTeamAssigned));
        tcDate.setCellValueFactory(cellData -> new SimpleStringProperty(TaskStartDateFormatter.getFormattedStartDateTime(cellData.getValue().workStartDate,cellData.getValue().workStartHour,cellData.getValue().workStartMinutes)));
        tcVehiclesAssigned.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().vehiclesAssigned)));
        fillTaskTable();


        tbTasks.setRowFactory(tv -> {
            TableRow<AgendaTaskDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    AgendaTaskDTO selectedTask = row.getItem();
                    showTaskDetailsPopup(selectedTask);
                }
            });
            return row;
        });
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
            if (selectedTask.isTeamAssigned.trim().equalsIgnoreCase("Yes")) {
                lblError.setText("This task already have a team assigned to.");
                lblError.setVisible(true);
            } else {
                lblError.setText("");
                lblError.setVisible(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectTeamPopupScene.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    SelectTeamPopupUI selectTeamPopupUI = loader.getController();
                    selectTeamPopupUI.fillTeamTable();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Assign Team");
                    Image image = new Image("file:MS_logo.png");
                    stage.getIcons().add(image);
                    stage.setResizable(false);

                    stage.showAndWait();


                    TeamDTO selectedTeam = selectTeamPopupUI.getTeamSelected();

                    if (selectedTeam != null) {

                        if (assignTeamToEntryAgendaController.assignTeamToTaskAgenda(selectedTask, selectedTeam).isPresent()) {
                            lblError.setText("");
                            lblError.setVisible(false);
                            updateTableView();

                            AlertUI.createAlert(Alert.AlertType.INFORMATION, "Assign Team to Task", "Confirmation of operation", "Team successfully assigned to task").show();
                            lblError.setVisible(false);
                        } else {
                            AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Team to Task", "Error occurred", "Not possible to assign this team to the task").show();
                            lblError.setVisible(false);
                        }
                    }
                } catch (IOException e) {
                    AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "An error occurred", e.getMessage()).show();

                } catch (IllegalArgumentException exception) {
                    AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Error assigning team to the task", exception.getMessage()).show();

                }
            }
        }
    }

    @FXML
    private void btnAssignVehicles() {
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
                stage.setResizable(false);
                stage.setTitle("Assign Vehicles");
                Image image = new Image("file:MS_logo.png");
                stage.getIcons().add(image);
                stage.showAndWait();


                List<VehicleDTO> selectedVehicles = selectVehiclesPopupUI.getVehiclesSelected();

                if (selectedVehicles != null) {

                    if (assignVehiclesToEntryAgendaController.assignVehiclesToTaskAgenda(selectedTask, selectedVehicles).isPresent()) {
                        lblError.setText("");
                        lblError.setVisible(false);
                        updateTableView();

                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Assign Vehicle to Task", "Confirmation of operation", "Vehicle successfully assigned to task").show();
                        lblError.setVisible(false);
                    } else {
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Assign Vehicle to Task", "Error occurred", "Not possible to assign one or more selected vehicles to the task").show();
                        lblError.setVisible(false);
                    }
                }
            } catch (IOException e) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "An error occurred", e.getMessage()).show();

            } catch (IllegalArgumentException exception) {
                AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Error assigning vehicles to the task", exception.getMessage()).show();

            }
        }
    }

    public void btnPostponeTask(){
        AgendaTaskDTO selectedTask = tbTasks.getSelectionModel().getSelectedItem();

        if (validateSelectedTask(selectedTask)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostponeTaskPopupScene.fxml"));
            Parent root;
            try {
                root = loader.load();
                PostponeTaskPopupUI postponeTaskPopupUI = loader.getController();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Postpone Task");
                Image image = new Image("file:MS_logo.png");
                stage.getIcons().add(image);
                stage.showAndWait();

                String selectedDate = postponeTaskPopupUI.getSelectedDate();
                int workStartingHours = postponeTaskPopupUI.getWorkStartingHours();
                int workStartingMinutes = postponeTaskPopupUI.getWorkStartingMinutes();
                if (selectedDate != null && (workStartingMinutes > 0 || workStartingHours >0)) {
                    if (postponeTaskController.postponeTask(selectedTask, selectedDate, workStartingHours, workStartingMinutes)) {
                        lblError.setText("");
                        lblError.setVisible(false);
                        updateTableView();

                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Postpone Task", "Operation Success", "Task successfully postponed").show();
                    } else {
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Postpone Task", "Error occurred", "Task cannot be postponed to selected date.").show();
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

        if(selectedTask.status == Status.POSTPONED){
            lblError.setText("The selected task is postponed");
            lblError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.PLANNED) {
            lblError.setText("");
            lblError.setVisible(true);
            return true;
        }
        return true;
    }

    private void showTaskDetailsPopup(AgendaTaskDTO task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskDetailsPopupScene.fxml"));
            Parent root = loader.load();
            TaskDetailsPopupUI controller = loader.getController();
            controller.initData(task);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Task Details");
            Image image = new Image("file:MS_logo.png");
            stage.getIcons().add(image);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
        }
    }

}

