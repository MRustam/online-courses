package unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rmamedov.courses.SpringBootApp;
import ru.rmamedov.courses.configuration.persist.PersistenceConfigurer;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.exception.user.UserNotSavedException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.service.interfaces.IUserService;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        SpringBootApp.class,
        PersistenceConfigurer.class
})
public class UserServiceTest {

    @Autowired
    private IUserService service;

    @Autowired
    private PasswordEncoder encoder;

    private String id1;
    private String id2;
    private String password;

    @Before
    public void init() {
        createTwoNewUsers();
    }
    @After
    public void destroy() {
        deleteAllCreatedUsers();
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    // Find by any criteria tests.
    //
    @Test
    public void findAllUsersTest() {
        assertEquals(2, service.findAll().size());
    }
    @Test
    public void findUserByIdTest() {
        assertEquals("John Travolta", service.findById(id2).getFullName());
    }
    @Test
    public void whenFindByIdNonexistentUserThenExceptionIsThrowsTest() {
        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("User with ID: 'id3' - Not Found");
        service.findById("id3");
    }
    @Test
    public void searchAllUsersWhoHasFullNameLikeThisTest() {
        assertEquals(2, service.searchHavingFullName("ta").size());
    }
    @Test
    public void whenSearchAllUsersWhoHasNoFullNameLikeThisTest() {
        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("There're is no any users with fullName LIKE: 'X'");
        service.searchHavingFullName("X");
    }
    @Test
    public void loadByUsernameTest() {
        assertEquals("Albert Enstain", ((User) service.loadUserByUsername("User_1")).getFullName());
    }
    @Test
    public void whenLoadByUsernameNonexistentUserThenExceptionIsThrowsTest() {
        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("User with Username: 'User_3' - Not Found");
        service.loadUserByUsername("User_3");
    }
    // Save new User tests.
    //
    @Test
    @DirtiesContext
    public void saveUserTest() {
        final String id3 = UUID.randomUUID().toString();
        service.save(new User(id3, "User_3", password,
                "Mat Dayman", 32, "+7(500)770-09-09",
                "mat@mail.ru", "mat@mail.ru",
                0, 4.3, LocalDateTime.now(),
                null, null, null));
        assertEquals(3, service.findAll().size());
    }
    @Test
    @DirtiesContext
    public void whenSaveUserWithTheSameUsernameThenExceptionIsThrowsTest() {
        exceptionRule.expect(UserNotSavedException.class);
        exceptionRule.expectMessage("User already exists, 'Username' or 'ID' have to be unique!");
        final String id3 = UUID.randomUUID().toString();
        service.save(new User(id3, "User_1", password,
                "Mat Dayman", 32, "+7(500)770-09-09",
                "mat@mail.ru", "mat@mail.ru",
                0, 4.3, LocalDateTime.now(),
                null, null, null));
    }
    @Test
    @DirtiesContext
    public void whileSavingUserAssertThatPasswordWillBeEncoded() {
        final String id3 = UUID.randomUUID().toString();
        final String pass = encoder.encode("qwe123");
        service.save(new User(id3, "User_3", pass,
                "Mat Dayman", 32, "+7(500)770-09-09",
                "mat@mail.ru", "mat@mail.ru",
                0, 4.3, LocalDateTime.now(),
                null, null, null));
        assertTrue(encoder.matches("qwe123", pass));
        assertNotEquals("qwe123", pass);
    }
    // Delete test.
    //
    @Test
    @DirtiesContext
    public void whenDeleteUserByIdThenReturnModifiedRowsTest() {
        assertEquals(1, service.deleteById(id1));
        assertEquals(1, service.findAll().size());
    }
    // Fetch tests.
    @Test
    @DirtiesContext
    public void whenFetchUserThenReturnModifiedRowsTest() {
        final User user = service.findById(id2);
        user.setFullName("Qwe Asd");
        assertEquals(1, service.fetch(user));
        assertEquals("Qwe Asd", service.findById(id2).getFullName());
    }


    // Not a tests - helper methods.
    private void createTwoNewUsers() {
        id1 = UUID.randomUUID().toString();
        id2 = UUID.randomUUID().toString();
        password = encoder.encode("123qwe");

        service.save(new User(id1, "User_1", password,
                "Albert Enstain", 55, "+7(900)900-09-09",
                "enstain@mail.ru", "enstain@mail.ru",
                20, 0.0, LocalDateTime.now(),
                null, null, null));
        service.save(new User(id2, "User_2", password,
                "John Travolta", 25, "+7(800)700-08-03",
                "travolta@mail.ru", "travolta@mail.ru",
                0, 3.1, LocalDateTime.now(),
                null, null, null));
    }
    private void deleteAllCreatedUsers() {
        service.deleteById(id1);
        service.deleteById(id2);
    }
}
