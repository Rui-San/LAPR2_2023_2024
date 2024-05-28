package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testGetPlateId() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals("00-00-AA", vehicle.getPlateId());
    }

    @Test
    void testGetBrand() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals("Vehicle Brand", vehicle.getBrand());
    }

    @Test
    void testGetModel() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals("Vehicle Model", vehicle.getModel());
    }

    @Test
    void testGetType() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(VehicleType.PASSENGERS, vehicle.getType());
    }

    @Test
    void testGetTare() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(10, vehicle.getTare());
    }

    @Test
    void testGetGrossWeight() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(10, vehicle.getGrossWeight());
    }

    @Test
    void testGetCurrentKm() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(1000, vehicle.getCurrentKm());
    }

    @Test
    void testGetRegisterDate() {
        String registerDate = "10/12/2003";
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, registerDate, "10/12/2012", 100);
        assertEquals(registerDate, vehicle.getRegisterDate().toString());
    }

    @Test
    void testGetAcquisitionDate() {
        String acquisitionDate = "10/12/2012";
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", acquisitionDate, 100);
        assertEquals(acquisitionDate, vehicle.getAcquisitionDate().toString());
    }

    @Test
    void testGetCheckupFrequencyKms() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(100, vehicle.getCheckupFrequencyKms());
    }

    @Test
    void testSetPlateId() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        vehicle.setPlateId("00-00-BB");
        assertEquals("00-00-BB", vehicle.getPlateId());
    }

    @Test
    void ensureSetPlateIdNotNullOrEmpty() {
        String[] plateIds = {null, ""};
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        for (String plateId : plateIds) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                vehicle.setPlateId(plateId);
            });
            assertEquals("Plate ID cannot be null or empty.", exception.getMessage());
        }
    }

    @Test
    void testEquals() {
        Vehicle vehicle1 = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        Vehicle vehicle2 = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(vehicle1, vehicle2);
    }

    @Test
    void testHashCode() {
        Vehicle vehicle1 = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        Vehicle vehicle2 = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        assertEquals(vehicle1.hashCode(), vehicle2.hashCode());
    }

    @Test
    void testClone() {
        Vehicle vehicle = new Vehicle("00-00-AA", "Vehicle Brand", "Vehicle Model", VehicleType.PASSENGERS, 10, 10, 1000, "10/12/2003", "10/12/2012", 100);
        Vehicle clonedVehicle = vehicle.clone();
        assertEquals(vehicle, clonedVehicle);
    }
}

