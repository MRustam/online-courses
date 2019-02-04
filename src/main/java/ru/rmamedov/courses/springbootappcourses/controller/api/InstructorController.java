package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    private IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAll() {
        if (instructorService.findAll().size() > 0) {
            return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        if (instructor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<Instructor> findByUsername(@PathVariable String username) {
        Instructor instructor = instructorService.findByUsername(username);
        if (instructor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Instructor> save(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(instructorService.save(user), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Instructor> update(@RequestBody Instructor instructor) {
        if (instructor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(instructorService.update(instructor), HttpStatus.OK);
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor instructor) {
//        if (id <= 0 & instructor == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
//        }
//        return new ResponseEntity<>(instructorService.update(id, instructor), HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        try {
            instructorService.deleteOneById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
}
