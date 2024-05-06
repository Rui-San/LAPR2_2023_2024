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

    @Test
    void testGetCheckupkms(){
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        assertEquals(100, checkup.getCheckupKms());
    }

    @Test
    void testGetCheckupDate(){
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        assertEquals(new Date("10/12/2012"), checkup.getCheckupDate());
    }

    @Test
    void testSetVehicle(){
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        Vehicle vehicle2 = new Vehicle("BB-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        checkup.setVehicle(vehicle2);
        assertEquals(vehicle2, checkup.getVehicle());
    }

    @Test
    void testSetCheckupkms(){
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        checkup.setCheckupKms(200);
        assertEquals(200, checkup.getCheckupKms());
    }

    @Test
    void testSetCheckupDate(){
        Vehicle vehicle = new Vehicle("AA-00-00", "Vehicle Brand", "Vehicle Model", "1", 10, 10, 1000, new Date("10/12/2003"), new Date("10/12/2012"), 100 );
        VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("10/12/2012"), 100);
        checkup.setCheckupDate(new Date("10/12/2013"));
        assertEquals(new Date("10/12/2013"), checkup.getCheckupDate());
    }


}