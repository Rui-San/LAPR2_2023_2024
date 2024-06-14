package pt.ipp.isep.dei.esoft.project.ui.gui.popups;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.tools.TaskDurationFormatter;

public class TodoTaskDetailsPopupUI {

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
    private Label lblStatus;


    public void initData(ToDoTaskWithStatusDTO task) {

        lblTitle.setText(task.title);
        lblDescription.setText(task.description);
        lblTaskType.setText(task.taskType.toString());
        lblGreenSpace.setText(task.greenSpaceName);
        lblUrgency.setText(task.urgency.toString());
        lblExpectedDuration.setText(TaskDurationFormatter.toStringDaysHoursMinutes(task.expectedDurationToString()));
        lblStatus.setText(task.status.toString());

    }

    @FXML
    private void btnExit() {
        closeUI();
    }

    private void closeUI() {

        Stage stage = (Stage) lblUrgency.getScene().getWindow();
        stage.close();
    }
}
