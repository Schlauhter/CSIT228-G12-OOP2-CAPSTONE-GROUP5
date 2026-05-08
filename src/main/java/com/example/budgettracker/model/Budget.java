package com.example.budgettracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Budget {
    private int budgetId;
    private int userId;
    private BigDecimal amount;
    private String periodType;  // Daily, Weekly, Monthly
    private LocalDate startDate;

    public Budget() {}

    public Budget(int userId, BigDecimal amount, String periodType, LocalDate startDate) {
        this.userId     = userId;
        this.amount     = amount;
        this.periodType = periodType;
        this.startDate  = startDate;
    }

    public int         getBudgetId()              { return budgetId; }
    public void        setBudgetId(int id)         { this.budgetId = id; }
    public int         getUserId()                 { return userId; }
    public void        setUserId(int id)           { this.userId = id; }
    public BigDecimal  getAmount()                 { return amount; }
    public void        setAmount(BigDecimal a)     { this.amount = a; }
    public String      getPeriodType()             { return periodType; }
    public void        setPeriodType(String p)     { this.periodType = p; }
    public LocalDate   getStartDate()              { return startDate; }
    public void        setStartDate(LocalDate d)   { this.startDate = d; }
}
