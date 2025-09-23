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
import com.example.orm_elite_driving_school_system.bo.custom.StudentsBO;
import com.example.orm_elite_driving_school_system.dto.CourseDTO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;
import com.example.orm_elite_driving_school_system.dto.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StudentManagePageController implements Initializable {
    public TableView<StudentTM> tblStudent;
    public TableColumn<StudentTM, String> colId;
    public TableColumn<StudentTM, String> colFirstName;
    public TableColumn<StudentTM, String> colLastName;
    public TableColumn<StudentTM, String> colEmail;
    public TableColumn<StudentTM, String> colContact;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, String> colDOB;
    public TableColumn<StudentTM, String> colRegDate;
    public TableColumn<StudentTM, String> colEnrolledCourses;

    private final StudentsBO studentsBO = (StudentsBO) BOFactory.getInstance().getBO(BOTypes.STUDENTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colEnrolledCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));

        loadAllStudents();
    }

    private StudentTM toStudentTM(StudentsDTO dto) {
        String courseIds = (dto.getCourses() == null || dto.getCourses().isEmpty()) ? "Not Found" :
                String.join(", ", dto.getCourses().stream()
                        .map(CourseDTO::getCourse_id)
                        .toList());
        System.out.println(dto);

        return new StudentTM(
                dto.getStudentId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getDob(),
                dto.getRegistrationDate(),
                courseIds
        );
    }


    private void loadAllStudents() {
        try {
            List<StudentsDTO> students = studentsBO.getAllStudents();
            List<StudentTM> studentTMs = students.stream()
                    .map(this::toStudentTM)
                    .collect(Collectors.toList());
            tblStudent.setItems(FXCollections.observableArrayList(studentTMs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddStudentPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadAllStudents();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }

    public void btnDeleteOnAAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this student?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Student");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a student to delete!").show();
                return;
            }

            try {
                boolean isDeleted = studentsBO.deleteStudents(selectedItem.getStudentId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully!").show();
                    loadAllStudents();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the student!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a student to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddStudentPopUp.fxml"));
                Parent parent = fxmlLoader.load();

                StudentPopUpController controller = fxmlLoader.getController();
                controller.setStudentData(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Update Student");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                loadAllStudents();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
