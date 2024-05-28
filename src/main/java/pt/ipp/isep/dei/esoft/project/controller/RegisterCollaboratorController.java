package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.List;
import java.util.Optional;


public class RegisterCollaboratorController {
    /**
     * Job Repository
     */
    private JobRepository jobRepository;
    /**
     * Collaborator Repository
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructor of RegisterCollaboratorController
     */
    public RegisterCollaboratorController() {
        getJobRepository();
        getCollaboratorRepository();
    }

    /**
     * Constructor of RegisterCollaboratorController with repositories in parameters.
     * Allows receiving the repositories as parameters for testing purposes.
     * @param jobRepository the
     * @param collaboratorRepository
     */
    public RegisterCollaboratorController(JobRepository jobRepository, CollaboratorRepository collaboratorRepository) {
        this.jobRepository = jobRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Returns the job repository
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
     * Returns the Collaborator repository
     * @return the Collaborator repository
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Returns the list of job from repository
     * @return the list of job from repository
     */
    public List<Job> getJobList() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobList();
    }

    /**
     * Creates a new collaborator.
     *
     * @param name          the name of the collaborator
     * @param birthdate     the birthdate of the collaborator
     * @param admissionDate the admission date of the collaborator
     * @param street        the street of the collaborator's address
     * @param streetNumber  the street number of the collaborator's address
     * @param postalCode    the postal code of the collaborator's address
     * @param city          the city of the collaborator's address
     * @param district      the district of the collaborator's address
     * @param email         the email of the collaborator
     * @param mobileNumber  the mobile number of the collaborator
     * @param idDocType     the ID document type of the collaborator
     * @param idDocNumber   the ID document number of the collaborator
     * @param job           the job of the collaborator
     * @return an Optional containing the newly created collaborator if successful, empty otherwise
     */
    public Optional<Collaborator> createCollaborator(String name, String birthdate, String admissionDate, String street, int streetNumber, String postalCode, String city, String district, String email, String mobileNumber, IdDocType idDocType, String idDocNumber, Job job) {

        Optional<Collaborator> newCollaborator = Optional.empty();
        newCollaborator = collaboratorRepository.createCollaborator(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job);

        if(newCollaborator.isPresent()){
            ApplicationSession.getInstance().getAuthenticationRepository().addUserWithRole(
                    name, email, "password", AuthenticationController.ROLE_COLLABORATOR);
        }

        return newCollaborator;
    }

    /**
     * Retrieves a job by its name.
     *
     * @param jobName the name of the job
     * @return the job object
     */
    private Job getJobByJobName(String jobName) {
        JobRepository jobRepository = getJobRepository();

        Job job = jobRepository.getJobByJobName(jobName);

        return job;
    }
}
