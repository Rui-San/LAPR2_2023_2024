package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.tools.TaskDurationFormatter;
import pt.ipp.isep.dei.esoft.project.tools.TaskStartDateFormatter;

public class TaskDetailsPopupUI {

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblTaskType;
    @FXML
    private Label lblGreenSpace;
    @FXML
    private Label lblUrgency;
    @FXML
    private Label lblExpectedDuration;
    @FXML
    private Label lblWorkStartDate;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblTeamAssigned;
    @FXML
    private Label lblVehiclesAssigned;


    public void initData(AgendaTaskDTO task) {
        StringBuilder vehiclesString = new StringBuilder();
        StringBuilder teamString = new StringBuilder();

        lblTitle.setText(task.title);
        lblDescription.setText(task.description);
        lblTaskType.setText(task.taskType.toString());
        lblGreenSpace.setText(task.greenSpaceName);
        lblUrgency.setText(task.urgency.toString());
        lblExpectedDuration.setText(TaskDurationFormatter.toStringDaysHoursMinutes(task.expectedDuration));
        lblWorkStartDate.setText(TaskStartDateFormatter.getFormattedStartDateTime(task.workStartDate, task.workStartHour, task.workStartMinutes));
        lblStatus.setText(task.status.toString());

        if (task.teamDTO != null) {
            teamString.append(task.teamDTO.namesToString()).append("\n").append(task.teamDTO.skillsToString());
            lblTeamAssigned.setText(teamString.toString());
        } else {
            lblTeamAssigned.setText(task.isTeamAssigned);
        }

        if (!task.vehicleDTOList.isEmpty()) {

            for (VehicleDTO vehicleDTO : task.vehicleDTOList) {
                vehiclesString.append(vehicleDTO.plateID).append(", ").append(vehicleDTO.brand).append(" - ").append(vehicleDTO.model).append("\n");
            }

            lblVehiclesAssigned.setText(vehiclesString.toString());
        } else {
            lblVehiclesAssigned.setText(Integer.toString(task.vehiclesAssigned));
        }


    }

    @FXML
    private void btnExit() {
        closeUI();
    }

    private void closeUI() {

        Stage stage = (Stage) lblVehiclesAssigned.getScene().getWindow();
        stage.close();
    }
}
