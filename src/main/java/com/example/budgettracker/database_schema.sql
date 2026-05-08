-- ============================================================
--  Budget Tracker - Capstone Group 5
--  MySQL Database Schema
--  Run this in phpMyAdmin (XAMPP) or MySQL Workbench
-- ============================================================

CREATE DATABASE IF NOT EXISTS budget_tracker_db;
USE budget_tracker_db;

-- ========================
-- Table: users
-- ========================
CREATE TABLE IF NOT EXISTS users (
    user_id     INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ========================
-- Table: budgets
-- ========================
CREATE TABLE IF NOT EXISTS budgets (
    budget_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT NOT NULL,
    amount      DECIMAL(12, 2) NOT NULL,
    period_type ENUM('Daily', 'Weekly', 'Monthly') NOT NULL,
    start_date  DATE NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ========================
-- Table: expenses
-- ========================
CREATE TABLE IF NOT EXISTS expenses (
    expense_id  INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT NOT NULL,
    category    VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    amount      DECIMAL(12, 2) NOT NULL,
    expense_date DATE NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ========================
-- Sample Data (optional)
-- ========================
INSERT INTO users (username, password, email) VALUES
('admin', 'admin123', 'admin@budgettracker.com');

INSERT INTO budgets (user_id, amount, period_type, start_date) VALUES
(1, 5000.00, 'Monthly', CURDATE());

INSERT INTO expenses (user_id, category, description, amount, expense_date) VALUES
(1, 'Food', 'Grocery shopping', 350.00, CURDATE()),
(1, 'Transport', 'Jeepney fare', 50.00, CURDATE()),
(1, 'Bills', 'Electricity bill', 800.00, CURDATE());
