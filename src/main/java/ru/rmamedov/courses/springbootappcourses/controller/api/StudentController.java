package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.StudentDTO;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        if (studentService.findAll().size() > 0) {
            return new ResponseEntity<>(studentService.findAllDTO(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/by-course-id/{id}")
    public ResponseEntity<List<StudentDTO>> getAllByCourseId(@PathVariable Long id) {
        if (studentService.findAll().size() > 0) {
            return new ResponseEntity<>(studentService.findAllDTOByCourseId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        StudentDTO student = studentService.findDTOById(id);
        if (student == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<Student> findByUsername(@PathVariable String username) {
        Student student = studentService.findByUsername(username);
        if (student == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(studentService.save(user), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        if (student == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }

    @PutMapping("/enroll/courseId/{id}")
    public ResponseEntity<Student> enroll(@PathVariable Long id,
                                          @AuthenticationPrincipal User user) {
        if (id <= 0 || user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(studentService.enroll(id, user), HttpStatus.OK);
    }

    @PutMapping("/leave/courseId/{id}")
    public ResponseEntity<Student> leave(@PathVariable Long id,
                                          @AuthenticationPrincipal User user) {
        if (id <= 0 || user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(studentService.leave(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        try {
            studentService.deleteOneById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
}
