package com.example.orm_elite_driving_school_system.dao;

import com.example.orm_elite_driving_school_system.dao.custom.impl.*;

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
                return new InstuctorsDAOImpl();
            case LESSONS:
                return new LessonsDAOImpl();
            case PAYMENTS:
                return new PaymentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case STUDENTS:
                return new StudentsDAOImpl();
            case USER:
                return new UserDAOImpl();

            default:
                return null;

        }
    }
}
