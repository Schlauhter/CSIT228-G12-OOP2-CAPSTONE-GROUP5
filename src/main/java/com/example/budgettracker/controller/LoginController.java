package com.example.budgettracker.controller;

import com.example.budgettracker.dao.UserDAO;
import com.example.budgettracker.model.User;
import com.example.budgettracker.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField     txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label         lblError;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Username and password cannot be empty.");
            return;
        }

        try {
            User user = userDAO.loginUser(username, password);
            if (user != null) {
                Session.setCurrentUser(user);
                switchScene("/com/example/budgettracker/fxml/Dashboard.fxml", 500, 400);
            } else {
                lblError.setText("Invalid username or password.");
            }
        } catch (Exception ex) {
            lblError.setText("DB Error - Is XAMPP MySQL running?");
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToSignUp() {
        try {
            switchScene("/com/example/budgettracker/fxml/SignUp.fxml", 450, 420);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void switchScene(String fxml, int width, int height) throws Exception {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        stage.setScene(new Scene(loader.load(), width, height));
        stage.show();
    }
}
