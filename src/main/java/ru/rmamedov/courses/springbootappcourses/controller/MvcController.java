package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    // General welcome page with list courses.
    @GetMapping(value = {"/", "/home"})
    public String toHomePage() {
        return "/index";
    }

    // Student own page.
    @GetMapping("/students")
    public String toStudentOwnPage() {
        return "/one-col-template";
    }

    // All Instructors page.
    @GetMapping("/instructors")
    public String toInstructorsListPage() {
        return "/one-col-template";
    }

    // Login page. If logged in then prevent to show login page.
    @GetMapping("/login")
    public String toLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "/index";
        }
        return "/login";
    }

    @GetMapping("/registration")
    public String register() {
        return "/registration";
    }

    // Custom forbidden page if access denied.
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/one-col-template";
    }

    // Custom forbidden page if access denied.
    @GetMapping("/success")
    public String successPage() {
        return "/one-col-template";
    }
}
