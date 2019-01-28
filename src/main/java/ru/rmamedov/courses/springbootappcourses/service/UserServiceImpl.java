package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.InstructorRepo;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRepo;
import ru.rmamedov.courses.springbootappcourses.repository.UserRepository;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IUserService;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRep roleRep;

    private UserRepository userRepository;
    private InstructorRepo instructorRepo;
    private StudentRepo studentRepo;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           RoleRep roleRep,
                           UserRepository userRepository,
                           InstructorRepo instructorRepo,
                           StudentRepo studentRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRep = roleRep;
        this.userRepository = userRepository;
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
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
    public User saveOne(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = user.getRole();

        if (role != null) {

            Role roleFromRepo = roleRep.findByName(role.getName());

            if (roleFromRepo != null) {
                switch (role.getName()) {
                    case "ROLE_INSTRUCTOR":
                        user.setRole(roleFromRepo);
                        userRepository.save(user);
                        Instructor instructor = new Instructor();
                        instructor.setUser(user);
                        instructorRepo.save(instructor);
                        break;
                    case "ROLE_STUDENT":
                        user.setRole(roleFromRepo);
                        userRepository.save(user);
                        Student student = new Student();
                        student.setUser(user);
                        studentRepo.save(student);
                    case "ROLE_ADMIN":
                        user.setRole(roleFromRepo);
                        userRepository.save(user);
                }

            } else {
                throw new EntityNotFoundException("Role with name: " + role.getName() + ", not found!");
            }
        }
        return user;
    }

    @Override
    public User updateOne(User user) {
        return saveOne(user);
    }

    @Override
    public User updateOne(Long id, User patch) {

        User user = findById(id);

        String roleName = user.getRole().getName();

        if (patch.getRole() != null && roleName != null) {
            Role role = roleRep.findByName(patch.getRole().getName());
            if (role != null) {
                switch (role.getName()) {
                    // If he wona become Admin then:
                    case "ROLE_ADMIN":
                        user.setRole(role);
                        // If he was an Instructor then:
                        if (roleName.equals("ROLE_INSTRUCTOR")) {
                            instructorRepo.delete(instructorRepo.findByUser(user));
                        }
                        // If he was a Student then:
                        if (roleName.equals("ROLE_STUDENT")) {
                            studentRepo.delete(studentRepo.findByUser(user));
                        }
                        break;
                    // If he wona become Instructor then:
                    case "ROLE_INSTRUCTOR":
                        user.setRole(role);
                        Instructor instructor;
                        if (roleName.equals("ROLE_ADMIN")) {
                            instructor = new Instructor();
                            instructor.setUser(user);
                        }
                        // If he was a Student then:
                        if (roleName.equals("ROLE_STUDENT")) {
                            studentRepo.delete(studentRepo.findByUser(user));
                            instructor = new Instructor();
                            instructor.setUser(user);
                        }
                        break;
                    // If he wona become Student then:
                    case "ROLE_STUDENT":
                        user.setRole(role);
                        Student student;
                        // If he was an Instructor then:
                        if (roleName.equals("ROLE_INSTRUCTOR")) {
                            instructorRepo.delete(instructorRepo.findByUser(user));
                            student = new Student();
                            student.setUser(user);
                        }
                        // If he was an Admin then:
                        if (roleName.equals("ROLE_ADMIN")) {
                            student = new Student();
                            student.setUser(user);
                        }
                        break;
                }
            }
        }
        if (patch.getUsername() != null) user.setUsername(patch.getUsername());
        if (patch.getPassword() != null) user.setPassword(patch.getPassword());
        if (patch.getFullName() != null) user.setFullName(patch.getFullName());
        if (patch.getAge() != 0) user.setAge(patch.getAge());
        if (patch.getPhone() != null) user.setPhone(patch.getPhone());
        if (patch.getEmail() != null) user.setEmail(patch.getEmail());
        if (patch.getSkype() != null) user.setSkype(patch.getSkype());

        return userRepository.save(user);
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
