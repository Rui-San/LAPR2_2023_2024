package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleIntegerProperty;
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
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;

public class ListCollaboratorTasksUI implements Initializable {

    private final ListCollaboratorTasksController controller;

    //Table showing the tasks
    @FXML
    private TableView<AgendaTaskDTO> taskTable;

    @FXML
    private TableColumn<AgendaTaskDTO, String> title, taskType, status,urgency,greenSpace,executionDate, expectedDuration;


    @FXML
    private DatePicker initialDatePicker;
    @FXML
    private DatePicker finalDatePicker;

    @FXML
    private Button showTasks;


    private Date initialDate;
    private Date finalDate;

    /**
     * Creates an instance of ListCollaboratorTasksUI.
     */

    public ListCollaboratorTasksUI() {
        this.initialDate = new Date();
        this.finalDate = new Date();
        controller = new ListCollaboratorTasksController();
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
        title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title));
        taskType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().taskType.toString()));
        status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status.toString()));
        urgency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urgency.toString()));
        greenSpace.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().greenSpaceName));
        executionDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().workStartDate));
        expectedDuration.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().expectedDuration)));

        fillTaskList();

    }

    /**
     * Method that shows the data in the table view.
     */

    @FXML
    private void fillTaskList(){
        taskTable.getItems().addAll(controller.getCollaboratorTasks());
    }

    @FXML
    private void handleSetInitialDate() {
        if (initialDatePicker.getValue() != null) {
            initialDate = new Date(initialDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            getController().updateInitialDate(initialDate);
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
        }
    }


}