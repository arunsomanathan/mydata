package com.mydata.userdata.repository;

import com.mydata.userdata.entity.DepositAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DepositAccountRepository extends ReactiveCrudRepository<DepositAccount, Integer> {

  /** Find Records By Active Column */
  Flux<DepositAccount> findByActive(Boolean active);
}
