package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Team Repository class
 */
public class TeamRepository {
    /**
     * List to store all the accepted teams.
     */
    private final List<Team> teamList;

    /**
     * Constructs a TeamRepository object.
     */
    public TeamRepository() {
        teamList = new ArrayList<>();
    }

    /**
     * Retrieves the list of teams.
     *
     * @return The list of teams.
     */
    public List<Team> getTeamList() {
        return teamList;
    }


    public Team getTeamByTeamMemberEmails(String memberEmail) {

        for (Team team : teamList) {
            for (Collaborator collaborator : team.getMembers()) {
                if (collaborator.getEmail().getEmail().trim().equalsIgnoreCase(memberEmail.trim())) {
                    return team;
                }
            }
        }
        return null;
    }

    /**
     * Generates a list with all possible teams with the given inputs.
     *
     * @param minTeamSize      the minimum size of the team
     * @param maxTeamSize      the maximum size of the team
     * @param skillsNeeded     the list of skills needed in the team
     * @param quantityNeeded   the quantity of each skill needed in the team
     * @param collaboratorList the list of collaborators of the organization
     * @return the list of all teams generated
     */
    public List<Team> generateAllTeamProposals(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList) {

        List<Team> definitiveTeamList = new ArrayList<>();

        if (!verifyQuantityNeeded(quantityNeeded, maxTeamSize) || minTeamSize < 2 || maxTeamSize < 2 || maxTeamSize < minTeamSize || skillsNeeded.isEmpty() || quantityNeeded.isEmpty()) {
            return definitiveTeamList;
        }

        List<Team> teamsList = new ArrayList<>();

        List<SkillSet> skillSetList = new ArrayList<>();

        for (int i = 0; i < skillsNeeded.size(); i++) {
            SkillSet skillSet = new SkillSet(skillsNeeded.get(i), quantityNeeded.get(i));
            skillSetList.add(skillSet);
        }

        List<Collaborator> possibleCollaborators = gatherPossibleCollaborators(collaboratorList, skillSetList);

        teamsList = createTeams(possibleCollaborators, minTeamSize, maxTeamSize);


        for (Team team : teamsList) {
            List<SkillSet> skillSetListCopy = new ArrayList<>();
            for (SkillSet skillSet : skillSetList) {
                skillSetListCopy.add(skillSet.clone());
            }
            for (Collaborator collaborator : team.getMembers()) {
                List<Skill> skillsOfThisCollaborator = collaborator.getSkillList();
                for (Skill collaboratorSkill : skillsOfThisCollaborator) {
                    for (SkillSet skillSet : skillSetListCopy) {
                        if (skillSet.getSkill().getSkillName().equals(collaboratorSkill.getSkillName())) {
                            skillSet.setInQuantity(skillSet.getInQuantity() - 1);
                        }
                    }
                }
            }
            if (verifyIfSkillSetIsFulfilled(skillSetListCopy)) {
                definitiveTeamList.add(team);
            }
        }
        return definitiveTeamList;
    }

    /**
     * Verifies if all skills needed are fulfilled according to their quantity.
     *
     * @param skillSetListCopy the list of object skillSet, containing the skill needed and its quantity
     * @return true if all skills/quantities are fulfilled for the team. False, otherwise.
     */
    private boolean verifyIfSkillSetIsFulfilled(List<SkillSet> skillSetListCopy) {
        boolean isFullfilled = true;

        for (SkillSet skillSet : skillSetListCopy) {
            if (skillSet.getInQuantity() > 0) {
                isFullfilled = false;
                return isFullfilled;
            }
        }
        return isFullfilled;
    }

    /**
     * Create all possible teams (from minimum size to maximum size).
     *
     * @param possibleCollaborators the collaborators able to join the team
     * @param minTeamSize           the minimum size of the team
     * @param maxTeamSize           the maximum size of the team
     * @return the list of all teams created
     */
    private List<Team> createTeams(List<Collaborator> possibleCollaborators, int minTeamSize, int maxTeamSize) {
        List<Team> allTeams = new ArrayList<>();

        for (int teamSize = minTeamSize; teamSize <= maxTeamSize && teamSize <= possibleCollaborators.size(); teamSize++) {
            List<Team> teamsBySize = createTeamsBySize(possibleCollaborators, teamSize);
            allTeams.addAll(teamsBySize);
        }
        return allTeams;
    }

    /**
     * By receiving the current team size in parameter (from the minimum to the maximum team size) creates all the teams for that specific size.
     *
     * @param possibleCollaborators the collaborators able to join the team
     * @param teamSize              the team size
     * @return the list of teams of that specific size
     */

