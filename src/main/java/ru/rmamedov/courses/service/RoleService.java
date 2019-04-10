package ru.rmamedov.courses.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.exception.user.UserNotSavedException;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.repository.RoleRepository;
import ru.rmamedov.courses.service.interfaces.IRoleService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Service
public class RoleService implements IRoleService {

    private RoleRepository repository;

    @NotNull
    @Override
    public List<Role> findAll() throws UserNotFoundException {
        return repository.findAll();
    }

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(@NotNull Role role) {
        try {
            repository.save(role);
        } catch (DataIntegrityViolationException ex) {
            throw new UserNotSavedException("Role already exists, 'Name' have to be unique!");
        }
    }

    @NotNull
    @Override
    public Role findByName(@NotNull String name) {
        try {
            return repository.findByName(name);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("Role with name: '" + name + "' - Not Found");
        }
    }

    @Override
    public int deleteById(@NotNull String id) {
        return repository.deleteById(id);
    }
}
