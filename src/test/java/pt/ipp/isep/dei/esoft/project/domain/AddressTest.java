package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void ensureStreetCantBeEmptyOrNullTest() {
        String[] invalidStreets = {"", null};

        for (String street : invalidStreets) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address(street, 123, "4450-123", "Matosinhos", "Porto");
            });
            assertTrue(exception.getMessage().contains("Street must not be empty!"));
        }
    }

    @Test
    public void ensurePostalCodeCantBeEmptyOrNullTest() {
        String[] invalidPostalCodes = {"", null};

        for (String postalCode : invalidPostalCodes) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Rua das travessas", 123, postalCode, "Matosinhos", "Porto");
            });
            assertTrue(exception.getMessage().contains("Postal code must not be empty"));
        }
    }

    @Test
    public void ensurePostalCodeDoesNotContainLettersTest() {
        IllegalArgumentException exceptionCONTAIN_LETTERS1 = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Rua das travessas", 123, "123A-567", "Matosinhos", "Porto");
        });

        assertTrue(exceptionCONTAIN_LETTERS1.getMessage().contains("Postal code must not contain letters"));

        IllegalArgumentException exceptionCONTAIN_LETTERS2 = assertThrows(IllegalArgumentException.class, () -> {
            new Address("Rua das travessas", 123, "1j3A-5bC", "Matosinhos", "Porto");
        });

        assertTrue(exceptionCONTAIN_LETTERS2.getMessage().contains("Postal code must not contain letters"));
    }

    @Test
    public void ensurePostalCodeIsValidTest() {
        Address address = new Address("Rua das travessas", 123, "1234-678", "Matosinhos", "Porto");

        assertNotNull(address);
    }

    @Test
    public void ensurePostalCodeCantHaveInvalidFormatTest() {
        String[] invalidPostalCodes = {"1234567", "12343-567", "12-67", "1234-67", "123-673", "1-3", "1111111", "1234/123"};

        for (String postalCode : invalidPostalCodes) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Rua das travessas", 123, postalCode, "Matosinhos", "Porto");
            });
            assertTrue(exception.getMessage().contains("Postal code must follow the format XXXX-XXX"));
        }
    }

    @Test
    public void ensureUpdatingFieldWithInvalidValueTest() {
        Address address = new Address("Rua das travessas", 123, "1235-678", "Matosinhos", "Porto");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            address.setPostalCode("2234-2343");
        });
        assertTrue(exception.getMessage().contains("Postal code must follow the format XXXX-XXX"));
    }

    @Test
    public void ensureCityCantBeNullOrEmptyTest() {
        String[] invalidCitys = {"", null};

        for (String city : invalidCitys) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Rua das travessas", 123, "1223-111", city, "Porto");
            });
            assertTrue(exception.getMessage().contains("City is empty"));
        }
    }

    @Test
    public void ensureDistrictCantBeNullOrEmptyTest() {
        String[] invalidDistricts = {"", null, " "};

        for (String district : invalidDistricts) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Rua das travessas", 123, "1223-111", "S.M.Feira", district);
            });
            assertTrue(exception.getMessage().contains("District is empty"));
        }
    }

    @Test
    public void ensureStreetNumberMustBePositiveIntegerTest() {
        Integer[] invalidStreetNumbers = {0, -1, -982};

        for (Integer streetNumber : invalidStreetNumbers) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Address("Rua das travessas", streetNumber, "1223-111", "S.M.Feira", "Porto");
            });
            assertTrue(exception.getMessage().contains("Street number must be a positive integer!"));
        }
    }

    @Test
    public void ensureAllFieldsAreValidTest() {
        Address address = new Address("Rua das travessas", 123, "1345-678", "Matosinhos", "Porto");
        assertNotNull(address);
    }

    @Test
    public void ensureGetStreetReturnsCorrectValue() {
        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");
        assertEquals("Rua das travessas", address.getStreet());
    }

    @Test
    public void ensureGetStreetNumberReturnsCorrectValue() {
        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");
        assertEquals(123, address.getStreetNumber());
    }

    @Test
    public void ensureGetPostalCodeReturnsCorrectValue() {
        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");
        assertEquals("1223-111", address.getPostalCode());
    }

    @Test
    public void ensureGetCityReturnsCorrectValue() {
        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");
        assertEquals("Matosinhos", address.getCity());
    }

    @Test
    public void ensureGetDistrictReturnsCorrectValue() {
        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");
        assertEquals("Porto", address.getDistrict());
    }

    @Test
    public void testToString() {

        Address address = new Address("Rua das travessas", 123, "1223-111", "Matosinhos", "Porto");

        String expectedString = "  Street: Rua das travessas\n" +
                "  Street Number: 123\n" +
                "  Postal Code: 1223-111\n" +
                "  City: Matosinhos\n" +
                "  District: Porto";

        assertEquals(expectedString, address.toString());
    }

}