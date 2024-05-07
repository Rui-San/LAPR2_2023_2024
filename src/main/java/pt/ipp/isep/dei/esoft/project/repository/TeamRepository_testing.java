package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;


public class TeamRepository_testing {
    private final List<Team_testing> teamList;

    public TeamRepository_testing() {
        teamList = new ArrayList<>();
    }

    /*
    public static void main(String[] args) {

        TeamRepository_testing teamRepository = new TeamRepository_testing();

        Job job = new Job("Gestor");

        Collaborator collaborator1 = new Collaborator("a a", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator collaborator2 = new Collaborator("b b", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator collaborator3 = new Collaborator("c c", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        Collaborator collaborator4 = new Collaborator("d d", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "e e", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        Collaborator collaborator5 = new Collaborator("e e", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        Collaborator collaborator6 = new Collaborator("f f", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        Collaborator collaborator7 = new Collaborator("g g", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator collaborator8 = new Collaborator("h h", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator collaborator9 = new Collaborator("i i", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator collaborator10 = new Collaborator("j j", "07/10/1995", "20/03/2024", "Rua das travessas", 123,
                "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);


        Skill skill1 = new Skill("license drive");
        Skill skill2 = new Skill("tree pruning");

        List<Skill> skillsNeeded = new ArrayList<>();
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(skillsNeeded);
        collaborator3.assignSkills(skillsNeeded);
        collaborator4.assignSkills(skillsNeeded);
        collaborator5.assignSkills(skillsNeeded);
        collaborator6.assignSkills(skillsNeeded);
        collaborator8.assignSkills(skillsNeeded);
        collaborator9.assignSkills(skillsNeeded);
        collaborator10.assignSkills(skillsNeeded);



        Skill skillDontHave = new Skill("Cortador");

        List<Skill> skillsNeeded2 = new ArrayList<>();
        skillsNeeded2.add(skill1);
        skillsNeeded2.add(skill2);
        skillsNeeded2.add(skillDontHave);

        collaborator2.assignSkills(skillsNeeded2);
        collaborator7.assignSkills(skillsNeeded2);

        List<Collaborator> collaboratorList = new ArrayList<>();
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);
        collaboratorList.add(collaborator3);
        collaboratorList.add(collaborator4);
        collaboratorList.add(collaborator5);
        collaboratorList.add(collaborator6);
        collaboratorList.add(collaborator7);
        collaboratorList.add(collaborator8);
        collaboratorList.add(collaborator9);
        collaboratorList.add(collaborator10);


        List<Team_testing> definitiveTeamList = teamRepository.generateTeams(2, 3, skillsNeeded2, quantityNeeded, collaboratorList);

        if (definitiveTeamList.isEmpty()) {
            System.out.println("Not possible to create teams with given information");
        } else {
            for (Team_testing team : definitiveTeamList) {
                System.out.println("TEAM GENERATE:");
                System.out.println(team.toString());
            }
            int totalAccTeams=definitiveTeamList.size();
            System.out.println("Total teams generated: " + totalAccTeams);
        }

    }

     */

