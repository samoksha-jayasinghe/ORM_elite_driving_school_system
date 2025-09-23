package com.example.orm_elite_driving_school_system.bo.custom.impl;

import com.example.orm_elite_driving_school_system.bo.custom.EnrollBO;
import com.example.orm_elite_driving_school_system.dao.DAOFactory;
import com.example.orm_elite_driving_school_system.dao.DAOTypes;
import com.example.orm_elite_driving_school_system.dao.custom.CourseDAO;
import com.example.orm_elite_driving_school_system.dao.custom.StudentsDAO;
import com.example.orm_elite_driving_school_system.dto.CourseDTO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;
import com.example.orm_elite_driving_school_system.entity.Course;
import com.example.orm_elite_driving_school_system.entity.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollBOImpl implements EnrollBO {

    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENTS);

    @Override
    public boolean saveStudents(StudentsDTO dto) throws Exception {
        Students student = new Students();
        student.setStudentId(dto.getStudentId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setDob(dto.getDob());
        student.setRegistrationDate(dto.getRegistrationDate());

        List<Course> courses = new ArrayList<>();
        if (dto.getCourses() != null) {
            for (CourseDTO c : dto.getCourses()) {
                Optional<Course> courseOpt = courseDAO.findById(c.getCourse_id());
                Course courseEntity = courseOpt.orElseThrow(
                        () -> new RuntimeException("Course not found: " + c.getCourse_id())
                );
                if (!courses.contains(courseEntity)) { // avoid duplicates
                    courses.add(courseEntity);
                }
            }
        }
        student.setCourses(courses);

        return studentsDAO.save(student);
    }

    @Override
    public boolean updateStudents(StudentsDTO dto) throws Exception {
        Optional<Students> studentOpt = studentsDAO.findById(dto.getStudentId());
        if (studentOpt.isEmpty()) return false;

        Students student = studentOpt.get();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setDob(dto.getDob());
        student.setRegistrationDate(dto.getRegistrationDate());

        student.getCourses().clear();
        if (dto.getCourses() != null) {
            for (CourseDTO c : dto.getCourses()) {
                Optional<Course> courseOpt = courseDAO.findById(c.getCourse_id());
                courseOpt.ifPresent(courseEntity -> {
                    if (!student.getCourses().contains(courseEntity)) {
                        student.getCourses().add(courseEntity);
                    }
                });
            }
        }

        return studentsDAO.update(student);
    }
}
