package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.RegisterSkillController;

public class RegisterSkillUI implements Runnable{
    private final RegisterSkillController controller;
    private String taskReference;
    private String taskDescription;
    private String taskInformalDescription;
    private String taskTechnicalDescription;
    private int taskDuration;
    private double taskCost;
    private String taskCategoryDescription;
    private String empployeeEmail;

    public RegisterSkillUI() {
        controller = new RegisterSkillController();
    }

    private RegisterSkillController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Task ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        /*
        Optional<Task> task = getController().createTask(taskReference, taskDescription, taskInformalDescription,
                taskTechnicalDescription, taskDuration, taskCost, taskCategoryDescription);

        if (task.isPresent()) {
            System.out.println("\nTask successfully created!");
        } else {
            System.out.println("\nTask not created!");
        }
         */
    }

    private void requestData() {
        /*
        //Request the Task Reference from the console
        taskReference = requestTaskReference();

        //Request the Task Description from the console
        taskDescription = requestTaskDescription();

        //Request the Task Informal Description from the console
        taskInformalDescription = requestTaskInformalDescription();

        //Request the Task Technical Description from the console
        taskTechnicalDescription = requestTaskTechnicalDescription();

        //Request the Task Duration from the console
        taskDuration = requestTaskDuration();

        //Request the Task Cost from the console
        taskCost = requestTaskCost();

         */
    }

}
