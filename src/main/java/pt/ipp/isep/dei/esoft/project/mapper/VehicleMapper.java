package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle) {
        return new VehicleDTO(vehicle);
    }

    public static Vehicle toDomain(VehicleDTO vehicleDTO){
        return Repositories.getInstance().getVehicleRepository().getVehicleByPlateId(vehicleDTO.plateID);
    }

    public static List<VehicleDTO> toDTOList(List<Vehicle> vehicles){
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        vehicles.forEach(vehicle ->
                vehicleDTOList.add(toDTO(vehicle)
                ));
        return vehicleDTOList;
    }

    public static List<Vehicle> toDomainList(List<VehicleDTO> vehicleDTOList){
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleDTOList.forEach(vehicleDTO ->
                vehicleList.add(toDomain(vehicleDTO)
                ));
        return vehicleList;
    }

}
