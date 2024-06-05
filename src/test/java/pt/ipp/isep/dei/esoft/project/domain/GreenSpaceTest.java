package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceTest {

    GreenSpace greenSpace;

    @BeforeEach
    void setUp() {
        greenSpace = new GreenSpace(GreenSpaceType.MEDIUM, "Parque Teste", "Rua Teste", 8, "4000-100", "Porto", "Porto", 2000.0, "gsm1@this.app");
    }

    @Test
    void getType() {
        GreenSpaceType expectedResult = GreenSpaceType.MEDIUM;
        GreenSpaceType result = greenSpace.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void setType() {
        GreenSpaceType expectedResult = GreenSpaceType.LARGE;
        greenSpace.setType(GreenSpaceType.LARGE);
        GreenSpaceType result = greenSpace.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void getName() {
        String expectedResult = "Parque Teste";
        String result = greenSpace.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void setName() {
        String expectedResult = "Parque Teste Two";
        greenSpace.setName("Parque Teste Two");
        String result = greenSpace.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalArea() {
        Double expectedResult = 2000.0;
        Double result = greenSpace.getTotalArea();
        assertEquals(expectedResult, result);
    }

    @Test
    void setTotalArea() {
        Double expectedResult = 3000.0;
        greenSpace.setTotalArea(3000.0);
        Double result = greenSpace.getTotalArea();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAddress() {
        Address expectedResult = new Address("Rua Teste", 8, "4000-100", "Porto", "Porto");
        Address result = greenSpace.getAddress();
        assertEquals(isEqualAddress(expectedResult,result), true);
    }

    @Test
    void setAddress() {
        Address expectedResult = new Address("Rua Teste Two", 9, "4000-100", "Porto", "Porto");
        greenSpace.setAddress("Rua Teste Two", 9, "4000-100", "Porto", "Porto");
        Address result = greenSpace.getAddress();
        assertEquals(isEqualAddress(expectedResult,result), true);
    }

    boolean isEqualAddress(Address expectedResult, Address result) {
        return expectedResult.getCity() == result.getCity()
                && expectedResult.getDistrict() == result.getDistrict()
                && expectedResult.getPostalCode() == result.getPostalCode()
                && expectedResult.getStreet() == result.getStreet()
                && expectedResult.getStreetNumber() == result.getStreetNumber();
    }

    @Test
    void getManager() {
        String expectedResult = "gsm1@this.app";
        String result = greenSpace.getManager();
        assertEquals(expectedResult, result);
    }

}