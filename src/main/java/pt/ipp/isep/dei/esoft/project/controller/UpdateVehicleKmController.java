package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import java.util.List;
import java.util.Optional;

public class UpdateVehicleKmController {

    /**
     * VehicleRepository
     */
    private VehicleRepository vehicleRepository;

    /**
     * Constructor of the RegisterVehicleController
     */
    public UpdateVehicleKmController() { getVehicleRepository(); }

    /**
     * Constructor of the RegisterVehicleController that receives a VehicleRepository
     * @param vehicleRepository VehicleRepository
     */
    public UpdateVehicleKmController(VehicleRepository vehicleRepository) { this.vehicleRepository = vehicleRepository; }

    /**
     * Get the VehicleRepository
     * @return VehicleRepository
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
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
