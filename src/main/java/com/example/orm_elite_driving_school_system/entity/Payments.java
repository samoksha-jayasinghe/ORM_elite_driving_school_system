package com.example.orm_elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @Column
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private Students students;

    @Column
    private Date paymentDate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String status;
}
