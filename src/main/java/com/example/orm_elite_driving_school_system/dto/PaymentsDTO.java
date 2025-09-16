package com.example.orm_elite_driving_school_system.dto;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class PaymentsDTO {
    private String paymentId;
    private String studentId;
    private Date paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;
}
