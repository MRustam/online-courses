package ru.rmamedov.courses.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.user.UserAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.model.user.User;
import ru.rmamedov.courses.repository.interfaces.IUserRepository;
import ru.rmamedov.courses.service.interfaces.IUserService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Service
@Transactional
public class UserService implements IUserService {

    private IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<User> findAll() throws UserNotFoundException {
        return repository.findAll();
    }

    @NotNull
    @Override
    public User findById(@NotNull final String id) throws UserNotFoundException {
        User user;
        try {
            user = repository.findById(id);
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
            user = repository.loadByUsername(username);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("User with Username: '" + username + "' - Not Found");
        }
        return user;
    }

    @NotNull
    @Override
    public List<User> searchHavingFullName(@NotNull final String fullName) throws UserNotFoundException {
        return repository.searchByHavingFullName(fullName);
    }

    @Override
    public User save(@NotNull final User user) throws UserAlreadyExistsException {
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User already exists, 'Username' or 'ID' have to be unique!");
        }
    }

    @Override
    public int deleteById(@NotNull final String id) {
        return repository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return repository.deleteAll();
    }

    @NotNull
    @Override
    public User update(@NotNull User user) {
        return repository.update(user);
    }

    @Override
    public int fetch(@NotNull final User user) {
        return repository.fetch(user);
    }
}
