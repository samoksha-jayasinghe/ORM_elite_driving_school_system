package com.example.orm_elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @Column
    private String lessonId;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructorId", referencedColumnName = "instructor_id")
    private Instructors instructors;

    @Column
    private Date lessonDate;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String status;
}
