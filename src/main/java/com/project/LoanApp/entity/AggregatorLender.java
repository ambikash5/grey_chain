package com.project.LoanApp.entity;


public interface AggregatorLender {

    String getLenderId();
    int getRemainingAmount();
    int getInterest();
    double getPenalty();

}
