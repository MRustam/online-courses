package ru.rmamedov.courses.springbootappcourses.model;

import javax.persistence.*;

@Entity
@Table(name = "student_detail")
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;

    @Column(name = "progress")
    private Double progress;

    @OneToOne(mappedBy = "detail", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Student student;

    public StudentDetail() {
    }

    public StudentDetail(int age, String email, String skype, Double progress) {
        this.age = age;
        this.email = email;
        this.skype = skype;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentDetail{" +
                "id=" + id +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                ", progress=" + progress +
                ", student=" + student +
                '}';
    }
}
