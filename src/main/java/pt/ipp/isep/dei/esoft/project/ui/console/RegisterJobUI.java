package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterJobUI implements Runnable {
    private final RegisterJobController controller;
    private String jobName;

    public RegisterJobUI() {

        controller = new RegisterJobController();
    }

    public RegisterJobController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n=== REGISTER NEW JOB ===");

        requestData();
        submitData();
    }

    private void submitData() {

        showAllDataForConfirmation(jobName,"You're about to register the following job:");
        if( Utils.confirm("Do you want to proceed? (y/n)") ) {

            Optional<Job> job = getController().registerJob(jobName);

            if (job.isPresent()) {
                System.out.println("\nJob successfully registered!");
            } else {
                System.out.println("\nThis job is already registered!");
            }
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
                System.out.print("\nEnter job name: ");
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

    private void showAllDataForConfirmation(String typedJob, String header){
        System.out.println();
        System.out.println(header);
        System.out.println(typedJob);

    }

}
