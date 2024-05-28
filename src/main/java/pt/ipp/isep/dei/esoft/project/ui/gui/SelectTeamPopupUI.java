package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.controller.AssignTeamToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectTeamPopupUI {

    private final AssignTeamToEntryAgendaController controller = new AssignTeamToEntryAgendaController();

    @FXML
    private TableView<TeamDTO> tbTeams;

    @FXML
    private Label lblError;

    @FXML
    private TableColumn<TeamDTO, String> tcCollaborator;

    @FXML
    private TableColumn<TeamDTO, String> tcSkills;

    private TeamDTO teamSelected;



    public void btnSelectTeam() {
        TeamDTO selectedTeam = tbTeams.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            lblError.setText("");
            lblError.setVisible(false);
            teamSelected = selectedTeam;
            closeUI();

        } else {
            lblError.setText("No team selected.");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void btnCancelAction() {
        closeUI();
    }

    private void closeUI() {
        lblError.setText("");
        lblError.setVisible(false);
        Stage stage = (Stage) tbTeams.getScene().getWindow();
        stage.close();
    }

    public TeamDTO getTeamSelected(){
        return teamSelected;
    }

    public void fillTeamTable() {
        tcCollaborator.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().namesToString()));
        tcSkills.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().skillsToString()));

        tbTeams.getItems().addAll(controller.getTeams());
    }



}
