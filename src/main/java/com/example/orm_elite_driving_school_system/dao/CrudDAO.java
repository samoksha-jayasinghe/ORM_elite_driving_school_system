package com.example.orm_elite_driving_school_system.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO {

    List<T> getAll() throws Exception;

    String getLastId() throws Exception;

    boolean save(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(String id) throws Exception;

    List<String> getAllIds() throws Exception;

    Optional<T> findById(String id) throws Exception;

    String generateNewId();
}
