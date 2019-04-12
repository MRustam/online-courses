package ru.rmamedov.courses.service.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.model.user.Role;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IRoleService {

    @NotNull
    List<Role> findAll() throws RoleNotFoundException;

    void save(@NotNull Role role) throws DataIntegrityViolationException;

    @NotNull
    Role findByName(@NotNull String name) throws RoleNotFoundException;

    int deleteById(@NotNull final String id);
}
