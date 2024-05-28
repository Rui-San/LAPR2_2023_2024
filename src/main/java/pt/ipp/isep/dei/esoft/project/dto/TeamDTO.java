package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;

import java.util.List;

public class TeamDTO {

    public List<Collaborator> members;

    public List<WorkPeriod> workPeriods;

    public TeamDTO(List<Collaborator> members, List<WorkPeriod> workPeriods) {
        this.members = members;
        this.workPeriods = workPeriods;
    }

}
