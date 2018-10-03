package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRep studentRep;

    @Autowired
    public StudentServiceImpl(StudentRep studentRep) {
        this.studentRep = studentRep;
    }

    @Override
    public List<Student> findAll() {
        return studentRep.findAll();
    }

    @Override
    public Student findOneById(Long id) {
        Student student = studentRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id: '" + id + "' - Not found!"));
        return student;
    }

    @Override
    public Student saveOne(Student student) {
        if (student.getFirstName() == null || student.getFirstName().equals("")) {
            throw new EntityNotFoundException("Saving student is null!");
        } else {
            return studentRep.save(student);
        }
    }

    @Override
    public void deleteOneById(Long id) {
        studentRep.delete(findOneById(id));
    }

    @Override
    public Student updateOneById(Long id, Student student) {
        deleteOneById(id);
        student.setId(id);
        return saveOne(student);
    }
}
