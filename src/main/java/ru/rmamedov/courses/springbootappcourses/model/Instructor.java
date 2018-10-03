package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "work_experience")
    private double workExperience;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;


    public Instructor() {
    }

    public Instructor(String firstName, String lastName, double workExperience, String email, String skype) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workExperience = workExperience;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(double workExperience) {
        this.workExperience = workExperience;
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
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workExperience=" + workExperience +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
