package ru.rmamedov.courses.model.course;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import ru.rmamedov.courses.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id", unique = true)
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Column(name = "text", length = 3000)
    private String text;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) &&
                created.equals(comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }
}
