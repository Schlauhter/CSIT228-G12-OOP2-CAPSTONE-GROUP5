package com.example.budgettracker.controller;

import com.example.budgettracker.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Label lblWelcome;

    @FXML
    public void initialize() {
        String user = Session.getCurrentUser() != null
                ? Session.getCurrentUser().getUsername() : "User";
        lblWelcome.setText("Welcome, " + user + "!");
    }

    @FXML
    private void openBudget() {
        openWindow("/com/example/budgettracker/fxml/Budget.fxml", "Budget Settings", 450, 400);
    }

    @FXML
    private void openExpenses() {
        openWindow("/com/example/budgettracker/fxml/Expense.fxml", "Expense Manager", 780, 560);
    }

    @FXML
    private void openReport() {
        openWindow("/com/example/budgettracker/fxml/Report.fxml", "Reports", 650, 560);
    }

    @FXML
    private void handleLogout() {
        try {
            Session.logout();
            Stage stage = (Stage) lblWelcome.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/budgettracker/fxml/Login.fxml"));
            stage.setScene(new Scene(loader.load(), 440, 340));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openWindow(String fxml, String title, int width, int height) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/com/example/budgettracker/fxml/budget.png")));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            stage.setScene(new Scene(loader.load(), width, height));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
