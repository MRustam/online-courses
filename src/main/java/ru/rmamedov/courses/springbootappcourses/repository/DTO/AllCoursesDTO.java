package ru.rmamedov.courses.springbootappcourses.repository.DTO;

public interface AllCoursesDTO {

    Long getId();

    String getTitle();

    String getDescription();

    Double getRating();

    Double getScore();

    Integer getEnrolled();
}
