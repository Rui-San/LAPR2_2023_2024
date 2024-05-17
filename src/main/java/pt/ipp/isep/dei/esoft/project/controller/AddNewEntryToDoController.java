package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;

public class AddNewEntryToDoController {

    private ToDoRepository toDoRepository;
    private GreenSpaceRepository greenSpaceRepository;

    public AddNewEntryToDoController() {
        getToDoRepository();
        getGreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    private ToDoRepository getToDoRepository() {
        if (toDoRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoRepository = repositories.getToDoRepository();
        }
        return toDoRepository;
    }
}
