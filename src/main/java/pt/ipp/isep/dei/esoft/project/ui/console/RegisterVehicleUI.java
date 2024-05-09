package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.util.*;

public class RegisterVehicleUI implements Runnable{

    private final RegisterVehicleController controller;
    private String plateID;
    private String brand;
    private String model;
    private String type;
    private double tare;
    private double grossWeight;
    private int currentKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int checkupFrequencyKms;

    /**
     * Creates an instance of RegisterVehicleUI
     */
    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
    }

    /**
     * Get the RegisterVehicleController
     * @return RegisterVehicleController
     */
    public RegisterVehicleController getRegisterVehicleController() {
        return controller;
    }

    /**
     * Run method of RegisterVehicleUI
     */
    @Override
    public void run() {
        System.out.println("\n\n----------- Register new Vehicle ---------------");

        requestData();
        submitData();
    }

    /**
     * Method that submits the data to the controller
     */
    private void submitData() {
        showAllDataForConfirmation("You're about to register the following vehicle:", plateID, brand, model, type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkupFrequencyKms);
        if (Utils.confirm("Do you want to proceed? (y/n)")) {

            Optional<Vehicle> vehicle = getRegisterVehicleController().createVehicle(plateID, brand, model, type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkupFrequencyKms);

            if (vehicle.isEmpty()) {
                System.out.println("An error occurred while registering the vehicle.");
            }else {
                System.out.println("Vehicle registered successfully.");
            }

        }

    }

    /**
     * Method that shows all the data for confirmation
     * @param message the message to show
     * @param plateID the plate ID
     * @param brand the brand
     * @param model the model
     * @param type the type
     * @param tare the tare
     * @param grossWeight the gross weight
     * @param currentKm the current kilometers
     * @param registerDate the register date
     * @param acquisitionDate the acquisition date
     * @param checkupFrequencyKms the checkup frequency in kilometers
     */
    private void showAllDataForConfirmation(String message, String plateID, String brand, String model, String type, double tare, double grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequencyKms) {
        System.out.println(message);
        System.out.println("Plate ID: " + plateID);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Type: " + type);
        System.out.println("Tare: " + tare);
        System.out.println("Gross Weight: " + grossWeight);
        System.out.println("Current Km: " + currentKm);
        System.out.println("Register Date: " + registerDate);
        System.out.println("Acquisition Date: " + acquisitionDate);
        System.out.println("Checkup Frequency Kms: " + checkupFrequencyKms);
    }

    /**
     * Method that requests all the data needed to register a vehicle
     */
    private void requestData() {
        plateID = requestPlateID();
        model = requestModel();
        brand = requestBrand();
        type = requestType();
        tare = requestTare();
        grossWeight = requestGrossWeight(tare);
        currentKm = requestCurrentKm();
        registerDate = requestRegisterDate();
        acquisitionDate = requestAcquisitionDate(registerDate);
        checkupFrequencyKms = requestCheckupFrequencyKms();

    }

    /**
     * Method that requests the plate ID
     * @return the plate ID
     */
    private String requestPlateID() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nPlate ID: ");
                response = input.nextLine();

                if (validatePlateID(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Plate ID is not in a correct format");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates the plate ID
     * @param plateID the plate ID
     * @return true if the plate ID is valid
     */
    private boolean validatePlateID(String plateID) {
        if (plateID == null || plateID.trim().isEmpty()) {
            return false;
        }

        String plateIDPattern = "[A-Z]{2}-[0-9]{2}-[A-Z]{2}";

        return plateID.matches(plateIDPattern);
    }

    /**
     * Method that requests the model
     * @return the model
     */
    private String requestModel() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nModel: ");
                response = input.nextLine();

                if (validateNotNullorEmpty(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Model must not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that requests the brand
     * @return the brand
     */
    private String requestBrand() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nBrand: ");
                response = input.nextLine();

                if (validateNotNullorEmpty(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Brand must not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that requests the type
     * @return the type
     */
    private String requestType() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nType: ");
                response = input.nextLine();

                if (validateNotNullorEmpty(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Type must not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates if a string is not null or empty
     * @param string the string to validate
     * @return true if the string is not null nor empty
     */
    private boolean validateNotNullorEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }

    /**
     * Method that requests the tare
     * @return the tare
     */
    private double requestTare() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        double response = 0;

        while (!validInput) {
            try {
                System.out.print("\nTare: ");
                response = input.nextDouble();

                if (validateTare(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Tare must be a positive number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Tare must be a positive number");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates the tare
     * @param tare the tare
     * @return true if the tare is valid
     */
    private boolean validateTare(double tare) {
        return tare > 0;
    }

    /**
     * Method that requests the gross weight
     * @param tare the tare
     * @return the gross weight
     */
    private double requestGrossWeight(double tare) {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        double response = 0;

        while (!validInput) {
            try {
                System.out.print("\nGross Weight: ");
                response = input.nextDouble();

                if (validateGrossWeight(response, tare)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Gross Weight must be a positive number and greater than Tare");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Gross Weight must be a positive number and greater than Tare");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates the gross weight
     * @param grossWeight the gross weight
     * @param tare the tare
     * @return true if the gross weight is valid
     */
    private boolean validateGrossWeight(double grossWeight, double tare) {
        return grossWeight > 0 && grossWeight > tare;
    }

    private int requestCurrentKm() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.print("\nCurrent Km: ");
                response = input.nextInt();

                if (validateCurrentKm(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Current Km must be a positive number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Current Km must be a positive number");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates the current kilometers
     * @param currentKm the current kilometers
     * @return true if the current kilometers are valid
     */
    private boolean validateCurrentKm(int currentKm) {
        return currentKm > 0;
    }

    /**
     * Method that requests the register date
     * @return the register date
     */
    private Date requestRegisterDate() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        Date response = null;

        while (!validInput) {
            try {
                System.out.print("\nRegister Date (format: dd/mm/yyyy): ");
                String registerDate = input.nextLine();
                response = new Date(registerDate);

                if (validateDate(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Date must be in the format dd/mm/yyyy");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates a date
     * @param date the date
     * @return true if the date is valid
     */
    private boolean validateDate(Date date) {
        return date != null;
    }

    /**
     * Method that requests the acquisition date
     * @param registerDate the register date
     * @return the acquisition date
     */
    private Date requestAcquisitionDate(Date registerDate) {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        Date response = null;

        while (!validInput) {
            try {
                System.out.print("\nAcquisition Date (format: dd/mm/yyyy): ");
                String acquisitionDate = input.nextLine();
                response = new Date(acquisitionDate);

                if (validateAcquisitionDate(response, registerDate)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Acquisition Date must be in the format dd/mm/yyyy and after Register Date");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Method that validates the acquisition date
     * @param acquisitionDate the acquisition date
     * @param registerDate the register date
     * @return true if the acquisition date is valid
     */
    private boolean validateAcquisitionDate(Date acquisitionDate, Date registerDate) {
        return acquisitionDate != null && acquisitionDate.compareTo(registerDate) > 0;
    }

    /**
     * Method that requests the checkup frequency in kilometers
     * @return the checkup frequency in kilometers
     */
    private int requestCheckupFrequencyKms() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.print("\nCheckup Frequency Kms: ");
                response = input.nextInt();

                if (validateCurrentKm(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Checkup Frequency Kms must be a positive number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Checkup Frequency Kms must be a positive number");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

}
