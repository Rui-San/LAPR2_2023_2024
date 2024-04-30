package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Optional;

public class RegisterJobController {
    private final JobRepository jobRepository;

    public RegisterJobController() {
        jobRepository = new JobRepository();
    }

    public boolean registerJob(String jobName) {
        Job newJob = new Job(jobName);
        Optional<Job> addedJob = jobRepository.add(newJob);
        return addedJob.isPresent();
    }
}
