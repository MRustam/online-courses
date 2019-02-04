package ru.rmamedov.courses.springbootappcourses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "academic_performance")
    private int academicPerformance;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "student_course", joinColumns =
            @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;
}
