package com.example.orm_elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Method connected to getStartedButton
    @FXML
    private void btnstarted(ActionEvent event) {
        try {
            // Load the next page (replace with your FXML file name)
            root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));

            // Get the current stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene with the new page
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page"); // optional
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}