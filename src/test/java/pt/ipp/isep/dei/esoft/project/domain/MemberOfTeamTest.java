package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberOfTeamTest {

    private MemberOfTeam memberOfTeam;
    private Team team;
    private Collaborator collaborator;
    private Date dateAdded;
    private Date dateRemoved;

    @BeforeEach
    void setUp() {
        // Inicializa os objetos necessários para os testes
        List<Collaborator> members = new ArrayList<>();
        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));

        members.add(collaborator);
        team = new Team(members);
        memberOfTeam = new MemberOfTeam(team, collaborator);
        dateAdded = new Date();
        dateRemoved = new Date();
    }

    @Test
    void testGetTeamRecord() {
        assertEquals(team, memberOfTeam.getTeamRecord());
    }

    @Test
    void testSetTeamRecord() {
        List<Collaborator> newMembers = new ArrayList<>();
        Collaborator newCollaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        newMembers.add(newCollaborator);
        Team newTeam = new Team(newMembers);
        memberOfTeam.setTeamRecord(newTeam);
        assertEquals(newTeam, memberOfTeam.getTeamRecord());
    }


    @Test
    void testSetCollaboratorReference() {
        Collaborator newCollaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        memberOfTeam.setCollaboratorReference(newCollaborator);
        assertEquals(newCollaborator, memberOfTeam.getCollaboratorReference());
    }

    @Test
    void testGetDateAddedToTeam() {
        assertNotNull(memberOfTeam.getDateAddedToTeam());
    }

    @Test
    void testSetDateAddedToTeam() {
        memberOfTeam.setDateAddedToTeam(dateAdded);
        assertEquals(dateAdded, memberOfTeam.getDateAddedToTeam());
    }

    @Test
    void testGetDateRemovedFromTeam() {
        assertNull(memberOfTeam.getDateRemovedFromTeam());
    }

    @Test
    void testSetDateRemovedFromTeam() {
        memberOfTeam.setDateRemovedFromTeam(dateRemoved);
        assertEquals(dateRemoved, memberOfTeam.getDateRemovedFromTeam());
    }

}
