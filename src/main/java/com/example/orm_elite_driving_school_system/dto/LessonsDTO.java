package com.example.orm_elite_driving_school_system.dto;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class LessonsDTO {
    private String lesson_id;
    private String student_id;
    private String course_id;
    private String instructor_id;
    private Date lessonDate;
    private String startTime;
    private String endTime;
    private String status;

}
