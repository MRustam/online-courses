package ru.rmamedov.courses.springbootappcourses.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "duration", nullable = false)
    private int duration;

    @CreationTimestamp
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "rating")
    private double rating;

    @Column(name = "status")
    private boolean status;

    @Lob
    @Column(name = "image")
    private Byte[] image;
}