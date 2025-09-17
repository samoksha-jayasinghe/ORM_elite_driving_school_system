package com.example.orm_elite_driving_school_system.dao.custom;

import lk.ijse.elitedrivingschoolsystemormcoursework.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    public int getStudentCountForLesson(String lessonId);
}
