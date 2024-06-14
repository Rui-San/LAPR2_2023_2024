package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
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
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskStartDateFormatter;
import pt.ipp.isep.dei.esoft.project.ui.gui.popups.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListCollaboratorsUI implements Initializable {


    @FXML
    private TableView<Collaborator> tbCollaborator;
    @FXML
    private TableColumn<Collaborator, String> name, email, age, job, mobileNumber, assignedToTeam, skillCount;
    @FXML
    private TextField txtSearchByName;

    @FXML
    private Label lblError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail().getEmail()));
        age.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAge())));
        job.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJob().getJobName()));
        mobileNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMobileNumber()));
        assignedToTeam.setCellValueFactory(cellData ->{
            Team team = Repositories.getInstance().getTeamRepository().getTeamByTeamMemberEmails(cellData.getValue().getEmail().toString());
            if(team != null){
                return new SimpleStringProperty("Yes");
            }else {
                return new SimpleStringProperty("No");
            }
        });
        skillCount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSkillList().size())));

        fillCollaboratorTable();

        tbCollaborator.setRowFactory(tv -> {
            TableRow<Collaborator> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Collaborator selectedCollaborator = row.getItem();
                    showCollaboratorDetailsPopup(selectedCollaborator);
                }
            });
            return row;
        });
    }

    private void fillCollaboratorTable() {
        tbCollaborator.getItems().addAll(Repositories.getInstance().getCollaboratorRepository().getCollaboratorList());
    }

    @FXML
    private void searchByName() {
        FilteredList<Collaborator> filteredData = new FilteredList<>(FXCollections.observableArrayList(Repositories.getInstance().getCollaboratorRepository().getCollaboratorList()), p -> true);
        tbCollaborator.setItems(filteredData);
        txtSearchByName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(collaborator -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (collaborator.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    private void showCollaboratorDetailsPopup(Collaborator selectedCollaborator) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorDetailsPopupScene.fxml"));
            Parent root = loader.load();
            CollaboratorDetailsPopupUI controller = loader.getController();
            controller.initData(selectedCollaborator);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Collaborator Details");
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

