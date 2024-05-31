package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.Duration;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.AddNewEntryToDoController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.tools.TaskDurationFormatter;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;


public class AddNewEntryToDoUI implements Initializable {

    private final AddNewEntryToDoController controller;

    private final List<GreenSpaceDTO> greenSpaceDTOList;

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtDays;
    @FXML
    private TextField txtHours;
    @FXML
    private TextField txtMins;
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
        this.greenSpaceDTOList = controller.getManagerGreenSpaceDTOList();
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

        try {

            if (validateAllInputs()) {
                int daysInt = txtDays.getText().isEmpty() ? 0 : Integer.parseInt(txtDays.getText().trim());
                int hoursInt = txtHours.getText().isEmpty() ? 0 : Integer.parseInt(txtHours.getText().trim());
                int minutesInt = txtMins.getText().isEmpty() ? 0 : Integer.parseInt(txtMins.getText().trim());

                ToDoTaskDTO toDoTaskDTO = new ToDoTaskDTO(
                        txtTitle.getText().trim(),
                        txtDescription.getText().trim(),
                        cbType.getValue(),
                        cbGreenSpace.getValue(),
                        cbUrgency.getValue(),
                        daysInt,
                        hoursInt,
                        minutesInt
                );

                StringBuilder sb = getConfirmationText(toDoTaskDTO);

                Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Add new entry To-Do", "Confirm the operation", sb.toString());
                if (alertConfirmation.showAndWait().get() == ButtonType.OK) {

                    if (controller.registerTask(toDoTaskDTO).isPresent()) {

                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Add new entry To-Do", "Confirmation of operation", "New Entry To-Do successfully registered").show();
                        clearFields();
                    } else {
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Add new entry To-Do", "Error occurred", "This task is already in the To-Do list").show();
                        clearFields();
                    }
                }
            }
        } catch (IllegalArgumentException ie) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Add new entry To-Do error", ie.getMessage()).show();
        }
    }

    private StringBuilder getConfirmationText(ToDoTaskDTO toDoTaskDTO) {
        StringBuilder sb = new StringBuilder();

        sb.append("You're about to register de following Entry:")
                .append("\nTitle: ").append(toDoTaskDTO.title)
                .append("\nDescription: ").append(toDoTaskDTO.description)
                .append("\nGreen Space: ").append(toDoTaskDTO.greenSpaceName)
                .append("\nType: ").append(toDoTaskDTO.taskType)
                .append("\nUrgency: ").append(toDoTaskDTO.urgency)
                .append("\nExpected Duration: ").append(TaskDurationFormatter.toStringDaysHoursMinutes(toDoTaskDTO.days,toDoTaskDTO.hours,toDoTaskDTO.minutes));

        return sb;
    }

    public void btnClear() {
        clearFields();
    }

    public boolean validateAllInputs() {
        boolean titleValid = validateTitle();
        boolean descriptionValid = validateDescription();
        boolean durationValid = validateExpectedDuration();
        boolean greenSpaceValid = validateGreenSpaceSelection();
        boolean typeValid = validateTypeSelection();
        boolean urgencyValid = validateUrgencySelection();

        return titleValid && descriptionValid && durationValid && greenSpaceValid && typeValid && urgencyValid;

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
        lblExpectedDurationError.setText("");
        lblExpectedDurationError.setStyle("");
        lblExpectedDurationError.setVisible(false);
        try {
            String daysText = txtDays.getText().trim();
            String hoursText = txtHours.getText().trim();
            String minutesText = txtMins.getText().trim();

            boolean isDaysEmpty = daysText.isEmpty();
            boolean isHoursEmpty = hoursText.isEmpty();
            boolean isMinutesEmpty = minutesText.isEmpty();

            if (isDaysEmpty && isHoursEmpty && isMinutesEmpty) {
                displayErrorLayout(txtDays, lblExpectedDurationError, "At least one field must be filled");
                displayErrorLayout(txtHours, lblExpectedDurationError, "At least one field must be filled");
                displayErrorLayout(txtMins, lblExpectedDurationError, "At least one field must be filled");
                return false;
            }

            if (!isDaysEmpty) {
                int daysInt = Integer.parseInt(daysText);
                if (daysInt < 0) {
                    displayErrorLayout(txtDays, lblExpectedDurationError, "Days must be a positive integer");
                    displayErrorLayout(txtHours, lblExpectedDurationError, "Days must be a positive integer");
                    displayErrorLayout(txtMins, lblExpectedDurationError, "Days must be a positive integer");
                    return false;
                }
            }

            if (!isHoursEmpty) {
                int hoursInt = Integer.parseInt(hoursText);
                if (hoursInt < 0) {
                    displayErrorLayout(txtDays, lblExpectedDurationError, "Hours must be a positive integer");
                    displayErrorLayout(txtHours, lblExpectedDurationError, "Hours must be a positive integer");
                    displayErrorLayout(txtMins, lblExpectedDurationError, "Hours must be a positive integer");
                    return false;
                }
            }

            if (!isMinutesEmpty) {
                int minutesInt = Integer.parseInt(minutesText);
                if (minutesInt < 0) {
                    displayErrorLayout(txtDays, lblExpectedDurationError, "Minutes must be a positive integer");
                    displayErrorLayout(txtHours, lblExpectedDurationError, "Minutes must be a positive integer");
                    displayErrorLayout(txtMins, lblExpectedDurationError, "Minutes must be a positive integer");
                    return false;
                }
            }

        } catch (NumberFormatException ne) {
            displayErrorLayout(txtDays, lblExpectedDurationError, "Enter valid integers");
            displayErrorLayout(txtHours, lblExpectedDurationError, "Enter valid integers");
            displayErrorLayout(txtMins, lblExpectedDurationError, "Enter valid integers");
            return false;
        }

        clearLayoutErrors(txtDays, lblExpectedDurationError);
        clearLayoutErrors(txtHours, lblExpectedDurationError);
        clearLayoutErrors(txtMins, lblExpectedDurationError);
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
        txtTitle.setStyle("");
        txtMins.clear();
        txtMins.setStyle("");
        txtDescription.clear();
        txtDescription.setStyle("");
        txtDays.clear();
        txtDays.setStyle("");
        txtHours.clear();
        txtHours.setStyle("");
        cbGreenSpace.getSelectionModel().clearSelection();
        cbGreenSpace.setStyle("");
        cbType.getSelectionModel().clearSelection();
        cbType.setStyle("");
        cbUrgency.getSelectionModel().clearSelection();
        cbUrgency.setStyle("");
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
