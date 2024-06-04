package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;
import pt.ipp.isep.dei.esoft.project.mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoRepository;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.List;
import java.util.Optional;

public class AddNewEntryAgendaController {

    /**
     * The repository of To-Do Tasks.
     */
    private ToDoRepository toDoRepository;
    /**
     * The repository of Agenda.
     */
    private AgendaRepository agendaRepository;
    /**
     * The repository of GreenSpaces.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Instantiates a new Add New Entry Agenda Controller.
     */
    public AddNewEntryAgendaController() {
        getToDoRepository();
        getAgendaRepository();
        getGreenSpaceRepository();
    }

    /**
     * Gets the agenda repository.
     * @return the agenda repository
     */
    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    /**
     * Gets the green space repository.
     * @return the green space repository
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Gets the to-do repository.
     * @return the to-do repository
     */
    private ToDoRepository getToDoRepository() {
        if (toDoRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoRepository = repositories.getToDoRepository();
        }
        return toDoRepository;
    }

    /**
     * Gets the DTO List of to-do list taks of a manager
     * @return the to-do list of a manager
     */
    public List<ToDoTaskWithStatusDTO> getToDoDTOManagerlist() {
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<Task> toDoTaskList = toDoRepository.getToDoManagerList(managerEmail);
        return ToDoListMapper.toDTOWithStatusList(toDoTaskList);
    }

    /**
     * Registers a task in the agenda
     * @param toDoTaskDTO the to-do task dto
     * @param executionDate the execution date
     * @param workStartingDays the work starting days
     * @param workStartingMinutes the work starting minutes
     * @return a new task in the agenda
     */
    public Optional<Task> registerTaskAgenda(ToDoTaskDTO toDoTaskDTO, String executionDate, int workStartingDays, int workStartingMinutes) {

        Optional<Task> newTaskAgenda = Optional.empty();
        Optional<GreenSpace> greenSpaceOptional = greenSpaceRepository.getGreenSpaceByName(toDoTaskDTO.greenSpaceName);

        if (greenSpaceOptional.isPresent()) {
            Task task = ToDoListMapper.toTask(toDoTaskDTO, greenSpaceOptional.get());
            System.out.println("duracao " + task.getExpectedDuration().getTotalDurationMinutes());
            newTaskAgenda = agendaRepository.registerTaskAgenda(task, executionDate, workStartingDays, workStartingMinutes);

            if (newTaskAgenda.isPresent()) {
                toDoRepository.updateTaskToProcessed(task);
            }
        } else {
            return Optional.empty();
        }
        return newTaskAgenda;
    }
}
