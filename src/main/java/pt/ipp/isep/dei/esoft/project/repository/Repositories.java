package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project._templateFiles.domain.repository.OrganizationRepository;

public class Repositories {
    private static Repositories instance;

    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final CheckupRepository checkupRepository;
    private final VehicleRepository vehicleRepository;

    private Repositories() {
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
        checkupRepository = new CheckupRepository();
        vehicleRepository = new VehicleRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
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

    public CheckupRepository getCheckupRepository() { return checkupRepository; }

    public VehicleRepository getVehicleRepository() { return vehicleRepository; }

}