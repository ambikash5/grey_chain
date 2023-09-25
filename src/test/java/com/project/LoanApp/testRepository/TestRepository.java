package com.project.LoanApp.testRepository;

import com.project.LoanApp.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Loans,String> {
}
