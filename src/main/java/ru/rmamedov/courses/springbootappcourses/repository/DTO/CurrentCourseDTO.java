package ru.rmamedov.courses.springbootappcourses.repository.DTO;

import java.util.Date;

public interface CurrentCourseDTO {

    String getTitle();

    String getDescription();

    String getOwner();

    Double getRating();

    Date getDate();

    Integer getDuration();

    String getCategory();

    Boolean getStatus();

    Integer getEnrolled();

    Integer getRcount();

}
