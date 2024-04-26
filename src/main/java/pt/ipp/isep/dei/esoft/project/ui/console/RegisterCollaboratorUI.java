package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.application.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Email;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.sql.SQLOutput;
import java.util.List;
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
    private Job job;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    public RegisterCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Register new Collaborator ------------------------");

        requestData();

        job = displayAndSelectJob();

        submitData();
    }

    private void submitData() {
    }

    private Job displayAndSelectJob() {
        List<Job> jobList = controller.getJobList();
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
