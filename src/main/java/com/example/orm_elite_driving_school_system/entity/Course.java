package com.example.orm_elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "course")
public class Course {

    @Id
    @EqualsAndHashCode.Include
    @Column
    private String course_id;

    @Column(nullable = false)
    private String course_name;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false, length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id")
    private Instructors instructors;

    // inverse side
    @ManyToMany(mappedBy = "courses")
    private List<Students> students = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lessons> lessons = new ArrayList<>();
}
