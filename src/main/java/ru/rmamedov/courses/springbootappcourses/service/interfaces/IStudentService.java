package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.StudentDTO;

import java.util.List;

public interface IStudentService extends BaseInterface<Student, Long> {

    @Override
    List<Student> findAll();

    List<StudentDTO> findAllDTO();

    @Override
    Student findById(Long id);

    Student save(User user);

    @Override
    Student save(Student student);

    @Override
    void deleteOneById(Long id);

    @Override
    Student update(Student student);

    Student findByUsername(String username);

    Student enroll(Long id, User user);

    Student leave(Long id, User user);

    StudentDTO findDTOById(Long id);

    List<StudentDTO> findAllDTOByCourseId(Long id);
}
