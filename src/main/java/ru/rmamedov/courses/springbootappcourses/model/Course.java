package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private int duration;
    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public Course() {
    }

    public Course(String category, String title, String description, int duration, LocalDate startDate, List<Review> reviews) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.startDate = startDate;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return duration == course.duration &&
                Objects.equals(id, course.id) &&
                Objects.equals(category, course.category) &&
                Objects.equals(title, course.title) &&
                Objects.equals(description, course.description) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(reviews, course.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, description, duration, startDate, reviews);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", reviews=" + reviews +
                '}';
    }
}
