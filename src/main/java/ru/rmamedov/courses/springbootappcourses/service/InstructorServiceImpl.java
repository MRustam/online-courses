package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
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
    public List<Instructor> findAll() {
        return instructorRep.findAll();
    }

    @Override
    public Instructor findOneById(Long id) {
        Instructor instructor = instructorRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with id: '" + id + "' - Not found!"));
        return instructor;
    }

    @Override
    public Instructor saveOne(Instructor instructor) {
        return null;
    }

    @Override
    public void deleteOneById(Long id) {

    }
}
