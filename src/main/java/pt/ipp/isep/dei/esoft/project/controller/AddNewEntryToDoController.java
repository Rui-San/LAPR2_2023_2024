package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;
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

        return GreenSpaceMapper.toDTO(greenSpaceList);
    }

    public Optional<Task> registerTask(String title, String description, Status status, GreenSpaceDTO greenSpaceDTO, UrgencyType urgency, int days, int hours) {

        Optional<Task> newTaskToDo = Optional.empty();

        Optional<GreenSpace> greenSpaceOptional = greenSpaceRepository.getGreenSpaceByName(greenSpaceDTO.name);

        if (greenSpaceOptional.isPresent()) {
            newTaskToDo = toDoRepository.registerTaskToDo(title, description, status, greenSpaceOptional.get(), urgency, days, hours);
        } else {
            return Optional.empty();
        }

        return newTaskToDo;
    }



}
