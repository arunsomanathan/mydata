package com.mydata.userdata.repository;

import com.mydata.userdata.entity.Stock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StockRepository extends ReactiveCrudRepository<Stock, Integer> {

  /** Find Active Records */
  Flux<Stock> findByActiveTrue();
}
