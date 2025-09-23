package com.example.orm_elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "instructors")
public class Instructors {

    @Id
    @Column
    private String instructor_id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false,unique = true,length = 15)
    private String phone;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String availability;

    @OneToMany(
            mappedBy = "instructors",
            cascade = CascadeType.ALL
    )
    private List<Lessons> lessons;

    @OneToMany(
            mappedBy = "instructors",
            cascade = CascadeType.ALL
    )
    private List<Course> courses;
}
