package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;


public class TeamRepository_testing {
    private final List<Team_testing> teamList;

    public TeamRepository_testing() {
        teamList = new ArrayList<>();
    }

    public static void main(String[] args) {

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

    public List<Team_testing> generateTeams(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList) {

        if (!verifyQuantityNeeded(quantityNeeded, maxTeamSize) || minTeamSize < 0 || maxTeamSize < 0 || maxTeamSize < minTeamSize || skillsNeeded.isEmpty() || quantityNeeded.isEmpty()) {
            return null;
        }
        List<Team_testing> teamsList = new ArrayList<>();

        List<SkillSet> skillSetList = new ArrayList<>();

        for (int i = 0; i < skillsNeeded.size(); i++) {
            SkillSet skillSet = new SkillSet(skillsNeeded.get(i), quantityNeeded.get(i));
            skillSetList.add(skillSet);
        }

        List<Collaborator> possibleCollaborators = gatherPossibleCollaborators(collaboratorList, skillSetList);

        teamsList = criarEquipas(possibleCollaborators, minTeamSize, maxTeamSize);

        List<Team_testing> definitiveTeamList = new ArrayList<>();

        for (Team_testing team : teamsList) {
            List<SkillSet> skillSetListCopy = List.copyOf(skillSetList);
            for (Collaborator collaborator : team.getMembers()) {
                List<Skill> skillsOfThisCollaborator = collaborator.getSkillList();
                for (Skill collaboratorSkill : skillsOfThisCollaborator) {
                    for (SkillSet skillSet : skillSetListCopy) {
                        if (skillSet.getSkill().equals(collaboratorSkill)) {
                            skillSet.setInQuantity(skillSet.getInQuantity() - 1);
                        }
                    }
                }
            }
            if (verifyIfSkillSetIsFullfilled(skillSetListCopy)) {
                definitiveTeamList.add(team);
            }
        }


        //TODO: ordena a lista final pelo numero de colaboradores(mais eficaz)

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
        return definitiveTeamList;
    }

    private boolean verifyIfSkillSetIsFullfilled(List<SkillSet> skillSetListCopy) {
        boolean isFullfilled = true;

        for (SkillSet skillSet : skillSetListCopy) {
            if (skillSet.getInQuantity() > 0) {
                isFullfilled = false;
                return isFullfilled;
            }
        }
        return isFullfilled;
    }

    private List<Team_testing> criarEquipas(List<Collaborator> possibleCollaborators, int minTeamSize, int maxTeamSize) {

        List<Team_testing> todasAsEquipas = new ArrayList<>();

        for (int teamSize = minTeamSize; teamSize < maxTeamSize && teamSize <= possibleCollaborators.size(); teamSize++) {
            List<Team_testing> equipasPorTamanho = criarEquipasPorTamanho(possibleCollaborators, teamSize);
            todasAsEquipas.addAll(equipasPorTamanho);
        }
        return todasAsEquipas;
    }

    private List<Team_testing> criarEquipasPorTamanho(List<Collaborator> possibleCollaborators, int teamSize) {
        List<Team_testing> equipas = new ArrayList<>();

        int n = possibleCollaborators.size();

        int[] indices = new int[teamSize];
        for (int i = 0; i < teamSize; i++) {
            indices[i] = i;
        }

        while (indices[0] < n - teamSize + 1) {
            List<Collaborator> equipeAtual = new ArrayList<>();
            for (int i = 0; i < teamSize; i++) {
                equipeAtual.add(possibleCollaborators.get(indices[i]));
            }
            equipas.add(new Team_testing(equipeAtual));

            int j = teamSize - 1;
            while (j >= 0 && indices[j] == n - teamSize + j) {
                j--;
            }
            if (j < 0) {
                break;
            }
            indices[j]++;
            for (int k = j + 1; k < teamSize; k++) {
                indices[k] = indices[k - 1] + 1;
            }
        }
        return equipas;
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

    private List<Collaborator> gatherPossibleCollaborators(List<Collaborator> collaboratorList, List<SkillSet> skillSetList) {

        List<Collaborator> possibleCollaborators = new ArrayList<>();

        for (Collaborator collaborator : collaboratorList) {
            if (!isAllreadyInOneTeam(collaborator) && collaborator.hasAtLeastOneSkill(SkillSet.getAllSkills(skillSetList))) {
                possibleCollaborators.add(collaborator);
            }
        }
        return possibleCollaborators;
    }

    private boolean isAllreadyInOneTeam(Collaborator collaborator) {

        boolean isInOneTeam = false;
        for (Team_testing team : teamList) {
            List<Collaborator> members = team.getMembers();
            if (members.contains(collaborator)) {
                isInOneTeam = true;
            }
        }
        return isInOneTeam;
    }

    private boolean verifyQuantityNeeded(List<Integer> quantityNeeded, int maxTeamSize) {
        boolean quantityCorrect = true;
        for (Integer quantity : quantityNeeded) {
            if (quantity > maxTeamSize) {
                quantityCorrect = false;
            }
        }
        return quantityCorrect;
    }
}

