package com.example.orm_elite_driving_school_system.bo.custom.impl;

import com.example.orm_elite_driving_school_system.bo.custom.PaymentsBO;
import com.example.orm_elite_driving_school_system.bo.custom.exception.DuplicateException;
import com.example.orm_elite_driving_school_system.bo.custom.exception.NotFoundException;
import com.example.orm_elite_driving_school_system.bo.custom.util.EntityDTOConverter;
import com.example.orm_elite_driving_school_system.dao.DAOFactory;
import com.example.orm_elite_driving_school_system.dao.DAOTypes;
import com.example.orm_elite_driving_school_system.dao.custom.PaymentDAO;
import com.example.orm_elite_driving_school_system.dto.PaymentsDTO;
import com.example.orm_elite_driving_school_system.entity.Payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentsBOImpl implements PaymentsBO {

    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOTypes.PAYMENTS);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<PaymentsDTO> getAllPayments() throws Exception {
        List<Payments> paymentsList = paymentDAO.getAll();
        List<PaymentsDTO> paymentsDTOList = new ArrayList<>();
        for (Payments payments : paymentsList) {
            paymentsDTOList.add(converter.getPaymentsDTO(payments));
        }
        return paymentsDTOList;
    }

    @Override
    public String getLastPaymentId() throws Exception {
        return paymentDAO.getLastId();
    }

    @Override
    public boolean savePayments(PaymentsDTO t) throws Exception {
        Optional<Payments> payments = paymentDAO.findById(t.getPaymentId());
        if (payments.isPresent()) {
            throw new DuplicateException("Payment already exists");
        }
        if(t.getStudentId()==null){
            throw new NotFoundException("Student ID cannot be null");
        }
        return paymentDAO.save(converter.getPaymentsEntity(t));
    }

    @Override
    public boolean updatePayments(PaymentsDTO t) throws Exception {
        Optional<Payments> payments = paymentDAO.findById(t.getPaymentId());
        if (payments.isEmpty()) {
            throw new NotFoundException("Payment not found");
        }
        return paymentDAO.update(converter.getPaymentsEntity(t));
    }

    @Override
    public boolean deletePayments(String id) throws Exception {
        Optional<Payments> payments = paymentDAO.findById(id);
        if (payments.isEmpty()) {
            throw new NotFoundException("Payment not found");
        }
        return paymentDAO.delete(id);
    }

    @Override
    public List<String> getAllPaymentIds() throws Exception {
        return paymentDAO.getAllIds();
    }

    @Override
    public Optional<PaymentsDTO> findByPaymentId(String id) throws Exception {
        Optional<Payments> payments = paymentDAO.findById(id);
        if (payments.isPresent()) {
            return Optional.of(converter.getPaymentsDTO(payments.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewPaymentId() {
        return paymentDAO.generateNewId();
    }
}
