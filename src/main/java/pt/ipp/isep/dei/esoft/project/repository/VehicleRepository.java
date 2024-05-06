package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    /**
     * List of all vehicles.
     */
    private ArrayList<Vehicle> vehicleList;

    /**
     * Creates an instance of VehicleRepository with the specified list of vehicles.
     * @param vehicles
     */
    public VehicleRepository(ArrayList<Vehicle> vehicles) {
        this.vehicleList = vehicles;
    }

    /**
     * Creates an instance of VehicleRepository with an empty list of vehicles.
     */
    public VehicleRepository() {
        this.vehicleList = new ArrayList<>();
    }

    /**
     * Gets the list of all vehicles.
     * @return
     */
    public List<Vehicle> getVehicleList() {
        return List.copyOf(vehicleList);
    }

    /**
     * Sets the list of all vehicles to a specified list.
     * @param vehicles
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicleList = vehicles;
    }

    /**
     * Gets the vehicles in the repository that need a checkup.
     * @param checkupRepository The checkup repository.
     * @return The list of vehicles that need a checkup.
     */
    public ArrayList<Vehicle> getVehiclesNeedingCheckup(CheckupRepository checkupRepository){

        List<Vehicle> vehicleList = getVehicleList();
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for(Vehicle vehicle : vehicleList){
            if(needsCheckup(vehicle, checkupRepository)){
                vehiclesNeedingCheckup.add(vehicle);
            }
        }

        return vehiclesNeedingCheckup;
    }

    /**
     * Creates a new vehicle and adds it to the list of vehicles.
     * @param plateId The plate ID of the vehicle.
     * @param brand The brand of the vehicle.
     * @param model The model of the vehicle.
     * @param type The type of the vehicle.
     * @param tare The tare of the vehicle.
     * @param grossWeight The gross weight of the vehicle.
     * @param currentKm The current kilometers of the vehicle.
     * @param registerDate The registration date of the vehicle.
     * @param acquisitionDate The acquisition date of the vehicle.
     * @param checkupFrequencyKms The checkup frequency of the vehicle in KMs.
     * @return An optional with the created vehicle if the operation was successful, an empty optional otherwise.
     */
    public Optional<Vehicle> createVehicle(String plateId, String brand, String model, String type, double tare, double grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequencyKms) {

        Vehicle vehicle = new Vehicle(
                plateId,
                brand,
                model,
                type,
                tare,
                grossWeight,
                currentKm,
                registerDate,
                acquisitionDate,
                checkupFrequencyKms
        );

        this.add(vehicle);
        return Optional.of(vehicle);

    }

    /**
     * Adds a vehicle to the list of vehicles.
     * @param vehicle The vehicle to be added.
     * @return An optional with the added vehicle if the operation was successful, an empty optional otherwise.
     */
    public Optional<Vehicle> add(Vehicle vehicle) {
        Optional<Vehicle> newVehicle = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicle(newVehicle)) {
            newVehicle = Optional.of(vehicle.clone());
            operationSuccess = vehicleList.add(newVehicle.get());
        }

        if (!operationSuccess) {
            newVehicle = Optional.empty();
        }

        return newVehicle;
    }

    /**
     * Validates a vehicle.
     * @param vehicle
     * @return
     */
    private boolean validateVehicle(Optional<Vehicle> vehicle) {
        boolean isValid = true; //TODO: this is here to avoid compilation errors, but it should be implemented

        return isValid;
    }

    /**
     * Checks if a vehicle needs a checkup. Making sure that it excedes the checkup frequency since last checkup or is close to it by 5%.
     * @param vehicle
     * @param checkupRepository
     * @return true if the vehicle needs a checkup, false if it doesn't.
     */
    public boolean needsCheckup(Vehicle vehicle, CheckupRepository checkupRepository){

        int lastCheckupKm = checkupRepository.getLastCheckupKm(vehicle);
        int checkUpThresholdKm = lastCheckupKm + vehicle.getCheckupFrequencyKms();
        checkUpThresholdKm -= (int) (checkUpThresholdKm * 0.05);

        return vehicle.getCurrentKm() >= checkUpThresholdKm;
    }

    /**
     * Gets a vehicle by its plate ID.
     * @param plateID The plate ID of the vehicle.
     * @return The vehicle with the specified plate ID if it exists, null otherwise.
     */
    public Vehicle getVehicleByPlateId(String plateID) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlateId().equals(plateID)) {
                return vehicle;
            }
        }
        return null;
    }

}
