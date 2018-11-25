package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    // Redirect to general page.
    @GetMapping("/")
    public String root() {
        return "index";
    }

    // General welcome page with list courses.
    @GetMapping("/home")
    public String toHomePage() {
        return "/index";
    }

    // My courses page.
    @GetMapping("/student")
    public String toStudentOwnPage() {
        return "/student/index";
    }

    // Admin page 'add course, add instructor, add student'.
    @GetMapping("/admin")
    public String allAccessPage() {
        return "/admin/index";
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

    // Custom forbidden page if access denied.
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
