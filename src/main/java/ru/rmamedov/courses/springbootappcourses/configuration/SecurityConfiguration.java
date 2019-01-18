package ru.rmamedov.courses.springbootappcourses.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private LoggingAccessDeniedHandler accessDeniedHandler;
    private IInstructorService instructorService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(LoggingAccessDeniedHandler accessDeniedHandler, IInstructorService instructorService, BCryptPasswordEncoder bCryptPasswordEncoder) {
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
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

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
