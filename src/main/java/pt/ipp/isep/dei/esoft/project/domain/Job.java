package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.regex.Pattern;

/**
 * Represents a Job object containing the job name.
 */
public class Job implements Cloneable{
    /**
     * The name of the job.
     */
    private String jobName;

    /**
     * Constructs a Job object with the specified job name.
     *
     * @param jobName the name of the job
     */
    public Job(String jobName) {
        setJobName(jobName);
    }

    /**
     * Returns the name of the job.
     *
     * @return the name of the job
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Sets the name of the job after validation.
     *
     * @param jobName the name to set for the job
     * @throws IllegalArgumentException if the job name is empty or contains special characters or numbers
     */
    public void setJobName(String jobName) {
        ValidationAttributeResults jobValidationResults = validateJob(jobName);
        switch (jobValidationResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Job name must not be empty");
            case CONTAINS_SPECIAL_CHARACTERS:
                throw new IllegalArgumentException("Job name must not contain special characters or numbers");
            case VALID:
                this.jobName = jobName;
        }
    }

    /**
     * Creates a deep copy of the Job.
     *
     * @return the cloned Job
     */
    @Override
    public Job clone() {
        return new Job(this.jobName);
    }

    /**
     * Validates the job name to ensure it contains only letters, spaces, or hyphens.
     * If the job name is not valid, returns the specific error (empty, null, or contains special characters)
     *
     * @param jobName the job name to validate
     * @return the validation result
     */
    private ValidationAttributeResults validateJob(String jobName) {
        if (jobName == null || jobName.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (namePattern.matcher(jobName).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

}
