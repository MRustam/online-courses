package ru.rmamedov.courses.repository.interfaces.base;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IBaseRepository<T, ID> {

    @NotNull
    List<T> findAll();

    @NotNull
    T findById(@NotNull final ID id);

    T save(@NotNull final T course);

    int deleteById(@NotNull final ID id);

    @NotNull
    T update(@NotNull T t);

    int fetch(@NotNull final T t);
}
