package ru.rmamedov.courses.springbootappcourses.repository.DTO;

import java.util.Date;

public interface StudentDTO {

    Long getId();

    String getFullname();

    String getUsername();

    Integer getAge();

    Date getRegistered();

    String getEmail();

    String getSkype();

    Integer getPerformance();

    Integer getCourses();
}
