package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

import java.net.URL;
import java.util.ResourceBundle;

import pt.ipp.isep.dei.esoft.project.controller.ListCollaboratorTasksController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.tools.Status;

public class ListCollaboratorTasksUI implements Initializable {

    private final ListCollaboratorTasksController controller;

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

        fillTaskList();
        fillStatusFilter();
        sceneTitle.setText("Tasks assigned to " + controller.getCollaboratorName());
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
        // TODO: implement filterTable
    }

    @FXML
    public void btnSetCompleted(){
        completeTask();
    }

    public void completeTask() {
        // TODO: implement completeTask
    }

    @FXML
    public void btnClearFilter(){
        taskTable.getItems().clear();
        fillTaskList();
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

    private void fillTaskList(){
        taskTable.getItems().addAll(getController().getCollaboratorTasks());
    }

    public void fillStatusFilter(){
        cbStatusFilter.getItems().add("All Status");
        for (Status status : Status.values()){
            cbStatusFilter.getItems().add(status.toString());
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

    /*
    @FXML
    private void handleSetInitialDate() {
        if (initialDatePicker.getValue() != null) {
            initialDate = new Date(initialDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            getController().updateInitialDate(initialDate);
        }
    }

    @FXML
    private void handleSetFinalDate() {
        if (finalDatePicker.getValue() != null) {
            finalDate = new Date(finalDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            getController().updateFinalDate(finalDate);
        }
    }
    */

}