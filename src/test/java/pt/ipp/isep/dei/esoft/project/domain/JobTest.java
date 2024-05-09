package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest {

    @Test
    public void ensureCreateJobWorksTest() {
        Job job = new Job("Gardener");
    }

    @Test
    public void ensureGetJobNameReturnsCorrectValue() {
        Job job = new Job("Gardener");
        String expectedJob = "Gardener";
        assertEquals(job.getJobName(), expectedJob);
    }

    @Test
    public void ensureJobCantBeNullOrEmpty() {
        String[] incorrectJobNames = {"", " ", null};

        for (String invalidJobName : incorrectJobNames) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Job(invalidJobName);
            });
            assertTrue(exception.getMessage().contains("Job name must not be empty"));
        }
    }

    @Test
    public void ensureJobCantContainSpecialCharacters() {
        String[] incorrectJobNames = {"Jo´´bs", "jo123b", ".job"};

        for (String invalidJobName : incorrectJobNames) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Job(invalidJobName);
            });
            assertTrue(exception.getMessage().contains("Job name must not contain special characters or numbers"));
        }
    }

    @Test
    public void ensureCloneWorks() {
        Job job = new Job("Gardener");
        Job jobClone = job.clone();
        assertEquals(job.getJobName(), jobClone.getJobName());
    }
}
