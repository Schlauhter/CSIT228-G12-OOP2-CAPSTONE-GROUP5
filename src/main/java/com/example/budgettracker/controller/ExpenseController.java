package com.example.budgettracker.controller;

import com.example.budgettracker.dao.ExpenseDAO;
import com.example.budgettracker.model.Expense;
import com.example.budgettracker.util.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExpenseController {

    @FXML private TextField  txtCategory;
    @FXML private TextField  txtDescription;
    @FXML private TextField  txtAmount;
    @FXML private TextField  txtDate;
    @FXML private Label      lblStatus;

    @FXML private TableView<Expense>          tableExpenses;
    @FXML private TableColumn<Expense, Integer>    colId;
    @FXML private TableColumn<Expense, String>     colCategory;
    @FXML private TableColumn<Expense, String>     colDescription;
    @FXML private TableColumn<Expense, BigDecimal> colAmount;
    @FXML private TableColumn<Expense, LocalDate>  colDate;

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    private int selectedExpenseId = -1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("expenseId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("expenseDate"));

        txtDate.setText(LocalDate.now().toString());
        loadExpenses();

        tableExpenses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) populateForm(newVal);
        });
    }

    @FXML
    private void addExpense() {
        if (!validateForm()) return;
        try {
            if (expenseDAO.addExpense(buildExpense())) {
                showMsg("Expense added!", "green");
                clearForm();
                loadExpenses();
            } else {
                showMsg("Failed to add expense.", "red");
            }
        } catch (Exception ex) {
            showMsg("DB Error: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }

    @FXML
    private void updateExpense() {
        if (selectedExpenseId == -1) { showMsg("Select an expense to update.", "orange"); return; }
        if (!validateForm()) return;
        try {
            Expense exp = buildExpense();
            exp.setExpenseId(selectedExpenseId);
            if (expenseDAO.updateExpense(exp)) {
                showMsg("Expense updated!", "orange");
                clearForm();
                loadExpenses();
            } else {
                showMsg("Failed to update.", "red");
            }
        } catch (Exception ex) {
            showMsg("DB Error: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }

    @FXML
    private void deleteExpense() {
        if (selectedExpenseId == -1) { showMsg("Select an expense to delete.", "orange"); return; }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this expense?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try {
                    if (expenseDAO.deleteExpense(selectedExpenseId, Session.getCurrentUserId())) {
                        showMsg("Expense deleted.", "red");
                        clearForm();
                        loadExpenses();
                    } else {
                        showMsg("Failed to delete.", "red");
                    }
                } catch (Exception ex) {
                    showMsg("DB Error: " + ex.getMessage(), "red");
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void clearForm() {
        txtCategory.clear();
        txtDescription.clear();
        txtAmount.clear();
        txtDate.setText(LocalDate.now().toString());
        selectedExpenseId = -1;
        tableExpenses.getSelectionModel().clearSelection();
        lblStatus.setText(" ");
    }

    @FXML
    private void goBack() {
        ((Stage) txtCategory.getScene().getWindow()).close();
    }

    private void loadExpenses() {
        try {
            List<Expense> list = expenseDAO.getAllExpenses(Session.getCurrentUserId());
            ObservableList<Expense> data = FXCollections.observableArrayList(list);
            tableExpenses.setItems(data);
        } catch (Exception ex) {
            showMsg("Error loading expenses: " + ex.getMessage(), "red");
        }
    }

    private void populateForm(Expense e) {
        selectedExpenseId = e.getExpenseId();
        txtCategory.setText(e.getCategory());
        txtDescription.setText(e.getDescription());
        txtAmount.setText(e.getAmount().toString());
        txtDate.setText(e.getExpenseDate().toString());
    }

    private boolean validateForm() {
        if (txtCategory.getText().trim().isEmpty()) { showMsg("Category is required.", "red"); return false; }
        if (txtAmount.getText().trim().isEmpty())   { showMsg("Amount is required.", "red"); return false; }
        try { new BigDecimal(txtAmount.getText().trim()); } catch (NumberFormatException e) { showMsg("Invalid amount.", "red"); return false; }
        try { LocalDate.parse(txtDate.getText().trim()); } catch (Exception e) { showMsg("Date must be YYYY-MM-DD.", "red"); return false; }
        return true;
    }

    private Expense buildExpense() {
        return new Expense(Session.getCurrentUserId(),
                txtCategory.getText().trim(),
                txtDescription.getText().trim(),
                new BigDecimal(txtAmount.getText().trim()),
                LocalDate.parse(txtDate.getText().trim()));
    }

    private void showMsg(String msg, String color) {
        lblStatus.setText(msg);
        lblStatus.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 11;");
    }
}
