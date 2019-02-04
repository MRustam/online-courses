package ru.rmamedov.courses.springbootappcourses.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 3000)
    private String text;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "course_id")
    private Course course;
}
