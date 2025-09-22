package com.example.orm_elite_driving_school_system.bo.custom;

import com.example.orm_elite_driving_school_system.bo.SuperBO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;

import java.util.List;
import java.util.Optional;

public interface StudentsBO extends SuperBO {
    List<StudentsDTO> getAllStudents() throws Exception;

    String getLastStudentId() throws Exception;

    boolean saveStudents(StudentsDTO t) throws Exception;

    boolean updateStudents(StudentsDTO t) throws Exception;

    boolean deleteStudents(String id) throws Exception;

    List<String> getAllStudentIds() throws Exception;

    Optional<StudentsDTO> findByStudentId(String id) throws Exception;

    String generateNewStudentId();
}
