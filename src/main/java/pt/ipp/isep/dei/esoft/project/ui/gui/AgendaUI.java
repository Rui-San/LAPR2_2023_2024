package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import pt.ipp.isep.dei.esoft.project.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;

import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaUI implements Initializable {

    private final AgendaController controller = new AgendaController();

    @FXML
    private TableView<AgendaTaskDTO> tbTasks;
    @FXML
    private TableColumn<AgendaTaskDTO, String> tcTitle, tcType, tcStatus, tcUrgency, tcTeamsAssigned, tcVehiclesAssigned;


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
}
