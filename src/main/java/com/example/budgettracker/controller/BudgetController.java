package com.example.budgettracker.controller;

import com.example.budgettracker.dao.BudgetDAO;
import com.example.budgettracker.model.Budget;
import com.example.budgettracker.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetController {

    @FXML private TextField        txtAmount;
    @FXML private ComboBox<String> cboPeriod;
    @FXML private Label            lblStatus;
    @FXML private Label            lblCurrentBudget;
    @FXML private Label            lblCurrentPeriod;

    private final BudgetDAO budgetDAO = new BudgetDAO();

    @FXML
    public void initialize() {
        cboPeriod.getItems().addAll("Daily", "Weekly", "Monthly");
        cboPeriod.setValue("Monthly");
        loadSavedBudget();
    }

    @FXML
    private void saveBudget() {
        String amountStr = txtAmount.getText().trim();
        String period    = cboPeriod.getValue();

        if (amountStr.isEmpty()) {
            showMsg("Please enter a budget amount.", "red"); return;
        }
        try {
            BigDecimal amount = new BigDecimal(amountStr);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                showMsg("Amount must be greater than zero.", "red"); return;
            }
            Budget budget = new Budget(Session.getCurrentUserId(), amount, period, LocalDate.now());
            boolean ok = budgetDAO.saveBudget(budget);
            if (ok) {
                showMsg("Budget saved successfully!", "green");
                loadSavedBudget();
            } else {
                showMsg("Failed to save budget.", "red");
            }
        } catch (NumberFormatException ex) {
            showMsg("Invalid amount. Enter a valid number.", "red");
        } catch (Exception ex) {
            showMsg("DB Error: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }

    private void loadSavedBudget() {
        try {
            Budget b = budgetDAO.getLatestBudget(Session.getCurrentUserId());
            if (b != null) {
                lblCurrentBudget.setText(String.format("Budget Amount: PHP %.2f", b.getAmount()));
                lblCurrentPeriod.setText("Period: " + b.getPeriodType());
            } else {
                lblCurrentBudget.setText("Budget Amount: Not set yet");
                lblCurrentPeriod.setText("Period: —");
            }
        } catch (Exception ex) {
            lblCurrentBudget.setText("Error loading budget.");
        }
    }

    @FXML
    private void goBack() {
        ((Stage) txtAmount.getScene().getWindow()).close();
    }

    private void showMsg(String msg, String color) {
        lblStatus.setText(msg);
        lblStatus.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 11;");
    }
}
