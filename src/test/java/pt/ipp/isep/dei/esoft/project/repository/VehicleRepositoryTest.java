package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepositoryTest {

    private VehicleRepository vehicleRepository;
    private Vehicle vehicle;
    private VehicleCheckup vehicleCheckup;
    private CheckupRepository checkupRepository;

    @BeforeEach
    public void createData() {
        vehicleRepository = new VehicleRepository();
        vehicle = new Vehicle(
                "AA-00-WW",
                "Toyota",
                "Corolla",
                VehicleType.PASSENGERS,
                1500,
                2000,
                10000,
                "01/01/2023",
                "01/05/2024",
                5000
        );
        vehicleRepository.add(vehicle);
        checkupRepository = new CheckupRepository();
        vehicleCheckup = new VehicleCheckup(vehicle, new Date("01/01/2023"), 1500);
        checkupRepository.add(vehicleCheckup);
    }

    @Test
    public void testAddVehicle() {

        Vehicle vehicleToAdd = new Vehicle(
                "AA-00-PP",
                "Toyota",
                "Corolla",
                VehicleType.PASSENGERS,
                1500,
                2000,
                10000,
                "01/01/2023",
                "01/05/2024",
                5000
        );
        Optional<Vehicle> addedVehicle = vehicleRepository.add(vehicleToAdd);

        assertTrue(addedVehicle.isPresent());
    }

    @Test
    void testGetVehicleList() {

        List<Vehicle> vehicleList = vehicleRepository.getVehicleList();

        assertNotNull(vehicleList);
        assertEquals(1, vehicleList.size());
    }

    @Test
    void testGetVehicleByPlateID() {

        Vehicle retrievedVehicle = vehicleRepository.getVehicleByPlateId(vehicle.getPlateId());

        assertEquals(vehicle.getPlateId(), retrievedVehicle.getPlateId());
    }

    @Test
    void testGetVehiclesNeedingCheckup() {

        List<VehicleNeedingCheckup> vehiclesNeedingCheckup = vehicleRepository.getVehiclesNeedingCheckup(checkupRepository.getVehicleCheckups());

        assertNotNull(vehiclesNeedingCheckup);
        assertEquals(1, vehiclesNeedingCheckup.size());
    }

    @Test
    void testCreateVehicle() {

        Optional<Vehicle> addedVehicle = vehicleRepository.createVehicle(
                "UU-00-AA",
                "Toyota",
                "Corolla",
                VehicleType.PASSENGERS,
                1500,
                2000,
                10000,
                "01/01/2023",
                "01/05/2024",
                5000
        );

        assertTrue(addedVehicle.isPresent());
    }

    @Test
    void testGetLastCheckupKm() {

        int lastCheckupKm = vehicleRepository.getLastCheckupKm(vehicle, checkupRepository.getVehicleCheckups());

        assertEquals(1500, lastCheckupKm);
    }

    @Test
    void testNeedsCheckup() {

        int lastCheckupKm = vehicleRepository.getLastCheckupKm(vehicle, checkupRepository.getVehicleCheckups());
        int optimalNextCheckupKms = lastCheckupKm + vehicle.getCheckupFrequencyKms();
        boolean needsCheckup = vehicleRepository.needsCheckup(vehicle, lastCheckupKm, optimalNextCheckupKms);

        assertTrue(needsCheckup);

    }

    @Test
    void testUpdateVehicleKms() {
        int newKms = 12000;
        Optional<Vehicle> updatedVehicle = vehicleRepository.updateVehicleKms(vehicle.getPlateId(), newKms);

        assertTrue(updatedVehicle.isPresent());
        assertEquals(newKms, updatedVehicle.get().getCurrentKm());
    }




}