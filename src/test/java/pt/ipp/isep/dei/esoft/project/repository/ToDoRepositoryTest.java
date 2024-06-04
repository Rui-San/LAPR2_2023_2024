package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepositoryTest {

    ToDoRepository toDoRepository = new ToDoRepository();
    GreenSpace greenSpace;
    Task task;

    @BeforeEach
    void setUp() {

        greenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Jardim da Esperança", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", 323.0, "admin@this.app");
        task = new Task("Test Task", "Task Description", TaskType.REGULAR, greenSpace, UrgencyType.LOW, 2, 0,0);
        toDoRepository.registerTaskToDo(task);
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
}