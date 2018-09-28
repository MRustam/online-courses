package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.repository.InstructorRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService {

    private InstructorRep instructorRep;

    @Autowired
    public InstructorServiceImpl(InstructorRep instructorRep) {
        this.instructorRep = instructorRep;
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRep.findAll();
    }
}
