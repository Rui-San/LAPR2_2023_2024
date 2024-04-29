package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    public void run() {
        addSkills();
        addJobs();
        addVehicle();
        addCheckup();
        addCollaborator();
        addUsers();
    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.add(new Skill("Tree Pruning"));
        skillRepository.add(new Skill("Operator of agriculte machinery"));
        skillRepository.add(new Skill("Application of Fertilizers and Pesticides"));
        skillRepository.add(new Skill("Equipment Maintenance"));
        skillRepository.add(new Skill("Heavy Vehicle Driving License"));
        skillRepository.add(new Skill("Light Vehicle Driving License"));
    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.add(new Job("Gardener"));
        jobRepository.add(new Job("Landscape Technician"));
        jobRepository.add(new Job("Farmer"));
        jobRepository.add(new Job("Electrician"));
        jobRepository.add(new Job("Forestry Engineer"));
        jobRepository.add(new Job("Agricultural Engineer"));
        jobRepository.add(new Job("Bricklayer"));
    }
    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        String name = "joao albert";
        Date birthdate = new Date("15/5/1990");
        Date admissionDate = new Date("1/1/2022");
        String street = "Rua Principal";
        int streetNumber = 123;
        String postalCode = "1235-678";
        String city = "Porto";
        String district = "Porto";
        String email = "joao.pedro@example.com";
        String mobileNumber = "912345678";
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        int idDocNumber = 123456789;
        Job job = new Job("x");

        Collaborator collaborator = new Collaborator(name, birthdate, admissionDate, street, streetNumber, postalCode,
                city, district, email, mobileNumber, idDocType, idDocNumber, job);

        collaboratorRepository.add(collaborator);
    }

    private void addUsers() {

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "pwd",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "pwd",
                AuthenticationController.ROLE_VFM);

    }

    private void addVehicle() {


    }

    private void addCheckup() {
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
    */

}