package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoRepository implements Serializable {

    /**
     * The list of tasks to do.
     */
    private List<Task> toDoList;
    /**
     * The default status for a task to do.
     */
    private static final Status TODO_DEFAULT_STATUS = Status.PENDING;

    /**
     * Instantiates a new To do repository.
     */
    public ToDoRepository() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Gets to do list.
     * @return
     */
    public List<Task> getToDoList() {
        return toDoList;
    }

    /**
     * Gets to do list of manager.
     * @param managerEmail the manager email
     * @return the to do list of manager
     */
    public List<Task> getToDoManagerList(String managerEmail) {
        List<Task> managerTasks = new ArrayList<>();
        for(Task task : toDoList){
            if(task.getGreenSpace().getManager().trim().equalsIgnoreCase(managerEmail.trim())){
             managerTasks.add(task);
            }
        }
        return managerTasks;
    }

    /**
     * Register task to do optional.
     * @param task the task
     * @return task registered
     */
    public Optional<Task> registerTaskToDo(Task task) {

        Optional<Task> addedTask = add(task);

        return addedTask;
    }

    /**
     * Add task to the list
     * @param task the task
     * @return the task added
     */
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

    /**
     * Validate task to do boolean.
     * @param task the task
     * @return true if task is valid
     */
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

    /**
     * Updates the task to processed
     * @param task the task with new status
     */
    public void updateTaskToProcessed(Task task) {
        for(Task taskTodo : toDoList){
            if (taskTodo.getTitle().trim().equalsIgnoreCase(task.getTitle().trim()) && taskTodo.getStatus() == TODO_DEFAULT_STATUS && taskTodo.getGreenSpace().toString().trim().equalsIgnoreCase(task.getGreenSpace().toString().trim())) {
                taskTodo.setStatus(Status.PROCESSED);
            }
        }
    }

}
