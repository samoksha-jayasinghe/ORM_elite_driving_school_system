package com.example.orm_elite_driving_school_system.dao.custom;

import com.example.orm_elite_driving_school_system.dao.CrudDAO;
import com.example.orm_elite_driving_school_system.entity.Lessons;
import com.example.orm_elite_driving_school_system.entity.Payments;

public interface PaymentDAO extends CrudDAO<Payments> {
    boolean save(Lessons lessons) throws Exception;

    boolean update(Lessons lessons) throws Exception;
}
