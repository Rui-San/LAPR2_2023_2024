package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.Duration;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.AddNewEntryToDoController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
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
    private Label lblExpectedDurationError;

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
        for (GreenSpaceDTO greenSpaceDTO : greenSpaceDTOList) {
            cbGreenSpace.getItems().add(greenSpaceDTO.name);
        }
    }

    private void fillTaskTypeComboBox() {
        cbType.setItems(FXCollections.observableArrayList(TaskType.values()));
    }

    private void fillUrgencyComboBox() {
        cbUrgency.setItems(FXCollections.observableArrayList(UrgencyType.values()));
    }


    public void btnSubmit() {
        if (validateAllInputs()) {
            Duration expDuration = Duration.ofDays(Integer.parseInt(txtDays.getText().trim())).plusHours(Integer.parseInt(txtHours.getText().trim()));

            ToDoTaskDTO toDoTaskDTO = new ToDoTaskDTO(
                    txtTitle.getText().trim(),
                    txtDescription.getText().trim(),
                    cbType.getValue(),
                    cbGreenSpace.getValue(),
                    cbUrgency.getValue(),
                    expDuration
            );

            StringBuilder sb = getConfirmationText(toDoTaskDTO);

            Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Add new entry To-Do", "Confirm the operation", sb.toString());
            if (alertConfirmation.showAndWait().get() == ButtonType.OK) {
                controller.registerTask(toDoTaskDTO);

                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Add new entry To-Do", "Confirmation of operation", "New Entry To-Do sucessfully registered");
                clearFields();
            }



        }
    }

    private StringBuilder getConfirmationText(ToDoTaskDTO toDoTaskDTO) {
        StringBuilder sb = new StringBuilder();

        sb.append("You're about to register de following Entry:")
                .append("\nTitle: ").append(toDoTaskDTO.title)
                .append("\nDescription: ").append(toDoTaskDTO.description)
                .append("\nGreen Space: ").append(toDoTaskDTO.greenSpaceName)
                .append("\nTypee: ").append(toDoTaskDTO.taskType)
                .append("\nUrgency: ").append(toDoTaskDTO.urgency)
                .append("\nExpected Duration: ").append(toDoTaskDTO.expectedDuration.toDays()).append(" days, ").append(toDoTaskDTO.expectedDuration.toHours()).append(" hours");

        return sb;
    }

    public void btnClear() {
        clearFields();
    }

    public boolean validateAllInputs() {
        return validateTitle()
                && validateDescription()
                && validateExpectedDuration()
                && validateGreenSpaceSelection()
                && validateTypeSelection()
                && validateUrgencySelection();
    }

    private boolean validateTitle() {
        String titleString = txtTitle.getText().trim();
        if (titleString.isEmpty()) {
            displayErrorLayout(txtTitle, lblTitleError, "Can't be empty or null");
            return false;
        }
        if (!titleString.matches("[a-zA-Z\\s-\\p{L}]+")) {
            displayErrorLayout(txtTitle, lblTitleError, "Can't contain special characters");
            return false;
        }
        clearLayoutErrors(txtTitle, lblTitleError);
        return true;
    }

    private boolean validateGreenSpaceSelection() {
        if (cbGreenSpace.getValue() == null) {
            displayErrorLayout(cbGreenSpace, lblGreenSpaceError, "Select a green space");
            return false;
        }
        clearLayoutErrors(cbGreenSpace, lblGreenSpaceError);
        return true;
    }

    private boolean validateTypeSelection() {
        if (cbType.getValue() == null) {
            displayErrorLayout(cbType, lblTypeError, "Select a task type");
            return false;
        }
        clearLayoutErrors(cbType, lblTypeError);
        return true;
    }

    private boolean validateUrgencySelection() {
        if (cbUrgency.getValue() == null) {
            displayErrorLayout(cbUrgency, lblUrgencyError, "Select a task urgency");
            return false;
        }
        clearLayoutErrors(cbUrgency, lblUrgencyError);
        return true;
    }

    private boolean validateDescription() {
        String descriptionString = txtDescription.getText().trim();
        if (descriptionString.isEmpty()) {
            displayErrorLayout(txtDescription, lblDescriptionError, "Can't be empty or null");
            return false;
        }
        if (!descriptionString.matches("[a-zA-Z\\s-\\p{L}]+")) {
            displayErrorLayout(txtDescription, lblDescriptionError, "Can't contain special characters");
            return false;
        }
        clearLayoutErrors(txtDescription, lblDescriptionError);
        return true;
    }

    private boolean validateExpectedDuration() {
        try {
            int daysInt = Integer.parseInt(txtDays.getText().trim());
            int hoursInt = Integer.parseInt(txtHours.getText().trim());

            if (daysInt < 0 || hoursInt < 0) {
                if (daysInt < 0) {
                    txtDays.setStyle("-fx-border-color: red");
                }
                if (hoursInt < 0) {
                    txtHours.setStyle("-fx-border-color: red");
                }
                lblExpectedDurationError.setVisible(true);
                lblDescriptionError.setText("Must be a positive integer");
                return false;
            }

            if (daysInt == 0 && hoursInt == 0) {
                txtHours.setStyle("-fx-border-color: red");
                displayErrorLayout(txtDays, lblExpectedDurationError, "At least one must be greater than zero");
                return false;
            }

            if (hoursInt >= 24) {
                displayErrorLayout(txtHours, lblExpectedDurationError, "Hours must be less than 24");
                return false;
            }
        } catch (NumberFormatException ne) {
            txtHours.setStyle("-fx-text-fill: red;");
            txtDays.setStyle("-fx-text-fill: red;");
            lblExpectedDurationError.setText("Enter valid integers");
            lblExpectedDurationError.setStyle("-fx-text-fill: red;");
            lblExpectedDurationError.setVisible(true);
            return false;

        }
        txtHours.setStyle("");
        clearLayoutErrors(txtDays, lblExpectedDurationError);
        return true;
    }


    private void displayErrorLayout(Control controlObject, Label labelToShowError, String errorMessage) {
        controlObject.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLayoutErrors(Control controlObject, Label labelWithError) {
        controlObject.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    private void clearFields() {
        txtTitle.clear();
        txtDescription.clear();
        txtDays.clear();
        txtHours.clear();
        cbGreenSpace.getSelectionModel().clearSelection();
        cbType.getSelectionModel().clearSelection();
        cbUrgency.getSelectionModel().clearSelection();
        lblTitleError.setStyle("");
        lblTitleError.setVisible(false);
        lblDescriptionError.setStyle("");
        lblDescriptionError.setVisible(false);
        lblTypeError.setStyle("");
        lblTypeError.setVisible(false);
        lblUrgencyError.setStyle("");
        lblUrgencyError.setVisible(false);
        lblGreenSpaceError.setStyle("");
        lblGreenSpaceError.setVisible(false);
        lblExpectedDurationError.setStyle("");
        lblExpectedDurationError.setVisible(false);
    }
}
