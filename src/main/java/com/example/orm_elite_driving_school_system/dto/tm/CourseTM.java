package com.example.orm_elite_driving_school_system.dto.tm;

import javafx.event.ActionEvent;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class CourseTM extends ActionEvent {
    private String course_id;
    private String course_name;
    private String duration;
    private double fee;
    private String description;
    private String instructor_id;
}
