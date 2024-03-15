package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.repository.Repositories;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }

    @Test
    void testGetTaskCategoryRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTaskCategoryRepository());
    }
}