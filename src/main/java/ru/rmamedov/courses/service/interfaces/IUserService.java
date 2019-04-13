package ru.rmamedov.courses.service.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.service.interfaces.base.IBaseService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface IUserService extends IBaseService<User, String>, UserDetailsService {

    @NotNull
    @Override
    List<User> findAll() throws UserNotFoundException;

    @NotNull
    @Override
    User findById(@NotNull final String id) throws UserNotFoundException;

    @NotNull
    List<User> searchHavingFullName(String fullName) throws UserNotFoundException;

    @Override
    User save(@NotNull final User object) throws UserAlreadyExistsException;

    @Override
    int deleteById(@NotNull final String id);

    int deleteAll();

    @Override
    int fetch(@NotNull final User user);

    @NotNull
    @Override
    User update(@NotNull final User user);

    @NotNull
    @Override
    UserDetails loadUserByUsername(@NotNull final String username) throws UsernameNotFoundException;
}
