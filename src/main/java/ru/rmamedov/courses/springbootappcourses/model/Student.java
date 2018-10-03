package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastNAme;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;

    public Student() {
    }

    public Student(String firstName, String lastNAme, int age, String email, String skype) {
        this.firstName = firstName;
        this.lastNAme = lastNAme;
        this.age = age;
        this.email = email;
        this.skype = skype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(id, student.id) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastNAme, student.lastNAme) &&
                Objects.equals(email, student.email) &&
                Objects.equals(skype, student.skype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastNAme, age, email, skype);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastNAme='" + lastNAme + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
