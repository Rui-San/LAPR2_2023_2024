package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleCheckupTest {

    @Test
    void ensureCheckupIsCreatedSuccessfully() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
    }

    @Test
    void ensureCheckupIsNotCreatedIfVehicleIsNull() {
        Vehicle vehicle = null;
        VehicleCheckup checkup = null;
        try {
            checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException ia) {
            assertNull(checkup);
        }
    }

    @Test
    void ensureCheckupkmsAreLessThanVehicleKms() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = null;
        try {
            checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 1100);
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException ia) {
            assertNull(checkup);
        }
    }

    @Test
    void testGetVehicle() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        assertEquals(vehicle, checkup.getVehicle());
    }


}