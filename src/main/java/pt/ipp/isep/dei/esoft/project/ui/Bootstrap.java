package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.domain.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.domain.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.domain.repository.SkillRepository;

public class Bootstrap implements Runnable {

    public void run() {
        addCollaborator();
        addSkills();
        addJobs();
    }

    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        String name = "João Pedro";
        Date birthdate = new Date(1990, 5, 15);
        Date admissionDate = new Date(2022, 1, 1);
        String street = "Rua Principal";
        int streetNumber = 123;
        String postalCode = "1235-678";
        String city = "Porto";
        String district = "Porto";
        String email = "joao.pedro@example.com";
        String mobileNumber = "912345678";
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        int idDocNumber = 123456789;
        Job job = new Job("Gardener");

        Collaborator collaborator = new Collaborator(name, birthdate, admissionDate, street, streetNumber, postalCode,
                city, district, email, mobileNumber, idDocType, idDocNumber, job);

        collaboratorRepository.add(collaborator);
    }

    public void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.add(new Skill("Tree Pruning"));
        skillRepository.add(new Skill("Operator of agriculte machinery"));
        skillRepository.add(new Skill("Application of Fertilizers and Pesticides"));
        skillRepository.add(new Skill("Equipment Maintenance"));
        skillRepository.add(new Skill("Heavy Vehicle Driving License"));
        skillRepository.add(new Skill("Light Vehicle Driving License"));
    }

    public void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.add(new Job("Gardener"));
        jobRepository.add(new Job("Landscape Technician"));
        jobRepository.add(new Job("Farmer"));
        jobRepository.add(new Job("Electrician"));
        jobRepository.add(new Job("Forestry Engineer"));
        jobRepository.add(new Job("Agricultural Engineer"));
        jobRepository.add(new Job("Bricklayer"));
    }
/*
    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
    }

    */
}