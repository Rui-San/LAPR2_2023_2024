package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import pt.ipp.isep.dei.esoft.project.controller.AddNewEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewEntryAgendaUI implements Initializable {

    private final AddNewEntryAgendaController controller;

    private final List<ToDoTaskWithStatusDTO> taskList;
    @FXML
    private TableView<ToDoTaskWithStatusDTO> taskTableView;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, String> titleColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, String> descriptionColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, TaskType> taskTypeColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, String> greenSpaceNameColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, UrgencyType> urgencyColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, String> expectedDurationColumn;
    @FXML
    private TableColumn<ToDoTaskWithStatusDTO, Status> statusColumn;

    @FXML
    private Label lblAddError;

    public AddNewEntryAgendaUI() {
        this.controller = new AddNewEntryAgendaController();
        this.taskList = controller.getToDoDTOManagerlist();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().description));
        taskTypeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().taskType));
        greenSpaceNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().greenSpaceName));
        urgencyColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().urgency));
        expectedDurationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().expectedDurationToString()));
        statusColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().status));

        fillTableInformation();
    }

    private void fillTableInformation() {

        for (ToDoTaskWithStatusDTO task : taskList) {
            taskTableView.getItems().add(task);
        }
    }

    @FXML
    private void handleAddToAgenda() {
        try {
            ToDoTaskWithStatusDTO selectedTask = taskTableView.getSelectionModel().getSelectedItem();
            lblAddError.setText("");
            lblAddError.setVisible(false);
            if (validateTaskSelected()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertDatePopupScene.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    InsertDatePopupUI insertDatePopupUI = loader.getController();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                    String selectedDate = insertDatePopupUI.getSelectedDate();
                    int workStartingHours = insertDatePopupUI.getWorkStartingHours();
                    int workStartingMinutes = insertDatePopupUI.getWorkStartingMinutes();
                    if (selectedDate != null && (workStartingMinutes > 0 || workStartingHours >0)) {
                        System.out.println(selectedTask.title + selectedTask.description + selectedTask.urgency + selectedTask.taskType + selectedTask.greenSpaceName + selectedTask.expectedDurationToString());
                        System.out.println(selectedDate);
                        System.out.println();
                        System.out.println(workStartingHours + " : " + workStartingMinutes) ;
                        if (controller.registerTaskAgenda(selectedTask, selectedDate, workStartingHours, workStartingMinutes).isPresent()) {

                            taskList.clear();
                            taskList.addAll(controller.getToDoDTOManagerlist());
                            updateTableView();

                            System.out.println(Repositories.getInstance().getAgendaRepository().getAgenda());

                            AlertUI.createAlert(Alert.AlertType.INFORMATION, "Add new entry to agenda", "Confirmation of operation", "Task successfully placed in Agenda").show();
                            lblAddError.setVisible(false);


                        } else {
                            AlertUI.createAlert(Alert.AlertType.ERROR, "Add new entry to agenda", "Error occurred", "This task was already in the Agenda").show();
                            lblAddError.setVisible(false);
                        }

                    }
                } catch (IOException e) {
                    AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Add new entry to agenda", e.getMessage()).show();

                }
            }
        } catch (IllegalArgumentException ie) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Add new entry to agenda", ie.getMessage()).show();
        }
    }

    public boolean validateTaskSelected() {

        ToDoTaskWithStatusDTO selectedTask = taskTableView.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            lblAddError.setText("Select one task To-Do");
            lblAddError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.PROCESSED) {
            lblAddError.setText("The selected task is already in the Agenda, as it status is PROCESSED");
            lblAddError.setVisible(true);
            return false;
        }

        if (selectedTask.status == Status.PENDING) {
            lblAddError.setText("");
            lblAddError.setVisible(false);
            return true;
        }
        return true;

    }

    private void updateTableView() {
        taskTableView.getItems().clear();

        for (ToDoTaskWithStatusDTO task : taskList) {
            taskTableView.getItems().add(task);
        }
    }

}



