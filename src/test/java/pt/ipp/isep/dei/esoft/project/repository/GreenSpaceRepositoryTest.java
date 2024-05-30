package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceRepositoryTest {

    private GreenSpaceRepository greenSpaceRepository;
    private GreenSpace greenSpace1;
    private GreenSpace greenSpace2;
    private String manager1;
    private String manager2;

    @BeforeEach
    void createData() {

        manager1 = "manager1@example.com";
        manager2 = "manager2@example.com";

        greenSpace1 = new GreenSpace(GreenSpaceType.GARDEN, "Park nameA", "Street1", 123, "1234-123", "Matosinhos", "Porto", 1234.0, manager1);
        greenSpace2 = new GreenSpace(GreenSpaceType.MEDIUM, "Park nameB", "Street2", 456, "1234-456", "Gaia", "Porto", 1234.0, manager2);

        greenSpaceRepository = new GreenSpaceRepository();
        greenSpaceRepository.add(greenSpace1);
        greenSpaceRepository.add(greenSpace2);
    }

    @Test
    void testGetGreenSpaceList() {
        assertEquals(2, greenSpaceRepository.getGreenSpaceList().size());
    }

    @Test
    void testGetGreenSpaceListByManager() {
        List<GreenSpace> manager1GreenSpaces = greenSpaceRepository.getGreenSpaceListByManager(manager1);
        assertEquals(1, manager1GreenSpaces.size());
        assertEquals(greenSpace1.getName(), manager1GreenSpaces.get(0).getName());

        List<GreenSpace> manager2GreenSpaces = greenSpaceRepository.getGreenSpaceListByManager(manager2);
        assertEquals(1, manager2GreenSpaces.size());
        assertEquals(greenSpace2.getName(), manager2GreenSpaces.get(0).getName());
    }

    @Test
    void testRegisterGreenSpace() {
        GreenSpace newGreenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Park nameC", "Street3", 123, "1234-222", "Matosinhos", "Porto", 124.0, manager1);
        Optional<GreenSpace> registeredGreenSpace = greenSpaceRepository.registerGreenSpace(newGreenSpace);
        assertTrue(registeredGreenSpace.isPresent());
        assertEquals(newGreenSpace.getName(), registeredGreenSpace.get().getName());
    }

    @Test
    void testAddGreenSpace() {
        GreenSpace newGreenSpace = new GreenSpace(GreenSpaceType.LARGE, "Park nameD", "Street4", 3333, "1224-222", "Matosinhos", "Porto", 1214.0, manager2);
        Optional<GreenSpace> addedGreenSpace = greenSpaceRepository.add(newGreenSpace);
        assertTrue(addedGreenSpace.isPresent());
        assertEquals(newGreenSpace.getName(), addedGreenSpace.get().getName());
    }

    @Test
    void testAddDuplicateGreenSpace() {
        GreenSpace duplicateGreenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Park nameA", "Street1", 123, "1234-123", "Matosinhos", "Porto", 1234.0, manager1);
        Optional<GreenSpace> addedDuplicate = greenSpaceRepository.add(duplicateGreenSpace);
        assertFalse(addedDuplicate.isPresent());
    }

    @Test
    void testGetGreenSpaceByName() {
        Optional<GreenSpace> foundGreenSpace = greenSpaceRepository.getGreenSpaceByName("Park nameA");
        assertTrue(foundGreenSpace.isPresent());
        assertEquals(greenSpace1.getName(), foundGreenSpace.get().getName());

        Optional<GreenSpace> notFoundGreenSpace = greenSpaceRepository.getGreenSpaceByName("Nonexistent Park");
        assertFalse(notFoundGreenSpace.isPresent());
    }

    @Test
    void ensureGreenSpaceNameCantBeDuplicated(){
        GreenSpace duplicateGreenSpace = new GreenSpace(GreenSpaceType.GARDEN, "Park nameA", "Street1", 123, "1234-123", "Matosinhos", "Porto", 1234.0, manager1);

        Optional<GreenSpace> addedGreenSpace = greenSpaceRepository.add(duplicateGreenSpace);
        assertTrue(!addedGreenSpace.isPresent());
    }
}
