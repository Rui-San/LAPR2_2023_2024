package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.List;
import java.util.Optional;

public class AddNewEntryToDoController {

    /**
     * The repository of To-Do tasks.
     */
    private ToDoRepository toDoRepository;
    /**
     * The repository of GreenSpaces.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Instantiates a new Add New Entry To Do Controller.
     */
    public AddNewEntryToDoController() {
        getToDoRepository();
        getGreenSpaceRepository();
    }

    /**
     * Gets the GreenSpaceRepository.
     * @return GreenSpaceRepository
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Gets the ToDoRepository.
     * @return ToDoRepository
     */
    private ToDoRepository getToDoRepository() {
        if (toDoRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoRepository = repositories.getToDoRepository();
        }
        return toDoRepository;
    }

    /**
     * Gets the GreenSpaceDTO list.
     * @return GreenSpaceDTO list
     */
    public List<GreenSpaceDTO> getGreenSpaceDTOlist() {
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceList();

        return GreenSpaceMapper.toDTOlist(greenSpaceList);
    }

    /**
     * Gets the GreenSpaceDTO list of the manager.
     * @return GreenSpaceDTO list
     */
    public List<GreenSpaceDTO> getManagerGreenSpaceDTOList(){
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<GreenSpace> managerGreenSpacesList = greenSpaceRepository.getGreenSpaceListByManager(managerEmail);

        return GreenSpaceMapper.toDTOlist(managerGreenSpacesList);
    }

    /**
     * Register a new task.
     * @param toDoTaskDTO to do task DTO
     * @return the new task as a DTO
     */
    public Optional<Task> registerTask(ToDoTaskDTO toDoTaskDTO) {

        Optional<Task> newTaskToDo = Optional.empty();
        Optional<GreenSpace> greenSpaceOptional = greenSpaceRepository.getGreenSpaceByName(toDoTaskDTO.greenSpaceName);

        if (greenSpaceOptional.isPresent()) {
            Task task = ToDoListMapper.toTask(toDoTaskDTO,greenSpaceOptional.get());
            newTaskToDo = toDoRepository.registerTaskToDo(task);
        } else {
            return Optional.empty();
        }
        return newTaskToDo;
    }
}
