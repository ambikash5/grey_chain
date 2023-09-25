package com.project.LoanApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAN_tbl")
public class Loans {
    @Id
    @GeneratedValue(generator = "custom_seq_gen")
    @GenericGenerator(name = "custom_seq_gen", strategy = "com.project.LoanApp.sequencegenerator.seqgen")
    @Column(name = "Loan ID")
    private String loanid;
    @Column(name = "Customer Id")
    private String customerid;
    @Column(name = "Lender Id")
    private String lenderid;
    @Column(name = "Amount")
    private int amount;
    @Column(name = "Remaining Amount")
    private int remainingamount;
    @Column(name = "Payment Date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentdate;

    @Column(name = "Interest Per Day(%)")
    private int interestperday;
    @Column(name = "Due Date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate duedate;
    @Column(name = "Penalty/Day")
    private double penaltyperday;
    @Column(name = "Cancel")
    private String cancel;
}
