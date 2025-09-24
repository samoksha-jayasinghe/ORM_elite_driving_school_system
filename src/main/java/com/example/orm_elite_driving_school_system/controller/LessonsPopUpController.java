package com.example.orm_elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.CourseBO;
import com.example.orm_elite_driving_school_system.bo.custom.InstructorsBO;
import com.example.orm_elite_driving_school_system.bo.custom.LessonsBO;
import com.example.orm_elite_driving_school_system.bo.custom.StudentsBO;
import com.example.orm_elite_driving_school_system.dto.LessonsDTO;
import com.example.orm_elite_driving_school_system.dto.tm.LessonsTM;

import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class LessonsPopUpController implements Initializable {

    private final LessonsBO lessonsBO = (LessonsBO) BOFactory.getInstance().getBO(BOTypes.LESSONS);
    private final StudentsBO studentBO = (StudentsBO) BOFactory.getInstance().getBO(BOTypes.STUDENTS);
    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);
    private final InstructorsBO instructorsBO = (InstructorsBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTORS);

    public TextField txtLessonDate;
    public TextField txtStartTime;
    public TextField txtEndTime;
    public TextField txtStatus;
    public ComboBox cmbStudentId;
    public ComboBox cmbCourseId;
    public ComboBox cmbInstructorId;
    public Button btnSave;
    public Button btnUpdate;
    public Label lblLessonId;


    public void btnSaveOncAction(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String lessonDateStr = txtLessonDate.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = txtStatus.getText();
        String studentId = cmbStudentId.getValue() != null ? cmbStudentId.getValue().toString() : "";
        String courseId = cmbCourseId.getValue() != null ? cmbCourseId.getValue().toString() : "";
        String instructorId = cmbInstructorId.getValue() != null ? cmbInstructorId.getValue().toString() : "";

        if (lessonId.isEmpty() || lessonDateStr.isEmpty() || startTimeStr.isEmpty() || endTimeStr.isEmpty() ||
                status.isEmpty() || studentId.isEmpty() || courseId.isEmpty() || instructorId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            Date lessonDate = dateFormat.parse(lessonDateStr);



            boolean isSaved = lessonsBO.saveLessons(new LessonsDTO(
                    lessonId, studentId, courseId, instructorId, lessonDate, startTimeStr, endTimeStr, status
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save lesson!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String lessonDateStr = txtLessonDate.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = txtStatus.getText();
        String studentId = cmbStudentId.getValue() != null ? cmbStudentId.getValue().toString() : "";
        String courseId = cmbCourseId.getValue() != null ? cmbCourseId.getValue().toString() : "";
        String instructorId = cmbInstructorId.getValue() != null ? cmbInstructorId.getValue().toString() : "";

        if (lessonId.isEmpty() || lessonDateStr.isEmpty() || startTimeStr.isEmpty() || endTimeStr.isEmpty() ||
                status.isEmpty() || studentId.isEmpty() || courseId.isEmpty() || instructorId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            Date lessonDate = dateFormat.parse(lessonDateStr);
            Date startTime = timeFormat.parse(startTimeStr);
            Date endTime = timeFormat.parse(endTimeStr);

            boolean isUpdated = lessonsBO.updateLessons(new LessonsDTO(
                    lessonId, studentId, courseId, instructorId, lessonDate, startTimeStr, endTimeStr , status
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update lesson!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblLessonId.setText(lessonsBO.generateNewLessonId());
            cmbStudentId.setItems(FXCollections.observableArrayList(studentBO.getAllStudentIds()));
            cmbCourseId.setItems(FXCollections.observableArrayList(courseBO.getAllCourseIds()));
            cmbInstructorId.setItems(FXCollections.observableArrayList(instructorsBO.getAllInstructorIds()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setLessonsData(LessonsTM selectedItem) {
        lblLessonId.setText(selectedItem.getLesson_id());
        txtLessonDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedItem.getLessonDate()));
        txtStartTime.setText(selectedItem.getStartTime().toString());
        txtEndTime.setText(selectedItem.getEndTime().toString());
        txtStatus.setText(selectedItem.getStatus());
        cmbStudentId.setValue(selectedItem.getStudent_id());
        cmbCourseId.setValue(selectedItem.getCourse_id());
        cmbInstructorId.setValue(selectedItem.getInstructor_id());
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }
}
