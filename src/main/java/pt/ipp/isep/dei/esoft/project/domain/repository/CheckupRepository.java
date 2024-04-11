package pt.ipp.isep.dei.esoft.project.domain.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckupRepository {

    private final List<VehicleCheckup> vehicleCheckups;
    public CheckupRepository() {
        vehicleCheckups = new ArrayList<>();
    }

    public Optional<VehicleCheckup> add(VehicleCheckup vehicleCheckup) {

        Optional<VehicleCheckup> newVehicleCheckup = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicleCheckup(vehicleCheckup)) {
            newVehicleCheckup = Optional.of(vehicleCheckup.clone());
            operationSuccess = vehicleCheckups.add(newVehicleCheckup.get());
        }

        if (!operationSuccess) {
            newVehicleCheckup = Optional.empty();
        }

        return newVehicleCheckup;
    }

    private boolean validateVehicleCheckup(VehicleCheckup vehicleCheckup) {
        boolean isValid = !vehicleCheckups.contains(vehicleCheckup);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<TaskCategory> getTaskCategories() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(taskCategories);
    }

}
