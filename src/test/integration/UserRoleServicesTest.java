package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rmamedov.courses.SpringBootApp;
import ru.rmamedov.courses.configuration.persist.PersistenceConfigurer;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.service.interfaces.IRoleService;
import ru.rmamedov.courses.service.interfaces.IUserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        SpringBootApp.class,
        PersistenceConfigurer.class
})
public class UserRoleServicesTest {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    /**
     * Init three roles(student, instructor, observer)
     * Init one User with two roles(student, instructor)
     */
    @Before
    public void init() {
        final Role role1 = new Role("1", "ROLE_STUDENT");
        final Role role2 = new Role("2", "ROLE_INSTRUCTOR");
        userService.save(new User("1", "User_123", "123qwe",
                "Albert Enstain", 55, "+7(900)900-09-09",
                "enstain@mail.ru", "enstain@mail.ru",
                20, 0.0, LocalDateTime.now(),
                null, null,
                new HashSet<>(Arrays.asList(role1, role2))));
        roleService.save(new Role("4", "ROLE_OBSERVER"));
    }

    @After
    public void destroy() {
        userService.deleteById("1");
        roleService.deleteById("1");
        roleService.deleteById("2");
        roleService.deleteById("4");
    }

    // Find tests.
    @Test
    public void findUserByIdWithRolesTest() {
        final User user = userService.findById("1");
        final Role role1 = roleService.findByName("ROLE_STUDENT");
        final Role role2 = roleService.findByName("ROLE_INSTRUCTOR");

        assertNotNull(user.getRoles());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));
        assertTrue(user.getRoles().contains(role2));
    }
    // Add & Remove user's role.
    @Test
    @DirtiesContext
    public void addRoleToUserTest() {
        final User user = userService.findById("1");
        final Role roleObserver = roleService.findByName("ROLE_OBSERVER");
        assertTrue(user.addRole(roleObserver));
        final User updatedUser = userService.update(user);

        assertTrue(updatedUser.getRoles().contains(roleObserver));
        assertEquals(3, updatedUser.getRoles().size());
        assertEquals(3, userService.findById("1").getRoles().size());
    }
    @Test
    @DirtiesContext
    public void removeRoleFromUserTest() {
        final User user = userService.findById("1");
        final Role roleStudent = roleService.findByName("ROLE_STUDENT");
        assertTrue(user.removeRole(roleStudent));
        final User updatedUser = userService.update(user);

        assertFalse(updatedUser.getRoles().contains(roleStudent));
        assertEquals(1, updatedUser.getRoles().size());
        assertEquals(1, userService.findById("1").getRoles().size());
    }
}
