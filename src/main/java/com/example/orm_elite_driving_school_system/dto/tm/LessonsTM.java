package com.example.orm_elite_driving_school_system.dto.tm;

import javafx.event.ActionEvent;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class LessonsTM extends ActionEvent {
    private String lesson_id;
    private String student_id;
    private String course_id;
    private String instructor_id;
    private Date lessonDate;
    private Time startTime;
    private Time endTime;
    private String status;
}
