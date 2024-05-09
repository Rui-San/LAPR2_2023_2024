package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamRepositoryTest {

    @Test
    public void generateAllTeamProposals_ValidInput_ReturnsExpectedTeams() {
        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Skill> individualSkills1 = new ArrayList<>();
        individualSkills1.add(skill1);

        List<Skill> individualSkills2 = new ArrayList<>();
        individualSkills2.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(individualSkills1);
        collaborator2.assignSkills(individualSkills2);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(2, 2, skillsNeeded, quantityNeeded, collaboratorList);

        assertEquals(1, teams.size());
    }

    @Test
    public void ensureSaveTeamProposalAddsTeamToList() {

        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        TeamRepository repository = new TeamRepository();
        Team team = new Team(collaboratorList);

        repository.saveTeamProposal(team);

        assertTrue(repository.getTeamList().contains(team));
    }

    @Test
    public void ensureMinTeamSizeIsntHigherThanMaxTeamSize() {

        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Skill> individualSkills1 = new ArrayList<>();
        individualSkills1.add(skill1);

        List<Skill> individualSkills2 = new ArrayList<>();
        individualSkills2.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(individualSkills1);
        collaborator2.assignSkills(individualSkills2);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(3, 2, skillsNeeded, quantityNeeded, collaboratorList);

        List<Team> expectedTeamList = new ArrayList<>();

        assertEquals(teams.size(), expectedTeamList.size());
    }

    @Test
    public void ensureCollaboratorListCantBeEmpty() {

        List<Collaborator> collaboratorList = new ArrayList<>();

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Skill> individualSkills1 = new ArrayList<>();
        individualSkills1.add(skill1);

        List<Skill> individualSkills2 = new ArrayList<>();
        individualSkills2.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(3, 2, skillsNeeded, quantityNeeded, collaboratorList);

        List<Team> expectedTeamList = new ArrayList<>();

        assertEquals(teams.size(), expectedTeamList.size());
    }

    @Test
    public void ensureSkillNeededCantBeEmpty() {

        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        List<Skill> skillsNeeded = new ArrayList<>();

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(2, 4, skillsNeeded, quantityNeeded, collaboratorList);

        List<Team> expectedTeamList = new ArrayList<>();

        assertEquals(teams.size(), expectedTeamList.size());
    }

    @Test
    public void ensureQuantityNeededCantBeEmpty() {

        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(2, 4, skillsNeeded, quantityNeeded, collaboratorList);

        List<Team> expectedTeamList = new ArrayList<>();

        assertEquals(teams.size(), expectedTeamList.size());
    }

    @Test
    public void ensureCollaboratorCantBeInTwoTeams() {
        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);

        List<Skill> individualSkills1 = new ArrayList<>();
        individualSkills1.add(skill1);

        List<Skill> individualSkills2 = new ArrayList<>();
        individualSkills2.add(skill2);

        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(individualSkills1);
        collaborator2.assignSkills(individualSkills2);

        TeamRepository repository = new TeamRepository();

        List<Team> teams1 = repository.generateAllTeamProposals(2, 2, skillsNeeded, quantityNeeded, collaboratorList);

        repository.saveTeamProposal(teams1.get(0));

        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician"));

        collaboratorList.add(collaborator3);
        collaboratorList.add(collaborator4);

        collaborator3.assignSkills(individualSkills1);
        collaborator4.assignSkills(individualSkills2);

        List<Team> teams2 = repository.generateAllTeamProposals(2, 2, skillsNeeded, quantityNeeded, collaboratorList);

        assertFalse(teams2.get(0).getMembers().contains(collaborator1));
        assertFalse(teams2.get(0).getMembers().contains(collaborator1));
    }

    @Test
    public void ensureAlgorithmCreatesTeamsForEachSize() {
        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician"));
        Collaborator collaborator5 = new Collaborator("Ana Rodrigues", "20/07/1998", "04/05/2024", "Rua das Flores", 5, "1050-201", "Lisboa", "Lisboa", "arodrigues@this.app", "912345678", Collaborator.IdDocType.CC, "123555789", new Job("Farmer"));
        Collaborator collaborator6 = new Collaborator("Miguel Oliveira", "12/12/1995", "06/05/2024", "Avenida Central", 30, "4000-100", "Porto", "Porto", "moliveira@this.app", "912345678", Collaborator.IdDocType.CC, "124456789", new Job("Electrician"));

        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);
        collaboratorList.add(collaborator3);
        collaboratorList.add(collaborator4);
        collaboratorList.add(collaborator5);
        collaboratorList.add(collaborator6);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);


        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(skillsNeeded);
        collaborator2.assignSkills(skillsNeeded);
        collaborator3.assignSkills(skillsNeeded);
        collaborator4.assignSkills(skillsNeeded);
        collaborator5.assignSkills(skillsNeeded);
        collaborator6.assignSkills(skillsNeeded);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(2, 5, skillsNeeded, quantityNeeded, collaboratorList);

        // Counts how many teams exist for each size (in this example: 2, 3, 4 e 5 members)
        int[] teamSizes = new int[4];
        for (Team team : teams) {
            int teamSize = team.getMembers().size();
            teamSizes[teamSize - 2]++;
        }

        // Ensure that, with given parameters, there must be at least one team of specified size
        for (int i = 0; i < 4; i++) {
            assertTrue(teamSizes[i] > 0, "With given parameters, there must be at least one team of size " + (i + 2) + ".");
        }
    }

    @Test
    public void ensureTotalMembersOfTeamISBetweenMaxAndMinSize() {
        List<Collaborator> collaboratorList = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM"));
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician"));
        Collaborator collaborator5 = new Collaborator("Ana Rodrigues", "20/07/1998", "04/05/2024", "Rua das Flores", 5, "1050-201", "Lisboa", "Lisboa", "arodrigues@this.app", "912345678", Collaborator.IdDocType.CC, "123555789", new Job("Farmer"));
        Collaborator collaborator6 = new Collaborator("Miguel Oliveira", "12/12/1995", "06/05/2024", "Avenida Central", 30, "4000-100", "Porto", "Porto", "moliveira@this.app", "912345678", Collaborator.IdDocType.CC, "124456789", new Job("Electrician"));

        collaboratorList.add(collaborator1);
        collaboratorList.add(collaborator2);
        collaboratorList.add(collaborator3);
        collaboratorList.add(collaborator4);
        collaboratorList.add(collaborator5);
        collaboratorList.add(collaborator6);

        List<Skill> skillsNeeded = new ArrayList<>();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        skillsNeeded.add(skill1);
        skillsNeeded.add(skill2);


        List<Integer> quantityNeeded = new ArrayList<>();
        quantityNeeded.add(1);
        quantityNeeded.add(1);

        collaborator1.assignSkills(skillsNeeded);
        collaborator2.assignSkills(skillsNeeded);
        collaborator3.assignSkills(skillsNeeded);
        collaborator4.assignSkills(skillsNeeded);
        collaborator5.assignSkills(skillsNeeded);
        collaborator6.assignSkills(skillsNeeded);

        TeamRepository repository = new TeamRepository();

        List<Team> teams = repository.generateAllTeamProposals(2, 5, skillsNeeded, quantityNeeded, collaboratorList);

        // Verifies if total number of collaborators inside the team is between the specified interval.
        for (Team team : teams) {
            int teamSize = team.getMembers().size();
            assertTrue(teamSize >= 2 && teamSize <= 5, "With given parameters, each team must have between 2 to 5 members");
        }
    }

    @Test
    public void ensureGetTeamListReturnsCorrectValue() {

        List<Collaborator> collaboratorList1 = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("João Silva", "02/03/2002", "06/05/2024", "Rua Dois", 12, "3340-302", "Espinho", "Aveiro", "jsilva@this.app", "912345678", Collaborator.IdDocType.CC, "123356789", new Job("Tree Pruner"));
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", new Job("Gardener"));
        collaboratorList1.add(collaborator1);
        collaboratorList1.add(collaborator2);

        TeamRepository repository = new TeamRepository();
        Team team1 = new Team(collaboratorList1);

        repository.saveTeamProposal(team1);

        List<Collaborator> collaboratorList2 = new ArrayList<>();
        Collaborator collaborator3 = new Collaborator("Maria Silva", "05/06/2002", "04/05/2024", "Rua K", 2, "2040-503", "Sagres", "Faro", "msilva@this.app", "912345678", Collaborator.IdDocType.CC, "123456789", new Job("HRM") );
        Collaborator collaborator4 = new Collaborator("Rui Costa", "15/10/2000", "06/05/2024", "Rua Principal", 20, "4400-302", "Vila Nova de Gaia", "Porto", "rcosta@this.app", "912345678", Collaborator.IdDocType.CC, "123456669", new Job("Landscape Technician") );
        collaboratorList2.add(collaborator3);
        collaboratorList2.add(collaborator4);

        Team team2 = new Team(collaboratorList2);

        repository.saveTeamProposal(team2);

        assertTrue(repository.getTeamList().contains(team1));
        assertTrue(repository.getTeamList().contains(team2));
    }
}
