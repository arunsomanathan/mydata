package com.mydata.userdata.entity;

import java.time.Instant;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Mutual Funds */
@Table
public record MutualFundSellTransaction(
    @Id Integer id,
    Integer mfId,
    List<Integer> buyIds,
    Double nav,
    Double units,
    Double charge,
    Instant soldDate,
    Double profitLoss,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt) {}
