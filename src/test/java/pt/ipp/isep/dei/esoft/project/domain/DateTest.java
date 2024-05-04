package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void ensureValidDateIsCreatedTest() {
        Date date = new Date("02/10/2024");

        String expectedDate = "02/10/2024";
        String actualDate = date.toString();

        assertEquals(actualDate, expectedDate);
    }

    @Test
    public void ensureDateCantHaveInvalidFormat() {

        String[] invalidFormatDate = {"02-10-2000", "02/10-2000", "02-10/2000", "02/10", "10/2023"};

        for (String invalidDate : invalidFormatDate) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(invalidDate);
            });
            assertTrue(exception.getMessage().contains("Date must follow the format DD/MM/YYYY"));
        }
    }

    @Test
    public void ensureDateCantHaveBeEmptyOrNull() {

        String[] invalidFormatDate = {"", " ", null};

        for (String invalidDate : invalidFormatDate) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(invalidDate);
            });
            assertTrue(exception.getMessage().contains("Date must not be empty!"));
        }
    }

    @Test
    public void ensureInvalidDayIsNotPermited() {

        String[] invalidDays = {"00/12/2023", "-02/12/2023", "32/12/2023"};

        for (String date : invalidDays) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(date);
            });
            assertTrue(exception.getMessage().contains("The provided day is invalid"));
        }
    }

    @Test
    public void ensureDay29InNonLeapYearIsNotPermited() {

        String[] invalidDays = {"29/2/2023", "29/2/2022"};

        for (String date : invalidDays) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(date);
            });
            assertTrue(exception.getMessage().contains("The provided day is invalid"));
        }
    }

    @Test
    public void ensureFebruaryCantHaveMoreThan29DaysInLeapYear() {

        String[] invalidDays = {"30/2/2024", "31/2/2024"};

        for (String date : invalidDays) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(date);
            });
            assertTrue(exception.getMessage().contains("The provided day is invalid"));
        }
    }

    @Test
    public void ensureFebruaryCantHaveMoreThan28DaysInNonLeapYear() {

        String[] invalidDays = {"29/2/2023", "30/02/2023"};

        for (String date : invalidDays) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(date);
            });
            assertTrue(exception.getMessage().contains("The provided day is invalid"));
        }
    }

    @Test
    public void ensureYearMustBeHigherThanMinimumYear() {

        String invalidYear = "23/2/1899";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date(invalidYear);
        });
        assertTrue(exception.getMessage().contains("The provided year is invalid"));
    }

    @Test
    public void ensureMonthCantBeInvalid() {

        String[] invalidDays = {"10/0/2023", "12/-1/2023", "8/13/2023"};

        for (String date : invalidDays) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Date(date);
            });
            assertTrue(exception.getMessage().contains("The provided month is invalid"));
        }
    }

    @Test
    public void ensureGetDayReturnsCorrectValue() {
        Date date = new Date("23/2/2009");

        int expectedResult = 23;
        int actualResult = date.getDay();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureGetMonthReturnsCorrectValue() {
        Date date = new Date("23/2/2009");

        int expectedResult = 2;
        int actualResult = date.getMonth();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureGetYearReturnsCorrectValue() {
        Date date = new Date("23/2/2009");

        int expectedResult = 2009;
        int actualResult = date.getYear();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureToStringReturnsCorrectValue() {
        Date date1 = new Date("23/2/2009");
        Date date2 = new Date("23/02/2009");

        List<Date> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);

        for (Date date : dateList) {
            String expectedResult = "23/02/2009";
            String actualResult = date.toString();
            assertEquals(expectedResult, actualResult);
        }
    }

    @Test
    public void testCompareTo_SameDate() {
        Date date1 = new Date("10/05/2024");
        Date date2 = new Date("10/05/2024");

        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    public void testCompareTo_Date1BeforeDate2() {
        Date date1 = new Date("10/04/2024");
        Date date2 = new Date("10/05/2024");

        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    public void testCompareTo_Date1AfterDate2() {
        Date date1 = new Date("10/06/2024");
        Date date2 = new Date("10/05/2024");

        assertEquals(1, date1.compareTo(date2));
    }

    @Test
    public void ensureCloneWorksTest() {

        Date date1 = new Date("10/06/2024");
        Date date2 = date1.clone();

        assertEquals(date1.toString(), date2.toString());

    }

    @Test
    public void testIsPastDate_FutureDate() {
        Date futureDate = new Date("10/06/2024");
        assertFalse(futureDate.isPastDate());
    }

    @Test
    public void testIsPastDate_PastDate() {
        Date pastDate = new Date("10/05/2020");
        assertTrue(pastDate.isPastDate());
    }

    @Test
    public void testIsPastDate_CurrentDate() {
        LocalDate today = LocalDate.now();
        String todayDateString = String.format("%02d/%02d/%d", today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        Date currentDate = new Date(todayDateString);
        assertFalse(currentDate.isPastDate());
    }

}
