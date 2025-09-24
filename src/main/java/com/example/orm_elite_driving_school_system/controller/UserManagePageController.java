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
import com.example.orm_elite_driving_school_system.bo.custom.UserBO;
import com.example.orm_elite_driving_school_system.dto.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagePageController implements Initializable {


    public TableView<UserTM> tblUser;
    public TableColumn<UserTM, String> colId;
    public TableColumn<UserTM, String> colUsername;
    public TableColumn<UserTM, String> colPassword;
    public TableColumn<UserTM, String> colRole;
    public TableColumn<UserTM, String> colEmail;
    public TableColumn<UserTM, String> colStatus;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllUsers() {
        try {
            tblUser.setItems(FXCollections.observableArrayList(
                    userBO.getAllUsers().stream().map(user -> new UserTM(
                            user.getUserId(),
                            user.getUserName(),
                            user.getPassword(),
                            user.getRole(),
                            user.getEmail(),
                            user.getStatus()
                    )).toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddUserPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Users Manually");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
            stage.showAndWait();
            loadAllUsers();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this user?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete User");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a user to delete!").show();
                return;
            }

            try {
                boolean isDeleted = userBO.deleteUsers(selectedItem.getUserId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
                    loadAllUsers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the user!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a user to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddUserPopUp.fxml"));
                Parent parent = fxmlLoader.load();

                // Get popup controller and send data
                UserPopUpController controller = fxmlLoader.getController();
                controller.setUserData(selectedItem); // ✅ custom method to load data

                Stage stage = new Stage();
                stage.setTitle("Update User");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                // Refresh table after update
                loadAllUsers();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }
}
