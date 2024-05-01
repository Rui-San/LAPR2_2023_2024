package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;
import java.util.Optional;

public class RegisterCollaboratorController {
    private JobRepository jobRepository;
    private CollaboratorRepository collaboratorRepository;

    public RegisterCollaboratorController() {
        getJobRepository();
        getCollaboratorRepository();
    }

    public RegisterCollaboratorController(JobRepository jobRepository, CollaboratorRepository collaboratorRepository) {
        this.jobRepository = jobRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    public List<Job> getJobList() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobList();
    }

    public Optional<Collaborator> createCollaborator(String name, String birthdate, String admissionDate, String street, int streetNumber, String postalCode, String city, String district, String email, String mobileNumber, IdDocType idDocType, String idDocNumber, Job job) {

        Optional<Collaborator> newCollaborator = Optional.empty();
        /*
        Se for necessário ir buscar o objeto pela string já está implementado
        Job job = getJobByJobName(jobName);
        */
        newCollaborator = collaboratorRepository.createCollaborator(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job);


        return newCollaborator;

        // check if this method needs to be Optional<Collaborator>
        // the Optional class, allows null or not null objects. this means less code in variables "=null"
    }
    private Job getJobByJobName(String jobName) {
        JobRepository jobRepository = getJobRepository();

        Job job = jobRepository.getJobByJobName(jobName);

        return job;
    }




}
