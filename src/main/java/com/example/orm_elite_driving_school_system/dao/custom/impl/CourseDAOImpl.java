package com.example.orm_elite_driving_school_system.dao.custom.impl;

import com.example.orm_elite_driving_school_system.config.FactoryConfiguration;
import com.example.orm_elite_driving_school_system.dao.custom.CourseDAO;
import com.example.orm_elite_driving_school_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Course> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Course> query = session.createQuery("from Course",Course.class);
            List<Course> courseList = query.list();
            return courseList;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        return "";
    }

    @Override
    public boolean save(Course course) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course course) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<String> getAllIds() throws Exception {
        return List.of();
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public String generateNewId() {
        return "";
    }
}
