package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import java.util.List;

public interface BaseInterface <T, ID> {

    List<T> findAll();

    T findOneById(ID id);

    T saveOne(T object);

    void deleteOneById(ID id);

}
