package com.mydata.userdata.repository;

import com.mydata.userdata.entity.SavingAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SavingAccountRepository extends ReactiveCrudRepository<SavingAccount, Integer> {

  /** Find Active Records */
  Flux<SavingAccount> findByActiveTrue();
}
