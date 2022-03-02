package com.mydata.userdata.repository;

import com.mydata.userdata.entity.Loan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LoanRepository extends ReactiveCrudRepository<Loan, Integer> {

  /** Find Records By Active Column */
  Flux<Loan> findByActive(Boolean active);
}
