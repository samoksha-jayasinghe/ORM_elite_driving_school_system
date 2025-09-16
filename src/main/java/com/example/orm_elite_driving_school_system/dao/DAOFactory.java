package com.example.orm_elite_driving_school_system.dao;

import lk.ijse.elitedrivingschoolsystemormcoursework.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getInstance(){
        return daoFactory == null ? (daoFactory=new DAOFactory()) : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){

            case COURSE :
                return new CourseDAOImpl();
            case INSTRUCTORS:
                return new InstructorsDAOImpl();
            case LESSONS:
                return new LessonsDAOImpl();
            case PAYMENTS:
                return new PaymentsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case STUDENT_COURSE_DETAILS:
                return new StudentCourseDetailsDAOImpl();
            case STUDENTS:
                return new StudentsDAOImpl();
            case USER:
                return new UserDAOImpl();

            default:
                return null;

        }
    }
}
