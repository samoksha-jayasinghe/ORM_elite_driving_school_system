package com.example.orm_elite_driving_school_system.bo.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import com.example.orm_elite_driving_school_system.bo.custom.LessonsBO;
import com.example.orm_elite_driving_school_system.bo.custom.exception.NotFoundException;
import com.example.orm_elite_driving_school_system.bo.custom.util.EntityDTOConverter;
import com.example.orm_elite_driving_school_system.dao.DAOFactory;
import com.example.orm_elite_driving_school_system.dao.DAOTypes;
import com.example.orm_elite_driving_school_system.dao.custom.CourseDAO;
import com.example.orm_elite_driving_school_system.dao.custom.InstructorsDAO;
import com.example.orm_elite_driving_school_system.dao.custom.LessonsDAO;
import com.example.orm_elite_driving_school_system.dao.custom.QueryDAO;
import com.example.orm_elite_driving_school_system.dto.LessonsDTO;
import com.example.orm_elite_driving_school_system.entity.Lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonsBOImpl implements LessonsBO {

    private LessonsDAO lessonsDAO = (LessonsDAO) DAOFactory.getInstance().getDAO(DAOTypes.LESSONS);
    private CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private InstructorsDAO instructorsDAO = (InstructorsDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final EntityDTOConverter converter = new EntityDTOConverter();


    @Override
    public List<LessonsDTO> getAllLessons() throws Exception {
        List<Lessons> lessons = lessonsDAO.getAll();
        List<LessonsDTO> lessonsDTOS = new ArrayList<>();
        for (Lessons lesson : lessons) {
            lessonsDTOS.add(converter.getLessonsDTO(lesson));
        }
        return lessonsDTOS;
    }

    @Override
    public String getLastLessonId() throws Exception {
        return lessonsDAO.getLastId();
    }

    @Override
    public boolean saveLessons(LessonsDTO t) throws Exception {
        // Check if course exists
        boolean courseExists = courseDAO.findById(t.getCourse_id()).isPresent();
        // Check if instructor exists
        boolean instructorExists = instructorsDAO.findById(t.getInstructor_id()).isPresent();

        if (courseExists && instructorExists) {
            return lessonsDAO.save(converter.getLessonsEntity(t));
        }
        throw new NotFoundException("Course or Instructor not found");
    }

    @Override
    public boolean updateLessons(LessonsDTO t) throws Exception {
        Optional<Lessons> lesson = lessonsDAO.findById(t.getLesson_id());
        if (lesson.isEmpty()) {
            throw new NotFoundException("Lesson not found");
        }
        return lessonsDAO.update(converter.getLessonsEntity(t));
    }

    @Override
    public boolean deleteLessons(String id) throws Exception {
        Optional<Lessons> lesson = lessonsDAO.findById(id);
        if (lesson.isEmpty()) {
            throw new NotFoundException("Lesson not found");
        }

        int studentsEnrolled = queryDAO.getStudentCountForLesson(id);
        if (studentsEnrolled > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Students are enrolled in this lesson. Are you sure you want to delete?",
                    ButtonType.YES,
                    ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (result != ButtonType.YES) {
                return false; // User cancelled deletion
            }
        }

        return lessonsDAO.delete(id);
    }

    @Override
    public List<String> getAllLessonIds() throws Exception {
        return lessonsDAO.getAllIds();
    }

    @Override
    public Optional<LessonsDTO> findByLessonId(String id) throws Exception {
        Optional<Lessons> lesson = lessonsDAO.findById(id);
        if (lesson.isPresent()) {
            return Optional.of(converter.getLessonsDTO(lesson.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewLessonId() {
        return lessonsDAO.generateNewId();
    }
}
