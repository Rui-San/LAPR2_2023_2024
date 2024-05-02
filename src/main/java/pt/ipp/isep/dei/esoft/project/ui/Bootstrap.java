package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

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
        String city = "Matosinhos";
        String district = "Matosinhos";
        String email = "joao.pedro@example.com";
        String mobileNumber = "912345678";
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        String idDocNumber = "123456789";
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

        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        Vehicle vehicle1 = new Vehicle("11-11-11", "Toyota", "Corolla", "1", 1300, 1700, 10000, new Date("15/3/2023"), new Date("1/4/2023"), 10000);
        vehicleRepository.add(vehicle1);
        Vehicle vehicle2 = new Vehicle("22-22-22", "Ford", "Mustang", "2", 1700, 2000, 7500, new Date("20/11/2022"), new Date("5/12/2022"), 7500);
        vehicleRepository.add(vehicle2);
        Vehicle vehicle3 = new Vehicle("33-33-33", "BMW", "X5", "3", 2100, 2600, 12000, new Date("10/6/2023"), new Date("1/7/2023"), 12000);
        vehicleRepository.add(vehicle3);
        Vehicle vehicle4 = new Vehicle("44-44-44", "Volkswagen", "Golf", "1", 1400, 1800, 8000, new Date("5/1/2024"), new Date("20/1/2024"), 8000);
        vehicleRepository.add(vehicle4);
        Vehicle vehicle5 = new Vehicle("55-55-55", "Mercedes-Benz", "E-Class", "1", 1900, 2300, 15000, new Date("30/9/2023"), new Date("15/10/2023"), 15000);
        vehicleRepository.add(vehicle5);

    }

    private void addCheckup() {

        CheckupRepository checkupRepository = Repositories.getInstance().getCheckupRepository();
        List<Vehicle> vehicles = Repositories.getInstance().getVehicleRepository().getVehicles();

        for (Vehicle vehicle : vehicles){
            VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date("1/5/2024"), 11000);
        }

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