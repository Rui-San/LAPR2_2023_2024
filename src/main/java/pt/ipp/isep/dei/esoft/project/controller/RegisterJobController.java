package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


import java.util.Optional;

public class RegisterJobController {
    private JobRepository jobRepository;

    public RegisterJobController() {
        jobRepository = getJobRepository();

    }

    public RegisterJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /*
    public boolean registerJob(String jobName) {
        Job newJob = new Job(jobName);
        Optional<Job> addedJob = jobRepository.add(newJob);
        return addedJob.isPresent();
    }*/

    public Optional<Job> registerJob(String jobName) {

        Optional<Job> newJob = Optional.empty();
        newJob = jobRepository.registerJob(jobName);
        return newJob;
    }
}
