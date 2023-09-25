package com.project.LoanApp.controller;

import com.project.LoanApp.customException.CustomException;
import com.project.LoanApp.entity.AggregateByCustomerId;
import com.project.LoanApp.entity.AggregateByInterest;
import com.project.LoanApp.entity.AggregatorLender;
import com.project.LoanApp.entity.Loans;
import com.project.LoanApp.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class LoanController {
    @Autowired
    private LoanService service;
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);


    //Get all loans
    @GetMapping("/loans")
    public List<Loans> loans(){
        return service.getAllLoans();
    }
    //add loan
    @PostMapping("/loans/add")
    public Loans addloans(@RequestBody Loans loan){

        if(loan.getPaymentdate().isAfter(loan.getDuedate())){
            throw new CustomException("Payment date can't be greater than Due date");

        }
        return service.saveLoan(loan);
    }

    //get loan by loan id
    @GetMapping("/loans/loanid/{loanid}")
    public Loans getLoanByLoanId(@PathVariable("loanid") String id){
        Loans l1 = service.getLoansById(id.toUpperCase());
        if(l1.getDuedate().isBefore(LocalDate.now())){
            logger.warn("Loan Crossed Due date");
        }
        return l1;
    }

    //get loan by customer id
    @GetMapping("/loans/customerid/{customerid}")
    public List<Loans> getLoanByCustomerId(@PathVariable("customerid") String id){

        return service.getLoansByCustomerId(id.toUpperCase());
    }
    //get loan by lender id
    @GetMapping("/loans/lenderid/{lenderid}")
    public List<Loans> getLoanBylenderId(@PathVariable("lenderid") String id){

        return service.getLoansByLenderIdId(id.toUpperCase());
    }

    // get get aggregate loans by Lender
    @GetMapping("/loans/aggregate/lender")
    public List<AggregatorLender> getAggregateByLender(){
        return service.getAggregateByLender();
    }
    @GetMapping("/loans/aggregate/customer")
    public List<AggregateByCustomerId> getAggregateByCustomer(){
        return service.getAggregateByCustomer();
    }

    @GetMapping("/loans/aggregate/interest")
    public List<AggregateByInterest> getAggregateByInterest(){
        return service.getAggregateByInterest();
    }

}
