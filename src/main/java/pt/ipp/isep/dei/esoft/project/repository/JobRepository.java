package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Job Repository class
 */
public class JobRepository {

    /**
     * List to store all the jobs.
     */
    private final List<Job> jobList;

    /**
     * Constructs a JobRepository object.
     */
    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * Adds a new job to the repository if it's valid.
     *
     * @param job the job to add
     * @return an Optional containing the newly added job if successful, empty otherwise
     */
    public Optional<Job> add(Job job) {

        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobList.add(newJob.get());
        }

        if (!operationSuccess) {
            newJob = Optional.empty();
        }

        return newJob;
    }

    /**
     * Validates if the job is already in the list of jobs
     *
     * @param job the job to validate
     * @return true if the job can be added, false otherwise
     */
    private boolean validateJob(Job job) {
        boolean isValid = true;
        String jobName = job.getJobName().trim().toLowerCase();

        for (Job registeredJobs : jobList) {
            if (registeredJobs.getJobName().trim().toLowerCase().equals(jobName)) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Job> getJobList() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobList);
    }

    /**
     * Retrieves a job object by its job name from the job repository list
     *
     * @param jobName the name of the job to retrieve
     * @return the job if found
     * @throws IllegalArgumentException if the job does not exist
     */
    public Job getJobByJobName(String jobName) {
        Job newJob = new Job(jobName);
        Job job = null;
        if (jobList.contains(newJob)) {
            job = jobList.get(jobList.indexOf(newJob));
        }
        if (job == null) {
            throw new IllegalArgumentException(
                    "Job requested for [" + jobName + "] does not exist.");
        }
        return job;
    }

    /**
     * Registers a new job and adds to the list of jobs if validated
     *
     * @param jobName the name of the job to register
     * @return an Optional containing the newly registered job if successful, empty otherwise
     */
    public Optional<Job> registerJob(String jobName) {

        Job newJob = new Job(jobName);
        Optional<Job> addedJob = add(newJob);
        return addedJob;
    }
}

