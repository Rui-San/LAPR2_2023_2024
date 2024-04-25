package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Job {
    private String jobName;

    private enum JobValidationResults {
        EMPTYNULL, VALID, CONTAINS_SPECIAL_CHARACTERS,
    }

    public Job(String jobName) {
        setJobName(jobName);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        Job.JobValidationResults jobValidationResults = validateJob(jobName);
        switch (jobValidationResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Job name must not be empty");
            case CONTAINS_SPECIAL_CHARACTERS:
                throw new IllegalArgumentException("Job name must not contain special characters or numbers");
            case VALID:
                this.jobName = jobName;
        }
    }

    @Override
    public Job clone() {
        return new Job(this.jobName);
    }

    private Job.JobValidationResults validateJob(String jobName) {
        if (jobName == null || jobName.trim().isEmpty()) {
            return Job.JobValidationResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-]+");

        if (namePattern.matcher(jobName).matches()) {
            return Job.JobValidationResults.VALID;
        } else {
            return Job.JobValidationResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

}
