package ru.rmamedov.courses.service.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.model.user.Role;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IRoleService {

    @NotNull
    List<Role> findAll() throws UserNotFoundException;

    void save(@NotNull Role role);

    @NotNull
    Role findByName(@NotNull String name);

    int deleteById(@NotNull final String id);
}
