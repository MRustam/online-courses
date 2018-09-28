package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_detail_generator")
    @SequenceGenerator(name = "instructor_detail_generator", sequenceName = "instructor_detail_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "work_experience")
    private int workExperience;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;

    public InstructorDetail() {
    }

    public InstructorDetail(int workExperience, String email) {
        this.workExperience = workExperience;
        this.email = email;
    }

    public InstructorDetail(int workExperience, String email, String skype) {
        this.workExperience = workExperience;
        this.email = email;
        this.skype = skype;
    }

    public Long getId() {
        return id;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
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
        return "InstructorDetail{" +
                "id=" + id +
                ", workExperience=" + workExperience +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }
}
