package ru.rmamedov.courses.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.repository.interfaces.IUserRepository;

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
public class UserRepository implements IUserRepository {

    @PersistenceContext
    private EntityManager em;

    private PasswordEncoder encoder;

    @Autowired
    public UserRepository(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<User> criteriaQuery;
    private Root<User> root;
    private TypedQuery<User> typedQuery;
    private CriteriaDelete<User> criteriaDelete;
    private CriteriaUpdate<User> criteriaUpdate;

    /**
     * Find one user if it exists in DB.
     *
     * @return List of all Users.
     */
    @NotNull
    @Override
    public List<User> findAll() throws UserNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(User.class);
        root = criteriaQuery.from(User.class);

        typedQuery = em.createQuery(criteriaQuery.select(root));
        final List<User> list = typedQuery.getResultList();
        if (list == null || list.isEmpty()) {
            throw new UserNotFoundException("There're is no any users!");
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * Find one user by ID if it exists in DB.
     *
     * @param id String.
     * @return found User.
     */
    @NotNull
    @Override
    public User findById(@NotNull final String id) throws EmptyResultDataAccessException, UserNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(User.class);
        root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        typedQuery = em.createQuery(criteriaQuery);
        final User user = typedQuery.getSingleResult();
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("User with ID: '" + id + "' - Not Found");
    }

    /**
     * Find one user by Username if it exists in DB.
     *
     * @param username String.
     * @return found User.
     */
    @NotNull
    @Override
    public User loadByUsername(@NotNull final String username) throws UserNotFoundException, EmptyResultDataAccessException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(User.class);
        root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        typedQuery = em.createQuery(criteriaQuery);
        final User user = typedQuery.getSingleResult();
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("User with Username: '" + username + "' - Not Found");
    }

    /**
     * Searches all Users who contains incoming 'string' in fullName.
     *
     * @param fullName String with is searching of.
     * @return List of found users.
     */
    @NotNull
    @Override
    public List<User> searchByHavingFullName(@NotNull final String fullName) throws UserNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(User.class);
        root = criteriaQuery.from(User.class);

        final Predicate predicate = criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%");
        criteriaQuery.where(predicate);
        typedQuery = em.createQuery(criteriaQuery.select(root));
        final List<User> list = typedQuery.getResultList();
        if (list == null || list.isEmpty()) {
            throw new UserNotFoundException("There're is no any users with fullName LIKE: '" + fullName + "'");
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * Save User.
     *
     * @param user incoming new User.
     */
    @Override
    public User save(@NotNull final User user) {
        return em.merge(user);
    }

    /**
     * Save User.
     *
     * @param user incoming new User.
     */
    @NotNull
    @Override
    public User update(@NotNull final User user) throws UserAlreadyExistsException {
        final User updatedUser = em.merge(user);
        if (updatedUser != null) {
            return updatedUser;
        }
        throw new UserAlreadyExistsException("Can't manage to update User!");
    }

    /**
     * Update User partly.
     *
     * @param user User with updated field/fields.
     * @return affected rows.
     */
    @Override
    public int fetch(@NotNull final User user) throws UserAlreadyExistsException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        root = criteriaUpdate.from(User.class);

        if (user.getUsername() != null) {
            criteriaUpdate.set("username", user.getUsername());
        }
        if (user.getPassword() != null) {
            criteriaUpdate.set("password", encoder.encode(user.getPassword()));
        }
        if (user.getFullName() != null) {
            criteriaUpdate.set("fullName", user.getFullName());
        }
        if (user.getAge() > 0) {
            criteriaUpdate.set("age", user.getAge());
        }
        if (user.getEmail() != null) {
            criteriaUpdate.set("email", user.getEmail());
        }
        if (user.getSkype() != null) {
            criteriaUpdate.set("skype", user.getSkype());
        }
        if (user.getWorkExperience() > 0) {
            criteriaUpdate.set("workExperience", user.getWorkExperience());
        }
        if (user.getAcademicPerformance() > 0) {
            criteriaUpdate.set("academicPerformance", user.getAcademicPerformance());
        }
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), user.getId()));
        int updated = em.createQuery(criteriaUpdate).executeUpdate();
        if (updated > 0) {
            return updated;
        }
        throw new UserAlreadyExistsException("Can't manage to update User!");
    }

    /**
     * Delete User by ID if it will be found.
     *
     * @param id String ID.
     * @return affected rows.
     */
    @Override
    public int deleteById(@NotNull final String id) {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        root = criteriaDelete.from(User.class);

        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        return em.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    public int deleteAll() {
        return em.createQuery("DELETE FROM User").executeUpdate();
    }
}
