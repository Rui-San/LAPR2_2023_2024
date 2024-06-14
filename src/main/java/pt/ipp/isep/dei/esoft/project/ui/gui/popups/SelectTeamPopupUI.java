package pt.ipp.isep.dei.esoft.project.ui.gui.popups;


import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.controller.AssignTeamToEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.io.IOException;

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

        tbTeams.setRowFactory(tv -> {
            TableRow<TeamDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    TeamDTO selectedTeam = row.getItem();
                    showTaskDetailsPopup(selectedTeam);
                }
            });
            return row;
        });

    }
    private void showTaskDetailsPopup(TeamDTO teamSelected) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamDetailsPopupScene.fxml"));
            Parent root = loader.load();
            TeamDetailsPopupUI controller = loader.getController();
            controller.initData(teamSelected);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
        }
    }




}
