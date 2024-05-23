package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {
    List<Task> agenda;

    private static final Status AGENDA_DEFAULT_STATUS = Status.PLANNED;

    public AgendaRepository(){
        this.agenda = new ArrayList<>();
    }

    public List<Task> getAgenda() {
        return agenda;
    }

    public Optional<Task> add(Task task, String executionDate) {

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskAgenda(task)) {
            task.setStatus(AGENDA_DEFAULT_STATUS);
            task.setExecutionDate(executionDate);
            newTask = Optional.of(task);
            operationSuccess = agenda.add(task);
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    private boolean validateTaskAgenda(Task task) {
        boolean isValid = true;
        String taskTitle = task.getTitle().trim();

        for (Task registeredTask : agenda) {
            if ((registeredTask.getTitle().trim().equalsIgnoreCase(taskTitle) && registeredTask.getStatus() == AGENDA_DEFAULT_STATUS)) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }

    public Optional<Task> registerTaskAgenda(Task task, String executionDate) {

        Optional<Task> addedTask = add(task, executionDate);

        return addedTask;
    }
}
