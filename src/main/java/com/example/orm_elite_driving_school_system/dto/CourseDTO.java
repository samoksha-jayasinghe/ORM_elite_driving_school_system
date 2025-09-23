package com.example.orm_elite_driving_school_system.dto;


import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CourseDTO {

    private String course_id;
    private String course_name;
    private String duration;
    private double fee;
    private String description;
    private String instructor_id;

    @Builder.Default
    private ArrayList<LessonsDTO> lessons = new ArrayList<>();

}
