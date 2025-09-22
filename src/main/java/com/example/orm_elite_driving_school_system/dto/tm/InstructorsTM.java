package com.example.orm_elite_driving_school_system.dto.tm;

import javafx.event.ActionEvent;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class InstructorsTM extends ActionEvent {
    private String instructor_id;
    private  String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String specialization;
    private String availability;
}
