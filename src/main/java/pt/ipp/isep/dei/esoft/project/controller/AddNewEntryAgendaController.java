package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;

import java.util.List;
import java.util.Optional;

public class AddNewEntryAgendaController {

    private ToDoRepository toDoRepository;
    private AgendaRepository agendaRepository;
    private GreenSpaceRepository greenSpaceRepository;


    public AddNewEntryAgendaController() {
        getToDoRepository();
        getAgendaRepository();
        getGreenSpaceRepository();
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
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

    public List<ToDoTaskWithStatusDTO> getToDoDTOlist() {
        List<Task> toDoTaskList = toDoRepository.getToDoList();
        return ToDoListMapper.toDTOWithStatusList(toDoTaskList);
    }

    public Optional<Task> registerTaskAgenda(ToDoTaskDTO toDoTaskDTO, String executionDate) {

        Optional<Task> newTaskAgenda = Optional.empty();
        Optional<GreenSpace> greenSpaceOptional = greenSpaceRepository.getGreenSpaceByName(toDoTaskDTO.greenSpaceName);

        if (greenSpaceOptional.isPresent()) {
            Task task = ToDoListMapper.toTask(toDoTaskDTO, greenSpaceOptional.get());

            newTaskAgenda = agendaRepository.registerTaskAgenda(task, executionDate);

            if (newTaskAgenda.isPresent()) {
                toDoRepository.updateTaskToProcessed(task);
            }
        } else {
            return Optional.empty();
        }
        return newTaskAgenda;
    }
}
