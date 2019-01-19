package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRep studentRep;
    private RoleRep roleRep;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRep studentRep, RoleRep roleRep, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRep = studentRep;
        this.roleRep = roleRep;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Student> findAll() {
        return studentRep.findAll();
    }

    @Override
    public Student findOneById(Long aLong) {
        return null;
    }

    @Override
    public Student saveOne(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (Role r : student.getRoles()) {
            if (r.getName() != null) {
                roles.add(roleRep.findByName(r.getName()));
            }
        }
        student.setRoles(new HashSet<>(roles));
        return studentRep.save(student);
    }

    @Override
    public void deleteOneById(Long aLong) {

    }

    @Override
    public Student updateOne(Student student) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRep.findByUsername(username);
        if (student != null) {
            return student;
        }
        throw new UsernameNotFoundException("User " + username + " not found.");
    }

}
