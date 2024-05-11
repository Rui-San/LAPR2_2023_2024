package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobRepositoryTest {
    @Test
    void testAddValidJob() {
        JobRepository jobRepository = new JobRepository();
        Job job = new Job("Manager");
        Optional<Job> addedJob = jobRepository.add(job);
        assertTrue(addedJob.isPresent());
    }

    @Test
    void testAddDuplicateJob() {
        JobRepository jobRepository = new JobRepository();

        Job job1 = new Job("Manager");
        Job job2 = new Job("Manager");

        jobRepository.add(job1);

        Optional<Job> addedJob = jobRepository.add(job2);
        assertTrue(addedJob.isEmpty());
        assertTrue(jobRepository.getJobList().size() == 1);
    }

    @Test
    void testGetJobList() {
        JobRepository jobRepository = new JobRepository();

        Job job1 = new Job("Manager");
        Job job2 = new Job("Engineer");

        jobRepository.add(job1);
        jobRepository.add(job2);

        List<Job> jobs = jobRepository.getJobList();
        assertEquals(2, jobs.size());

        for (Job job : jobs) {
            assertTrue(job.getJobName().equals(job1.getJobName()) || job.getJobName().equals(job2.getJobName()));

        }
    }

    @Test
    void testJobListImmutability() {
        JobRepository jobRepository = new JobRepository();
        List<Job> jobs = jobRepository.getJobList();
        assertThrows(UnsupportedOperationException.class, () -> jobs.add(new Job("Manager")));
    }

    @Test
    void testRegisterJob() {
        JobRepository jobRepository = new JobRepository();

        String jobName = "Manager";
        Optional<Job> addedJob = jobRepository.registerJob(jobName);

        assertTrue(addedJob.isPresent());
        assertEquals(jobName, addedJob.get().getJobName());

        List<Job> jobs = jobRepository.getJobList();
        assertTrue(jobs.contains(addedJob.get()));
    }

    @Test
    void testGetJobByJobName() {
        JobRepository jobRepository = new JobRepository();

        String jobName1 = "Manager";
        String jobName2 = "Engineer";
        String jobName3 = "Constructor";
        String jobName4 = "Driver";

        jobRepository.registerJob(jobName1);
        jobRepository.registerJob(jobName2);
        jobRepository.registerJob(jobName3);
        jobRepository.registerJob(jobName4);

        assertEquals(jobName1, jobRepository.getJobByJobName(jobName1).getJobName());
        assertEquals(jobName2, jobRepository.getJobByJobName(jobName2).getJobName());
        assertEquals(jobName3, jobRepository.getJobByJobName(jobName3).getJobName());
        assertEquals(jobName4, jobRepository.getJobByJobName(jobName4).getJobName());

        Job retrievedJob = jobRepository.getJobByJobName(jobName1);
        assertNotNull(retrievedJob);
        assertEquals(jobName1, retrievedJob.getJobName());



    }
}
