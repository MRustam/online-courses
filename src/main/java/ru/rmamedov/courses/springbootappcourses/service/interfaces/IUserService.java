package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.User;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<User> findAll();

    User saveOne(User user);

    User updateOne(User user);

    User updateOne(Long id, User user);

    User findById(Long aLong);

    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
