package ru.rmamedov.courses.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.exception.user.UserNotSavedException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.repository.UserRepository;
import ru.rmamedov.courses.service.interfaces.IUserService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NotNull
    @Override
    public List<User> findAll() throws UserNotFoundException {
        return userRepository.findAll();
    }

    @NotNull
    @Override
    public User findById(@NotNull final String id) throws UserNotFoundException {
        User user;
        try {
            user = userRepository.findById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("User with ID: '" + id + "' - Not Found");
        }
        return user;
    }

    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull final String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepository.loadByUsername(username);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("User with Username: '" + username + "' - Not Found");
        }
        return user;
    }

    @NotNull
    @Override
    public List<User> searchHavingFullName(@NotNull final String fullName) throws UserNotFoundException {
        return userRepository.searchByHavingFullName(fullName);
    }

    @Override
    public void save(@NotNull final User user) throws UserNotSavedException {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserNotSavedException("User already exists, 'Username' or 'ID' have to be unique!");
        }
    }

    @Override
    public int deleteById(@NotNull final String id) {
        return userRepository.deleteById(id);
    }

    @NotNull
    @Override
    public User update(@NotNull User user) {
        return userRepository.update(user);
    }

    @Override
    public int fetch(@NotNull final User user) {
        return userRepository.fetch(user);
    }
}
