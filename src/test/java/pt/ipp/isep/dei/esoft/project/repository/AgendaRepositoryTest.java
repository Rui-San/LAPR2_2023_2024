package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgendaRepositoryTest {

    AgendaRepository agendaRepository = new AgendaRepository();
    GreenSpace greenSpace;
    Collaborator collaborator;
    Collaborator collaborator2;
    Vehicle vehicle;
    Vehicle vehicle2;
    Task task;

    @BeforeEach
    void setUp() {
        greenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Jardim da Esperança", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", 323.0, "admin@this.app");
        vehicle = new Vehicle("00-11-AA", "Toyota", "Corolla", VehicleType.PASSENGERS, 1300, 1700, 10000, "15/3/1999", "1/4/2023", 10000);
        vehicle2 = new Vehicle("BB-22-BB", "Ford", "Mustang", VehicleType.PASSENGERS, 1700, 2000, 7500, "20/11/2022", "5/12/2022", 7500);
        collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));

        task = new Task("Test Task", "Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0,0);
        task.setTeamAssigned(new Team(List.of(collaborator, collaborator2)));
        task.setVehiclesAssigned((List.of(vehicle)));
        agendaRepository.registerTaskAgenda(task, "16/10/2024", 8, 0);
    }

    @Test
    void getAgenda() {
        assertEquals(1, agendaRepository.getAgenda().size());
    }

    @Test
    void getManagerSpecificAgenda() {
        assertEquals(1, agendaRepository.getManagerSpecificAgenda("admin@this.app").size());
    }

    @Test
    void getCollaboratorSpecificAgenda() {
        assertEquals(0, agendaRepository.getCollbaboratorSpecificAgenda("test@this.app").size());
    }

    @Test
    void updateTaskToCanceled() {
        agendaRepository.updateTaskToCanceled("Test Task", greenSpace.getName(), "16/10/2024", task.getStatus());
        assertEquals(Status.CANCELED, agendaRepository.getAgenda().get(0).getStatus());
    }

    @Test
    void updateTaskToDone() {
        agendaRepository.updateTaskToDone("Test Task", greenSpace.getName(), "16/10/2024", task.getStatus());
        assertEquals(Status.DONE, agendaRepository.getAgenda().get(0).getStatus());
    }

    @Test
    void postponeTaskAgenda() {
        agendaRepository.postponeTaskAgenda(task, new WorkPeriod(new Date("17/10/2024"), 8, 0, new TaskDuration(2, 0, 0)));
        assertEquals(Status.POSTPONED, agendaRepository.getAgenda().get(0).getStatus());
    }

    @Test
    void testRegisterTask() {
        Task newTask = new Task("New Task", "New Task Description", TaskType.REGULAR, greenSpace, UrgencyType.HIGH, 3, 1, 0);
        Optional<Task> result = agendaRepository.registerTaskAgenda(newTask, "17/10/2024", 9, 0);
        assertTrue(result.isPresent());
        assertEquals(newTask, result.get());
        assertEquals(Status.PLANNED, newTask.getStatus());
    }

    @Test
    void testRegisterDuplicate() {
        Optional<Task> result = agendaRepository.registerTaskAgenda(task, "16/10/2024", 8, 0);
        assertFalse(result.isPresent());
    }

    @Test
    void testAssignTeamToTaskAgenda() {
        Task newTask = new Task("New Task", "New Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0, 0);
        agendaRepository.registerTaskAgenda(newTask, "17/10/2024", 9, 0);

        Team newTeam = new Team(List.of(collaborator, collaborator2));
        Optional<Task> result = agendaRepository.assignTeamToTaskAgenda("New Task", greenSpace.getName(), "17/10/2024", Status.PLANNED, newTeam);
        assertTrue(result.isPresent());
        assertEquals(newTeam, result.get().getTeamAssigned());
    }

    @Test
    void testAssignVehiclesToTaskAgenda() {
        Task newTask = new Task("Another New Task", "Another New Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0, 0);
        agendaRepository.registerTaskAgenda(newTask, "18/10/2024", 10, 0);

        List<Vehicle> newVehicles = List.of(vehicle, vehicle2);
        Optional<Task> result = agendaRepository.assignVehiclesToTaskAgenda("Another New Task", greenSpace.getName(), "18/10/2024", Status.PLANNED, newVehicles);
        assertTrue(result.isPresent());
        assertEquals(newVehicles, result.get().getVehiclesAssigned());
    }



}