package pt.ipp.isep.dei.esoft.project.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import static org.junit.jupiter.api.Assertions.*;

class VehicleNeedingCheckupTest {

    Vehicle vehicle;
    VehicleNeedingCheckup vehicleNeedingCheckup;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("00-11-AA", "Toyota", "Corolla", VehicleType.PASSENGERS, 1300, 1700, 10000, "15/03/1999", "01/04/2023", 10000);
        vehicleNeedingCheckup = new VehicleNeedingCheckup(vehicle, 5000, 15000);
    }

    @Test
    void getVehicle() {
        assertEquals(vehicle, vehicleNeedingCheckup.getVehicle());
    }

    @Test
    void getLastCheckupKm() {
        assertEquals(5000, vehicleNeedingCheckup.getLastCheckupKm());
    }

    @Test
    void getOptimalNextCheckupKm() {
        assertEquals(15000, vehicleNeedingCheckup.getOptimalNextCheckupKm());
    }

    @Test
    void setVehicle() {
        Vehicle newVehicle = new Vehicle("BB-22-BB", "Ford", "Mustang", VehicleType.PASSENGERS, 1700, 2000, 7500, "20/11/2022", "05/12/2022", 7500);
        vehicleNeedingCheckup.setVehicle(newVehicle);
        assertEquals(newVehicle, vehicleNeedingCheckup.getVehicle());
    }

    @Test
    void setLastCheckupKm() {
        vehicleNeedingCheckup.setLastCheckupKm(7000);
        assertEquals(7000, vehicleNeedingCheckup.getLastCheckupKm());
    }

    @Test
    void setOptimalNextCheckupKm() {
        vehicleNeedingCheckup.setOptimalNextCheckupKm(20000);
        assertEquals(20000, vehicleNeedingCheckup.getOptimalNextCheckupKm());
    }

    @Test
    void testToString() {
        String expected = "- 00-11-AA: Toyota Corolla | Current km: 10000km\n   (Last Checkup Km: 5000km | Checkup Frequency: every 10000km | Optimal next Checkup Km: 15000km )";
        assertEquals(expected, vehicleNeedingCheckup.toString());
    }
}
