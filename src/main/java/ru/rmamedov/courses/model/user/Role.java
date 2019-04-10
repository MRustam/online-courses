package ru.rmamedov.courses.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Table(name = "role")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Column(name = "id", unique = true)
    private String id = UUID.randomUUID().toString();

    @Column(name = "name", unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
