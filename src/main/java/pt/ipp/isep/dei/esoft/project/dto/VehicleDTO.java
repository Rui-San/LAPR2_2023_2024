package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDTO {
    public String plateID;
    public String brand;
    public String model;
    public String type;

    public VehicleDTO(Vehicle vehicle) {
        this.plateID = vehicle.getPlateId();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.type = vehicle.getType().toString();
    }

}
