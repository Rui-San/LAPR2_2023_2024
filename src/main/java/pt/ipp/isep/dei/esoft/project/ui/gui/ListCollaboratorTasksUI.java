package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.controller.ListCollaboratorTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import pt.ipp.isep.dei.esoft.project.domain.Task;

public class ListCollaboratorTasksUI implements Initializable {

    private final ListCollaboratorTasksController controller;

    //Table showing the tasks
    @FXML
    private TableView<Task> tasks;

    @FXML
    private TableColumn<Task, String> title;
    @FXML
    private TableColumn<Task, String> taskType;
    @FXML
    private TableColumn<Task, String> status;
    @FXML
    private TableColumn<Task, String> urgency;
    @FXML
    private TableColumn<Task, String> greenSpace;
    @FXML
    private TableColumn<Task, String> executionDate;
    @FXML
    private TableColumn<Task, String> expectedDuration;


    @FXML
    private DatePicker initialDatePicker;
    @FXML
    private DatePicker finalDatePicker;
    @FXML
    private Button initialDateButton;
    @FXML
    private Button finalDateButton;

    private Date initialDate;
    private Date finalDate;

    /**
     * Creates an instance of ListCollaboratorTasksUI.
     */

    public ListCollaboratorTasksUI() {
        this.initialDate = new Date();
        this.finalDate = new Date();
        controller = new ListCollaboratorTasksController(initialDate, finalDate);
    }

    /**
     * Gets the controller.
     * @return controller
     */
    private ListCollaboratorTasksController getController(){
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        taskType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTaskType().toString()));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
        urgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrgency().toString()));
        greenSpace.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGreenSpace().toString()));
        executionDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExecutionDate().toString()));
        expectedDuration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpectedDuration().toString()));

        showData();

    }

    /**
     * Method that shows the data in the table view.
     */
    private void showData(){
        tasks.getItems().setAll(getController().getTasks());
    }

    @FXML
    private void handleSetInitialDate() {
        if (initialDatePicker.getValue() != null) {
            initialDate = new Date(initialDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            getController().updateInitialDate(initialDate);
            showData();
        }
    }

    /**
     * Handles the action of setting the final date.
     */
    @FXML
    private void handleSetFinalDate() {
        if (finalDatePicker.getValue() != null) {
            finalDate = new Date(finalDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            getController().updateFinalDate(finalDate);
            showData();
        }
    }


}