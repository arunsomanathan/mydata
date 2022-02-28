package com.mydata.userdata.repository;

import com.mydata.userdata.entity.MutualFund;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MutualFundRepository extends ReactiveCrudRepository<MutualFund, Integer> {

  /** Find Active Records */
  Flux<MutualFund> findByActiveTrue();
}
