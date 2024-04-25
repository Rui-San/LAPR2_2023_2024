package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project._templateFiles.domain.repository.OrganizationRepository;

public class Repositories {
    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        authenticationRepository = new AuthenticationRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }



}