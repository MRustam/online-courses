package ru.rmamedov.courses.repository.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.model.user.Role;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IRoleRepository {
    @NotNull
    List<Role> findAll() throws RoleNotFoundException;

    @NotNull
    Role findByName(@NotNull final String name) throws EmptyResultDataAccessException, RoleNotFoundException;

    void save(@NotNull final Role object) throws DataIntegrityViolationException;

    int deleteById(@NotNull final String s);
}
