package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
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
    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicleList);
    }

    /**
     * Sets the list of all vehicles to a specified list.
     * @param vehicles
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicleList = vehicles;
    }

    public ArrayList<Vehicle> getVehiclesNeedingCheckup(CheckupRepository checkupRepository){

        List<Vehicle> vehicleList = getVehicles();
        ArrayList<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for(Vehicle vehicle : vehicleList){
            if(needsCheckup(vehicle, checkupRepository)){
                vehiclesNeedingCheckup.add(vehicle);
            }
        }

        return vehiclesNeedingCheckup;
    }

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

}
