package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rmamedov.courses.springbootappcourses.model.Role;

public interface RoleRep extends JpaRepository<Role, Long> {

    Role findByName(String role);

}
