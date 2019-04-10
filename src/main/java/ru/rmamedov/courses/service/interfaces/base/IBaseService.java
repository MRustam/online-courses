package ru.rmamedov.courses.service.interfaces.base;

import org.jetbrains.annotations.NotNull;
import ru.rmamedov.courses.model.user.User;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IBaseService<T, ID> {

    @NotNull
    List<T> findAll();

    @NotNull
    T findById(@NotNull final ID id);

    void save(@NotNull final T object);

    int deleteById(@NotNull final ID id);

    int fetch(@NotNull final T t);

    @NotNull
    User update(@NotNull final User user);
}
