package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
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
        return instructorRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Instructor with id: " + id + "not found"));
    }

    @Override
    public Instructor saveOne(Instructor instructor) {
        return instructorRep.save(instructor);
    }

    @Override
    public void deleteOneById(Long id) {
        instructorRep.delete(findOneById(id));
    }

    @Override
    public Instructor updateOne(Instructor instructor) {

        if (instructor == null) {
            throw new EntityNotFoundException("Updating instructor not found");
        }
        return instructorRep.save(instructor);
    }

    @Override
    public List<Course> getExistingCourses(Long id) {

        List<Course> courses = findOneById(id).getCourses();

        if (courses.size() < 1) {
            throw new EntityNotFoundException("This instructor has no any courses");
        }
        return courses;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Instructor instructor = instructorRep.findByUsername(username);
        if (instructor == null) {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
        return instructor;
    }

}
