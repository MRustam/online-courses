package unit.role;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rmamedov.courses.SpringBootApp;
import ru.rmamedov.courses.configuration.persist.PersistenceConfigurer;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.service.interfaces.IRoleService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        SpringBootApp.class,
        PersistenceConfigurer.class
})
public class RoleServiceTest {

    @Autowired
    private IRoleService service;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private final String id1 = UUID.randomUUID().toString();
    private final String id2 = UUID.randomUUID().toString();
    private final String id3 = UUID.randomUUID().toString();

    // Find tests.
    @Test
    public void whenFindAllRolesButThereAreIsNoAnyThenExceptionThrowsTest() {
        exceptionRule.expect(RoleNotFoundException.class);
        exceptionRule.expectMessage("There're is no any roles!");
        service.findAll();
    }
    @Test
    @DirtiesContext
    public void findAllRolesTest() {
        service.save(new Role(id1, "ROLE_STUDENT"));
        service.save(new Role(id2, "ROLE_INSTRUCTOR"));
        service.save(new Role(id3, "ROLE_ADMIN"));
        assertEquals(3, service.findAll().size());
    }
    @Test
    @DirtiesContext
    public void findRoleByNameTest() {
        service.save(new Role(id2, "ROLE_INSTRUCTOR"));
        assertEquals("ROLE_INSTRUCTOR", service.findByName("ROLE_INSTRUCTOR").getName());
    }
    @Test
    public void whenFindByNameNonexistentRoleThenThrowsExceptionTest() {
        final String roleName = "INSTRUCTOR";
        exceptionRule.expect(RoleNotFoundException.class);
        exceptionRule.expectMessage("Role with name: '" + roleName + "' - Not Found");
        service.findByName(roleName);
    }
    // Save tests.
    @Test
    @DirtiesContext
    public void saveRoleTest() {
        service.save(new Role("4", "ROLE_SUPER_ADMIN"));
        assertEquals("ROLE_SUPER_ADMIN", service.findByName("ROLE_SUPER_ADMIN").getName());
        assertEquals(1, service.findAll().size());
    }
    @Test
    @DirtiesContext
    public void saveRoleWithTheSameNameTest() {
        exceptionRule.expect(DataIntegrityViolationException.class);
        service.save(new Role(id1, "ROLE_STUDENT"));
        service.save(new Role("id4", "ROLE_STUDENT"));
    }
    // Delete rests.
    @Test
    @DirtiesContext
    public void deleteRoleTest() {
        service.save(new Role(id1, "ROLE_STUDENT"));
        service.save(new Role(id2, "ROLE_INSTRUCTOR"));
        service.deleteById(id1);
        assertEquals(1, service.findAll().size());
        assertEquals("ROLE_INSTRUCTOR", service.findByName("ROLE_INSTRUCTOR").getName());
    }
}
