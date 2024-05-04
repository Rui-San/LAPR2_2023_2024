package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;

    private String name;
    private String birthdate;
    private String admissionDate;
    private String street;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String district;
    private String email;
    private String mobileNumber;
    private IdDocType idDocType;
    private String idDocNumber;
    private Job job;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    public RegisterCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    public static void main(String[] args) {
        RegisterCollaboratorUI ui = new RegisterCollaboratorUI();
        ui.run();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Register new Collaborator ------------------------");

        requestData();
        job = displayAndSelectJob();
        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job);
    }

    private Job displayAndSelectJob() {

        //List<Job> jobList = controller.getJobList();

        List<Job> jobList = new ArrayList<>();
        Job randomJob = new Job("Programador");
        jobList.add(randomJob);

        int listSize = jobList.size();
        int answer = 0;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobOptions(jobList);
            System.out.print("Select a job: ");
            answer = input.nextInt();

        }

        Job job = jobList.get(answer - 1);
        return job;
    }

    private void displayJobOptions(List<Job> jobList) {
        int i = 1;
        for (Job job : jobList) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }

    private void requestData() {
        name = requestName();
        birthdate = requestBirthdate();
        admissionDate = requestAdmissionDate();
        street = requestStreet();
        streetNumber = requestStreetNumber();
        postalCode = requestPostalCode();
        city = requestCity();
        district = requestDistrict();
        email = requestEmail();
        mobileNumber = requestMobileNumber();
        idDocType = requestIdDocType();
        idDocNumber = requestIdDocNumber();
    }

    private String requestIdDocNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("ID Doc Number: ");
        return input.nextLine();
    }

    private IdDocType requestIdDocType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select ID Document Type:");
        System.out.println("1. CC");
        System.out.println("2. BI");
        System.out.println("3. Passport");
        int choice = input.nextInt();

        boolean inputValid = true;
        do {
            switch (choice) {
                case 1:
                    return IdDocType.CC;
                case 2:
                    return IdDocType.BI;
                case 3:
                    return IdDocType.PASSPORT;
                default:
                    inputValid = false;
                    System.out.println("Please select a valid option.");
            }
        } while (!inputValid);

        return null;
    }

    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Email: ");
        return input.nextLine();
    }

    private String requestMobileNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Mobile Number: ");
        return input.nextLine();
    }

    private String requestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("City: ");
        return input.nextLine();
    }


    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("District: ");
        return input.nextLine();
    }

    private String requestPostalCode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Postal Code: ");
        return input.nextLine();
    }

    private int requestStreetNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street Number: ");
        return input.nextInt();
    }

    private String requestStreet() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street: ");
        return input.nextLine();
    }

    private String requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Admission date (format: dd/mm/yyyy): ");
        return input.nextLine();
    }

    private String requestBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Birthdate (format: dd/mm/yyyy): ");
        return input.nextLine();
    }

    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }
}
