package com.example.orm_elite_driving_school_system.dao.custom;

import com.example.orm_elite_driving_school_system.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    public int getStudentCountForLesson(String lessonId);
}
