package com.example.orm_elite_driving_school_system.bo.custom;

import com.example.orm_elite_driving_school_system.bo.SuperBO;
import com.example.orm_elite_driving_school_system.dto.InstructorsDTO;

import java.util.List;
import java.util.Optional;

public interface InstructorsBO extends SuperBO {
    List<InstructorsDTO> getAllInstructors() throws Exception;

    String getLastInstructorId() throws Exception;

    boolean saveInstructors(InstructorsDTO t) throws Exception;

    boolean updateInstructors(InstructorsDTO t) throws Exception;

    boolean deleteInstructors(String id) throws Exception;

    List<String> getAllInstructorIds() throws Exception;

    Optional<InstructorsDTO> findByInstructorId(String id) throws Exception;

    String generateNewInstructorsId();
}
