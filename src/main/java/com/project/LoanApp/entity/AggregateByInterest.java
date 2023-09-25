package com.project.LoanApp.entity;

public interface AggregateByInterest {
    int getRemainingAmount();
    int getInterest();
    double getPenalty();
}
