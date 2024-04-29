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

        //This is for testing purposes
        Vehicle vehicle1 = new Vehicle("11-11-11", "Toyota", "Yaris", "Prime", 1000, 1000, 2000, new Date("1/10/2003"), new Date("2/3/2000"), 500);
        VehicleCheckup checkup1 = new VehicleCheckup(vehicle1, new Date("1/10/2003"), 1500); //exceeds

        Vehicle vehicle2 = new Vehicle("22-22-22", "Honda", "Civic", "1.2", 2000, 2000, 1900, new Date("1/10/2003"), new Date("2/3/2000"), 500);
        VehicleCheckup checkup2 = new VehicleCheckup(vehicle2, new Date("1/10/2003"), 1500); //is close by 5%

        Vehicle vehicle3 = new Vehicle("33-33-33", "Suzuki", "Shin", "Type 3", 2000, 2000, 1500, new Date("1/10/2003"), new Date("2/3/2000"), 100);
        VehicleCheckup checkup3 = new VehicleCheckup(vehicle3, new Date("1/10/2003"), 1500); //doesn't need checkup

        ArrayList<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicle1);
        vehiclesTest.add(vehicle2);
        vehiclesTest.add(vehicle3);

        getCheckupRepository().add(checkup1);
        getCheckupRepository().add(checkup2);
        getCheckupRepository().add(checkup3);
        getVehicleRepository().setVehicles(vehiclesTest);

        return getVehicleRepository().getVehiclesNeedingCheckup(getCheckupRepository());

    }

}
