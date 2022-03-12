package com.mydata.userdata.repository;

import com.mydata.userdata.entity.MutualFundBuyTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MutualFundBuyTransactionRepository
    extends ReactiveCrudRepository<MutualFundBuyTransaction, Integer> {

  /** Find Records By is_sold_out Column */
  Flux<MutualFundBuyTransaction> findByIsSoldOut(Boolean active);
}
