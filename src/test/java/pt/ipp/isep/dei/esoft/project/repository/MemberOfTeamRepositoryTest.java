package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberOfTeamRepositoryTest {

    private MemberOfTeamRepository repository;
    private Team teamDisbanded;
    private Collaborator collaborator;
    private MemberOfTeam memberOfTeam;

    @BeforeEach
    void setUp() {
        repository = new MemberOfTeamRepository();


        collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12,
                "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678",
                Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);


        teamDisbanded = new Team(collaborators);


        memberOfTeam = new MemberOfTeam(teamDisbanded, collaborator);
        repository.getListMemberOfTeam().add(memberOfTeam);
    }

    @Test
    void testRegisterTeamDisbandDate() {
        String disbandDate = "05/06/2024";
        repository.registerTeamDisbandDate(teamDisbanded, disbandDate);

        assertNotNull(memberOfTeam.getDateRemovedFromTeam());
        assertEquals(disbandDate, memberOfTeam.getDateRemovedFromTeam().toString());
    }

    @Test
    void testRegisterTeamDisbandDate_TeamNotFound() {

        List<Collaborator> newCollaborators = new ArrayList<>();
        Collaborator newCollaborator = new Collaborator("Jane Doe", "01/01/2000", "01/01/2020",
                "Rua Um", 10, "1234-567", "Lisboa", "Lisboa",
                "jane.doe@example.com", "927654321", Collaborator.IdDocType.PASSPORT,
                "AU123123", new Job("Engineer"));
        newCollaborators.add(newCollaborator);
        Team newTeam = new Team(newCollaborators);

        String disbandDate = "05/06/2024";
        repository.registerTeamDisbandDate(newTeam, disbandDate);


        assertNull(memberOfTeam.getDateRemovedFromTeam());
    }

    @Test
    void testRegisterTeamDisbandDate_AlreadyDisbanded() {
        String initialDisbandDate = "05/06/2024";
        memberOfTeam.setDateRemovedFromTeam(new Date(initialDisbandDate));

        String newDisbandDate = "2024-06-05";
        repository.registerTeamDisbandDate(teamDisbanded, newDisbandDate);

        assertEquals(initialDisbandDate, memberOfTeam.getDateRemovedFromTeam().toString());
    }
}
