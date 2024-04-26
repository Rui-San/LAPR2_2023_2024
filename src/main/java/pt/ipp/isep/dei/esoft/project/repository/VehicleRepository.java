package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    /**
     * List of all vehicles.
     */
    private ArrayList<Vehicle> vehicles;

    /**
     * Creates an instance of VehicleRepository with the specified list of vehicles.
     * @param vehicles
     */
    public VehicleRepository(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Creates an instance of VehicleRepository with an empty list of vehicles.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    /**
     * Gets the list of all vehicles.
     * @return
     */
    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }

    /**
     * Sets the list of all vehicles to a specified list.
     * @param vehicles
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehiclesNeedingCheckup(){

        //TODO : implement
        return new ArrayList<>();
    }

    public boolean needCheckup(Vehicle vehicle){

        //TODO : implement
        return false;
    }

}
