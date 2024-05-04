package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void ensureValidDateIsCreatedTest() {
        Date date = new Date("02/10/2024");

        String expectedDate = "02/10/2024";
        String actualDate = date.toString();

        assertEquals(actualDate,expectedDate);
    }

    @Test
    public void ensureDateCantHaveInvalidFormat(){

        String[] invalidFormatDate = {"02-10-2000","02/10-2000", "02-10/2000", "02/10", "10/2023"};

        for(String invalidDate : invalidFormatDate){
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(invalidDate);
            });
            assertTrue(exception.getMessage().contains("Date must follow the format DD/MM/YYYY"));
        }
    }


}
