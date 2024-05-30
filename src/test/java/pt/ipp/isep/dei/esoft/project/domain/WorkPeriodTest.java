package pt.ipp.isep.dei.esoft.project.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class WorkPeriodTest {

    private Date startDate;
    private TaskDuration taskDuration;

    @BeforeEach
    public void setUp() {
        startDate = new Date("30/05/2024");
        taskDuration = new TaskDuration(0,8,0);
    }

    @Test
    public void testCalculateEndDate() {
        Date startDate = new Date("30/05/2024");
        TaskDuration taskDuration = new TaskDuration(0,8,0);

        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, taskDuration);


        assertEquals(new Date("30/05/2024").toString().trim(), workPeriod.getWorkEndDate().toString().trim());
        assertEquals(17, workPeriod.getWorkEndHour());
        assertEquals(0, workPeriod.getWorkEndMin());
    }

    @Test
    public void testOverlapsWith_SimpleCase() {
        Date startDate = new Date("31/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0, 1, 0));
        WorkPeriod period2 = new WorkPeriod(startDate, 8, 30, new TaskDuration(0, 1, 0));

        assertTrue(period2.isOverlap(period1));
    }

    @Test
    public void testOverlapsWith_DifferentDayOverlap() {
        WorkPeriod period1 = new WorkPeriod(startDate, 14, 0, new TaskDuration(0,4,0));
        Date nextDay = new Date("31/05/2024");
        WorkPeriod period2 = new WorkPeriod(nextDay, 8, 0, new TaskDuration(0,1,0));

        assertFalse(period1.overlapsWith(period2));
    }

    @Test
    public void testOverlapsWith_NoOverlap() {
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0,3,0));
        WorkPeriod period2 = new WorkPeriod(startDate, 12, 0, new TaskDuration(0,3,0));

        assertFalse(period1.overlapsWith(period2));
    }

    @Test
    public void testMatches_SamePeriod() {
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, taskDuration);
        WorkPeriod period2 = new WorkPeriod(startDate, 8, 0, taskDuration);

        assertTrue(period1.matches(period2));
    }

    @Test
    public void testMatches_DifferentPeriod() {
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, taskDuration);
        Date differentDate = new Date("01/06/2024");
        WorkPeriod period2 = new WorkPeriod(differentDate, 8, 0, taskDuration);

        assertFalse(period1.matches(period2));
    }

    @Test
    public void testIsOverlapDates_True() {
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0,10,0));
        Date nextDay = new Date("31/05/2024");
        WorkPeriod period2 = new WorkPeriod(nextDay, 9, 0, new TaskDuration(0,2,0));

        assertTrue(period1.isOverlapDates(period2));
    }

    @Test
    public void testIsOverlapDates_False() {
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0,3,0));
        Date nextDay = new Date("31/05/2024");
        WorkPeriod period2 = new WorkPeriod(nextDay, 8, 0, new TaskDuration(0,2,0));

        assertFalse(period1.isOverlapDates(period2));
    }

    @Test
    public void testIsOverlap_PartialOverlapSameDay() {
        Date startDate = new Date("30/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0, 3, 0));
        WorkPeriod period2 = new WorkPeriod(startDate, 10, 0, new TaskDuration(0, 3, 0));

        assertTrue(period1.isOverlap(period2));
    }

    @Test
    public void testIsOverlap_TotalOverlapSameDay() {
        Date startDate = new Date("30/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0, 5, 0));
        WorkPeriod period2 = new WorkPeriod(startDate, 9, 0, new TaskDuration(0, 2, 0));

        assertTrue(period1.isOverlap(period2));
    }

    @Test
    public void testIsOverlap_NoOverlapSameDay() {
        Date startDate = new Date("30/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate, 8, 0, new TaskDuration(0, 2, 0));
        WorkPeriod period2 = new WorkPeriod(startDate, 10, 0, new TaskDuration(0, 2, 0));

        assertFalse(period1.isOverlap(period2));
    }

    @Test
    public void testIsOverlap_PartialOverlapDifferentDays() {
        Date startDate1 = new Date("30/05/2024");
        Date startDate2 = new Date("31/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate1, 16, 0, new TaskDuration(0, 5, 0));
        WorkPeriod period2 = new WorkPeriod(startDate2, 8, 0, new TaskDuration(0, 3, 0));

        assertTrue(period1.isOverlap(period2));
    }

    @Test
    public void testIsOverlap_NoOverlapDifferentDays() {
        Date startDate1 = new Date("30/05/2024");
        Date startDate2 = new Date("31/05/2024");
        WorkPeriod period1 = new WorkPeriod(startDate1, 8, 0, new TaskDuration(0, 2, 0));
        WorkPeriod period2 = new WorkPeriod(startDate2, 8, 0, new TaskDuration(0, 2, 0));

        assertFalse(period1.isOverlap(period2));
    }
    @Test
    public void testGetWorkEndDate() {
        Date startDate = new Date("30/05/2024");
        TaskDuration taskDuration = new TaskDuration(0, 8, 0);

        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, taskDuration);

        Date expectedEndDate = new Date("30/05/2024");
        assertEquals(expectedEndDate.toString(), workPeriod.getWorkEndDate().toString());
    }

    @Test
    public void testGetWorkEndHour() {
        Date startDate = new Date("30/05/2024");
        TaskDuration taskDuration = new TaskDuration(0, 8, 0);

        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, taskDuration);

        int expectedEndHour = 17;
        assertEquals(expectedEndHour, workPeriod.getWorkEndHour());
    }

    @Test
    public void testGetWorkEndMin() {
        Date startDate = new Date("30/05/2024");
        TaskDuration taskDuration = new TaskDuration(0, 8, 0);

        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, taskDuration);


        int expectedEndMin = 0;
        assertEquals(expectedEndMin, workPeriod.getWorkEndMin());
    }

    @Test
    public void testCalculateEndDate2() {
        Date startDate = new Date("30/05/2024");
        TaskDuration taskDuration = new TaskDuration(0, 8, 0);
        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, taskDuration);

        assertEquals(new Date("30/05/2024").toString(), workPeriod.getWorkEndDate().toString());
        assertEquals(17, workPeriod.getWorkEndHour());
        assertEquals(0, workPeriod.getWorkEndMin());
    }

    @Test
    public void testConstructor() {

        Date startDate = new Date("30/05/2024");
        int startHour = 8;
        int startMin = 0;
        TaskDuration expectedDuration = new TaskDuration(0, 8, 0);


        WorkPeriod workPeriod = new WorkPeriod(startDate, startHour, startMin, expectedDuration);

        assertEquals(startDate, workPeriod.getWorkStartDate());
        assertEquals(startHour, workPeriod.getWorkStartHour());
        assertEquals(startMin, workPeriod.getWorkStartMin());
        assertEquals(new Date("30/05/2024").toString(), workPeriod.getWorkEndDate().toString());
        assertEquals(17, workPeriod.getWorkEndHour());
        assertEquals(0, workPeriod.getWorkEndMin());
    }

    @Test
    public void testGetWorkStartDate() {

        Date startDate = new Date("30/05/2024");
        TaskDuration expectedDuration = new TaskDuration(0, 8, 0);
        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, expectedDuration);

        Date actualStartDate = workPeriod.getWorkStartDate();

        assertEquals(startDate, actualStartDate);
    }

    @Test
    public void testGetWorkStartHour() {

        Date startDate = new Date("30/05/2024");
        TaskDuration expectedDuration = new TaskDuration(0, 8, 0);
        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, expectedDuration);

        int actualStartHour = workPeriod.getWorkStartHour();

        assertEquals(8, actualStartHour);
    }

    @Test
    public void testGetWorkStartMin() {

        Date startDate = new Date("30/05/2024");
        TaskDuration expectedDuration = new TaskDuration(0, 8, 0);
        WorkPeriod workPeriod = new WorkPeriod(startDate, 8, 0, expectedDuration);

        int actualStartMin = workPeriod.getWorkStartMin();

        assertEquals(0, actualStartMin);
    }
}
