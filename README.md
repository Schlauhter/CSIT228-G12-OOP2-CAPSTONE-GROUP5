# 💰 Budget Tracker

A personal finance desktop application built with **JavaFX** and **MySQL**, designed to help users manage their budget, track expenses, and generate spending reports — all from a clean, user-friendly interface.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Setup](#database-setup)
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

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/budget-tracker.git
   cd budget-tracker
   ```

2. **Import the database** following the [Database Setup](#database-setup) section above.

3. **Open the project** in IntelliJ IDEA or your preferred Java IDE.

4. **Add dependencies** to your project libraries:
   - JavaFX SDK modules (`javafx.controls`, `javafx.fxml`)
   - `mysql-connector-j` JAR file

5. **Configure VM options** for JavaFX (in Run Configuration):
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
   ```

6. **Run `Main.java`** — the Login screen will appear.

> ⚠️ If you see `"DB Error - Is XAMPP MySQL running?"`, make sure the MySQL module is active in XAMPP before launching the app.

---

## Authors

> *KEITH ALLEN LARIEGO*

- **Keith Allen A. Lariego** — Role
- **Miguel Carlos Sibi** — Role
- **Prince Kenn P. Sorita** — Role
- **Akshey Luke F. Gallardo** — Role
- **Joshua Dave G. Abella** — Role

---

*Capstone Project — [CSIT228] | [5/22/2026]*
