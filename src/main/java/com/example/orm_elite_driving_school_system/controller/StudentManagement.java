// DashboardController.java
package com.example.orm_elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentManagement {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAge;
    public TextField txtNic;
    public TextField txtContact;
    public TableView tblStudents;
    public TableColumn colId;
    public TableColumn colAge;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colContact;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        setUI("StudentPage");
    }

    @FXML
    void btnInstructorOnAction(ActionEvent event) throws IOException {
        setUI("InstructorPage");
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        setUI("VehiclePage");
    }

    @FXML
    void btnScheduleOnAction(ActionEvent event) throws IOException {
        setUI("SchedulePage");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        setUI("PaymentPage");
    }

    private void setUI(String fileName) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/" + fileName + ".fxml"));
        rootPane.getChildren().clear();
        rootPane.getChildren().add(load);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
