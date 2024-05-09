package pt.ipp.isep.dei.esoft.project.controller;


import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

public class RegisterCheckupController {

    private CheckupRepository checkupRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Repository instances are obtained from the Repositories class
     */
    public RegisterCheckupController() {
        getCheckupRepository();
        getVehicleRepository();
    }

    /**
     * Allows receiving the repositories as parameters for testing purposes
     * @param checkupRepository the checkup repository
     * @param vehicleRepository the vehicle repository
     */
    public RegisterCheckupController(CheckupRepository checkupRepository,
                                VehicleRepository vehicleRepository) {
        this.checkupRepository = checkupRepository;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Get the checkup repository
     * @return the checkup repository
     */
    public CheckupRepository getCheckupRepository() {
        if (checkupRepository == null) {
            Repositories repositories = Repositories.getInstance();

            checkupRepository = repositories.getCheckupRepository();
        }
        return checkupRepository;
    }

    /**
     * Get the vehicle repository
     * @return the vehicle repository
     */
    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;

    }

    /**
     * Register a vehicle checkup
     * @param vehicle the vehicle
     * @param checkupDate the checkup date
     * @param checkupKm the checkup km
     * @return the vehicle checkup
     */
    public Optional<VehicleCheckup> registerVehicleCheckup(Vehicle vehicle, String checkupDate, int checkupKm) {

        Optional<VehicleCheckup> newCheckup = Optional.empty();

        newCheckup = checkupRepository.registerVehicleCheckup(vehicle, checkupDate, checkupKm);

        return newCheckup;
    }

    /**
     * Get the list of vehicles from the repository
     * @return the list of vehicles
     */
    public List<Vehicle> getVehicles() {
        VehicleRepository vehicleRepository = getVehicleRepository();
        return vehicleRepository.getVehicleList();
    }

}
