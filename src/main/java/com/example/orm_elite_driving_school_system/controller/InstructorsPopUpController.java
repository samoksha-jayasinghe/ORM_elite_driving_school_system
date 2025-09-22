package com.example.orm_elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.orm_elite_driving_school_system.bo.BOFactory;
import com.example.orm_elite_driving_school_system.bo.BOTypes;
import com.example.orm_elite_driving_school_system.bo.custom.InstructorsBO;
import com.example.orm_elite_driving_school_system.dto.InstructorsDTO;
import com.example.orm_elite_driving_school_system.dto.tm.InstructorsTM;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructorsPopUpController  implements Initializable {

    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtSpecialization;
    public TextField txtAvailability;
    public Button btnSave;
    public Button btnUpdate;
    public Label lblInstructorId;

    private final InstructorsBO instructorsBO = (InstructorsBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTORS);


    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String phoneRegex = "^07\\d{8}$";

    public void btnSaveOncAction(ActionEvent actionEvent) {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String specialization = txtSpecialization.getText();
        String availability = txtAvailability.getText();

        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = contact.matches(phoneRegex);

        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contact.isEmpty() || specialization.isEmpty() || availability.isEmpty()) {
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
            boolean isSaved = instructorsBO.saveInstructors(InstructorsDTO.builder()
                    .instructor_id(lblInstructorId.getText())
                    .first_name(firstName)
                    .last_name(lastName)
                    .email(email)
                    .phone(contact)
                    .specialization(specialization)
                    .availability(availability)
                    .build());
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor saved successfully", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save instructor", ButtonType.OK).show();
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
        String specialization = txtSpecialization.getText();
        String availability = txtAvailability.getText();

        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = contact.matches(phoneRegex);

        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contact.isEmpty() || specialization.isEmpty() || availability.isEmpty()) {
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
            boolean isUpdated = instructorsBO.updateInstructors(InstructorsDTO.builder()
                    .instructor_id(lblInstructorId.getText())
                    .first_name(firstName)
                    .last_name(lastName)
                    .email(email)
                    .phone(contact)
                    .specialization(specialization)
                    .availability(availability)
                    .build());
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor updated successfully", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update instructor", ButtonType.OK).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblInstructorId.setText(instructorsBO.generateNewInstructorsId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setInstructorsData(InstructorsTM selectedItem) {
        lblInstructorId.setText(selectedItem.getInstructor_id());
        txtFirstName.setText(selectedItem.getFirst_name());
        txtLastName.setText(selectedItem.getLast_name());
        txtEmail.setText(selectedItem.getEmail());
        txtContact.setText(selectedItem.getPhone());
        txtSpecialization.setText(selectedItem.getSpecialization());
        txtAvailability.setText(selectedItem.getAvailability());
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }
}
