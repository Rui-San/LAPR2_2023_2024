package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamTest {

    @Test
    public void ensureCreateTeamWorks() {
        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician"));
        Collaborator collaborator5 = new Collaborator("Ana Rodrigues", "20/07/1998", "04/05/2024", "Rua das Flores", 5, "1050-201", "Lisboa", "Lisboa", "arodrigues@this.app", "912345678", Collaborator.IdDocType.CC, "123555789", new Job("Farmer"));

        List<Collaborator> collaboratorsToAdd = List.of(collaborator, collaborator2, collaborator3, collaborator4, collaborator5);

        Team newTeam = new Team(collaboratorsToAdd);
    }

    @Test
    public void ensureGetMembersReturnsCorrectValue() {
        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));

        List<Collaborator> collaboratorsToAdd = List.of(collaborator, collaborator2);
        Team newTeam = new Team(collaboratorsToAdd);
        List<Collaborator> actualMembers = newTeam.getMembers();
        assertEquals(actualMembers, collaboratorsToAdd);
    }

    @Test
    public void ensureToStringWorksTest() {

        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));

        List<Collaborator> collaboratorsToAdd = List.of(collaborator, collaborator2);
        Team newTeam = new Team(collaboratorsToAdd);

        Skill skill1 = new Skill("License Drive");
        Skill skill2 = new Skill("Tree Prunning");

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill1);
        skillList.add(skill2);

        collaborator2.assignSkills(skillList);

        String expectedResult = "Name: João Silva |  Email: jsilva@this.app\n" +
                "Skills: \n" +
                "Name: Guilherme Santos |  Email: gsantos@this.app\n" +
                "Skills: License Drive, Tree Prunning\n";
        String actualResult = newTeam.toString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureAddMemberWorksTest() {
        Collaborator collaborator = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));

        List<Collaborator> collaboratorsToAdd = new ArrayList<>();
        collaboratorsToAdd.add(collaborator);
        collaboratorsToAdd.add(collaborator2);
        Team newTeam = new Team(collaboratorsToAdd);

        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        newTeam.addMember(collaborator3);

        assertTrue(newTeam.getMembers().contains(collaborator3));
    }
}
