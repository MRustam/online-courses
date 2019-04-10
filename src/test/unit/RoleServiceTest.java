package unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rmamedov.courses.SpringBootApp;
import ru.rmamedov.courses.configuration.persist.PersistenceConfigurer;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.exception.user.UserNotSavedException;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.service.interfaces.IRoleService;

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

    @Before
    public void init() {
        service.save(new Role("1", "ROLE_STUDENT"));
        service.save(new Role("2", "ROLE_INSTRUCTOR"));
        service.save(new Role("3", "ROLE_ADMIN"));
    }

    @After
    public void destroy() {
        service.deleteById("1");
        service.deleteById("2");
        service.deleteById("3");
    }

    // Find tests.
    @Test
    public void findAllRolesTest() {
        assertEquals(3, service.findAll().size());
    }
    @Test
    public void findRoleByNameTest() {
        assertEquals("ROLE_INSTRUCTOR", service.findByName("ROLE_INSTRUCTOR").getName());
    }
    @Test
    public void whenFindNonexistentRoleByNameThenThrowsExceptionTest() {
        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("Role with name: 'INSTRUCTOR' - Not Found");
        service.findByName("INSTRUCTOR");
    }
    // Save tests.
    @Test
    @DirtiesContext
    public void saveRoleTest() {
        service.save(new Role("4", "ROLE_SUPER_ADMIN"));
        assertEquals(4, service.findAll().size());
    }
    @Test
    @DirtiesContext
    public void saveRoleWithTheSameNameTest() {
        exceptionRule.expect(UserNotSavedException.class);
        service.save(new Role("2", "ROLE_STUDENT"));
    }
    // Delete rests.
    @Test
    @DirtiesContext
    public void deleteRoleTest() {
        service.deleteById("1");
        assertEquals(2, service.findAll().size());
    }
}
