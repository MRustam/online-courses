package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Instructor;

import java.util.List;

public interface IInstructorService {
    List<Instructor> getAll();
}
