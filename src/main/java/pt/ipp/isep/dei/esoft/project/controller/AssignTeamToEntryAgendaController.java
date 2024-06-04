package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.tools.EmailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignTeamToEntryAgendaController {

    /**
     * The Agenda Repository.
     */
    private AgendaRepository agendaRepository;
    /**
     * The Team Repository.
     */
    private TeamRepository teamRepository;

    /**
     * Instantiates a new Assign Team to Entry Agenda Controller.
     */
    public AssignTeamToEntryAgendaController() {
        getAgendaRepository();
        getTeamRepository();
    }

    /**
     * Gets the Team Repository.
     * @return the Team Repository.
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Gets the Agenda Repository.
     * @return the Agenda Repository.
     */
    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    /**
     * Gets the list of TaskDTO of a manager.
     * @return the list of TaskDTO of a manager.
     */
    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }

    /**
     * gets all the teams
     * @return list of teams
     */
    public List<TeamDTO> getTeams(){
        List<TeamDTO> teamListDTO = new ArrayList<>();

        for(Team team : teamRepository.getTeamList()){
            List<CollaboratorDTO> collaboratorDTOList = CollaboratorMapper.toDTOlist(team.getMembers());
            teamListDTO.add(TeamMapper.toDTO(collaboratorDTOList));
        }
        return teamListDTO;
    }

    /**
     * Assigns a team to a task in the agenda
     * @param agendaTaskDTO task to assign team to
     * @param selectedTeamDTO team to assign
     * @return task assigned
     */
    public Optional<Task> assignTeamToTaskAgenda(AgendaTaskDTO agendaTaskDTO, TeamDTO selectedTeamDTO) {
        Team teamObj = teamRepository.getTeamByTeamMemberEmails(selectedTeamDTO.collaborators.get(0).email);
        Optional<Task> task =  agendaRepository.assignTeamToTaskAgenda(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status, teamObj);
        for(CollaboratorDTO teamDTO : selectedTeamDTO.collaborators){
            try {
                Class<?> emailService = Class.forName("pt.ipp.isep.dei.esoft.project.tools." + ApplicationSession.getInstance().getProperties().getProperty(ApplicationSession.EMAIL_SERVICE));

                EmailSender emailSender = (EmailSender) emailService.getDeclaredConstructor().newInstance();
                String body = "You have been assigned to a task in the agenda:\n" +
                        "Title: " + agendaTaskDTO.title + "\n" +
                        "Green Space: " + agendaTaskDTO.greenSpaceName + "\n" +
                        "Start Date: " + agendaTaskDTO.workStartDate + "\n" +
                        "Start Time: " + agendaTaskDTO.workStartHour + ":" + agendaTaskDTO.workStartMinutes + "\n";
                emailSender.sendEmailToCollaborator(teamDTO.email, "new task assigned.", body);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return task;
    }

}
