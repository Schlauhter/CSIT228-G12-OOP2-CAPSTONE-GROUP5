package com.example.budgettracker.dao;

import com.example.budgettracker.db.DatabaseConnection;
import com.example.budgettracker.model.Budget;

import java.math.BigDecimal;
import java.sql.*;

public class BudgetDAO {

    public boolean saveBudget(Budget budget) throws SQLException {
        String sql = "INSERT INTO budgets (user_id, amount, period_type, start_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, budget.getUserId());
            stmt.setBigDecimal(2, budget.getAmount());
            stmt.setString(3, budget.getPeriodType());
            stmt.setDate(4, Date.valueOf(budget.getStartDate()));
            return stmt.executeUpdate() > 0;
        }
    }

    public Budget getLatestBudget(int userId) throws SQLException {
        String sql = "SELECT * FROM budgets WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Budget b = new Budget();
                b.setBudgetId(rs.getInt("budget_id"));
                b.setUserId(rs.getInt("user_id"));
                b.setAmount(rs.getBigDecimal("amount"));
                b.setPeriodType(rs.getString("period_type"));
                b.setStartDate(rs.getDate("start_date").toLocalDate());
                return b;
            }
        }
        return null;
    }

    public BigDecimal getBudgetAmount(int userId) {
        try {
            Budget b = getLatestBudget(userId);
            return (b != null) ? b.getAmount() : BigDecimal.ZERO;
        } catch (SQLException e) {
            return BigDecimal.ZERO;
        }
    }
}
