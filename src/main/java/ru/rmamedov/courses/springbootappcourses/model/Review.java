package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "course_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_time")
    private LocalDate creation_time;

    public Review() {
    }

    public Review(String text, LocalDate creation_time) {
        this.text = text;
        this.creation_time = creation_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDate creation_time) {
        this.creation_time = creation_time;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creation_time=" + creation_time +
                '}';
    }
}
