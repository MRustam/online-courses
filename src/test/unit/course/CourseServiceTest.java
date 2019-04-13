package unit.course;

import org.jetbrains.annotations.NotNull;
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
import ru.rmamedov.courses.exception.exceptions.course.CourseNotFoundException;
import ru.rmamedov.courses.model.category.Category;
import ru.rmamedov.courses.model.course.Course;
import ru.rmamedov.courses.service.interfaces.ICourseService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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
public class CourseServiceTest {

    @Autowired
    private ICourseService service;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private final String id1 = UUID.randomUUID().toString();
    private final String id2 = UUID.randomUUID().toString();
    private final String id3 = UUID.randomUUID().toString();

    @Before
    public void init() {
        service.save(new Course(id1, "JavaSE 8", "JavaSE in 180 steps.", Category.IT, 50,
                false, true, LocalDate.of(2019, Month.JUNE, 10), 7.3));
        service.save(new Course(id2, "JavaEE 7", "JavaEE in 100 steps.", Category.IT, 45,
                false, true, LocalDate.of(2019, Month.JULY, 20), 8.5));
        service.save(new Course(id3, "C++", "C++ in 100 steps.", Category.IT, 30,
                false, true, LocalDate.of(2019, Month.MAY, 1), 6.5));
    }
    @After
    public void destroy() {
        service.deleteAll();
    }

    // Find.
    @Test
    public void findAllCoursesTest() {
        @NotNull final List<Course> courses = service.findAll();
        assertEquals(3, courses.size());
    }
    @Test
    public void findCourseByIdTest() {
        @NotNull final Course course = service.findById(id1);
        assertEquals("JavaSE 8", course.getTitle());
        assertEquals("JavaSE in 180 steps.", course.getDescription());
    }
    @Test
    public void findAllCoursesOrderByRatingDescTest() {
        @NotNull final List<Course> courses = service.findAllOrderByRatingCourses();
        assertEquals(3, courses.size());
        assertEquals("JavaEE 7", courses.get(0).getTitle());
        assertEquals("JavaSE 8", courses.get(1).getTitle());
        assertEquals("C++", courses.get(2).getTitle());
    }
    @Test
    public void searchCoursesByHavingTitleTest() {
        assertEquals(2, service.searchByHavingTitle("Java").size());
    }
    @Test
    @DirtiesContext
    public void whenFindAllCoursesButThereAreIsNothingThenExceptionTest() {
        exceptionRule.expect(CourseNotFoundException.class);
        exceptionRule.expectMessage("There're is no any courses!");
        service.deleteAll();
        service.findAll();
    }
    // Save.
    @Test
    @DirtiesContext
    public void saveCourseTest() {
        service.save(new Course("id4", "Swift", "Swift in 70 steps.", Category.IT, 60,
                false, true, LocalDate.of(2019, Month.NOVEMBER, 1), 2.5));
        assertEquals(4, service.findAll().size());
    }
    // Update.
    @Test
    @DirtiesContext
    public void updateCourseTest() {
        @NotNull final Course course = service.findById(id1);
        course.setTitle("SE8 - for beginners.");
        @NotNull final Course updated = service.update(course);
        assertEquals("SE8 - for beginners.", updated.getTitle());
    }
    // Fetch.
    @Test
    @DirtiesContext
    public void FetchCourseTest() {
        @NotNull final Course course = service.findById(id1);
        course.setTitle("SE8 - for beginners.");
        course.setDescription("Bla-Bla");
        course.setCategory(Category.BUSINESS);
        course.setStarts(LocalDate.of(2019, Month.JUNE, 13));
        assertEquals(1, service.fetch(course));
        @NotNull final Course updated = service.findById(id1);
        assertEquals("SE8 - for beginners.", updated.getTitle());
        assertEquals("Bla-Bla", updated.getDescription());
        assertEquals(Category.BUSINESS, updated.getCategory());
        assertEquals(13, updated.getStarts().getDayOfMonth());
    }
    // Delete.
    @Test
    @DirtiesContext
    public void deleteCoursesByIdTest() {
        assertEquals(1, service.deleteById(id1));
        assertEquals(2, service.findAll().size());
    }
    @Test
    @DirtiesContext
    public void deleteAllCoursesTest() {
        assertEquals(3, service.deleteAll());
    }
}
