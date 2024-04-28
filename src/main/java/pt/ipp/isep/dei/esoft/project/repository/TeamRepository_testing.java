package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team_testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TeamRepository_testing {
    private final List<Team_testing> teamList;

    public TeamRepository_testing() {
        teamList = new ArrayList<>();
    }

    public Optional<Team_testing> generateTeamPropostal(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded, List<Collaborator> collaboratorList) {

        if (!verifyQuantityNeeded(quantityNeeded, maxTeamSize) || minTeamSize < 0 || maxTeamSize < 0 || maxTeamSize < minTeamSize || skillsNeeded.isEmpty() || quantityNeeded.isEmpty()) {
            return null;
        }

        List<Integer> quantityFullfilled = new ArrayList<>(Collections.nCopies(quantityNeeded.size(), 0));

        int betterOption = 0;
        List<Collaborator> possibleCollaborators = new ArrayList<>();
        List<Integer> totalNumberSkillsCollaboratorHave = new ArrayList<>();

        for (Collaborator collaborator : collaboratorList) {
            if (!isAllreadyInOneTeam(collaborator)) {
                //Se não tiver em nenhuma equipa, verifica.
                //skills desse colaborador:
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


        //TODO: CONTINUE THE IMPLEMENTATION OF THIS ALGORITHM.

        return null;
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
