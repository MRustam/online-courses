package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.InstructorDetail;
import ru.rmamedov.courses.springbootappcourses.repository.InstructorDetailRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorDetailService;

import java.util.List;

@Service
public class InstructorDetailServiceImpl implements IInstructorDetailService {

    private InstructorDetailRep instructorDetailRep;

    @Autowired
    public InstructorDetailServiceImpl(InstructorDetailRep instructorDetailRep) {
        this.instructorDetailRep = instructorDetailRep;
    }

    @Override
    public List<InstructorDetail> findAll() {
        return instructorDetailRep.findAll();
    }

    @Override
    public InstructorDetail findOneById(Long id) {
        InstructorDetail instructorDetail = instructorDetailRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor detail with id: '" + id + "' - Not found!"));
        return instructorDetail;
    }

    @Override
    public InstructorDetail saveOne(InstructorDetail object) {
        return null;
    }

    @Override
    public void deleteOneById(Long aLong) {

    }
}
