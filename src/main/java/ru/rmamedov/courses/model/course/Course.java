package ru.rmamedov.courses.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.rmamedov.courses.model.category.Category;
import ru.rmamedov.courses.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Rustam Mamedov
 */

@Data
@Table(name = "course")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "is_started")
    private boolean isStarted;

    @Column(name = "is_free")
    private boolean isFree;

    @NotNull
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "starts")
    private LocalDate starts;

    @Column(name = "rating")
    private double rating = 0.0D;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Image image;

    @ManyToMany(mappedBy = "enrolledCourses",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private Set<User> users;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                title.equals(course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}