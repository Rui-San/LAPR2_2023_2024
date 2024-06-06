package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepositoryTest {

    ToDoRepository toDoRepository = new ToDoRepository();
    GreenSpace greenSpace;
    Task task;
    Task duplicateTask;
    Task differentGreenSpaceTask;

    @BeforeEach
    void setUp() {

        greenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Jardim da Esperança", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", 323.0, "admin@this.app");
        task = new Task("Test Task", "Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0,0);
        toDoRepository.registerTaskToDo(task);
        duplicateTask = new Task("Test Task", "Duplicate Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0, 0);
        differentGreenSpaceTask = new Task("Test Task", "Different GreenSpace Task", TaskType.REGULAR, new GreenSpace(GreenSpaceType.GARDEN, "Other Garden", "Rua Um", 11, "4400-302", "Vila Nova de Gaia", "Porto", 300.0, "admin@this.app"), UrgencyType.LOW, 2, 0, 0);

    }

    @Test
    void getToDoList() {
        List<Task> result = toDoRepository.getToDoList();
        assertEquals(1, result.size());
    }

    @Test
    void getToDoManagerList() {
        List<Task> result = toDoRepository.getToDoManagerList("admin@this.app");
        assertEquals(1, result.size());
    }

    @Test
    void updateTaskToProcessed() {
        toDoRepository.updateTaskToProcessed(task);
        assertEquals(Status.PROCESSED, task.getStatus());
    }

    @Test
    void registerTaskWithSuccessTest() {
        Task newTask = new Task("New Task", "New Task Description", TaskType.REGULAR, greenSpace, UrgencyType.HIGH, 3, 1, 0);
        Optional<Task> result = toDoRepository.registerTaskToDo(newTask);
        assertTrue(result.isPresent());
        assertEquals(newTask, result.get());
        assertEquals(Status.PENDING, newTask.getStatus());
    }

    @Test
    void testRegisterDuplicateTask() {
        Optional<Task> result = toDoRepository.registerTaskToDo(duplicateTask);
        assertFalse(result.isPresent());
    }

    @Test
    void testRegisterTaskDifferentGreenSpace() {
        Optional<Task> result = toDoRepository.registerTaskToDo(differentGreenSpaceTask);
        assertTrue(result.isPresent());
        assertEquals(differentGreenSpaceTask, result.get());
        assertEquals(Status.PENDING, differentGreenSpaceTask.getStatus());
    }


    @Test
    void testAddWithSuccess() {
        Task newTask = new Task("Another New Task", "Another Task Description", TaskType.REGULAR, greenSpace, UrgencyType.MEDIUM, 1, 2, 30);
        Optional<Task> result = toDoRepository.add(newTask);
        assertTrue(result.isPresent());
        assertEquals(newTask, result.get());
    }

    @Test
    void testAddDuplicate() {
        Optional<Task> result = toDoRepository.add(duplicateTask);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateTaskToProcessedWithNullTask() {
        Task nonExistentTask = new Task("Non Existent Task", "Non Existent Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0, 0);
        toDoRepository.updateTaskToProcessed(nonExistentTask);
        assertNotEquals(Status.PROCESSED, nonExistentTask.getStatus());
    }

    @Test
    void testUpdateTaskToProcessed() {
        toDoRepository.updateTaskToProcessed(task);
        assertEquals(Status.PROCESSED, task.getStatus());
    }


}