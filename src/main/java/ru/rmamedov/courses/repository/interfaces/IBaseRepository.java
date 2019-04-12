package ru.rmamedov.courses.repository.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.model.user.User;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IBaseRepository<T, ID> {

    @NotNull
    List<T> findAll() throws UserNotFoundException;

    @NotNull
    T findById(@NotNull final ID id) throws UserNotFoundException, EmptyResultDataAccessException;

    @NotNull
    T loadByUsername(@NotNull final ID username) throws UserNotFoundException, EmptyResultDataAccessException;

    @NotNull
    List<T> searchByHavingFullName(@NotNull final ID fullName) throws UserNotFoundException;

    void save(@NotNull final T object);// TODO throws some exception.

    int deleteById(@NotNull final ID id) throws UserNotFoundException;

    @NotNull
    User update(@NotNull final User user);

    int fetch(@NotNull final T t) throws UserAlreadyExistsException;
}
