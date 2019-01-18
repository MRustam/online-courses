package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.repository.InstructorRep;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService {

    private InstructorRep instructorRep;
    private RoleRep roleRep;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public InstructorServiceImpl(InstructorRep instructorRep, RoleRep roleRep, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.instructorRep = instructorRep;
        this.roleRep = roleRep;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRep.findAll();
    }

    @Override
    public Instructor findOneById(Long id) {
        return instructorRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Instructor with id: " + id + "not found"));
    }

    @Override
    public Instructor saveOne(Instructor instructor) {
        instructor.setPassword(bCryptPasswordEncoder.encode(instructor.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (Role r : instructor.getRoles()) {
            if (r.getRole() != null) {
                roles.add(roleRep.findByRole(r.getRole()));
            }
        }
        instructor.setRoles(new HashSet<>(roles));
        return instructorRep.save(instructor);
    }

    @Override
    public void deleteOneById(Long id) {
        instructorRep.delete(findOneById(id));
    }

    @Override
    public Instructor updateOne(Instructor instructor) {

        if (instructor == null) {
            throw new EntityNotFoundException("Updating instructor not found");
        }
        return instructorRep.save(instructor);
    }

    @Override
    public List<Course> getExistingCourses(Long id) {

        List<Course> courses = findOneById(id).getCourses();

        if (courses.size() < 1) {
            throw new EntityNotFoundException("This instructor has no any courses");
        }
        return courses;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Instructor instructor = instructorRep.findByUsername(username);
        if (instructor == null) {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
        return instructor;
    }

}
