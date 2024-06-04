package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDurationTest {

    TaskDuration taskDuration;

    @BeforeEach
    void setUp() {
        taskDuration = new TaskDuration(2, 0, 0);
    }

    @Test
    void getDays() {
        int expectedResult = 2;
        int result = taskDuration.getDays();
        assertEquals(expectedResult, result);
    }

    @Test
    void setDays() {
        taskDuration.setDays(3);
        int expectedResult = 3;
        int result = taskDuration.getDays();
        assertEquals(expectedResult, result);
    }

    @Test
    void getHours() {
        int expectedResult = 0;
        int result = taskDuration.getHours();
        assertEquals(expectedResult, result);
    }

    @Test
    void setHours() {
        taskDuration.setHours(1);
        int expectedResult = 1;
        int result = taskDuration.getHours();
        assertEquals(expectedResult, result);
    }

    @Test
    void getMinutes() {
        int expectedResult = 0;
        int result = taskDuration.getMinutes();
        assertEquals(expectedResult, result);
    }

    @Test
    void setMinutes() {
        taskDuration.setMinutes(30);
        int expectedResult = 30;
        int result = taskDuration.getMinutes();
        assertEquals(expectedResult, result);
    }

    @Test
    void setTaskDuration() {
        taskDuration.setTaskDuration(3, 1, 30);
        int expectedResult = 3;
        int result = taskDuration.getDays();
        assertEquals(expectedResult, result);
        expectedResult = 1;
        result = taskDuration.getHours();
        assertEquals(expectedResult, result);
        expectedResult = 30;
        result = taskDuration.getMinutes();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalDurationMinutes() {
        int expectedResult = 960;
        int result = taskDuration.getTotalDurationMinutes();
        assertEquals(expectedResult, result);
    }
}