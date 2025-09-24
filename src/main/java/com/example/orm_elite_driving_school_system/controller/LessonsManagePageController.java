package com.example.orm_elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.LessonsBO;
import com.example.orm_elite_driving_school_system.dto.tm.LessonsTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LessonsManagePageController implements Initializable {

    private final LessonsBO lessonsBO = (LessonsBO) BOFactory.getInstance().getBO(BOTypes.LESSONS);

    public TableView<LessonsTM> tblLessons;
    public TableColumn<LessonsTM,String> colId;
    public TableColumn<LessonsTM,String> colLessonDate;
    public TableColumn<LessonsTM,String> colStartTime;
    public TableColumn<LessonsTM,String> colEndTime;
    public TableColumn<LessonsTM,String> colStatus;
    public TableColumn<LessonsTM,String> colStudentId;
    public TableColumn<LessonsTM,String> colCourseId;
    public TableColumn<LessonsTM,String> colInstructorId;

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddLessonsPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Lessons");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }


    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            LessonsTM selectedItem = tblLessons.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a lessons to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddLessonsPopUp.fxml"));
                Parent parent = fxmlLoader.load();

                LessonsPopUpController controller = fxmlLoader.getController();
                controller.setLessonsData(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Update Lessons");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
                stage.showAndWait();

                // Refresh the table after the popup is closed
                loadAllLessons();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("lesson_id"));
        colLessonDate.setCellValueFactory(new PropertyValueFactory<>("lessonDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));

        try {
            loadAllLessons();
        } catch (Exception e) {
        }
    }

    private void loadAllLessons() {
        try {
            tblLessons.setItems(FXCollections.observableArrayList(
                    lessonsBO.getAllLessons().stream().map(lessonsDTO -> new LessonsTM(
                            lessonsDTO.getLesson_id(),
                            lessonsDTO.getStudent_id(),
                            lessonsDTO.getCourse_id(),
                            lessonsDTO.getInstructor_id(),
                            lessonsDTO.getLessonDate(),
                            lessonsDTO.getStartTime(),
                            lessonsDTO.getEndTime(),
                            lessonsDTO.getStatus()
                    )).toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this lesson?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Lessons");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            LessonsTM selectedItem = tblLessons.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a lesson to delete!").show();
                return;
            }

            try {
                boolean isDeleted = lessonsBO.deleteLessons(selectedItem.getLesson_id());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Lesson deleted successfully!").show();
                    loadAllLessons();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the lessons!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
