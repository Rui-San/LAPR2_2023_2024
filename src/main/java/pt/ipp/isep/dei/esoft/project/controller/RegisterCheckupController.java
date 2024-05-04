package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
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

    //Repository instances are obtained from the Repositories class
    public RegisterCheckupController() {
        getCheckupRepository();
        getVehicleRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterCheckupController(CheckupRepository checkupRepository,
                                VehicleRepository vehicleRepository) {
        this.checkupRepository = checkupRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public CheckupRepository getCheckupRepository() {
        if (checkupRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            checkupRepository = repositories.getCheckupRepository();
        }
        return checkupRepository;
    }

    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;

    }

    public Optional<VehicleCheckup> registerVehicleCheckup(Vehicle vehicle, String checkupDate, int checkupKm) {

        Optional<VehicleCheckup> newCheckup = Optional.empty();

        newCheckup = checkupRepository.registerVehicleCheckup(vehicle, checkupDate, checkupKm);

        return newCheckup;
    }

    //return the list of vehicles
    public List<Vehicle> getVehicles() {
        VehicleRepository vehicleRepository = getVehicleRepository();
        return vehicleRepository.getVehicles();
    }

}
