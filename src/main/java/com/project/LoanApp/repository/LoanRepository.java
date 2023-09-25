package com.project.LoanApp.repository;

import com.project.LoanApp.entity.AggregateByCustomerId;
import com.project.LoanApp.entity.AggregateByInterest;
import com.project.LoanApp.entity.AggregatorLender;
import com.project.LoanApp.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loans,String> {


    List<Loans> findByCustomerid(String id);

    List<Loans> findByLenderid(String id);

   @Query(value = "SELECT  l.\"lender id\" as lenderId ,SUM(l.\"remaining amount\") as remainingAmount, SUM(l.\"interest per day(%)\") as interest, SUM(l.\"penalty/day\") as penalty FROM loan_tbl l GROUP BY l.\"lender id\"",nativeQuery = true)
    List<AggregatorLender> getAggregateByLender();

    @Query(value = "SELECT  l.\"customer id\" as customerId ,SUM(l.\"remaining amount\") as remainingAmount, SUM(l.\"interest per day(%)\") as interest, SUM(l.\"penalty/day\") as penalty FROM loan_tbl l GROUP BY l.\"customer id\"",nativeQuery = true)

    List<AggregateByCustomerId> getAggregateByCustomer();

    @Query(value = "SELECT  l.\"interest per day(%)\" as interest ,SUM(l.\"remaining amount\") as remainingAmount, SUM(l.\"penalty/day\") as penalty FROM loan_tbl l GROUP BY l.\"interest per day(%)\"",nativeQuery = true)
    List<AggregateByInterest> getAggregateByInterest();
}
