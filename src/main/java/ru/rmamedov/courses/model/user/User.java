package ru.rmamedov.courses.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rmamedov.courses.model.course.Course;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Rustam Mamedov
 */

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "id", unique = true)
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "skype")
    private String skype;

    @Column(name = "work_experience", nullable = true)
    private int workExperience;

    @Column(name = "academic_performance", nullable = true)
    private double academicPerformance = 0.0D;

    @CreationTimestamp
    @Column(name = "registered")
    private LocalDateTime registered = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Set<Course> createdCourses;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "user_enrolled",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"})
    )
    private Set<Course> enrolledCourses;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public boolean createCourse(@NotNull final Course course) {
        if (createdCourses == null) {
            createdCourses = new HashSet<>();
            return createdCourses.add(course);
        } else {
            return createdCourses.add(course);
        }
    }

    public boolean enroll(@NotNull final Course course) {
        if (enrolledCourses == null) {
            enrolledCourses = new HashSet<>();
            return enrolledCourses.add(course);
        } else {
            return enrolledCourses.add(course);
        }
    }

    public boolean dropCourse(@NotNull final Course course) {
        if (enrolledCourses.contains(course)) {
            return createdCourses.remove(course);
        }
        return false;
    }

    @NotNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        @NotNull final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return authorities;
    }

    public boolean addRole(@NotNull final Role role) {
        return roles.add(role);
    }

    public boolean removeRole(@NotNull final Role role) {
        return roles.remove(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
