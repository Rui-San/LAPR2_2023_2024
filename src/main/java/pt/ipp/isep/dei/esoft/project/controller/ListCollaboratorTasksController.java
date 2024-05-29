package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.tools.Sorting;


import java.util.ArrayList;
import java.util.List;

public class ListCollaboratorTasksController {

    private Date initialDate;
    private Date finalDate;
    private String status;
    private String collaboratorName;

    private AgendaRepository agendaRepository;

    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    public ListCollaboratorTasksController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    /**
     * @return sorted tasks assigned to the collaborator between two selected dates
     */

    public List<AgendaTaskDTO> getCollaboratorTasks() {

        List<Task> collaboratorTasks = getAgendaRepository().getManagerSpecificAgenda(ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail());
        return AgendaMapper.toDTOlist(collaboratorTasks);
    }

    /**
     * Checks if dateToCheck is after or equal to startDate and before or equal to endDate
     */

    public static boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;

    }

    /**
     * Updates the initial date.
     * @param initialDate the new initial date
     */
    public void updateInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * Updates the final date.
     * @param finalDate the new final date
     */
    public void updateFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}