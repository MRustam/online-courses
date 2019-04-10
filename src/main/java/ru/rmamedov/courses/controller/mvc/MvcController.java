package ru.rmamedov.courses.controller.mvc;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rustam Mamedov
 */

@Controller("/")
public class MvcController {

    @GetMapping(value = {"/", "/home"})
    public String toHomePage() {
        return "index";
    }

    // Login page. If logged in then prevent to show login page.
    @GetMapping("/login")
    public String toLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "index";
        }
        return "login";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @GetMapping(value = {
            "/access-denied",
            "/success",
            "/instructors",
            "/users",
            "/new-course"
    })
    public String toGeneralOneColumnTemplate() {
        return "one-col-template";
    }

    @GetMapping("/students")
    public String toStudents() {
        return "students";
    }
}
