package pt.ipp.isep.dei.esoft.project.domain.repository;
import pt.ipp.isep.dei.esoft.project.domain.Job;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobs;

    public JobRepository() {
        jobs = new ArrayList<>();
    }

    public Optional<Job> add(Job job) {

        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;

        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobs.add(newJob.get());
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
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of jobs.
     *
     * @return The list of jobs.
     */
    public List<Job> getJobs() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }
}

