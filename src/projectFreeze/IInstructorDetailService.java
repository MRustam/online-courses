package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.InstructorDetail;

import java.util.List;

public interface IInstructorDetailService extends BaseInterface<InstructorDetail, Long> {

    @Override
    List<InstructorDetail> findAll();

    @Override
    InstructorDetail findOneById(Long aLong);

    @Override
    InstructorDetail saveOne(InstructorDetail object);

    @Override
    void deleteOneById(Long aLong);
}
