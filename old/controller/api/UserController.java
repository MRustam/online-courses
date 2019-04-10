package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
@CrossOrigin(origins="*")
public class UserController {

    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveOne(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(iUserService.saveOne(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        if (iUserService.findAll().size() > 0) {
            return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> FindById(@PathVariable Long id) {
        User user = iUserService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<User> FindByUsername(@PathVariable String username) {
        User user = iUserService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/fetch")
    public ResponseEntity<User> updateOne(@RequestBody User user) {
        if (iUserService.update(user) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(iUserService.update(user), HttpStatus.ACCEPTED);
    }

}
