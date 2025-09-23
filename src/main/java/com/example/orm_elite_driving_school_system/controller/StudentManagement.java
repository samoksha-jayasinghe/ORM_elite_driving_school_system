// DashboardController.java
package com.example.orm_elite_driving_school_system.controller;

import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.StudentsBO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;
import com.example.orm_elite_driving_school_system.dto.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManagement {

    public TableColumn colFirstName;
    public TableView tblStudents;
    StudentsBO studentsBO = (StudentsBO) BOFactory.getInstance().getBO(BOTypes.STUDENTS);
    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddStudentPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

//            loadAllStudents();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }
//
//    private void loadAllStudents() {
//        try {
//            List<StudentsDTO> students = studentsBO.getAllStudents();
//            List<StudentTM> studentTMs = students.stream()
//                    .map(this::toStudentTM)
//                    .collect(Collectors.toList());
//            tblStudents.setItems(FXCollections.observableArrayList(studentTMs));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//

    public void onClickTable(MouseEvent mouseEvent) {
    }
}
