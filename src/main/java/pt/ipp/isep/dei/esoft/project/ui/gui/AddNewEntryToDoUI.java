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

        try {

            if (validateAllInputs()) {
                int daysInt = Integer.parseInt(txtDays.getText().trim());
                int hoursInt = Integer.parseInt(txtHours.getText().trim());
                int minutesInt = Integer.parseInt(txtMins.getText().trim());

                long totalMinutes = (long) daysInt * 24 * 60 + hoursInt * 60L + minutesInt;
                Duration expDuration = Duration.ofMinutes(totalMinutes);

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

                    if (controller.registerTask(toDoTaskDTO).isPresent()) {
                        System.out.println(controller.registerTask(toDoTaskDTO).get());
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

        long totalDays = toDoTaskDTO.expectedDuration.toDays();
        long remainingMinutes = toDoTaskDTO.expectedDuration.toMinutes() % (24 * 60);
        long remainingHours = remainingMinutes / 60;
        remainingMinutes %= 60;

        sb.append("You're about to register de following Entry:")
                .append("\nTitle: ").append(toDoTaskDTO.title)
                .append("\nDescription: ").append(toDoTaskDTO.description)
                .append("\nGreen Space: ").append(toDoTaskDTO.greenSpaceName)
                .append("\nTypee: ").append(toDoTaskDTO.taskType)
                .append("\nUrgency: ").append(toDoTaskDTO.urgency)
                .append("\nExpected Duration: ").append(totalDays).append(" days and ").append(remainingHours).append(":").append(remainingMinutes).append("H");

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
        try {
            int daysInt = Integer.parseInt(txtDays.getText().trim());
            int hoursInt = Integer.parseInt(txtHours.getText().trim());
            int minutesInt = Integer.parseInt(txtMins.getText().trim());

            if (daysInt < 0 || hoursInt < 0 || minutesInt < 0) {
                displayErrorLayout(txtDays, lblExpectedDurationError, "All values of duration must be positive integers");
                displayErrorLayout(txtHours, lblExpectedDurationError, "All values of duration must be positive integers");
                displayErrorLayout(txtMins, lblExpectedDurationError, "All values of duration must be positive integers");
                return false;
            }

            if (daysInt == 0 && hoursInt == 0 && minutesInt == 0) {
                displayErrorLayout(txtDays, lblExpectedDurationError, "At least one must be greater than zero");
                displayErrorLayout(txtHours, lblExpectedDurationError, "At least one must be greater than zero");
                displayErrorLayout(txtMins, lblExpectedDurationError, "At least one must be greater than zero");
                return false;
            }

            if (hoursInt >= 24 || minutesInt >= 60) {
                displayErrorLayout(txtHours, lblExpectedDurationError, "Hours/mins must be within their respective ranges");
                displayErrorLayout(txtMins, lblExpectedDurationError, "Hours/mins must be within their respective ranges");
                return false;
            }
        } catch (NumberFormatException ne) {
            displayErrorLayout(txtDays, lblExpectedDurationError, "Enter valid integers");
            displayErrorLayout(txtHours, lblExpectedDurationError, "Enter valid integers");
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
