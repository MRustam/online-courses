package ru.rmamedov.courses.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.repository.interfaces.IRoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RoleRepository implements IRoleRepository {

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Role> criteriaQuery;
    private Root<Role> root;
    private TypedQuery<Role> typedQuery;
    private CriteriaDelete<Role> criteriaDelete;

    /**
     * Find one user if it exists in DB.
     *
     * @return List of all Users.
     */
    @NotNull
    public List<Role> findAll() throws RoleNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Role.class);
        root = criteriaQuery.from(Role.class);

        typedQuery = em.createQuery(criteriaQuery.select(root));
        final List<Role> list = typedQuery.getResultList();
        if (list != null && !list.isEmpty()) {
            return Collections.unmodifiableList(list);
        }
        throw new RoleNotFoundException("There're is no any roles!");
    }

    /**
     * Save User.
     *
     * @param role incoming new User.
     */
    public void save(@NotNull final Role role) throws DataIntegrityViolationException {
        em.merge(role);
    }

    /**
     * Find one user by ID if it exists in DB.
     *
     * @param name String.
     * @return found User.
     */
    @NotNull
    public Role findByName(@NotNull final String name) throws EmptyResultDataAccessException, RoleNotFoundException {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Role.class);
        root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        typedQuery = em.createQuery(criteriaQuery);
        final Role role = typedQuery.getSingleResult();
        if (role != null) {
            return role;
        }
        throw new RoleNotFoundException("Role with ID: '" + name + "' - Not Found");
    }

    /**
     * Delete User by ID if it will be found.
     *
     * @param id String ID.
     * @return affected rows.
     */
    public int deleteById(@NotNull final String id) {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaDelete = criteriaBuilder.createCriteriaDelete(Role.class);
        root = criteriaDelete.from(Role.class);

        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        return em.createQuery(criteriaDelete).executeUpdate();
    }
}
