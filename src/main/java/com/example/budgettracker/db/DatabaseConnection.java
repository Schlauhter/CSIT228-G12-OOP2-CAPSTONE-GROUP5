package com.example.budgettracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection - Manages MySQL connection via XAMPP
 * Returns a fresh connection each call to avoid stale/null connection bugs.
 */
public class DatabaseConnection {

    // useSSL=false + serverTimezone=UTC required for most XAMPP setups
    private static final String URL      = "jdbc:mysql://localhost:3306/budget_tracker_db";
    private static final String USER     = "root";
    private static final String PASSWORD = "";  // XAMPP default has no password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Add mysql-connector-j JAR to your project libraries.");
        }
    }

    /**
     * Returns a NEW connection every call.
     * Use with try-with-resources: try (Connection conn = getConnection()) { ... }
     * Throws SQLException so callers can show proper error messages to the user.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
