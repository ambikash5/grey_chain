package com.project.LoanApp.service;

import com.project.LoanApp.entity.AggregateByCustomerId;
import com.project.LoanApp.entity.AggregateByInterest;
import com.project.LoanApp.entity.AggregatorLender;
import com.project.LoanApp.entity.Loans;
import com.project.LoanApp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository repository;

    public Loans saveLoan(Loans loan){

        return repository.save(loan);
    }

    public List<Loans> getAllLoans(){
        return  repository.findAll();
    }

    public Loans getLoansById(String id){
        return  repository.findById(id).orElse(null);
    }

    public List<Loans> getLoansByCustomerId(String id){
        return repository.findByCustomerid(id);
    }

    public List<Loans> getLoansByLenderIdId(String id) {
        return repository.findByLenderid(id);
    }

    public List<AggregatorLender> getAggregateByLender() {

        return repository.getAggregateByLender();
    }

    public List<AggregateByCustomerId> getAggregateByCustomer() {
        return repository.getAggregateByCustomer();
    }

    public List<AggregateByInterest> getAggregateByInterest() {
        return repository.getAggregateByInterest();
    }
}
