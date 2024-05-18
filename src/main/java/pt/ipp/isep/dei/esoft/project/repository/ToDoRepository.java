package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoRepository {

    private List<Task> toDoList;

    private final Status DEFAULT_STATUS = Status.PENDING;

    public ToDoRepository() {
        this.toDoList = new ArrayList<>();
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public Optional<Task> registerTaskToDo(String title, String description, Status status, GreenSpace greenSpace, UrgencyType urgency, int days, int hours) {

        Task task = new Task(
                title,
                description,
                status,
                greenSpace,
                urgency,
                days,
                hours
        );

        Optional<Task> addedTask = add(task);


        return addedTask;
    }

    public Optional<Task> add(Task task) {

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskToDo(task)) {
            task.setStatus(DEFAULT_STATUS);
            newTask = Optional.of(task);
            operationSuccess = toDoList.add(newTask.get());
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    private boolean validateTaskToDo(Task task) {
        boolean isValid = true;
        String taskTitle = task.getTitle().trim().toLowerCase();
        Status taskStatus = task.getStatus();

        for (Task registeredTask : toDoList) {
            if ((registeredTask.getTitle().trim().toLowerCase().equals(taskTitle) && registeredTask.getStatus().equals(taskStatus))) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }
}
