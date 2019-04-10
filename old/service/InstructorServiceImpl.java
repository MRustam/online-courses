package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotSavedException;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.InstructorRepo;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements IInstructorService {


    private InstructorRepo instructorRepo;
    private UserServiceImpl userService;
    private RoleRep roleRep;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo,
                                 UserServiceImpl userService, RoleRep roleRep,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.instructorRepo = instructorRepo;
        this.userService = userService;
        this.roleRep = roleRep;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepo.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        Optional<Instructor> optInstructor = instructorRepo.findById(id);
        if (optInstructor.isPresent()) {
            return optInstructor.get();
        }
        throw new EntityNotFoundException("Instructor with id: " + id + " not found");
    }

    @Override
    public Instructor findByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            Instructor instructor = instructorRepo.findByUser(user);
            if (instructor != null) {
                return instructor;
            }
        }
        throw new EntityNotFoundException("Instructor with username: '" + "', not found");
    }

    @Override
    public Instructor save(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    @Override
    public Instructor save(User user) {
        Role roleFromRepo = roleRep.findByName(user.getRole().getName());
        if (roleFromRepo != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(roleFromRepo);
            Instructor instructor = new Instructor();
            instructor.setUser(user);
            return instructorRepo.save(instructor);
        } else {
            throw new EntityNotSavedException("Can't manage to save User: " + user);
        }
    }

    @Override
    public Instructor update(Instructor instructor) {
        if (instructor.getUser().getRole() == null) {
            throw new EntityNotFoundException("Updating Instructor mast contains a Role!");
        }
        return save(instructor);
    }

    @Override
    public void deleteOneById(Long id) {
        Instructor instructor = findById(id);
        User user = userService.findByUsername(instructor.getUser().getUsername());
        user.setRole(roleRep.findByName("ROLE_USER"));
        instructorRepo.delete(instructor);
    }

}
