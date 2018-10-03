package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String getLoginPage() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        System.out.println("user: "+ auth.getName());
        System.out.println("roles: "+ auth.getAuthorities());
        return "login";
    }
}
