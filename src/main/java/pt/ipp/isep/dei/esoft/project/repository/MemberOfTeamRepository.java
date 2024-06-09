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
}