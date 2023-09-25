package com.project.LoanApp.entity;

public interface AggregateByCustomerId {
    String getCustomerId();
    int getRemainingAmount();
    int getInterest();
    double getPenalty();
}
