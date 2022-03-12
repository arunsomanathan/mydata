package com.mydata.userdata.repository;

import com.mydata.userdata.entity.MutualFundSellTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MutualFundSellTransactionRepository
    extends ReactiveCrudRepository<MutualFundSellTransaction, Integer> {}
