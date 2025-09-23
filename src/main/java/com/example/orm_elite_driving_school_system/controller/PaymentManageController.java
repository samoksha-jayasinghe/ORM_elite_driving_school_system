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
import com.example.orm_elite_driving_school_system.bo.custom.PaymentsBO;
import com.example.orm_elite_driving_school_system.dto.tm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentManageController implements Initializable {
    public TableView<PaymentTM> tblPayment;
    public TableColumn<PaymentTM, String> colPaymentId;
    public TableColumn<PaymentTM, String> colPaymentDate;
    public TableColumn<PaymentTM, String> colAmount;
    public TableColumn<PaymentTM, String> colPaymentMethod;
    public TableColumn<PaymentTM, String> colStatus;
    public TableColumn<PaymentTM, String> colStudentId;
    
    private final PaymentsBO paymentsBO = (PaymentsBO) BOFactory.getInstance().getBO(BOTypes.PAYMENTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        
        try {
            loadAllPayments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllPayments() {

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddPaymentPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Payment");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
        }
    }

    public void btnDeleteOnAAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this payment?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Payment");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            PaymentTM selectedItem = tblPayment.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a payment to delete!").show();
                return;
            }

            try {
                boolean isDeleted = paymentsBO.deletePayments(selectedItem.getPaymentId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully!").show();
                    loadAllPayments();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the payment!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            PaymentTM selectedItem = tblPayment.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a payment to update!").show();
                return;
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/view/AddPaymentPopUp.fxml"));
                Parent parent = fxmlLoader.load();

                PaymentPopUpController controller = fxmlLoader.getController();
                controller.setPaymentData(selectedItem);

                Stage stage = new Stage();
                stage.setTitle("Update Payment");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
                stage.showAndWait();

                // Refresh the table after the popup is closed
                loadAllPayments();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to open the popup!").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
