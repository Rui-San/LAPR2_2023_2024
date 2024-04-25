package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository {
    private final List<List<Skill>> teams;

    public TeamRepository() {
        teams = new ArrayList<>();
    }

    public Optional<List<Skill>> add(List<Skill> team) {
        Optional<List<Skill>> newTeam = Optional.empty();
        boolean operationSuccess = false;

        if (validateTeam(team)) {
            newTeam = Optional.of(new ArrayList<>(team));
            operationSuccess = teams.add(newTeam.get());
        }

        if (!operationSuccess) {
            newTeam = Optional.empty();
        }

        return newTeam;
    }

    private boolean validateTeam(List<Skill> team) {
        boolean isValid = !teams.contains(team);
        return isValid;
    }

    public List<List<Skill>> getTeams() {
        return List.copyOf(teams);
    }

    public boolean addTeam(List<Skill> team) {
        if (validateTeam(team)) {
            return teams.add(new ArrayList<>(team));
        }
        return false;
    }
}