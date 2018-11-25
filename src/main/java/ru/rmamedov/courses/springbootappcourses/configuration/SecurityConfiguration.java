package ru.rmamedov.courses.springbootappcourses.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfiguration(LoggingAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers(
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/lib/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/student/**").hasRole("STUDENT")
                    .anyRequest().authenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
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

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        List<UserDetails> users = new ArrayList<>();

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("student_001")
                        .password("123qwe")
                        .roles("STUDENT")
                        .build();

        UserDetails manager =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("123qwe")
                        .roles("ADMIN")
                        .build();

        UserDetails instructor =
                User.withDefaultPasswordEncoder()
                        .username("instructor_001")
                        .password("123qwe")
                        .roles("INSTRUCTOR")
                        .build();

        users.add(user);
        users.add(manager);
        users.add(instructor);

        return new InMemoryUserDetailsManager(users);
    }

}
