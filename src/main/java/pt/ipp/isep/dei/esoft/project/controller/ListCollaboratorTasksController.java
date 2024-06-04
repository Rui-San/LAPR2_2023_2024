package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Email;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.ArrayList;
import java.util.List;

public class ListCollaboratorTasksController {

    /**
     * Initial date
     */
    private Date initialDate;
    /**
     * Final date
     */
    private Date finalDate;
    /**
     * Agenda Repository
     */
    private AgendaRepository agendaRepository;

    /**
     * Gets the Agenda Repository.
     * @return Agenda Repository
     */
    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    /**
     * Instantiates a new List Collaborator Tasks Controller.
     */
    public ListCollaboratorTasksController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    /**
     * gets the collaborator name of the current session
     * @return collaborator name of the current session
     */
    public String getCollaboratorName(){
        return Repositories.getInstance().getCollaboratorRepository().getCollaboratorByCollaboratorEmail(new Email(ApplicationSession.getInstance().getCurrentSession().getUserId().toString())).get().getName();
    }

    /**
     * gets the tasks a the collaborator of the current session
     * @return list of tasks of the collaborator of the current session
     */
    public List<AgendaTaskDTO> getCollaboratorTasks() {

        List<Task> collaboratorTasks = getAgendaRepository().getCollbaboratorSpecificAgenda(ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail());
        return AgendaMapper.toDTOlist(collaboratorTasks);
    }

    /**
     * gets the tasks of the collaborator of the current session sorted by two dates
     * @param initialDate initial date
     * @param finalDate final date
     * @return list of tasks of the collaborator of the current session sorted by initial and final dates
     */
    public List<AgendaTaskDTO> getFilteredTasks(Date initialDate, Date finalDate) {

        List<Task> collabTasks = getAgendaRepository().getCollbaboratorSpecificAgenda(ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail());;

        List<AgendaTaskDTO> filteredTasks = new ArrayList<>();

        WorkPeriod workPeriodFilter = new WorkPeriod(initialDate, finalDate);

        for (Task task : collabTasks){
            if(task.getTaskWorkPeriod().isOverlap(workPeriodFilter)){
                filteredTasks.add(AgendaMapper.toDTO(task, TeamMapper.toDTO(CollaboratorMapper.toDTOlist(task.getTeamAssigned().getMembers())), VehicleMapper.toDTOList(task.getVehiclesAssigned())));
            }
        }
        return filteredTasks;
    }

    /**
     * Updates the initial date.
     * @param initialDate the new initial date
     */
    public void updateInitialDate(Date initialDate) { this.initialDate = initialDate; }

    /**
     * Updates the final date.
     * @param finalDate the new final date
     */
    public void updateFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }


}