package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignTeamToEntryAgendaController {

    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;
    private TeamRepository teamRepository;

    public AssignTeamToEntryAgendaController() {
        getAgendaRepository();
        getAuthenticationRepository();
        getTeamRepository();

    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    public List<AgendaTaskDTO> getAgenda() {
        List<Task> agenda = agendaRepository.getAgenda();
        return AgendaMapper.toDTOlist(agenda);
    }

    public List<TeamDTO> getTeams(){
        List<TeamDTO> teamListDTO = new ArrayList<>();

        for(Team team : teamRepository.getTeamList()){
            List<CollaboratorDTO> collaboratorDTOList = CollaboratorMapper.toDTOlist(team.getMembers());
            teamListDTO.add(TeamMapper.toDTO(collaboratorDTOList));
        }
        return teamListDTO;
    }


/*
    public List<Team> getTeams(){
        return teamRepository.getTeamList();
    }
*/
    public Optional<Task> assignTeamToTaskAgenda(AgendaTaskDTO agendaTaskDTO, TeamDTO selectedTeamDTO) {
        Team teamObj = teamRepository.getTeamByTeamMemberEmails(selectedTeamDTO.collaborators.get(0).email);
        return agendaRepository.assignTeamToTaskAgenda(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status, teamObj);
    }
}
