package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Role;

@Repository
public interface RoleRep extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
