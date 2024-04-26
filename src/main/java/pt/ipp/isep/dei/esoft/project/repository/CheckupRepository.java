package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckupRepository {

    private final ArrayList<VehicleCheckup> vehicleCheckupList;

    /**
     * This is the constructor of the CheckupRepository class.
     */
    public CheckupRepository() {
        vehicleCheckupList = new ArrayList<>();
    }

    /**
     * This method returns the  list with all the checkups.
     * @return
     */
    public ArrayList<VehicleCheckup> getVehicleCheckupList() {
        return this.vehicleCheckupList;
    }

    /**
     * This method sets the checkup list to a specific list.
     * @param vehicleCheckupList
     */
    public void setVehicleCheckupList(ArrayList<VehicleCheckup> vehicleCheckupList) {
        this.vehicleCheckupList.clear();
        this.vehicleCheckupList.addAll(vehicleCheckupList);
    }

    /**
     * This method adds a new checkup to the checkup List
     * @param vehicleCheckup
     * @return
     */
    public Optional<VehicleCheckup> add(VehicleCheckup vehicleCheckup) {

        Optional<VehicleCheckup> newVehicleCheckup = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicleCheckup(vehicleCheckup)) {
            newVehicleCheckup = Optional.of(vehicleCheckup.clone());
            operationSuccess = vehicleCheckupList.add(newVehicleCheckup.get());
        }

        if (!operationSuccess) {
            newVehicleCheckup = Optional.empty();
        }

        return newVehicleCheckup;
    }

    /**
     * This method validates if the checkup is a duplicate.
     * @param vehicleCheckup
     * @return
     */
    private boolean validateVehicleCheckup(VehicleCheckup vehicleCheckup) {
        boolean isValid = !vehicleCheckupList.contains(vehicleCheckup);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<VehicleCheckup> getVehicleCheckups() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(vehicleCheckupList);
    }

    /**
     * This
     * @param vehicle
     * @param checkupDateString
     * @param checkupKms
     */
    public Optional<VehicleCheckup> registerVehicleCheckup(Vehicle vehicle, String checkupDateString, int checkupKms){
        String[] dateParts = checkupDateString.split("/");
        Date checkupDate = new Date(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
        VehicleCheckup checkup = new VehicleCheckup(vehicle, checkupDate, checkupKms);
        this.add(checkup);
        return Optional.of(checkup);
    }

}
