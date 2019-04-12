package ru.rmamedov.courses.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.model.user.Role;
import ru.rmamedov.courses.repository.RoleRepository;
import ru.rmamedov.courses.repository.interfaces.IRoleRepository;
import ru.rmamedov.courses.service.interfaces.IRoleService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Service
@Transactional
public class RoleService implements IRoleService {

    private IRoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<Role> findAll() throws RoleNotFoundException {
        return repository.findAll();
    }

    @NotNull
    @Override
    public Role findByName(@NotNull String name) throws RoleNotFoundException {
        try {
            return repository.findByName(name);
        } catch (EmptyResultDataAccessException ex) {
            throw new RoleNotFoundException("Role with name: '" + name + "' - Not Found");
        }
    }

    @Override
    public void save(@NotNull Role role) throws DataIntegrityViolationException {
        repository.save(role);
    }

    @Override
    public int deleteById(@NotNull String id) {
        return repository.deleteById(id);
    }
}
