package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.matdisc.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.matdisc.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;

import java.util.List;
import java.util.Optional;

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

    public List<GreenSpaceDTO> getGreenSpaceDTOlist() {
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceList();

        return GreenSpaceMapper.toDTOlist(greenSpaceList);
    }

    public List<GreenSpaceDTO> getManagerGreenSpaceDTOList(){
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<GreenSpace> managerGreenSpacesList = greenSpaceRepository.getGreenSpaceListByManager(managerEmail);

        return GreenSpaceMapper.toDTOlist(managerGreenSpacesList);
    }

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
