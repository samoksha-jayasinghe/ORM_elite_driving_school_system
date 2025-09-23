package com.example.orm_elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.CourseBO;
import com.example.orm_elite_driving_school_system.bo.custom.EnrollBO;
import com.example.orm_elite_driving_school_system.bo.custom.StudentsBO;
import com.example.orm_elite_driving_school_system.dto.CourseDTO;
import com.example.orm_elite_driving_school_system.dto.StudentsDTO;
import com.example.orm_elite_driving_school_system.dto.tm.StudentTM;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentPopUpController implements Initializable {

    private final StudentsBO studentsBO = (StudentsBO) BOFactory.getInstance().getBO(BOTypes.STUDENTS);
    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);
    private final EnrollBO enrollBO = (EnrollBO) BOFactory.getInstance().getBO(BOTypes.ENROLL);

    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String phoneRegex = "^07\\d{8}$";


    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtDOB;
    public TextField txtRegDate;
    public Button btnSave;
    public Button btnUpdate;
    public Label lblStudentId;
    public ListView listViewCourses;

    public void btnSaveOncAction(ActionEvent actionEvent) {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String dob = txtDOB.getText();
        String regDate = txtRegDate.getText();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = null;
        Date regDateDate = null;
        try {
            dobDate = sdf.parse(dob);
            regDateDate = sdf.parse(regDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = contact.matches(phoneRegex);

        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || dob.isEmpty() || regDate.isEmpty()) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format", ButtonType.OK).show();
            return;
        }
        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number format", ButtonType.OK).show();
            return;
        }
        ArrayList<CourseDTO> selectedCourses = new ArrayList<>(listViewCourses.getSelectionModel().getSelectedItems());

        try {
            boolean isSaved = enrollBO.saveStudents(StudentsDTO.builder()
                    .studentId(lblStudentId.getText())
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(contact)
                    .address(address)
                    .dob(dobDate)
                    .registrationDate(regDateDate)
                    .courses(selectedCourses)
                    .build());
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save student", ButtonType.OK).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String dob = txtDOB.getText();
        String regDate = txtRegDate.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = null;
        Date regDateDate = null;
        try {
            dobDate = sdf.parse(dob);
            regDateDate = sdf.parse(regDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = contact.matches(phoneRegex);

        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || dob.isEmpty() || regDate.isEmpty()) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format", ButtonType.OK).show();
            return;
        }
        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number format", ButtonType.OK).show();
            return;
        }
        try {

            ArrayList<CourseDTO> selectedCourses = new ArrayList<>(listViewCourses.getSelectionModel().getSelectedItems());

            boolean isUpdated = enrollBO.updateStudents(StudentsDTO.builder()
                    .studentId(lblStudentId.getText())
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(contact)
                    .address(address)
                    .dob(dobDate)
                    .registrationDate(regDateDate)
                    .courses(selectedCourses)
                    .build());
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update student", ButtonType.OK).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listViewCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lblStudentId.setText(studentsBO.generateNewStudentId());
            listViewCourses.setItems(FXCollections.observableArrayList(courseBO.getAllCourses()
            ));
            listViewCourses.setCellFactory(lv -> new ListCell<CourseDTO>() {
                @Override
                protected void updateItem(CourseDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText((empty || item == null) ? null : item.getCourse_name());
}
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setStudentData(StudentTM student) {
        lblStudentId.setText(student.getStudentId());
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        txtEmail.setText(student.getEmail());
        txtContact.setText(student.getPhone());
        txtAddress.setText(student.getAddress());
        txtDOB.setText(String.valueOf(student.getDob()));
        txtRegDate.setText(String.valueOf(student.getRegistrationDate()));
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }

}
