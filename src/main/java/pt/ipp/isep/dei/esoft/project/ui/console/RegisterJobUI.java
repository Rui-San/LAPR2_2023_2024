package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterJobController;

import java.util.Scanner;

public class RegisterJobUI implements Runnable{
    private final RegisterJobController controller;
    private String jobName;

    public RegisterJobUI() {

        controller = new RegisterJobController();
    }

    private RegisterJobController getController() {
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
        boolean isRegistered = controller.registerJob(jobName);

        if (isRegistered) {
            System.out.println("\nJob registered!");
        } else {
            System.out.println("\nregistration failed!");
        }
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter job name:");
        jobName = scanner.nextLine();
    }

}
