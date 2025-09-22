package com.example.orm_elite_driving_school_system.bo.custom;

import com.example.orm_elite_driving_school_system.bo.SuperBO;
import com.example.orm_elite_driving_school_system.dto.PaymentsDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentsBO extends SuperBO {
    List<PaymentsDTO> getAllPayments() throws Exception;

    String getLastPaymentId() throws Exception;

    boolean savePayments(PaymentsDTO t) throws Exception;

    boolean updatePayments(PaymentsDTO t) throws Exception;

    boolean deletePayments(String id) throws Exception;

    List<String> getAllPaymentIds() throws Exception;

    Optional<PaymentsDTO> findByPaymentId(String id) throws Exception;

    String generateNewPaymentId();
}
