package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;

public class InsertDatePopupUI {
    @FXML
    private DatePicker dpExecutionDate;
    @FXML
    private Label lblExecutionDateError;

    private String selectedDate;


    @FXML
    private void btnConfirmDate() {

        if (validateExecutionDate()) {
            selectedDate = convertFormat(dpExecutionDate.getValue().toString());
            encerrarUI();
        }
    }

    @FXML
    private void btnCancelAction() {
        encerrarUI();
    }

    private void encerrarUI() {
        clearLayoutErrors(dpExecutionDate, lblExecutionDateError);
        Stage stage = (Stage) dpExecutionDate.getScene().getWindow();
        stage.close();
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    private String convertFormat(String date) {
        String[] dateSplit = date.split("-");
        return dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];
    }

    private boolean validateExecutionDate() {
        try {
            if (dpExecutionDate.getValue() == null) {
                displayErrorLayout(dpExecutionDate, lblExecutionDateError, "Execution date is empty");
                return false;
            }

            LocalDate selectedDate = dpExecutionDate.getValue();
            LocalDate currentDate = LocalDate.now();

            int year = selectedDate.getYear();
            int month = selectedDate.getMonthValue();
            int day = selectedDate.getDayOfMonth();

            if (year < 1 || month < 1 || month > 12 || day < 1 || day > selectedDate.lengthOfMonth()) {
                displayErrorLayout(dpExecutionDate, lblExecutionDateError, "Not a valid date");
                return false;
            }

            if (!selectedDate.isAfter(currentDate)) {
                displayErrorLayout(dpExecutionDate, lblExecutionDateError, "Must be a future date");
                return false;
            }
            clearLayoutErrors(dpExecutionDate, lblExecutionDateError);
            return true;

        } catch (Exception e) {
            displayErrorLayout(dpExecutionDate, lblExecutionDateError, "Invalid date");
            return false;
        }

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
}


