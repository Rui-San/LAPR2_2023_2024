package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.Date;


import java.util.*;
import java.util.regex.Pattern;

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

    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
    }

    public RegisterVehicleController getRegisterVehicleController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n----------- Register new Vehicle ---------------");

        requestData();
        submitData();
    }

    private void submitData() {
        Optional<Vehicle> vehicle = getRegisterVehicleController().createVehicle(plateID, brand, model, type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkupFrequencyKms);
    }

    private void requestData() {
        plateID = requestPlateID();
        model = requestModel();
        brand = requestBrand();
        type = requestType();
        tare = requestTare();
        grossWeight = requestGrossWeight(tare);
        currentKm = requestCurrentKm();
        registerDate = requestRegisterDate();
        acquisitionDate = requestAcquisitionDate(acquisitionDate);
        checkupFrequencyKms = requestCheckupFrequencyKms();

    }

    private String requestPlateID() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Plate ID: ");
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

    private boolean validatePlateID(String plateID) {
        if (plateID == null || plateID.trim().isEmpty()) {
            return false;
        }

        String plateIDPattern = "[A-Z]{2}-[0-9]{2}-[A-Z]{2}";

        return plateID.matches(plateIDPattern);
    }

    private String requestModel() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Model: ");
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

    private String requestBrand() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Brand: ");
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

    private String requestType() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Type: ");
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

    private boolean validateNotNullorEmpty(String model) {
        return model != null && !model.trim().isEmpty();
    }

    private double requestTare() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        double response = 0;

        while (!validInput) {
            try {
                System.out.println("Tare: ");
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

    private boolean validateTare(double tare) {
        return tare > 0;
    }

    private double requestGrossWeight(double tare) {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        double response = 0;

        while (!validInput) {
            try {
                System.out.println("Gross Weight: ");
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

    private boolean validateGrossWeight(double grossWeight, double tare) {
        return grossWeight > 0 && grossWeight > tare;
    }

    private int requestCurrentKm() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.println("Current Km: ");
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

    private boolean validateCurrentKm(int currentKm) {
        return currentKm > 0;
    }

    private Date requestRegisterDate() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        Date response = null;

        while (!validInput) {
            try {
                System.out.println("Register Date (format: dd/mm/yyyy): ");
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

    private boolean validateDate(Date date) {
        return date != null;
    }

    private Date requestAcquisitionDate(Date registerDate) {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        Date response = null;

        while (!validInput) {
            try {
                System.out.println("Acquisition Date (format: dd/mm/yyyy): ");
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

    private boolean validateAcquisitionDate(Date acquisitionDate, Date registerDate) {
        return acquisitionDate != null && acquisitionDate.compareTo(registerDate) > 0;
    }

    private int requestCheckupFrequencyKms() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.println("Checkup Frequency Kms: ");
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
