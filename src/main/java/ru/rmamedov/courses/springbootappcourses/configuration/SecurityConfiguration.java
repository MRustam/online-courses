package ru.rmamedov.courses.springbootappcourses.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private LoggingAccessDeniedHandler accessDeniedHandler;
    private IUserService instructorService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(LoggingAccessDeniedHandler accessDeniedHandler, IUserService instructorService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accessDeniedHandler = accessDeniedHandler;
        this.instructorService = instructorService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(instructorService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/css/**",
                "/js/**",
                "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/", "/home/**", "/students/**", "/instructors/**").access("permitAll()");

        // Look to all users can only admin.
        http
                .authorizeRequests()
                .antMatchers("/users/**").access("hasRole('ROLE_ADMIN')");

        // Enroll on course can only Student.
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/student/enroll/**").access("hasRole('ROLE_STUDENT')");

        // Add new course can only Instructor.
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/course/save")
                    .access("hasRole('ROLE_INSTRUCTOR')")
                .antMatchers("/new-course")
                    .access("hasRole('ROLE_INSTRUCTOR')");
        // Only instructor can delete course.
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/course/delete/**")
                    .access("hasRole('ROLE_INSTRUCTOR')");

        // Everybody can process registration.
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/save", "/api/student/save", "/api/instructor/save")
                .access("permitAll()");

        // Access to general pages.
        http
                .authorizeRequests()
                .antMatchers("/access-denied")
                .access("permitAll()");

        // Login Logout pages.
        http.authorizeRequests()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(accessDeniedHandler);
    }


}
