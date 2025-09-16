package com.example.orm_elite_driving_school_system.dto;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class InstructorsDTO {
    private String instructor_id;
    private  String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String specialization;
    private String availability;
    @Builder.Default
    private ArrayList<LessonsDTO> lessons = new  ArrayList<>();
    @Builder.Default
    private ArrayList<CourseDTO> courses = new ArrayList<>();
}
