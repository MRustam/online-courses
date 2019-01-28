package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Role;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.RoleRep;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRepo;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRepo studentRepo;
    private UserServiceImpl userService;
    private RoleRep roleRep;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, UserServiceImpl userService, RoleRep roleRep) {
        this.studentRepo = studentRepo;
        this.userService = userService;
        this.roleRep = roleRep;
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> optInstructor = studentRepo.findById(id);
        if (optInstructor.isPresent()) {
            return optInstructor.get();
        }
        throw new EntityNotFoundException("Student with id: " + id + " not found");
    }

    @Override
    public Student findByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            Student student = studentRepo.findByUser(user);
            if (student != null) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with username: '" + "', not found");
    }

    @Override
    public Student save(Student student) {
        if (student.getUser().getRole() != null) {
            String roleName = student.getUser().getRole().getName();
            Role role = roleRep.findByName(roleName);
            if (role != null && role.getName().equals("ROLE_STUDENT")) {
                student.getUser().setRole(role);
                return studentRepo.save(student);
            } else {
                throw new EntityNotFoundException("Role mast be an 'ROLE_STUDENT', but found: " + roleName);
            }
        } else {
            throw new EntityNotFoundException("Saving Student mast contains a Role");
        }
    }

    @Override
    public Student update(Student student) {
        if (student.getUser().getRole() == null) {
            throw new EntityNotFoundException("Updating Student mast contains a Role!");
        }
        return save(student);
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
        Student student = findById(id);
        User user = userService.findByUsername(student.getUser().getUsername());
        user.setRole(roleRep.findByName("ROLE_USER"));
        studentRepo.delete(student);
    }

}
