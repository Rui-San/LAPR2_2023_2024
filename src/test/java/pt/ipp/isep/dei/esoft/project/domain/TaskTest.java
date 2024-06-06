package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.tools.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;
    Team team;
    Collaborator collaborator;
    Collaborator collaborator2;
    Vehicle vehicle;
    GreenSpace greenSpace;

    @BeforeEach
    void setUp() {
        collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        team = new Team(List.of(collaborator, collaborator2));
        vehicle = new Vehicle("00-11-AA", "Toyota", "Corolla", VehicleType.PASSENGERS, 1300, 1700, 10000, "15/3/1999", "1/4/2023", 10000);
        greenSpace = new GreenSpace(GreenSpaceType.MEDIUM, "Teste", "street", 12, "4000-400", "Porto", "Gaia", 1000.0, "gsm1@this.app");
        task = new Task("Test Task", "Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0,0);
    }

    @Test
    void getTitle() {
        String expectedResult = "Test Task";
        String result = task.getTitle();
        assertEquals(expectedResult, result);
    }

    @Test
    void setTitle() {
        String expectedResult = "New Task";
        task.setTitle("New Task");
        String result = task.getTitle();
        assertEquals(expectedResult, result);
    }

    @Test
    void getDescription() {
        String expectedResult = "Task Description";
        String result = task.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTaskType() {
        TaskType expectedResult = TaskType.REGULAR;
        TaskType result = task.getTaskType();
        assertEquals(expectedResult, result);
    }
    @Test
    void testNullTitle() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setTitle(null));
        assertEquals("Title can't be empty or null.", exception.getMessage());
    }

    @Test
    void testEmptyTitle() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setTitle(""));
        assertEquals("Title can't be empty or null.", exception.getMessage());
    }

    @Test
    void testTitleSpecialCharacters() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setTitle("Invalid@Title!"));
        assertEquals("Title cannot contain Special characters.", exception.getMessage());
    }

    @Test
    void testValidTitle() {

        task.setTitle("Valid Title");
        assertEquals("Valid Title", task.getTitle());
    }

    @Test
    void testNullDescription() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertEquals("Description can't be empty or null.", exception.getMessage());
    }

    @Test
    void testEmptyDescription() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setDescription(""));
        assertEquals("Description can't be empty or null.", exception.getMessage());
    }

    @Test
    void testValidDescription() {

        task.setDescription("This is a valid description.");
        assertEquals("This is a valid description.", task.getDescription());
    }

    @Test
    void testValidTaskType() {

        task.setTaskType(TaskType.REGULAR);
        assertEquals(TaskType.REGULAR, task.getTaskType());
    }

    @Test
    void testInvalidTaskType() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setTaskType(null));
        assertEquals("Task type is not valid.", exception.getMessage());
    }

    @Test
    void testValidStatus() {

        task.setStatus(Status.PLANNED);
        assertEquals(Status.PLANNED, task.getStatus());
    }

    @Test
    void testInvalidStatus() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setStatus(null));
        assertEquals("Status is not valid.", exception.getMessage());
    }

    @Test
    void testValidUrgency() {

        task.setUrgency(UrgencyType.HIGH);
        assertEquals(UrgencyType.HIGH, task.getUrgency());
    }

    @Test
    void testInvalidUrgency() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setUrgency(null));
        assertEquals("Urgency option is not valid.", exception.getMessage());
    }

    @Test
    void setDescription() {
        String expectedResult = "New Description";
        task.setDescription("New Description");
        String result = task.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    void getStatus() {
        Status expectedResult = null;
        Status result = task.getStatus();
        assertEquals(expectedResult, result);
    }

    @Test
    void setStatus() {
        Status expectedResult = Status.PLANNED;
        task.setStatus(Status.PLANNED);
        assertEquals(expectedResult, task.getStatus());
    }

    @Test
    void assignTeam() {
        task.assignTeam(team);
        assertEquals(team, task.getTeamAssigned());
    }

    @Test
    void assignVehicles() {
        task.assignVehicles(List.of(vehicle));
        assertEquals(List.of(vehicle), task.getVehiclesAssigned());
    }

    @Test
    void getGreenSpace() {
        GreenSpace expectedResult = greenSpace;
        GreenSpace result = task.getGreenSpace();
        assertEquals(expectedResult, result);
    }

    @Test
    void setGreenSpace() {
        GreenSpace expectedResult = new GreenSpace(GreenSpaceType.LARGE, "Teste Two", "street", 12, "4000-400", "Porto", "Gaia", 1000.0, "gsm2@this.app");
        task.setGreenSpace(expectedResult);
        assertEquals(expectedResult, task.getGreenSpace());
    }

    @Test
    void getTeamAssigned() {
        Team expectedResult = team;
        task.assignTeam(team);
        Team result = task.getTeamAssigned();
        assertEquals(expectedResult, result);
    }

    @Test
    void setTeamAssigned() {
        Team expectedResult = new Team(List.of(collaborator));
        task.setTeamAssigned(expectedResult);
        assertEquals(expectedResult, task.getTeamAssigned());
    }

    @Test
    void getVehiclesAssigned() {
        List<Vehicle> expectedResult = List.of(vehicle);
        task.assignVehicles(expectedResult);
        List<Vehicle> result = task.getVehiclesAssigned();
        assertEquals(expectedResult, result);
    }

    @Test
    void setVehiclesAssigned() {
        List<Vehicle> expectedResult = List.of(new Vehicle("00-22-BB", "Toyota", "Corolla", VehicleType.PASSENGERS, 1300, 1700, 10000, "15/3/1999", "1/4/2023", 10000));
        task.setVehiclesAssigned(expectedResult);
        assertEquals(expectedResult, task.getVehiclesAssigned());
    }

    @Test
    void getExpectedDuration() {
        TaskDuration expectedResult = new TaskDuration(2, 0, 0);
        TaskDuration result = task.getExpectedDuration();
        assertEquals(expectedResult.getTotalDurationMinutes(), result.getTotalDurationMinutes());
    }

    @Test
    void getUrgency() {
        UrgencyType expectedResult = UrgencyType.LOW;
        UrgencyType result = task.getUrgency();
        assertEquals(expectedResult, result);
    }

    @Test
    void setUrgency() {
        UrgencyType expectedResult = UrgencyType.HIGH;
        task.setUrgency(UrgencyType.HIGH);
        assertEquals(expectedResult, task.getUrgency());
    }

}