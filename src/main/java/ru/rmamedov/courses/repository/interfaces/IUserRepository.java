package ru.rmamedov.courses.repository.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.repository.interfaces.base.IBaseRepository;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IUserRepository extends IBaseRepository<User, String> {
    @NotNull
    @Override
    List<User> findAll() throws UserNotFoundException;

    @NotNull
    @Override
    User findById(@NotNull final String s) throws UserNotFoundException, EmptyResultDataAccessException;

    @NotNull
    User loadByUsername(@NotNull final String username) throws UserNotFoundException, EmptyResultDataAccessException;

    @NotNull
    List<User> searchByHavingFullName(@NotNull final String fullName) throws UserNotFoundException;

    @Override
    User save(@NotNull final User object);

    @Override
    int deleteById(@NotNull final String s) throws UserNotFoundException;

    int deleteAll();

    @NotNull
    @Override
    User update(@NotNull final User user);

    @Override
    int fetch(@NotNull final User user) throws UserAlreadyExistsException;
}
