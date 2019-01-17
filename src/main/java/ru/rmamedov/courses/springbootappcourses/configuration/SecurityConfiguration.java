package ru.rmamedov.courses.springbootappcourses.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private LoggingAccessDeniedHandler accessDeniedHandler;
    private IInstructorService instructorService;

    public SecurityConfiguration(LoggingAccessDeniedHandler accessDeniedHandler, IInstructorService instructorService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.instructorService = instructorService;
    }

    // Password encoder bean.
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure custom user details service, password encoder.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(instructorService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Allow to access login, registration pages.
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/registration").access("permitAll()");

        // Login Logout pages.
        http.authorizeRequests()
                .and()
                    .formLogin()
                        .loginPage("/login")
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
