package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class InsertDatePopupUI {
    @FXML
    private DatePicker dpExecutionDate;
    @FXML
    private Label lblExecutionDateError, lblTimeError;

    @FXML
    private TextField txtHours, txtMins;

    private String selectedDate;
    private int workStartingHours;
    private int workStartingMinutes;


    @FXML
    private void btnConfirmData() {

        if (validateExecutionDate()) {
            selectedDate = convertFormat(dpExecutionDate.getValue().toString());

            if(validateTime()){
                workStartingHours = txtHours.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtHours.getText().trim());
                workStartingMinutes = txtMins.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtMins.getText().trim());
                encerrarUI();
            }
        }

    }

    private boolean validateTime() {
        try {
            String hoursText = txtHours.getText().trim();
            String minutesText = txtMins.getText().trim();

            boolean isHoursEmpty = hoursText.isEmpty();
            boolean isMinutesEmpty = minutesText.isEmpty();

            // Verifica se todos os campos estão vazios
            if (!isHoursEmpty) {
                int hoursInt = Integer.parseInt(hoursText);
                if (hoursInt < 8 || (hoursInt > 12 && hoursInt < 13) || hoursInt > 17) {
                    displayErrorLayout(txtHours, lblTimeError, "Hours must be within 08:00-12:00 or 13:00-17:00");
                    return false;
                }
            }


            if (!isHoursEmpty) {
                int hoursInt = Integer.parseInt(hoursText);
                if (hoursInt < 0 || hoursInt >= 24) {
                    displayErrorLayout(txtHours, lblTimeError, "Hours must be between 0 and 23");
                    return false;
                }
            }

            if (!isMinutesEmpty) {
                int minutesInt = Integer.parseInt(minutesText);
                if (minutesInt < 0 || minutesInt >= 60) {
                    displayErrorLayout(txtMins, lblTimeError, "Minutes must be between 0 and 59");
                    return false;
                }
            }

        } catch (NumberFormatException ne) {
            displayErrorLayout(txtHours, lblTimeError, "Enter valid integers");
            displayErrorLayout(txtMins, lblTimeError, "Enter valid integers");
            return false;
        }

        clearLayoutErrors(txtHours, lblTimeError);
        clearLayoutErrors(txtMins, lblTimeError);
        return true;
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

    public int getWorkStartingHours() {
        return workStartingHours;
    }

    public int getWorkStartingMinutes() {
        return workStartingMinutes;
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


