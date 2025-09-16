package com.example.orm_elite_driving_school_system.dto;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class StudentCourseDetailsDTO {
    private String studentCourseId;
    private String studentId;
    private String courseId;
    private Date enrollmentDate;
    private String status;
    private String grade;
}
