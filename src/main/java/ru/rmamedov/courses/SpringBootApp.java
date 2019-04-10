package ru.rmamedov.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Rustam Mamedov
 */

@Component
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class);
    }
}
