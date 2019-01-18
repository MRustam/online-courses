package ru.rmamedov.courses.springbootappcourses.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role")
    private String role;

}
