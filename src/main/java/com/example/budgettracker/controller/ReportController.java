package com.example.budgettracker.controller;

import com.example.budgettracker.dao.BudgetDAO;
import com.example.budgettracker.dao.ExpenseDAO;
import com.example.budgettracker.util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;

public class ReportController {

    @FXML private Label            lblTotalBudget;
    @FXML private Label            lblTotalExpenses;
    @FXML private Label            lblRemaining;
    @FXML private Label            lblAlert;
    @FXML private ComboBox<String> cboBreakdown;
    @FXML private TableView<Object[]>         tableBreakdown;
    @FXML private TableColumn<Object[], String> colPeriod;
    @FXML private TableColumn<Object[], String> colTotal;

    private final BudgetDAO  budgetDAO  = new BudgetDAO();
    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @FXML
    public void initialize() {
        cboBreakdown.getItems().addAll("Daily", "Weekly", "Monthly");
        cboBreakdown.setValue("Monthly");

        colPeriod.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty((String) data.getValue()[0]));
        colTotal.setCellValueFactory(data  -> new javafx.beans.property.SimpleStringProperty(String.format("%.2f", data.getValue()[1])));

        loadSummary();
        loadBreakdown();
    }

    private void loadSummary() {
        int userId = Session.getCurrentUserId();
        BigDecimal budget    = budgetDAO.getBudgetAmount(userId);
        BigDecimal expenses  = expenseDAO.getTotalExpenses(userId);
        BigDecimal remaining = budget.subtract(expenses);

        lblTotalBudget.setText(  String.format("Total Budget:   PHP %.2f", budget));
        lblTotalExpenses.setText(String.format("Total Expenses: PHP %.2f", expenses));
        lblRemaining.setText(    String.format("Remaining:      PHP %.2f", remaining));

        if (budget.compareTo(BigDecimal.ZERO) > 0) {
            double ratio = expenses.doubleValue() / budget.doubleValue();
            if (ratio >= 1.0) {
                lblAlert.setText("ALERT: You have exceeded your budget!");
                lblAlert.setStyle("-fx-text-fill: #F44336; -fx-font-weight: bold;");
            } else if (ratio >= 0.8) {
                lblAlert.setText("WARNING: You have used over 80% of your budget.");
                lblAlert.setStyle("-fx-text-fill: #FF9800; -fx-font-weight: bold;");
            } else {
                lblAlert.setText("You are within your budget.");
                lblAlert.setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");
            }
        } else {
            lblAlert.setText("No budget set. Please set a budget first.");
            lblAlert.setStyle("-fx-text-fill: gray;");
        }
    }

    @FXML
    private void loadBreakdown() {
        int userId = Session.getCurrentUserId();
        String period = cboBreakdown.getValue();
        List<Object[]> data;

        switch (period) {
            case "Daily":  data = expenseDAO.getDailyBreakdown(userId);   break;
            case "Weekly": data = expenseDAO.getWeeklyBreakdown(userId);  break;
            default:       data = expenseDAO.getMonthlyBreakdown(userId); break;
        }

        if (data.isEmpty()) data.add(new Object[]{"No data found", BigDecimal.ZERO});
        tableBreakdown.setItems(FXCollections.observableArrayList(data));
    }

    @FXML
    private void goBack() {
        ((Stage) lblTotalBudget.getScene().getWindow()).close();
    }
}
