package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterJobUI implements Runnable {
    private final RegisterJobController controller;
    private String jobName;

    public RegisterJobUI() {

        controller = new RegisterJobController();
    }

    private RegisterJobController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register new Job ------------------------");

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

        /*
        boolean isRegistered = controller.registerJob(jobName);

        if (isRegistered) {
            System.out.println("\nJob registered!");
        } else {
            System.out.println("\nregistration failed!");
        }*/

        Optional<Job> job = getController().registerJob(jobName);

        if (job.isPresent()) {
            System.out.println("\nJob successfully registered!");
        } else {
            System.out.println("\nJob not registered!");
        }
    }

    private void requestData() {
        jobName = requestJobName();
    }

    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Enter job name: ");
                response = input.nextLine();

                ValidationAttributeResults validateJobNameResults = validateJob(response);

                switch (validateJobNameResults) {
                    case EMPTYNULL:
                        throw new IllegalArgumentException("Job name must not be empty");
                    case CONTAINS_SPECIAL_CHARACTERS:
                        throw new IllegalArgumentException("Job name must not contain special characters or numbers");
                    case VALID:
                        validInput = true;
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    private ValidationAttributeResults validateJob(String jobName) {
        if (jobName == null || jobName.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (namePattern.matcher(jobName).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

}