    /**
     * Generates a list with all possible teams with the given inputs.
     *
     * @param minTeamSize the minimum size of the team
     * @param maxTeamSize the maximum size of the team
     * @param skillsNeeded the list of skills needed in the team
     * @param quantityNeeded the quantity of each skill needed in the team
     * @param collaboratorList the list of collaborators of the organization
     * @return the list of all teams generated
     */
    public List<Team_testing> generateAllTeamProposals(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList) {

        List<Team_testing> definitiveTeamList = new ArrayList<>();

        if (!verifyQuantityNeeded(quantityNeeded, maxTeamSize) || minTeamSize < 2 || maxTeamSize < 2 || maxTeamSize < minTeamSize || skillsNeeded.isEmpty() || quantityNeeded.isEmpty()) {
            return definitiveTeamList;
        }

        List<Team_testing> teamsList = new ArrayList<>();

        List<SkillSet> skillSetList = new ArrayList<>();

        for (int i = 0; i < skillsNeeded.size(); i++) {
            SkillSet skillSet = new SkillSet(skillsNeeded.get(i), quantityNeeded.get(i));
            skillSetList.add(skillSet);
        }

        List<Collaborator> possibleCollaborators = gatherPossibleCollaborators(collaboratorList, skillSetList);

        teamsList = createTeams(possibleCollaborators, minTeamSize, maxTeamSize);


        for (Team_testing team : teamsList) {
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


        //TODO: ordena a lista final pelo numero de colaboradores(mais eficaz)


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
     * @param minTeamSize the minimum size of the team
     * @param maxTeamSize the maximum size of the team
     * @return the list of all teams created
     */
    private List<Team_testing> createTeams(List<Collaborator> possibleCollaborators, int minTeamSize, int maxTeamSize) {
        List<Team_testing> allTeams = new ArrayList<>();

        for (int teamSize = minTeamSize; teamSize <= maxTeamSize && teamSize <= possibleCollaborators.size(); teamSize++) {
            List<Team_testing> teamsBySize = createTeamsBySize(possibleCollaborators, teamSize);
            allTeams.addAll(teamsBySize);
        }
        return allTeams;
    }

    /**
     * By receiving the current team size in parameter (from the minimum to the maximum team size) creates all the teams for that specific size.
     *
     * @param possibleCollaborators the collaborators able to join the team
     * @param teamSize the team size
     * @return the list of teams of that specific size
     */
    private List<Team_testing> createTeamsBySize(List<Collaborator> possibleCollaborators, int teamSize) {
        List<Team_testing> teams = new ArrayList<>();

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
            teams.add(new Team_testing(actualTeam));

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


    /**
     * Verifies which collaborators of the company are not currently in a team and have at least one skill required to join the team.
     *
     * @param collaboratorList the list of collaborators of the organization
     * @param skillSetList the set of skills needed in the team
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
        for (Team_testing team : teamList) {
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
     * @param maxTeamSize the maximum size of the team
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
     * @param teamAccepted
     */
    public void saveTeamProposal(Team_testing teamAccepted) {
        teamList.add(teamAccepted);
    }

    private boolean collaboratorIsInCurrentTeam(List<Collaborator> membersInTeam, Collaborator collaborator) {

        if (membersInTeam.contains(collaborator)) {
            return true;
        }
        return false;
    }

    private void updateSkillSet(List<SkillSet> skillSetList, Collaborator newMember) {

        List<Skill> newMemberSkills = newMember.getSkillList();

        for (Skill skill : newMemberSkills) {
            for (SkillSet skillSet : skillSetList) {
                if (skillSet.getSkill().equals(skill)) {
                    skillSet.setInQuantity(skillSet.getInQuantity() - 1);
                }
            }

        }
    }


}
/*
    public Optional<Team_testing> generateTeamPropostal(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList) {

        if (!verifyQuantityNeeded(quantityNeeded, maxTeamSize) || minTeamSize < 0 || maxTeamSize < 0 || maxTeamSize < minTeamSize || skillsNeeded.isEmpty() || quantityNeeded.isEmpty()) {
            return null;
        }

        // List<Integer> quantityFullfilled = new ArrayList<>(Collections.nCopies(quantityNeeded.size(), 0));

        //  int betterOption = 0;

        List<Collaborator> possibleCollaborators = new ArrayList<>();
        List<Integer> totalNumberSkillsCollaboratorHave = new ArrayList<>();

        for (Collaborator collaborator : collaboratorList) {
            if (!isAllreadyInOneTeam(collaborator)) {

                //Se não tiver em nenhuma equipa, verifica as skills desse colaborador:
                List<Skill> collaboratorSkillList = collaborator.getSkillList();

                int numberOfSkillsHad = 0;

                for (Skill skillNeeded : skillsNeeded) {
                    if (collaboratorSkillList.contains(skillNeeded)) {
                        numberOfSkillsHad++;
                    }
                }
                if (numberOfSkillsHad > 0) {
                    possibleCollaborators.add(collaborator);
                    totalNumberSkillsCollaboratorHave.add(numberOfSkillsHad);
                }
            }
        }

        //Neste ponto já temos a lista de colaboradores que podem integrar a equipa e qual o colaborador
        // que tem mais quantidades de skills de uma só vez (para ser um algoritmo mais eficiente)
        // Agora é ordenar a lista de collaborators por quantidade de skill possuida maior e continuar algoritmo

        sortCollaboratorByDescendingQuantity(possibleCollaborators, totalNumberSkillsCollaboratorHave);




        return null;
    }

    private void sortCollaboratorByDescendingQuantity(List<Collaborator> possibleCollaborators, List<Integer> totalNumberSkillsCollaboratorHave) {



        Collaborator[] collaboratorArray = possibleCollaborators.toArray(new Collaborator[0]);

        List<Integer> originalIndexes = new ArrayList<>();

        for (int i = 0; i < totalNumberSkillsCollaboratorHave.size(); i++) {
            originalIndexes.add(i);
        }

        Collections.sort(totalNumberSkillsCollaboratorHave, Collections.reverseOrder());

        for (int i = 0; i < totalNumberSkillsCollaboratorHave.size(); i++) {
            int originalIndex = originalIndexes.get(i);
            Collaborator collaborator = collaboratorArray[originalIndex];
            possibleCollaborators.set(i, collaborator);
        }
    }


     */

/*

               for (int i = 0; i < possibleCollaborators.size(); i++) {
            Team_testing newTeam = new Team_testing();
            Collaborator member = possibleCollaborators.get(i);
            newTeam.addMember(member);
            updateSkillSet(skillSetList, member);
            for (int j = 0; j < possibleCollaborators.size(); j++) {
                if (!collaboratorIsInCurrentTeam(newTeam.getMembers(), possibleCollaborators.get(j))) {
                    Collaborator newMember = possibleCollaborators.get(j);
                    newTeam.addMember(newMember);
                    updateSkillSet(skillSetList, newMember);
                }
            }
            teamsList.add(newTeam);
        }

*/