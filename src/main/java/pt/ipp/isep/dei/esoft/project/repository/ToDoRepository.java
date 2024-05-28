package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoRepository {

    private List<Task> toDoList;

    private static final Status TODO_DEFAULT_STATUS = Status.PENDING;

    public ToDoRepository() {
        this.toDoList = new ArrayList<>();
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public List<Task> getToDoManagerList(String managerEmail) {
        List<Task> managerTasks = new ArrayList<>();
        for(Task task : toDoList){
            if(task.getGreenSpace().getManager().trim().equalsIgnoreCase(managerEmail.trim())){
             managerTasks.add(task);
            }
        }
        return managerTasks;
    }

    public Optional<Task> registerTaskToDo(Task task) {

        Optional<Task> addedTask = add(task);

        return addedTask;
    }

    public Optional<Task> add(Task task) {

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskToDo(task)) {
            task.setStatus(TODO_DEFAULT_STATUS);
            newTask = Optional.of(task);
            operationSuccess = toDoList.add(task);
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    private boolean validateTaskToDo(Task task) {
        boolean isValid = true;
        String taskTitle = task.getTitle().trim();
        String taskGreenSpace = task.getGreenSpace().toString().trim();

        for (Task registeredTask : toDoList) {
            if ((registeredTask.getTitle().trim().equalsIgnoreCase(taskTitle) && registeredTask.getStatus() == TODO_DEFAULT_STATUS) && registeredTask.getGreenSpace().toString().trim().equalsIgnoreCase(taskGreenSpace)) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }

    public void updateTaskToProcessed(Task task) {
        for(Task taskTodo : toDoList){
            if (taskTodo.getTitle().trim().equalsIgnoreCase(task.getTitle().trim()) && taskTodo.getStatus() == TODO_DEFAULT_STATUS && taskTodo.getGreenSpace().toString().trim().equalsIgnoreCase(task.getGreenSpace().toString().trim())) {
                taskTodo.setStatus(Status.PROCESSED);
            }
        }
    }


}
