package com.example.orm_elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.CourseBO;
import com.example.orm_elite_driving_school_system.bo.custom.InstructorsBO;
import com.example.orm_elite_driving_school_system.dto.CourseDTO;
import com.example.orm_elite_driving_school_system.dto.tm.CourseTM;

import java.net.URL;
import java.util.ResourceBundle;

public class CoursePopUpController implements Initializable {

    public ComboBox cmbInstructorId;
    public TextField txtDescription;
    public TextField txtCourseFee;
    public TextField txtDuration;
    public TextField txtCourseName;
    public Button btnSave;
    public Button btnUpdate;
    public Label lblCourseId;

    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);
    private final InstructorsBO instructorsBO = (InstructorsBO)  BOFactory.getInstance().getBO(BOTypes.INSTRUCTORS);


    private boolean validateCourseInputs() {

        if (txtCourseName.getText().isEmpty() || txtDescription.getText().isEmpty() ||
                txtCourseFee.getText().isEmpty() || txtDuration.getText().isEmpty() ||
                cmbInstructorId.getValue() == null) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return false;
        }
        return true;
    }

    @FXML
    private void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateCourseInputs())
            return;

        try {
            String courseId = lblCourseId.getText();
            String courseName = txtCourseName.getText();
            String description = txtDescription.getText();
            double courseFee = Double.parseDouble(txtCourseFee.getText());
            String duration = txtDuration.getText();
            String instructorId = cmbInstructorId.getValue().toString();


            boolean isSaved = courseBO.saveCourses(CourseDTO.builder()
                    .course_id(courseId)
                    .course_name(courseName)
                    .description(description)
                    .fee(courseFee)
                    .duration(duration)
                    .instructor_id(instructorId)
                    .build());


            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Course saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save course").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblCourseId.setText(courseBO.generateNewCourseId());
            cmbInstructorId.setItems(FXCollections.observableArrayList(instructorsBO.getAllInstructorIds()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (!validateCourseInputs()) return;

        try {
            String courseId = lblCourseId.getText();
            String courseName = txtCourseName.getText();
            String description = txtDescription.getText();
            double courseFee = Double.parseDouble(txtCourseFee.getText());
            String duration = txtDuration.getText();
            String instructorId = cmbInstructorId.getValue().toString();

            boolean isUpdated = courseBO.updateCourses(CourseDTO.builder()
                            .course_id(courseId)
                            .course_name(courseName)
                            .description(description)
                            .fee(courseFee)
                            .duration(duration)
                            .instructor_id(instructorId)
                            .build());

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update course").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setCourseData(CourseTM selectedItem) {
        lblCourseId.setText(selectedItem.getCourse_id());
        txtCourseName.setText(selectedItem.getCourse_name());
        txtDescription.setText(selectedItem.getDescription());
        txtCourseFee.setText(String.valueOf(selectedItem.getFee()));
        txtDuration.setText(selectedItem.getDuration());
        cmbInstructorId.setValue(selectedItem.getInstructor_id());
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }
}
