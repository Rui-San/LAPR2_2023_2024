package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.controller.AddNewEntryToDoController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;


public class AddNewEntryToDoUI implements Initializable {

    private final AddNewEntryToDoController controller;

    private List<GreenSpaceDTO> greenSpaceDTOList;

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtDays;
    @FXML
    private TextField txtHours;
    @FXML
    private ComboBox<String> cbGreenSpace;
    @FXML
    private ComboBox<TaskType> cbType;
    @FXML
    private ComboBox<UrgencyType> cbUrgency;
    @FXML
    private Label lblTitleError;
    @FXML
    private Label lblDescriptionError;
    @FXML
    private Label lblTypeError;
    @FXML
    private Label lblUrgencyError;
    @FXML
    private Label lblGreenSpaceError;
    @FXML
    private Label lblExcpectedDurationError;

    public AddNewEntryToDoUI() {
        this.controller = new AddNewEntryToDoController();
        this.greenSpaceDTOList = controller.getGreenSpaceDTOlist();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fillUrgencyComboBox();
        fillTaskTypeComboBox();
        fillGreenSpaceComboBox();




    }

    private void fillGreenSpaceComboBox() {

        for(GreenSpaceDTO greenSpaceDTO : greenSpaceDTOList){
            cbGreenSpace.getItems().add(greenSpaceDTO.name);
        }
    }

    private void fillTaskTypeComboBox() {
    }

    private void fillUrgencyComboBox() {
    }


    public void btnSubmit(ActionEvent actionEvent) {
    }

    public void btnClear(ActionEvent actionEvent) {
    }
}
