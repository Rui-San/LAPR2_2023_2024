package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.*;
import java.util.HashMap;

public class TeamRepository {
    private final List<Map<Skill, Integer>> teams;

    public TeamRepository() {

        teams = new ArrayList<>();
    }

    public Optional<Map<Skill, Integer>> add(Map<Skill, Integer> team) {
        Optional<Map<Skill, Integer>> newTeam = Optional.empty();
        boolean operationSuccess = false;

        if (validateTeam(team)) {
            newTeam = Optional.of(new HashMap<>(team));
            operationSuccess = teams.add(newTeam.get());
        }

        if (!operationSuccess) {
            newTeam = Optional.empty();
        }

        return newTeam;
    }

    private boolean validateTeam(Map<Skill, Integer> team) {
        boolean isValid = !teams.contains(team);
        return isValid;
    }

    public List<Map<Skill, Integer>> getTeams() {

        return List.copyOf(teams);
    }

    public boolean addTeam(Map<Skill, Integer> team) {
        if (validateTeam(team)) {
            return teams.add(new HashMap<>(team));
        }
        return false;
    }
}