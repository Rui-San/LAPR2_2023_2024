package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testGetPlateId() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals("AA-00-00", vehicle.getPlateId());
    }

    @Test
    void testGetBrand() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals("Vehicle Brand", vehicle.getBrand());
    }

    @Test
    void testGetModel() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals("Vehicle Model", vehicle.getModel());
    }

    @Test
    void testGetType() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals("1", vehicle.getType());
    }

    @Test
    void testGetTare() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(10, vehicle.getTare());
    }

    @Test
    void testGetGrossWeight() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(10, vehicle.getGrossWeight());
    }

    @Test
    void testGetCurrentKm() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(1000, vehicle.getCurrentKm());
    }

    @Test
    void testGetRegisterDate() {
        Date registerDate = new Date();
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, registerDate, new Date(), 100);
        assertEquals(registerDate, vehicle.getRegisterDate());
    }

    @Test
    void testGetAcquisitionDate() {
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), acquisitionDate, 100);
        assertEquals(acquisitionDate, vehicle.getAcquisitionDate());
    }

    @Test
    void testGetCheckupFrequencyKms() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(100, vehicle.getCheckupFrequencyKms());
    }

    @Test
    void testSetPlateId() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        vehicle.setPlateId("BB-00-00");
        assertEquals("BB-00-00", vehicle.getPlateId());
    }

    @Test
    void ensureSetPlateIdNotNullOrEmpty(){
        String[] plateIds = {null, ""};
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        for (String plateId : plateIds) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                vehicle.setPlateId(plateId);
            });
            assertEquals("Plate ID cannot be null or empty.", exception.getMessage());
        }
    }

    @Test
    void testEquals() {
        Vehicle vehicle1 = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        Vehicle vehicle2 = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(vehicle1, vehicle2);
    }

    @Test
    void testHashCode() {
        Vehicle vehicle1 = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        Vehicle vehicle2 = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        assertEquals(vehicle1.hashCode(), vehicle2.hashCode());
    }

    @Test
    void testClone() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date(), new Date(), 100);
        Vehicle clonedVehicle = vehicle.clone();
        assertEquals(vehicle, clonedVehicle);
    }
}