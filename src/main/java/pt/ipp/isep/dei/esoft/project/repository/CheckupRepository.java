package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckupRepository implements Serializable {

    private final ArrayList<VehicleCheckup> vehicleCheckupList;

    /**
     * This is the constructor of the CheckupRepository class.
     */
    public CheckupRepository() {
        vehicleCheckupList = new ArrayList<>();
    }

    /**
     * This method returns the list with all the checkups.
     * @return
     */
    public ArrayList<VehicleCheckup> getVehicleCheckupList() { return this.vehicleCheckupList; }

    /**
     * This method sets the checkup list to a specific list.
     * @param vehicleCheckupList
     */
    public void setVehicleCheckupList(ArrayList<VehicleCheckup> vehicleCheckupList) {
        this.getVehicleCheckupList().clear();
        this.getVehicleCheckupList().addAll(vehicleCheckupList);
    }

    /**
     * This method adds a new checkup to the checkup List
     * @param vehicleCheckup The checkup to add to the list.
     * @return The checkup added to the list.
     */
    public Optional<VehicleCheckup> add(VehicleCheckup vehicleCheckup) {

        Optional<VehicleCheckup> newVehicleCheckup = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicleCheckup(vehicleCheckup)) {
            newVehicleCheckup = Optional.of(vehicleCheckup.clone());
            operationSuccess = getVehicleCheckupList().add(newVehicleCheckup.get());
        }

        if (!operationSuccess) {
            newVehicleCheckup = Optional.empty();
        }

        return newVehicleCheckup;
    }

    /**
     * This method validates if the checkup is a duplicate.
     * @param vehicleCheckup The checkup to validate.
     * @return True if the checkup is not a duplicate, else return false.
     */
    private boolean validateVehicleCheckup(VehicleCheckup vehicleCheckup) {
        boolean isValid = !getVehicleCheckupList().contains(vehicleCheckup);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<VehicleCheckup> getVehicleCheckups() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(getVehicleCheckupList());
    }

    /**
     * This method registers a new checkup for a vehicle.
     * @param vehicle The vehicle to register the checkup.
     * @param checkupDateString The date of the checkup.
     * @param checkupKm The km of the checkup.
     */
    public Optional<VehicleCheckup> registerVehicleCheckup(Vehicle vehicle, String checkupDateString, int checkupKm){
        Date checkupDate = new Date(checkupDateString);
        VehicleCheckup checkup = new VehicleCheckup(vehicle, checkupDate, checkupKm);
        this.add(checkup);
        return Optional.of(checkup);
    }

}
