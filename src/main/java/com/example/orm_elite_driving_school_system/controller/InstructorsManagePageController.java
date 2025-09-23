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
import com.example.orm_elite_driving_school_system.bo.custom.InstructorsBO;
import com.example.orm_elite_driving_school_system.dto.tm.InstructorsTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class InstructorsManagePageController implements Initializable {
    public TableView tblInstructors;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colSpecialization;
    public TableColumn colAvailability;

    private final InstructorsBO instructorsBO = (InstructorsBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTORS);

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddInstructorsPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Instructor");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
            stage.showAndWait();
            loadAllInstructors();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this instructor?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Instructor");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            InstructorsTM selectedItem = (InstructorsTM) tblInstructors.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a instructor to delete!").show();
                return;
            }

            try {
                boolean isDeleted = instructorsBO.deleteInstructors(selectedItem.getInstructor_id());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Instructor deleted successfully!").show();
                    loadAllInstructors();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the instructors!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            InstructorsTM selectedItem = (InstructorsTM) tblInstructors.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a instructor to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddInstructorsPopUp.fxml"));
                Parent parent = fxmlLoader.load();

                InstructorsPopUpController controller = fxmlLoader.getController();
                controller.setInstructorsData(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Update Instructor");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
                stage.showAndWait();
                loadAllInstructors();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        try {
            loadAllInstructors();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void loadAllInstructors() {
        try {
            tblInstructors.setItems(FXCollections.observableArrayList(
                     instructorsBO.getAllInstructors().stream().map(instructor -> new InstructorsTM(
                             instructor.getInstructor_id(),
                             instructor.getFirst_name(),
                             instructor.getLast_name(),
                             instructor.getEmail(),
                             instructor.getPhone(),
                             instructor.getSpecialization(),
                             instructor.getAvailability()
                     )).toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
