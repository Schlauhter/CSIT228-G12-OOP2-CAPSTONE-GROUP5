package com.example.budgettracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private int expenseId;
    private int userId;
    private String category;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;

    public Expense() {}

    public Expense(int userId, String category, String description, BigDecimal amount, LocalDate expenseDate) {
        this.userId      = userId;
        this.category    = category;
        this.description = description;
        this.amount      = amount;
        this.expenseDate = expenseDate;
    }

    public int        getExpenseId()               { return expenseId; }
    public void       setExpenseId(int id)          { this.expenseId = id; }
    public int        getUserId()                   { return userId; }
    public void       setUserId(int id)             { this.userId = id; }
    public String     getCategory()                 { return category; }
    public void       setCategory(String c)         { this.category = c; }
    public String     getDescription()              { return description; }
    public void       setDescription(String d)      { this.description = d; }
    public BigDecimal getAmount()                   { return amount; }
    public void       setAmount(BigDecimal a)        { this.amount = a; }
    public LocalDate  getExpenseDate()              { return expenseDate; }
    public void       setExpenseDate(LocalDate d)   { this.expenseDate = d; }
}
