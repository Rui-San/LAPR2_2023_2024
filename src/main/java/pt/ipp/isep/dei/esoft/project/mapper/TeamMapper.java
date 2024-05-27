package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public TeamMapper(){

    }

    public static List<TeamDTO> toDTOlist(List<Team> teamList) {
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team : teamList) {
            teamDTOS.add(toDTO(team));
        }
        return teamDTOS;
    }

    public static TeamDTO toDTO(Team team) {

        return new TeamDTO(team.getMembers(),team.getWorkPeriods());
    }

}
