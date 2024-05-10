package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

public class RegisterVehicleController {

    /**
     * VehicleRepository
     */
    private VehicleRepository vehicleRepository;

    /**
     * Constructor of the RegisterVehicleController
     */
    public RegisterVehicleController() { getVehicleRepository(); }

    /**
     * Constructor of the RegisterVehicleController that receives a VehicleRepository
     * @param vehicleRepository VehicleRepository
     */
    public RegisterVehicleController(VehicleRepository vehicleRepository) { this.vehicleRepository = vehicleRepository; }

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
     * Method that calls the creation of a new Vehicle
     * @param plateId plateId of the Vehicle
     * @param brand brand of the Vehicle
     * @param model model of the Vehicle
     * @param type type of the Vehicle
     * @param tare tare of the Vehicle
     * @param grossWeight grossWeight of the Vehicle
     * @param currentKm currentKm of the Vehicle
     * @param registerDate registerDate of the Vehicle
     * @param acquisitionDate acquisitionDate of the Vehicle
     * @param checkupFrequencyKms checkupFrequencyKms of the Vehicle
     * @return Vehicle created
     */
    public Optional<Vehicle> createVehicle(String plateId, String brand, String model, String type, double tare, double grossWeight, int currentKm, String registerDate, String acquisitionDate, int checkupFrequencyKms) {
        Optional<Vehicle> newVehicle = Optional.empty();

        newVehicle = getVehicleRepository().createVehicle(plateId, brand, model, type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkupFrequencyKms);

        return newVehicle;
    }

    /**
     * Method that calls the method of the VehicleRepository to get the Vehicle by the plateId
     * @param plateId plateId of the Vehicle
     * @return Vehicle with the plateId, null if the Vehicle does not exist
     */
    private Vehicle getVehicleByPlateId(String plateId) {
        VehicleRepository vehicleRepository = getVehicleRepository();

        Vehicle vehicle = vehicleRepository.getVehicleByPlateId(plateId);

        return vehicle;
    }

}
