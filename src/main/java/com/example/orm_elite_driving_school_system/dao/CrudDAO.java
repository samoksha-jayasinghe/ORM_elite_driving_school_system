package com.example.orm_elite_driving_school_system.dao;

import com.example.orm_elite_driving_school_system.entity.Lessons;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO {

    List<Lessons> getAll() throws Exception;

    String getLastId() throws Exception;

    boolean save(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(String id) throws Exception;

    List<String> getAllIds() throws Exception;

    Optional<Lessons> findById(String id) throws Exception;

    String generateNewId();
}
