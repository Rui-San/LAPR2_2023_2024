package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class ToDoRepository {

    private List<Task> ToDoList;

    public ToDoRepository() {
        this.ToDoList = new ArrayList<>();
    }

    public List<Task> getToDoList() {
        return ToDoList;
    }
}
