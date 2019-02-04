package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotSaved;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.repository.UserRepository;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRep roleRep;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRep roleRep, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRep = roleRep;
        this.userRepository = userRepository;
    }

    @Override
    public User saveOne(User user) {
        Role roleFromRepo = roleRep.findByName(user.getRole().getName());
        if (roleFromRepo != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(roleFromRepo);
            return userRepository.save(user);
        } else {
            throw new EntityNotSaved("Can't manage to save User: " + user);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        throw new EntityNotFoundException("User with id: " + id + ", not found");
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new EntityNotFoundException("User with username: " + username + ", not found");
    }

    @Override
    public User update(User user) {
        return saveOne(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + ", not found");
        }
        return user;
    }

}
