module com.example.budgettracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.budgettracker to javafx.fxml;
    opens com.example.budgettracker.controller to javafx.fxml;
    opens com.example.budgettracker.model to javafx.fxml;
    opens com.example.budgettracker.dao to javafx.fxml;
    opens com.example.budgettracker.util to javafx.fxml;

    exports com.example.budgettracker;
    exports com.example.budgettracker.controller;
    exports com.example.budgettracker.dao;
    exports com.example.budgettracker.model;
    exports com.example.budgettracker.db;
    exports com.example.budgettracker.util;
}
