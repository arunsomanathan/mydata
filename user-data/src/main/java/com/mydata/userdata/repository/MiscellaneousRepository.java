package com.mydata.userdata.repository;

import com.mydata.userdata.entity.Miscellaneous;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MiscellaneousRepository extends ReactiveCrudRepository<Miscellaneous, Integer> {

  /** Find Records By Active Column */
  Flux<Miscellaneous> findByActive(Boolean active);
}
