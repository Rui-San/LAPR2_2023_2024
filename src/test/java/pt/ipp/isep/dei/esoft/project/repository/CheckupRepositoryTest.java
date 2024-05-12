package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CheckupRepositoryTest {

    private CheckupRepository checkupRepository;
    private VehicleCheckup vc1;
    private VehicleCheckup vc2;
    private Vehicle vehicle1;

    @BeforeEach
    void createData() {

        vehicle1 = new Vehicle(
                "AA-00-AA",
                "Toyota",
                "Corolla",
                "Sedan",
                1500,
                2000,
                10000,
                "01/01/2021",
                "01/05/2022",
                5000
        );

        checkupRepository = new CheckupRepository();
        vc1 = new VehicleCheckup(vehicle1, new Date("10/07/2023"), 500);
        vc2 = new VehicleCheckup(vehicle1, new Date("09/01/2024"), 1500);
        checkupRepository.add(vc1);
        checkupRepository.add(vc2);
    }

    @Test
    void testGetVehicleCheckupList() {
        assertEquals(2, checkupRepository.getVehicleCheckupList().size());
    }

    @Test
    void testGetVehicleCheckups() {
        List<VehicleCheckup> lstVC = new ArrayList<>();
        lstVC.add(vc1);
        lstVC.add(vc2);
        assertEquals(lstVC, checkupRepository.getVehicleCheckups());
    }

    @Test
    void testRegisterVehicleCheckup() {
        Optional<VehicleCheckup> Vcopt = Optional.of(vc1);
        assertEquals(Vcopt, checkupRepository.registerVehicleCheckup(vehicle1, "10/07/2023", 500) );
    }

}