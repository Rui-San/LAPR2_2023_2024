package pt.ipp.isep.dei.esoft.project.controller;


import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class ListVehiclesNeedingCheckupController {

    private CheckupRepository checkupRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Repository instances are obtained from the Repositories class
     */
    public ListVehiclesNeedingCheckupController() {
        getCheckupRepository();
        getVehicleRepository();
    }

    /**
     * Instantiates the controller with the repositories
     * @param checkupRepository the checkup repository
     * @param vehicleRepository the vehicle repository
     */
    public ListVehiclesNeedingCheckupController(CheckupRepository checkupRepository,
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

            //Get the TaskCategoryRepository
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
     * Gets the vehicles needing checkup
     * @return the vehicles needing checkup
     */
    public List<Vehicle> getVehiclesNeedingCheckup(){

        return getVehicleRepository().getVehiclesNeedingCheckup(getCheckupRepository());

    }

}
