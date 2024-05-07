package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.regex.Pattern;

public class Job implements Cloneable{
    private String jobName;

    public Job(String jobName) {
        setJobName(jobName);
    }

    public String getJobName() {
        return jobName;
    }

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

    @Override
    public Job clone() {
        return new Job(this.jobName);
    }

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
