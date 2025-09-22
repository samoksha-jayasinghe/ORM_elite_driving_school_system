package com.example.orm_elite_driving_school_system.dto.tm;


import javafx.event.ActionEvent;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString


public class PaymentTM extends ActionEvent {
    private String paymentId;
    private Date paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;
    private String studentId;
}

