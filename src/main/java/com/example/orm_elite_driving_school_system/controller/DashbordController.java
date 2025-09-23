package com.example.orm_elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DashbordController {
    public Button btnCourse;
    public Button btnUser;
    public StackPane stackPaneComponent;
    public AnchorPane ancDashboard;

    public void btnStudentOnAction(ActionEvent mouseEvent) {
        navigateTo("/view/StudentManagePage2.fxml");
    }

    public void btnInstructorOnAction(ActionEvent actionEvent) {
        navigateTo("/view/InstructorManage.fxml");
        backDashboard("Instructor Management");
    }

    private void backDashboard(String instructorManagement) {
//        navigateTo("/view");
    }

    void navigateTo(String path) {
        try {
            ancDashboard.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashboard.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashboard.heightProperty());

            ancDashboard.getChildren().add(anchorPane);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnCourseOnAction(ActionEvent actionEvent) {
        navigateTo("/view/CourseManagePage.fxml");
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
        navigateTo("/view/UserManage.fxml");
    }
}
