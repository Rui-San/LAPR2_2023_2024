package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.SerializationFiles;
import pt.ipp.isep.dei.esoft.project.tools.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MemberOfTeam Repository
 */
public class MemberOfTeamRepository implements Serializable {
    /**
     * List of MemberOfTeam
     */
    private List<MemberOfTeam> listMemberOfTeam;

    /**
     * Returns the list of MemberOfTeam
     *
     * @return the list of MemberOfTeam
     */
    public List<MemberOfTeam> getListMemberOfTeam() {
        return listMemberOfTeam;
    }

    /**
     * Constructs a MemberOfTeamRepository object.
     */
    public MemberOfTeamRepository() {
        listMemberOfTeam = new ArrayList<>();
    }

    /**
     * This method is responsible to find the Team that was disbanded on listMemberOfTeam and set the disband date.
     *
     * @param teamDisbanded the team disbanded
     * @param dateDisband   the date of team disband
     */
    public void registerTeamDisbandDate(Team teamDisbanded, String dateDisband) {
        List<Collaborator> collaboratorsOfDisbandedTeam = teamDisbanded.getMembers();

        for (MemberOfTeam memberOfTeam : listMemberOfTeam) {
            List<Collaborator> collaboratorsOfTeam = memberOfTeam.getTeamRecord().getMembers();

            if (collaboratorsOfTeam.size() == collaboratorsOfDisbandedTeam.size()) {
                boolean allMatch = true;

                for (int i = 0; i < collaboratorsOfDisbandedTeam.size() && allMatch; i++) {
                    boolean matchFound = isMatchFound(collaboratorsOfDisbandedTeam, i, collaboratorsOfTeam);

                    if (!matchFound) {
                        allMatch = false;
                    }
                }

                if (allMatch && memberOfTeam.getDateRemovedFromTeam() == null) {
                    memberOfTeam.setDateRemovedFromTeam(new Date(dateDisband));
                }
            }
        }
    }

    /**
     * Verifies if a specific collaborator belongs to the team by comparing the emails.
     *
     * @param collaboratorsOfDisbandedTeam the list of collaborators of the disbanded team.
     * @param i the index of the collaborator of the disbanded team
     * @param collaboratorsOfTeam the list of collaborators of the team of membersOfTeam
     * @return the logical state. True if the email of the collaborator on the "i" index matches, false otherwise.
     */
    private boolean isMatchFound(List<Collaborator> collaboratorsOfDisbandedTeam, int i, List<Collaborator> collaboratorsOfTeam) {
        Collaborator disbandedCollaborator = collaboratorsOfDisbandedTeam.get(i);
        boolean matchFound = false;

        for (int j = 0; j < collaboratorsOfTeam.size() && !matchFound; j++) {
            Collaborator collaborator = collaboratorsOfTeam.get(j);
            if (disbandedCollaborator.getEmail().getEmail().trim()
                    .equals(collaborator.getEmail().getEmail().trim())) {
                matchFound = true;
            }
        }
        return matchFound;
    }


}