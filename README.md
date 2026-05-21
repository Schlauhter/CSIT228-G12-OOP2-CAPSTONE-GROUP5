**MEMBERS**

Keith Allen A. Lariego

Miguel Carlos Sibi

Akshey Luke F. Gallardo

Prince Kenn P. Sorita

Joshua Dave G. Abella

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
- [Screenshots](#screenshots)

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
| IDE | IntelliJ IDEA / NetBeans |

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

### 1. Start XAMPP
Open XAMPP Control Panel and start the **MySQL** module.

### 2. Open phpMyAdmin
Go to `http://localhost/phpmyadmin` in your browser.

### 3. Create the Database
Click **New** and create a database named:
```
budget_tracker_db
```

### 4. Run the following SQL to create the tables:

```sql
USE budget_tracker_db;

CREATE TABLE users (
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(100) NOT NULL
);

CREATE TABLE budgets (
    budget_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT            NOT NULL,
    amount      DECIMAL(10, 2) NOT NULL,
    period_type VARCHAR(20)    NOT NULL,
    start_date  DATE           NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE expenses (
    expense_id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT            NOT NULL,
    category     VARCHAR(100)   NOT NULL,
    description  VARCHAR(255),
    amount       DECIMAL(10, 2) NOT NULL,
    expense_date DATE           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

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

2. **Set up the database** following the [Database Setup](#database-setup) section above.

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

## Screenshots

> *(Add screenshots of your Login, Dashboard, Expense Manager, and Report screens here)*

To add screenshots:
1. Take a screenshot of each screen
2. Save them in a `/screenshots` folder in the repo
3. Reference them like this:

```markdown
![Login Screen](screenshots/login.png)
![Dashboard](screenshots/dashboard.png)
![Expense Manager](screenshots/expenses.png)
![Report](screenshots/report.png)
```

---

## Authors

> *(Add your names and student IDs here)*

- **Name** — Role
- **Name** — Role

---

*Capstone Project — [Your Course Name] | [Your School] | [Year]*


