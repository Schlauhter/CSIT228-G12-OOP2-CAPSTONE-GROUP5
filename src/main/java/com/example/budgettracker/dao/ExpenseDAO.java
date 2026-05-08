package com.example.budgettracker.dao;

import com.example.budgettracker.db.DatabaseConnection;
import com.example.budgettracker.model.Expense;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public boolean addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (user_id, category, description, amount, expense_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expense.getUserId());
            stmt.setString(2, expense.getCategory());
            stmt.setString(3, expense.getDescription());
            stmt.setBigDecimal(4, expense.getAmount());
            stmt.setDate(5, Date.valueOf(expense.getExpenseDate()));
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET category=?, description=?, amount=?, expense_date=? WHERE expense_id=? AND user_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, expense.getCategory());
            stmt.setString(2, expense.getDescription());
            stmt.setBigDecimal(3, expense.getAmount());
            stmt.setDate(4, Date.valueOf(expense.getExpenseDate()));
            stmt.setInt(5, expense.getExpenseId());
            stmt.setInt(6, expense.getUserId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteExpense(int expenseId, int userId) throws SQLException {
        String sql = "DELETE FROM expenses WHERE expense_id=? AND user_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expenseId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Expense> getAllExpenses(int userId) throws SQLException {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE user_id=? ORDER BY expense_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Expense e = new Expense();
                e.setExpenseId(rs.getInt("expense_id"));
                e.setUserId(rs.getInt("user_id"));
                e.setCategory(rs.getString("category"));
                e.setDescription(rs.getString("description"));
                e.setAmount(rs.getBigDecimal("amount"));
                e.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                list.add(e);
            }
        }
        return list;
    }

    public BigDecimal getTotalExpenses(int userId) {
        String sql = "SELECT SUM(amount) FROM expenses WHERE user_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getBigDecimal(1) != null) {
                return rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total expenses: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    public List<Object[]> getDailyBreakdown(int userId)   { return getBreakdown(userId, "DATE(expense_date)"); }
    public List<Object[]> getWeeklyBreakdown(int userId)  { return getBreakdown(userId, "DATE_FORMAT(expense_date, '%x-W%v')"); }
    public List<Object[]> getMonthlyBreakdown(int userId) { return getBreakdown(userId, "DATE_FORMAT(expense_date, '%Y-%m')"); }

    private List<Object[]> getBreakdown(int userId, String groupByExpr) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT " + groupByExpr + " AS period, SUM(amount) AS total " +
                     "FROM expenses WHERE user_id=? GROUP BY period ORDER BY period DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("period"), rs.getBigDecimal("total")});
            }
        } catch (SQLException e) {
            System.err.println("Error fetching breakdown: " + e.getMessage());
        }
        return result;
    }
}
