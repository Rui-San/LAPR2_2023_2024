package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void ensureStreetCantBeEmptyOrNullTest() {
        String[] invalidStreets = {"", null};

        for (String street : invalidStreets) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address(street, 123, "4450-123", "City", "District");
            });
            assertTrue(exception.getMessage().contains("Street must not be empty!"));
        }
    }

    @Test
    public void ensurePostalCodeCantBeEmptyOrNullTest() {
        String[] invalidPostalCodes = {"", null};

        for (String postalCode : invalidPostalCodes) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Main St", 123, postalCode, "City", "District");
            });
            assertTrue(exception.getMessage().contains("Postal code must not be empty"));
        }
    }

    @Test
    public void ensurePostalCodeDoesNotContainLettersTest() {
        IllegalArgumentException exceptionCONTAIN_LETTERS1 = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Main St", 123, "123A-567", "City", "District");
        });

        assertTrue(exceptionCONTAIN_LETTERS1.getMessage().contains("Postal code must not contain letters"));

        IllegalArgumentException exceptionCONTAIN_LETTERS2 = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Main St", 123, "1j3A-5bC", "City", "District");
        });

        assertTrue(exceptionCONTAIN_LETTERS2.getMessage().contains("Postal code must not contain letters"));
    }

    @Test
    public void ensurePostalCodeIsValidTest() {
        Address address = new Address("Main St", 123, "1234-678", "City", "District");

        assertNotNull(address);
    }

    @Test
    public void ensurePostalCodeCantHaveInvalidFormatTest() {
        String[] invalidPostalCodes = {"1234567", "12343-567", "12-67", "1234-67", "123-673", "1-3", "1111111", "1234/123"};

        for (String postalCode : invalidPostalCodes) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Main St", 123, postalCode, "City", "District");
            });
            assertTrue(exception.getMessage().contains("Postal code must follow the format XXXX-XXX"));
        }
    }

    @Test
    public void ensureCityCantBeNullOrEmptyTest() {
        String[] invalidCitys = {"", null};

        for (String city : invalidCitys) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Main St", 123, "1223-111", city, "District");
            });
            assertTrue(exception.getMessage().contains("City must not be empty"));
        }
    }

    @Test
    public void ensureDistrictCantBeNullOrEmptyTest() {
        String[] invalidDistricts = {"", null, " "};

        for (String district : invalidDistricts) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Main St", 123, "1223-111", "S.M.Feira", district);
            });
            assertTrue(exception.getMessage().contains("District must not be empty"));
        }
    }

    @Test
    public void ensureStreetNumberMustBePositiveIntegerTest() {
        Integer[] invalidStreetNumbers = {0, -1, -982};

        for (Integer streetNumber : invalidStreetNumbers) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Main St", streetNumber, "1223-111", "S.M.Feira", "district");
            });
            assertTrue(exception.getMessage().contains("Street number must be a positive integer!"));
        }
    }

}