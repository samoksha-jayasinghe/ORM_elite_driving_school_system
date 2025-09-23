package com.example.orm_elite_driving_school_system.bo.custom;

import com.example.orm_elite_driving_school_system.bo.SuperBO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;

public interface EnrollBO extends SuperBO {
    public boolean saveStudents(StudentsDTO dto) throws Exception;
    public boolean updateStudents(StudentsDTO dto) throws Exception;
}
