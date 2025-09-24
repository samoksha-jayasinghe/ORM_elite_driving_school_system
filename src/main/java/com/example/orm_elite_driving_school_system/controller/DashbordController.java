package com.example.orm_elite_driving_school_system.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class DashbordController {
    public Button btnCourse;
    public Button btnUser;
    public StackPane stackPaneComponent;
    public AnchorPane ancDashboard;
    public Button btnPayment;
    public Button btnLesson;
    public ImageView logoImageView;
    public HBox headerHBox;

//    private AnchorPane ancDashboard;

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
    public void initialize() {
        ImageView car = new ImageView(new Image(getClass().getResource("/images/image.png").toExternalForm()));
        car.setFitWidth(500);
        car.setFitHeight(500);
        car.setLayoutY(380); // vertical position

        ancDashboard.getChildren().add(car);

        TranslateTransition drive = new TranslateTransition(Duration.seconds(5), car);
        drive.setFromX(-300);
        drive.setToX(ancDashboard.getPrefWidth());
        drive.setCycleCount(TranslateTransition.INDEFINITE);
        drive.setAutoReverse(true);
        drive.play();
    }

    public void btnCourseOnAction(ActionEvent actionEvent) {

        navigateTo("/view/CourseManagePage.fxml");
    }

    public void btnUserOnAction(ActionEvent actionEvent) {

        navigateTo("/view/UserManage.fxml");
    }

    public void btnLessonOnAction(ActionEvent actionEvent) {
        navigateTo("/view/LessonsManage.fxml");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PaymentManage.fxml");
    }

    public void onClick(MouseEvent mouseEvent) {
        navigateTo("/view/Dashbord.fxml");
    }
}
