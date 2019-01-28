package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
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

    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo, UserServiceImpl userService, RoleRep roleRep) {
        this.instructorRepo = instructorRepo;
        this.userService = userService;
        this.roleRep = roleRep;
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
        if (instructor.getUser().getRole() != null) {
            String roleName = instructor.getUser().getRole().getName();
            Role role = roleRep.findByName(roleName);
            if (role != null && role.getName().equals("ROLE_INSTRUCTOR")) {
                instructor.getUser().setRole(role);
                return instructorRepo.save(instructor);
            } else {
                throw new EntityNotFoundException("Role mast be an 'ROLE_INSTRUCTOR', but found: " + roleName);
            }
        } else {
            throw new EntityNotFoundException("Saving Instructor mast contains a Role");
        }
    }

    @Override
    public Instructor update(Instructor instructor) {
        if (instructor.getUser().getRole() == null) {
            throw new EntityNotFoundException("Updating Instructor mast contains a Role!");
        }
        return save(instructor);
    }

//    @Override
//    public Instructor update(Long id, Instructor patch) {
//
//        Instructor instructor = findById(id);
//
//        if (patch.getCourses() != null) instructor.setCourses(patch.getCourses());
//        if (patch.getWorkExperience() != 0) instructor.setWorkExperience(patch.getWorkExperience());
//
//        return save(instructor);
//    }

    @Override
    public void deleteOneById(Long id) {
        Instructor instructor = findById(id);
        User user = userService.findByUsername(instructor.getUser().getUsername());
        user.setRole(roleRep.findByName("ROLE_USER"));
        instructorRepo.delete(instructor);
    }

}
