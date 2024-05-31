package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.controller.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.tools.SerializationFiles;
import pt.ipp.isep.dei.esoft.project.tools.SerializationUtils;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import java.util.List;

public class Bootstrap implements Runnable {

    public void run() {
        addUsers();
        addSkills();
        addJobs();
        addVehicle();
        addCheckup();
        addCollaborator();
        assignSkills();
        addGreenSpaces();

        Repositories.save();
        Repositories.load();
    }


    public void fillRepositoryLists() {
        Repositories.getInstance().getJobRepository().loadJobsFromFile();
        Repositories.getInstance().getSkillRepository().loadSkillsFromFile();
        Repositories.getInstance().getCollaboratorRepository().loadCollaboratorsFromFile();
        Repositories.getInstance().getTeamRepository().loadTeamsFromFile();

    }
/*
    private void fillJobRepositoryFromFile() {
        List<Job> jobList = SerializationUtils.readFromFile(SerializationFiles.JOB_DATABASE);
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        for (Job job : jobList) {
            jobRepository.add(job);
        }
    }

    private void fillSkillRepositoryFromFile() {
        List<Skill> skillList = SerializationUtils.readFromFile(SerializationFiles.SKILL_DATABASE);
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        for (Skill skill : skillList) {
            skillRepository.add(skill);
        }
    }

    private void fillCollaboratorRepositoryFromFile() {
        List<Collaborator> collaboratorList = SerializationUtils.readFromFile(SerializationFiles.COLLABORATOR_DATABASE);
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        for (Collaborator collaborator : collaboratorList) {
            collaboratorRepository.add(collaborator);
        }
    }

 */

    private void addGreenSpaces() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        greenSpaceRepository.registerGreenSpace(new GreenSpace(GreenSpaceType.GARDEN, "Jardim da Esperança", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", 323.0, "admin@this.app"));
        greenSpaceRepository.registerGreenSpace(new GreenSpace(GreenSpaceType.LARGE, "Central Park", "Main Street", 123, "3343-122", "New York", "NY", 843.5, "admin@this.app"));
        greenSpaceRepository.registerGreenSpace(new GreenSpace(GreenSpaceType.MEDIUM, "Amazon Rainforest", "Amazon River", 2, "4440-232", "Manaus", "Amazonas", 5500000.0, "admin@this.app"));
        greenSpaceRepository.registerGreenSpace(new GreenSpace(GreenSpaceType.GARDEN, "Royal Botanic Gardens", "Kew Road", 20, "3350-102", "Richmond", "London", 121.0, "admin@this.app"));

    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.add(new Skill("Tree Pruning"));
        skillRepository.add(new Skill("Operator of agriculture machinery"));
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

    private void assignSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        collaboratorRepository.getCollaboratorList().get(1).assignSkills(skillRepository.getSkillList());

    }

    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician"));
        Collaborator collaborator5 = new Collaborator("Ana Rodrigues", "20/07/1998", "04/05/2024", "Rua das Flores", 5, "1050-201", "Lisboa", "Lisboa", "arodrigues@this.app", "912345678", Collaborator.IdDocType.CC, "123555789", new Job("Farmer"));
        Collaborator collaborator6 = new Collaborator("Miguel Oliveira", "12/12/1995", "06/05/2024", "Avenida Central", 30, "4000-100", "Porto", "Porto", "moliveira@this.app", "912345678", Collaborator.IdDocType.CC, "124456789", new Job("Electrician"));
        Collaborator collaborator7 = new Collaborator("Sara Sousa", "03/09/1990", "04/05/2024", "Rua do Sol", 8, "8500-302", "Portimão", "Faro", "ssousa@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("Forestry Engineer"));
        Collaborator collaborator8 = new Collaborator("Pedro Martins", "28/05/1993", "06/05/2024", "Rua dos Cravos", 15, "2560-302", "Torres Vedras", "Lisboa", "pmartins@this.app", "912345678", Collaborator.IdDocType.CC, "133456789", new Job("Agricultural Engineer"));
        Collaborator collaborator9 = new Collaborator("Carla Ferreira", "17/04/1985", "04/05/2024", "Rua da Praia", 25, "4900-302", "Viana do Castelo", "Viana do Castelo", "cferreira@this.app", "912345678", Collaborator.IdDocType.CC, "111456789", new Job("Bricklayer"));
        Collaborator collaborator10 = new Collaborator("António Pereira", "10/02/1980", "06/05/2024", "Rua do Campo", 40, "8000-302", "Faro", "Faro", "apereira@this.app", "912345678", Collaborator.IdDocType.CC, "123333789", new Job("VFM"));

        List<Collaborator> collaboratorsToAdd = List.of(collaborator, collaborator2, collaborator3, collaborator4, collaborator5, collaborator6, collaborator7, collaborator8, collaborator9, collaborator10);

        for (Collaborator collaboratorToAdd : collaboratorsToAdd) {
            collaboratorRepository.add(collaboratorToAdd);
        }

    }

    public static void addUsers() {

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);

        authenticationRepository.addUserWithRole("Quick Administrator", "a@this.app", "b",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Human Resources Manager", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Vehicle Fleet Manager", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Green Space Manager One", "gsm1@this.app", "gsm1",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Green Space Manager Two", "gsm2@this.app", "gsm2",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Green Space Manager Three", "gsm3@this.app", "gsm3",
                AuthenticationController.ROLE_GSM);

        for(Collaborator collaborator : Repositories.getInstance().getCollaboratorRepository().getCollaboratorList()) {
            authenticationRepository.addUserWithRole(collaborator.getName(), collaborator.getEmail().getEmail(), "password", AuthenticationController.ROLE_COLLABORATOR);
        }
    }

    private void addVehicle() {

        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        Vehicle vehicle1 = new Vehicle("00-11-AA", "Toyota", "Corolla", VehicleType.PASSENGERS, 1300, 1700, 10000, "15/3/1999", "1/4/2023", 10000);
        vehicleRepository.add(vehicle1);
        Vehicle vehicle2 = new Vehicle("BB-22-BB", "Ford", "Mustang", VehicleType.PASSENGERS, 1700, 2000, 7500, "20/11/2022", "5/12/2022", 7500);
        vehicleRepository.add(vehicle2);
        Vehicle vehicle3 = new Vehicle("CC-33-CC", "BMW", "X5", VehicleType.PASSENGERS, 2100, 2600, 12000, "10/6/2023", "1/7/2023", 12000);
        vehicleRepository.add(vehicle3);
        Vehicle vehicle4 = new Vehicle("44-DD-44", "Volkswagen", "Golf", VehicleType.PASSENGERS, 1400, 1800, 8000, "5/1/2006", "20/1/2024", 8000);
        vehicleRepository.add(vehicle4);
        Vehicle vehicle5 = new Vehicle("55-EE-55", "Mercedes-Benz", "E-Class", VehicleType.PASSENGERS, 1900, 2300, 15000, "30/9/2008", "15/10/2023", 15000);
        vehicleRepository.add(vehicle5);
    }

    private void addCheckup() {

        CheckupRepository checkupRepository = Repositories.getInstance().getCheckupRepository();
        List<Vehicle> vehicles = Repositories.getInstance().getVehicleRepository().getVehicleList();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCurrentKm() >= 5000) {
                int kmsForCheckupBootstrap = vehicle.getCurrentKm() - 1000;
                VehicleCheckup checkup = new VehicleCheckup(vehicle, new Date(), kmsForCheckupBootstrap);
            }
        }

    }

}