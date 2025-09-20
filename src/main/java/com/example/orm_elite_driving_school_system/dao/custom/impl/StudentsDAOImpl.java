package com.example.orm_elite_driving_school_system.dao.custom.impl;

import com.example.orm_elite_driving_school_system.config.FactoryConfiguration;
import com.example.orm_elite_driving_school_system.dao.custom.StudentsDAO;
import com.example.orm_elite_driving_school_system.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class StudentsDAOImpl implements StudentsDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Students> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Students> query = session.createQuery("from Students ",Students.class);
            List<Students> studentsList = query.list();
            return studentsList;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery("SELECT s.studentId FROM Students s ORDER BY s.studentId DESC", String.class)
                    .setMaxResults(1);
            List<String> studentsList = query.list();
            if (studentsList.isEmpty()) {
                return null;
            }
            return studentsList.getFirst();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Students students) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(students);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Students students) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(students);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Students students = (Students) session.get(Students.class, id);
            if (students != null) {
                session.remove(students);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getAllIds() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery("SELECT s.studentId FROM Students s", String.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Students> findById(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Students students = session.get(Students.class, id);
            return Optional.ofNullable(students);
        } finally {
            session.close();
        }
    }

    @Override
    public String generateNewId() {
        String lastId = null;
        try {
            lastId = getLastId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (lastId == null) {
            return "S-001";
        } else {
            int num = Integer.parseInt(lastId.split("-")[1]);
            num++;
            return String.format("S-%03d", num);
        }
    }

}
