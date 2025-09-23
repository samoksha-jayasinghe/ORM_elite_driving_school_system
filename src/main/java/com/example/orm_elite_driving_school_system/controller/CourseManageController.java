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
import com.example.orm_elite_driving_school_system.bo.custom.CourseBO;
import com.example.orm_elite_driving_school_system.dto.tm.CourseTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseManageController implements Initializable {

    public TableView<CourseTM> tblCourse;
    public TableColumn<CourseTM, String> colCourseId;
    public TableColumn<CourseTM, String> colCourseName;
    public TableColumn<CourseTM, String> colDuration;
    public TableColumn<CourseTM, String> colFee;
    public TableColumn<CourseTM, String> colDescription;
    public TableColumn<CourseTM, String> colInstructorId;

    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddCoursePopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Course");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this course?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Course");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a course to delete!").show();
                return;
            }

            try {
                boolean isDeleted = courseBO.deleteCourses(selectedItem.getCourse_id());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully!").show();
                    loadAllCourses();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the course!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a course to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddCoursePopUp.fxml"));
                Parent parent = fxmlLoader.load();

                CoursePopUpController controller = fxmlLoader.getController();
                controller.setCourseData(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Update Course");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
                stage.showAndWait();

                loadAllCourses();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));

        try {
            loadAllCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCourses() {
        try {
            tblCourse.setItems(FXCollections.observableArrayList(
                    courseBO.getAllCourses().stream().map(
                            dto -> new CourseTM(
                                    dto.getCourse_id(),
                                    dto.getCourse_name(),
                                    dto.getDuration(),
                                    dto.getFee(),
                                    dto.getDescription(),
                                    dto.getInstructor_id()
                            )).toList()
            ));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
