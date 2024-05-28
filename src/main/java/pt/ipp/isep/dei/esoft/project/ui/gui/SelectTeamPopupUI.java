package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.controller.AssignTeamToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class SelectTeamPopupUI implements Initializable {

    private final AssignTeamToEntryAgendaController controller = new AssignTeamToEntryAgendaController();

    @FXML
    private TableView<Team> tbTeams;

    @FXML
    private Label lblError;

    @FXML
    private TableColumn<Team, String> tcCollaborator;

    @FXML
    private TableColumn<Team, String> tcSkills;

    private Team teamSelected;

    public void btnSelectTeam() {
        Team selectedTeam = tbTeams.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            lblError.setText("");
            lblError.setVisible(false);
            teamSelected = selectedTeam;
        } else {
            lblError.setText("No team selected.");
            lblError.setVisible(true);
        }
    }

    public Team getTeamSelected(){
        return teamSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        fillTeamTable();
    }

    private void fillTeamTable() {
        tbTeams.getItems().addAll(controller.getTeams());
    }

}
