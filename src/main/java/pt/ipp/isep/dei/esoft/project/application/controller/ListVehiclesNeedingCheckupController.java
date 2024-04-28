package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListVehiclesNeedingCheckupController {

    private CheckupRepository checkupRepository;
    private VehicleRepository vehicleRepository;

    //Repository instances are obtained from the Repositories class
    public ListVehiclesNeedingCheckupController() {
        getCheckupRepository();
        getVehicleRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListVehiclesNeedingCheckupController(CheckupRepository checkupRepository,
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

    public List<Vehicle> getVehiclesNeedingCheckup(){

        //This is a mockup
        Vehicle vehicle1 = new Vehicle("11-11-11", "Toyota", "Yaris", "Prime", 1000, 1000, 1000, new Date("1/10/2003"), new Date("2/3/2000"), 1000);
        Vehicle vehicle2 = new Vehicle("22-22-22", "Honda", "Civic", "1.2", 2000, 2000, 2099, new Date("1/10/2003"), new Date("2/3/2000"), 2000);
        Vehicle vehicle3 = new Vehicle("33-33-33", "Suzuki", "Shin", "Type 3", 2000, 2000, 3000, new Date("1/10/2003"), new Date("2/3/2000"), 100);
        ArrayList<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicle1);
        vehiclesTest.add(vehicle2);
        vehiclesTest.add(vehicle3);
        getVehicleRepository().setVehicles(vehiclesTest);

        return getVehicleRepository().getVehiclesNeedingCheckup(getCheckupRepository());

    }

}
