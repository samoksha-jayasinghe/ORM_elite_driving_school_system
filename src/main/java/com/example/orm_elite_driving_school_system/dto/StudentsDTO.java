package com.example.orm_elite_driving_school_system.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class StudentsDTO {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private Date registrationDate;
    @Builder.Default
    private ArrayList<CourseDTO> courses = new ArrayList<>();
    @Builder.Default
    private ArrayList<LessonsDTO> lessons =new ArrayList<>();
    @Builder.Default
    private ArrayList<PaymentsDTO> payments =new ArrayList<>();

}

