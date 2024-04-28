package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team_testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository_testing {
    private final List<Team_testing> teamList;


    public TeamRepository_testing() {
        teamList = new ArrayList<>();
    }

    public Optional<Team_testing> generateTeamPropostal (int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList){


        //TODO: IMPLEMENT ALGORITHM.

        return null;
    }
}
