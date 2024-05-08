package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobList;

    public JobRepository() {
        jobList = new ArrayList<>();
    }

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
     * @param job
     * @return the logical state of the validation. True if the list of jobs doesn't contain that job.
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

    // Caso seja necessário ir buscar o objeto Job pelo jobName já está implementado
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

    public Optional<Job> registerJob(String jobName) {

        Job newJob = new Job(jobName);
        Optional<Job> addedJob = add(newJob);
        return addedJob;
    }
}

