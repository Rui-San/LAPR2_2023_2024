package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCheckupUI implements Runnable {

    private final RegisterCheckupController controller;
    private Vehicle vehicle;
    private String checkupDate;
    private int checkupKm;

    public RegisterCheckupUI() {
        controller = new RegisterCheckupController();
    }

    private RegisterCheckupController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n=== REGISTER VEHICLE CHECKUP ===\n");

        vehicle = displayAndSelectVehicle();

        requestData();

        showAllDataForConfirmation(vehicle, checkupDate, checkupKm);
        if (Utils.confirm("Do you want to proceed? (y/n)")) {
            submitData();
        }

    }

    private void submitData() {

        Optional<VehicleCheckup> checkup = getController().registerVehicleCheckup(vehicle, checkupDate, checkupKm);

        if (checkup.isPresent()) {
            System.out.println("\nCheckup successfully created!");
        } else {
            System.out.println("\nCheckup not created!");
        }

    }

    private void showAllDataForConfirmation(Vehicle vehicle, String checkupDate, int checkupKm) {
        System.out.println("\nYou're about to register the following checkup:");
        System.out.println("Vehicle: " + vehicle.getPlateId());
        System.out.println("Checkup Date: " + checkupDate);
        System.out.println("Checkup Km: " + checkupKm);
    }

    private void requestData() {

        //Request the checkup date
        checkupDate = requestCheckupDate();

        //Request the kilometrage at checkup
        checkupKm = requestCheckupKm();

    }

    private String requestCheckupDate() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        Date response = null;
        String checkupDate = "";

        while (!validInput) {
            try {
                System.out.print("\nRegister Date (format: dd/mm/yyyy): ");
                checkupDate = input.nextLine();
                response = new Date(checkupDate);

                if (validateDate(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Date must be in the format dd/mm/yyyy");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return checkupDate;
    }

    private boolean validateDate(Date date) {

        if (date.compareTo(vehicle.getRegisterDate()) < 0) {
            throw new IllegalArgumentException("Checkup date must not be earlier than register date.");
        }
        return date != null;
    }

    private int requestCheckupKm() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.print("\nKm at Checkup: ");
                response = input.nextInt();

                if (validateCheckupKms(response)) {
                    validInput = true;
                } else {
                    if (response <= 0) {
                        throw new IllegalArgumentException("Checkup Kms must be a positive number");
                    } else {
                        throw new IllegalArgumentException("Checkup kilometers must be less than the current kilometers of the vehicle.");
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Checkup Kms must be a positive number");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    private boolean validateCheckupKms(int checkupKm) {
        return (checkupKm <= vehicle.getCurrentKm() && checkupKm > 0);

    }

    private Vehicle displayAndSelectVehicle() {

        List<Vehicle> vehicleList = getController().getVehicles();

        int numberOfVehicles = vehicleList.size();
        int answer = -1;

        do {
            displayVehicleOptions(vehicleList);
            Scanner input = new Scanner(System.in);
            System.out.print("\nVehicle to register checkup: ");
            answer = input.nextInt();
        } while (!validateIndex(answer, numberOfVehicles));

        Vehicle vehicle = vehicleList.get(answer - 1);
        return vehicle;
    }

    public boolean validateIndex(int answer, int numberOfVehicles) {
        if (answer < 1 || answer > numberOfVehicles) {
            System.out.println("Error: Please select the number of the vehicle from the list");
            return false;
        }
        return true;
    }

    private void displayVehicleOptions(List<Vehicle> vehicleList) {
        int i = 1;
        for (Vehicle vehicle : vehicleList) {
            System.out.println("  " + i + " - " + vehicle.getPlateId());
            i++;
        }
    }

}
