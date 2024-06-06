package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import java.util.List;
import java.util.ResourceBundle;

import pt.ipp.isep.dei.esoft.project.controller.CompleteTaskController;
import pt.ipp.isep.dei.esoft.project.controller.ListCollaboratorTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.tools.Sorting;
import pt.ipp.isep.dei.esoft.project.tools.Status;


public class ListCollaboratorTasksUI implements Initializable {

    private final ListCollaboratorTasksController controller;
    private final CompleteTaskController completeTaskController = new CompleteTaskController();
    @FXML
    private TableView<AgendaTaskDTO> taskTable;
    @FXML
    private Text sceneTitle;
    @FXML
    private TableColumn<AgendaTaskDTO, String> title, taskType, status,urgency,greenSpace,executionDate, expectedDuration;
    @FXML
    private DatePicker dpInitialDate, dpFinalDate;
    @FXML
    private ComboBox<String> cbStatusFilter;
    @FXML
    private Label lblError;


    public ListCollaboratorTasksUI() {
        controller = new ListCollaboratorTasksController();
    }

    private ListCollaboratorTasksController getController(){
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title));
        taskType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().taskType.toString()));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status.toString()));
        urgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urgency.toString()));
        greenSpace.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().greenSpaceName));
        executionDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().workStartDate));
        expectedDuration.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().expectedDuration)));

        fillTaskList(getController().getCollaboratorTasks());
        fillStatusFilter();
        sceneTitle.setText("Tasks assigned to " + controller.getCollaboratorName());


        taskTable.setRowFactory(tv -> {
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

    @FXML
    public void btnFilterAction(){
        boolean isOneNull = checkIsOneNull();
        boolean isFinalDateBeforeInitialDate = false;
        if(!isOneNull){
            isFinalDateBeforeInitialDate = validateFinalDate();
            if(!isFinalDateBeforeInitialDate){
                filterTable();
            }
        }
    }

    public void filterTable(){
        taskTable.getItems().clear();
        List<AgendaTaskDTO> filteredTasks = controller.getFilteredTasks(dpToDate(dpInitialDate), dpToDate(dpFinalDate));
        fillTaskList(filteredTasks);
    }

    @FXML
    public void btnSetCompleted(){
        completeTask();
    }

    public void completeTask() {
        AgendaTaskDTO selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (validateSelectedTask(selectedTask)) {

            Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Set Entry on Agenda to Done", "Confirm the operation", "Do you wish to complete the selected Entry?");
            if (alertConfirmation.showAndWait().get() == ButtonType.OK) {

                if (completeTaskController.completeTaskAgenda(selectedTask).isPresent()) {

                    AlertUI.createAlert(Alert.AlertType.INFORMATION, "Agenda", "Confirmation of operation", "Selected task successfully set to Done").show();
                    lblError.setText("");
                    lblError.setVisible(false);
                    updateTableView();
                } else {
                    AlertUI.createAlert(Alert.AlertType.ERROR, "Agenda", "Error occurred", "Task couldn't be complete").show();
                    lblError.setText("");
                    lblError.setVisible(false);
                }

            }
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

    @FXML
    public void filterStatus(){
        taskTable.getItems().clear();
        List<AgendaTaskDTO> colabTasks = controller.getCollaboratorTasks();
        for(AgendaTaskDTO taskDto : colabTasks){
            if(!cbStatusFilter.getValue().equals("All Status")){
                if(taskDto.status.toString().equals(cbStatusFilter.getValue())){
                    taskTable.getItems().add(taskDto);
                }
            }else {
                taskTable.getItems().add(taskDto);
            }
        }
    }

    @FXML
    public void btnClearFilter(){
        taskTable.getItems().clear();
        fillTaskList(getController().getCollaboratorTasks());
        dpInitialDate.setValue(null);
        dpFinalDate.setValue(null);
        cbStatusFilter.getSelectionModel().selectFirst();
        clearError(dpInitialDate, lblError);
        clearError(dpFinalDate, lblError);
        clearError(cbStatusFilter, lblError);
    }

    public boolean validateFinalDate(){
        if (dpFinalDate.getValue().isBefore(dpInitialDate.getValue())){
            setError(dpFinalDate, lblError, "Final date must be after initial date");
            return true;
        }else {
            clearError(dpFinalDate, lblError);
        }
        return false;
    }

    public boolean checkIsOneNull(){
        int validFields = 0;
        if(cbStatusFilter.getValue() == null){
            setError(cbStatusFilter, lblError, "Fill all fields before filtering.");
        }else {
            clearError(cbStatusFilter, lblError);
            validFields++;
        }
        if(dpInitialDate.getValue() == null){
            setError(dpInitialDate, lblError, "Fill all fields before filtering.");
        }else {
            clearError(dpInitialDate, lblError);
            validFields++;
        }
        if(dpFinalDate.getValue() == null){
            setError(dpFinalDate, lblError, "Fill all fields before filtering.");
        }else {
            clearError(dpFinalDate, lblError);
            validFields++;
        }

        return validFields != 3;
    }

    private void fillTaskList(List<AgendaTaskDTO> collabAgenda){
        taskTable.getItems().addAll(collabAgenda);
    }

    public void fillStatusFilter(){
        cbStatusFilter.getItems().add("All Status");
        for (Status status : Status.values()){
            if(status != Status.PENDING && status != Status.PROCESSED){
                cbStatusFilter.getItems().add(status.toString());
            }
        }
        cbStatusFilter.getSelectionModel().selectFirst();
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

    private void updateTableView() {
        taskTable.getItems().clear();

        for (AgendaTaskDTO task : controller.getCollaboratorTasks()) {
            taskTable.getItems().add(task);
        }
    }

    @FXML
    private Date dpToDate(DatePicker dp) {
        if (dp.getValue() != null) {
            Date date = new Date(dp.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return date;
        } else {
            return null;
        }
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
            stage.setTitle("Task details");
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