package pt.ipp.isep.dei.esoft.project.ui.console;

import jdk.jshell.ImportSnippet;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCheckupUI implements Runnable {

    public static void main(String[] args) {
        RegisterCheckupUI ui = new RegisterCheckupUI();
        ui.run();
    }

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
        System.out.println("\n ----------REGISTER VEHICLE CHECKUP ----------------\n");

        vehicle = displayAndSelectVehicle();

        requestData();

        submitData();

        ArrayList<VehicleCheckup> checkupList = getController().getCheckupRepository().getVehicleCheckupList();

        System.out.println("\nCheckup list:");
        for (VehicleCheckup checkup : checkupList) {
            System.out.println(checkup.toString());
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

    private void requestData() {

        //Request the checkup date
        checkupDate = requestCheckupDate();

        //Request the kilometrage at checkup
        checkupKm = requestCheckupKm();

    }

    private String requestCheckupDate() {
        Scanner read = new Scanner(System.in);
        System.out.printf("\nCheckup Date (dd/mm/yyyy): ");
        return read.nextLine();
    }

    private int requestCheckupKm() {
        int checkupKm = -1;
        Scanner read = new Scanner(System.in);
        do {
            System.out.printf("\nCheckup Km: ");
            checkupKm = read.nextInt();
        }while (checkupKm < 0 || checkupKm > vehicle.getCurrentKm());
        return checkupKm;
    }

    private Vehicle displayAndSelectVehicle() {

        //this is the correct way of doing it. This list in unmodifiable
        //List<Vehicle> vehicleList = controller.getVehicles();

        List<Vehicle> vehicleList = new ArrayList<>();

        //This is a mockup
        Vehicle vehicle1 = new Vehicle("11-11-11", "Car", "Gasoline", "type1", 1000, 1000, 1000, new Date("1/12/2003"), new Date("2/3/2000  "), 1000);
        Vehicle vehicle2 = new Vehicle("22-22-22", "Car", "Gasoline", "type2", 2000, 2000, 2000, new Date("1/12/2003"), new Date("2/3/2000"), 2000);
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

        int numberOfVehicles = vehicleList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > numberOfVehicles) {
            displayVehicleOptions(vehicleList);
            System.out.print("Vehicle to register checkup: ");
            answer = input.nextInt();
        }

        Vehicle vehicle = vehicleList.get(answer - 1);
        return vehicle;
    }

    private void displayVehicleOptions(List<Vehicle> vehicleList) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Vehicle vehicle : vehicleList) {
            System.out.println("  " + i + " - " + vehicle.getPlateId());
            i++;
        }
    }

}
