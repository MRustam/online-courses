package ru.rmamedov.courses.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.course.CourseAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.course.CourseFailToSaveException;
import ru.rmamedov.courses.exception.exceptions.course.CourseNotFoundException;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.model.course.Course;
import ru.rmamedov.courses.repository.interfaces.ICourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CourseRepository implements ICourseRepository {

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Course> criteriaQuery;
    private Root<Course> root;
    private TypedQuery<Course> typedQuery;
    private CriteriaDelete<Course> criteriaDelete;
    private CriteriaUpdate<Course> criteriaUpdate;

    @NotNull
    @Override
    public List<Course> findAll() throws CourseNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Course.class);
        root = criteriaQuery.from(Course.class);

        typedQuery = em.createQuery(criteriaQuery.select(root));
        final List<Course> list = typedQuery.getResultList();
        if (list == null || list.isEmpty()) {
            throw new CourseNotFoundException("There're is no any courses!");
        }
        return Collections.unmodifiableList(list);
    }

    @NotNull
    @Override
    public Course findById(@NotNull String id) throws CourseNotFoundException, EmptyResultDataAccessException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Course.class);
        root = criteriaQuery.from(Course.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        typedQuery = em.createQuery(criteriaQuery);
        final Course course = typedQuery.getSingleResult();
        if (course != null) {
            return course;
        }
        throw new CourseNotFoundException("Course with ID: '" + id + "' - Not Found!");
    }

    @NotNull
    @Override
    public List<Course> findAllOrderByRatingCourses() throws CourseNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Course.class);
        root = criteriaQuery.from(Course.class);

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("rating")));

        typedQuery = em.createQuery(criteriaQuery);
        final List<Course> list = typedQuery.getResultList();
        if (list == null || list.isEmpty()) {
            throw new CourseNotFoundException("There are is no any courses!");
        }
        return Collections.unmodifiableList(list);
    }

    @NotNull
    @Override
    public List<Course> searchByHavingTitle(@NotNull final String title) throws CourseNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Course.class);
        root = criteriaQuery.from(Course.class);

        @NotNull final Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + title + "%");
        criteriaQuery.where(predicate);
        typedQuery = em.createQuery(criteriaQuery.select(root));
        final List<Course> list = typedQuery.getResultList();
        if (list == null || list.isEmpty()) {
            throw new CourseNotFoundException("There're is no any courses with title LIKE: '" + title + "'");
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public Course save(@NotNull Course course) throws DataIntegrityViolationException, CourseAlreadyExistsException {
        return em.merge(course);
    }

    @Override
    public int deleteById(@NotNull String id) throws CourseNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaDelete = criteriaBuilder.createCriteriaDelete(Course.class);
        root = criteriaDelete.from(Course.class);

        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        return em.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    public int deleteAll() {
        return em.createQuery("DELETE FROM Course").executeUpdate();
    }

    @Override
    public int fetch(@NotNull Course course) throws CourseFailToSaveException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Course.class);
        root = criteriaUpdate.from(Course.class);

        if (course.getTitle() != null) {
            criteriaUpdate.set("title", course.getTitle());
        }
        if (course.getDescription() != null) {
            criteriaUpdate.set("description", course.getDescription());
        }
        if (course.getCategory() != null) {
            criteriaUpdate.set("category", course.getCategory());
        }
        if (course.getDuration() > 0) {
            criteriaUpdate.set("duration", course.getDuration());
        }
        if (course.getCreated() != null) {
            criteriaUpdate.set("created", course.getCreated());
        }
        if (course.getStarts() != null) {
            criteriaUpdate.set("starts", course.getStarts());
        }
        if (course.getRating() > 0) {
            criteriaUpdate.set("rating", course.getRating());
        }
        criteriaUpdate.set("isStarted", course.isStarted());
        criteriaUpdate.set("isFree", course.isFree());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), course.getId()));
        int updated = em.createQuery(criteriaUpdate).executeUpdate();
        if (updated > 0) {
            return updated;
        }
        throw new CourseAlreadyExistsException("Can't manage to update Course!");
    }

    @NotNull
    @Override
    public Course update(@NotNull Course course) throws CourseFailToSaveException {
        final Course updatedUser = em.merge(course);
        if (updatedUser != null) {
            return updatedUser;
        }
        throw new CourseAlreadyExistsException("Can't manage to update Course - '" + course.getTitle() + "'!");
    }
}
