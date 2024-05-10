package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorRepositoryTest {

    @Test
    public void testAddCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        Job job = new Job("Manager");

        Collaborator collaborator = new Collaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job);

        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(collaborator);

        assertTrue(addedCollaborator.isPresent());
    }

    @Test
    void testGetCollaboratorList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job1 = new Job("Manager");
        Job job2 = new Job("Driver");

        collaboratorRepository.createCollaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job1);
        collaboratorRepository.createCollaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", job2);

        List<Collaborator> collaboratorList = collaboratorRepository.getCollaboratorList();

        assertNotNull(collaboratorList);
        assertEquals(2, collaboratorList.size());
    }

    @Test
    void testGetCollaboratorByCollaboratorEmail() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        Job job1 = new Job("Manager");
        Job job2 = new Job("Driver");

        Collaborator collaborator1 = new Collaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job1);
        Collaborator collaborator2 = new Collaborator("Guilherme Santos", "30/08/2005", "06/05/2024", "Rua Principal", 12, "4400-302", "Vila Nova de Gaia", "Porto", "gsantos@this.app", "912345678", Collaborator.IdDocType.CC, "123456788", job2);

        collaboratorRepository.add(collaborator1);
        collaboratorRepository.add(collaborator2);

        Optional<Collaborator> retrievedCollaborator = collaboratorRepository.getCollaboratorByCollaboratorEmail(collaborator1.getEmail());

        assertEquals(collaborator1.getEmail().getEmail(), retrievedCollaborator.get().getEmail().getEmail());
        assertTrue(retrievedCollaborator.isPresent());
    }

    @Test
    void testCreateCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        Job job1 = new Job("Manager");

        Optional<Collaborator> addedCollaborator = collaboratorRepository.createCollaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job1);

        assertTrue(addedCollaborator.isPresent());
    }

    @Test
    void testAssignSkillsToCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job1 = new Job("Manager");
        Collaborator collaborator = new Collaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job1);

        collaboratorRepository.add(collaborator);

        Skill skill1 = new Skill("Driving License");
        Skill skill2 = new Skill("Tree pruning");

        boolean skillsAssigned = collaboratorRepository.assignSkillsToCollaborator(collaborator, List.of(skill1, skill2));

        assertTrue(skillsAssigned);
    }

    @Test
    void testAssignSkillsToCollaboratorWithExistingSkills() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job1 = new Job("Manager");
        Collaborator collaborator = new Collaborator("Pedro Costa", "07/10/1995", "20/03/2024", "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "930123452", Collaborator.IdDocType.CC, "234324235", job1);

        collaboratorRepository.add(collaborator);

        Skill skill1 = new Skill("Driving License");
        Skill skill2 = new Skill("Tree pruning");
        collaborator.assignSkills(List.of(skill1, skill2));

        assertEquals(2, collaborator.getSkillList().size());

        boolean skillsAssigned = collaboratorRepository.assignSkillsToCollaborator(collaborator, List.of(skill2));

        assertTrue(skillsAssigned);

        assertEquals(2, collaborator.getSkillList().size());
    }
}
