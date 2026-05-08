package com.example.budgettracker.controller;

import com.example.budgettracker.dao.UserDAO;
import com.example.budgettracker.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {

    @FXML private TextField     txtUsername;
    @FXML private TextField     txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Label         lblMessage;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String email    = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        String confirm  = txtConfirmPassword.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showMsg("All fields are required.", "red"); return;
        }
        if (username.length() < 3) {
            showMsg("Username must be at least 3 characters.", "red"); return;
        }
        if (!email.contains("@")) {
            showMsg("Please enter a valid email address.", "red"); return;
        }
        if (password.length() < 6) {
            showMsg("Password must be at least 6 characters.", "red"); return;
        }
        if (!password.equals(confirm)) {
            showMsg("Passwords do not match.", "red"); return;
        }

        try {
            if (userDAO.usernameExists(username)) {
                showMsg("Username already taken. Choose another.", "red"); return;
            }
            boolean ok = userDAO.registerUser(new User(0, username, password, email));
            if (ok) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Account created! Please log in.", ButtonType.OK);
                alert.showAndWait();
                goToLogin();
            } else {
                showMsg("Registration failed. Try again.", "red");
            }
        } catch (Exception ex) {
            showMsg("DB Error: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToLogin() {
        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/budgettracker/fxml/Login.fxml"));
            stage.setScene(new Scene(loader.load(), 440, 340));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showMsg(String msg, String color) {
        lblMessage.setText(msg);
        lblMessage.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 11;");
    }
}
