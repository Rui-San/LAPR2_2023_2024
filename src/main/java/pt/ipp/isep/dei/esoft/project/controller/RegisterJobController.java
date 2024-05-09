package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

/**
 * Controller class responsible for handling operations related to registering jobs.
 */
public class RegisterJobController {
    /**
     * Job Repository
     */
    private JobRepository jobRepository;

    /**
     * Constructs a RegisterJobController object.
     */
    public RegisterJobController() {
        jobRepository = getJobRepository();
    }

    /**
     * Constructs a RegisterJobController object with the specified job repository.
     * Allows receiving the repositories as parameters for testing purposes.
     *
     * @param jobRepository the job repository
     */
    public RegisterJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Returns the job repository.
     *
     * @return the job repository
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Registers a new job.
     *
     * @param jobName the name of the job to register
     * @return an Optional containing the newly registered job if successful, empty otherwise
     */
    public Optional<Job> registerJob(String jobName) {

        Optional<Job> newJob = Optional.empty();
        newJob = jobRepository.registerJob(jobName);
        return newJob;
    }
}
