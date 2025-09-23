package com.example.orm_elite_driving_school_system.bo.custom.impl;

import com.example.orm_elite_driving_school_system.bo.custom.QueryBO;
import com.example.orm_elite_driving_school_system.dao.DAOFactory;
import com.example.orm_elite_driving_school_system.dao.DAOTypes;
import com.example.orm_elite_driving_school_system.dao.custom.QueryDAO;

public class QueryBOImpl implements QueryBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    @Override
    public double getTotalCourseAmountByStudentId(String studentId) throws Exception {
        return queryDAO.getTotalCourseAmountByStudentId(studentId);
    }

}
