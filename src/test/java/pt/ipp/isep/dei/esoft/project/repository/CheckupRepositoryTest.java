package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

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
                VehicleType.PASSENGERS,
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
        assertEquals(lstVC.size(), checkupRepository.getVehicleCheckups().size());

        for (int i = 0; i < lstVC.size(); i++) {
            assertEquals(lstVC.get(i).getVehicle(), checkupRepository.getVehicleCheckups().get(i).getVehicle());
            assertEquals(lstVC.get(i).getCheckupDate(), checkupRepository.getVehicleCheckups().get(i).getCheckupDate());
            assertEquals(lstVC.get(i).getCheckupKms(), checkupRepository.getVehicleCheckups().get(i).getCheckupKms());
        }
    }

    @Test
    void testRegisterVehicleCheckup() {
        Optional<VehicleCheckup> Vcopt = Optional.of(vc1);
        assertEquals(Vcopt.isPresent(), checkupRepository.registerVehicleCheckup(vehicle1, "10/07/2023", 500).isPresent() );
    }

}