    private List<Team> createTeamsBySize(List<Collaborator> possibleCollaborators, int teamSize) {
        List<Team> teams = new ArrayList<>();

        int n = possibleCollaborators.size();

        int[] index = new int[teamSize];
        for (int i = 0; i < teamSize; i++) {
            index[i] = i;
        }

        boolean done = false;
        while (!done) {
            List<Collaborator> actualTeam = new ArrayList<>();
            for (int i = 0; i < teamSize; i++) {
                actualTeam.add(possibleCollaborators.get(index[i]));
            }
            teams.add(new Team(actualTeam));

            int j = teamSize - 1;
            while (j >= 0 && index[j] == n - teamSize + j) {
                j--;
            }
            if (j < 0) {
                done = true;
            } else {
                index[j]++;
                for (int k = j + 1; k < teamSize; k++) {
                    index[k] = index[k - 1] + 1;
                }
            }
        }
        return teams;
    }


/*
    private List<Team> createTeamsBySize(List<Collaborator> possibleCollaborators, int teamSize) {
        List<Team> teams = new ArrayList<>();

        int n = possibleCollaborators.size();

        int[] index = new int[teamSize];
        for (int i = 0; i < teamSize; i++) {
            index[i] = i;
        }

        while (index[0] < n - teamSize + 1) {
            List<Collaborator> actualTeam = new ArrayList<>();
            for (int i = 0; i < teamSize; i++) {
                actualTeam.add(possibleCollaborators.get(index[i]));
            }
            teams.add(new Team(actualTeam));

            int j = teamSize - 1;
            while (j >= 0 && index[j] == n - teamSize + j) {
                j--;
            }
            if (j < 0) {
                break;
            }
            index[j]++;
            for (int k = j + 1; k < teamSize; k++) {
                index[k] = index[k - 1] + 1;
            }
        }
        return teams;
    }

 */

    /**
     * Check which collaborators of the company can join a new team.
     * In order to join a new team, this method verifies if the collaborator is already in a team (by calling method isAlreadyInOneTeam) and if the collaborator has at least one needed skill.
     *
     * @param collaboratorList the list of collaborators of the organization
     * @param skillSetList     the set of skills needed in the team
     * @return the possible collaborators that can be integrated in the specific team
     */
    private List<Collaborator> gatherPossibleCollaborators(List<Collaborator> collaboratorList, List<SkillSet> skillSetList) {

        List<Collaborator> possibleCollaborators = new ArrayList<>();

        for (Collaborator collaborator : collaboratorList) {
            if (!isAlreadyInOneTeam(collaborator) && collaborator.hasAtLeastOneSkill(SkillSet.getAllSkills(skillSetList))) {
                possibleCollaborators.add(collaborator);
            }
        }
        return possibleCollaborators;
    }

    /**
     * Verifies if a specific collaborator is already in one team at the moment.
     *
     * @param collaborator the collaborator
     * @return true if the collaborator is already in one team. False, otherwise.
     */
    private boolean isAlreadyInOneTeam(Collaborator collaborator) {

        boolean isInOneTeam = false;
        for (Team team : teamList) {
            List<Collaborator> members = team.getMembers();
            if (members.contains(collaborator)) {
                isInOneTeam = true;
            }
        }
        return isInOneTeam;
    }

    /**
     * Verifies if the quantity of a specific skill is superior to the maximum team size.
     *
     * @param quantityNeeded the quantity needed of each skill
     * @param maxTeamSize    the maximum size of the team
     * @return true if the quantity of any skill is lower than the team size. False, otherwise.
     */
    private boolean verifyQuantityNeeded(List<Integer> quantityNeeded, int maxTeamSize) {
        boolean quantityCorrect = true;
        for (Integer quantity : quantityNeeded) {
            if (quantity > maxTeamSize) {
                quantityCorrect = false;
            }
        }
        return quantityCorrect;
    }

    /**
     * Adds the selected team to the list of teams in the repository.
     *
     * @param teamAccepted the team accepted
     */
    public void saveTeamProposal(Team teamAccepted) {
        teamList.add(teamAccepted);
    }

    public void removeWorkPeriodFromTeam(Task canceledTask) {
        WorkPeriod taskWorkPeriod = canceledTask.getTaskWorkPeriod();
        String emailToVerify = canceledTask.getTeamAssigned().getMembers().get(0).getEmail().getEmail().trim();

        for(Team team : teamList){
            for(Collaborator collaborator : team.getMembers()){
                if(collaborator.getEmail().getEmail().trim().equalsIgnoreCase(emailToVerify)){
                    team.removeWorkPeriodIfExists(taskWorkPeriod);
                }
            }
        }
    }

    public void postponeWorkPeriods(Task postponedTask, WorkPeriod newWorkPeriod) {
        removeWorkPeriodFromTeam(postponedTask);
        for (Team team : teamList) {
            for (Collaborator collaborator : team.getMembers()) {
                if (collaborator.getEmail().getEmail().trim().equalsIgnoreCase(postponedTask.getTeamAssigned().getMembers().get(0).getEmail().getEmail().trim())) {
                    team.addWorkPeriod(newWorkPeriod);
                    System.out.println("Team work period postponed!");
                }
            }
        }
    }
}