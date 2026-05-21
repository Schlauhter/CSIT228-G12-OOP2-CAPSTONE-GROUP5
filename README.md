# <img width="512" height="512" alt="budget" src="https://github.com/user-attachments/assets/cdad500d-0119-4478-ba90-1b31b01cd166" /> Budget Tracker

A personal finance desktop application built with **JavaFX** and **MySQL**, designed to help users manage their budget, track expenses, and generate spending reports — all from a clean, user-friendly interface.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Authors](#Authors)

---

## Overview

Budget Tracker is a capstone project that demonstrates a full-stack desktop application using the **MVC (Model-View-Controller)** architecture pattern. Each user has their own account, and all financial data — budgets and expenses — is isolated per user through session management.

The application connects to a **local MySQL database** via XAMPP and uses JDBC with PreparedStatements for all database operations.

---

## Features

### 🔐 User Authentication
- **Register** a new account with username, email, and password
- Input validation on all fields (minimum length, email format, password confirmation, duplicate username check)
- **Login** with username and password
- **Logout** clears the session and returns to the Login screen
- Each user's data is fully isolated — no cross-user data access

### 💵 Budget Settings
- Set a personal budget amount (in PHP)
- Choose a budget period: **Daily**, **Weekly**, or **Monthly**
- The most recently saved budget is always displayed
- Budget is linked to the logged-in user's account

### 🧾 Expense Manager
- **Add** expenses with: category, description, amount, and date
- **Update** any existing expense by selecting it from the table
- **Delete** expenses with a confirmation dialog to prevent accidents
- Date defaults to today for convenience
- All expenses are displayed in a sortable table, ordered by most recent date
- Full form validation (required fields, valid number format, valid date format)

### 📊 Reports
- Summary view showing:
  - **Total Budget** — your current set budget
  - **Total Expenses** — sum of all logged expenses
  - **Remaining Balance** — budget minus expenses
- **Budget alert system** with color-coded status:
  - 🟢 **Green** — within budget
  - 🟠 **Orange warning** — over 80% of budget used
  - 🔴 **Red alert** — budget exceeded
- **Expense Breakdown Table** — view spending grouped by:
  - Daily
  - Weekly
  - Monthly

---

## Tech Stack

| Layer | Technology |
|---|---|
| UI Framework | JavaFX (FXML) |
| Language | Java |
| Database | MySQL (via XAMPP) |
| DB Driver | MySQL Connector/J (JDBC) |
| Architecture | MVC + DAO Pattern |
| IDE | IntelliJ IDEA |

---

## Project Structure

```
src/
└── com/example/budgettracker/
    ├── Main.java                  # App entry point, loads Login.fxml
    │
    ├── model/
    │   ├── User.java              # User data class
    │   ├── Budget.java            # Budget data class (amount, period, date)
    │   └── Expense.java           # Expense data class (category, amount, date)
    │
    ├── dao/
    │   ├── UserDAO.java           # Register, login, username check
    │   ├── BudgetDAO.java         # Save and retrieve budgets
    │   └── ExpenseDAO.java        # CRUD operations + breakdown queries
    │
    ├── controller/
    │   ├── LoginController.java   # Handles login and navigation to Sign Up
    │   ├── SignUpController.java  # Handles registration with validation
    │   ├── DashboardController.java # Main hub, opens Budget/Expense/Report
    │   ├── BudgetController.java  # Budget form logic
    │   ├── ExpenseController.java # Expense table and form logic
    │   └── ReportController.java  # Summary + breakdown report logic
    │
    ├── db/
    │   └── DatabaseConnection.java # JDBC connection to MySQL (XAMPP)
    │
    └── util/
        └── Session.java           # Stores the currently logged-in user
```

---

## Database Setup

> **Requirement:** [XAMPP](https://www.apachefriends.org/) must be installed with **MySQL** running.

The SQL file is already included in the project under `src/`. Simply import it into your local MySQL server:

1. Open XAMPP Control Panel and start the **MySQL** module.
2. Go to `http://localhost/phpmyadmin` in your browser.
3. Click **Import**, select the `.sql` file found in the `src/` folder, and click **Go**.

That's it — the database and all tables will be created automatically.

---

## How to Run

### Prerequisites
- Java JDK 17 or higher
- JavaFX SDK
- MySQL Connector/J JAR added to project libraries
- XAMPP with MySQL running


## Authors

- **Keith Allen A. Lariego** 
- **Miguel Carlos Sibi** 
- **Prince Kenn P. Sorita** 
- **Akshey Luke F. Gallardo** 
- **Joshua Dave G. Abella** 

---

*Capstone Project — [CSIT228] | [5/22/2026]*
