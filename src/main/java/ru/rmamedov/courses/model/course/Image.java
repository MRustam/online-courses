package ru.rmamedov.courses.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Rustam Mamedov
 */

@Data
@Table(name = "image")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {

    @Id
    @NotNull
    @Column(name = "id", unique = true)
    private String id = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "persist")
    private byte[] data;

    @OneToOne(mappedBy = "image",
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH
            })
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id) &&
                name.equals(image.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
