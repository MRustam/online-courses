package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import java.util.List;

public interface BaseInterface<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T object);

    void deleteOneById(ID id);

    T update(T t);

//    T update(Long id, T patch);

}
