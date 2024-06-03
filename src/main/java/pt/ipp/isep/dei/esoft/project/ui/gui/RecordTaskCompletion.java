package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import java.util.List;

public class RecordTaskCompletion {

    @FXML
    private ComboBox<Task> taskComboBox;

    @FXML
    private TextArea notesTextArea;

    private CollaboratorDTO collaborator;

    public void initialize() {

        List<Task> tasks = collaborator.getTasks();
        taskComboBox.getItems().addAll(tasks);
    }

    @FXML
    public void recordCompletion() {
        Task selectedTask = taskComboBox.getSelectionModel().getSelectedItem();
        String notes = notesTextArea.getText();

        if (selectedTask != null && !notes.isEmpty()) {


            AgendaTaskDTO taskDTO = new AgendaTaskDTO(selectedTask.getTitle(), selectedTask.getDescription(),
                    selectedTask.getTaskType();

            collaborator.completeTask(taskDTO);
        } else {

        }
    }

    public void setCollaborator(CollaboratorDTO collaborator) {
        this.collaborator = collaborator;
    }
}