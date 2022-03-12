package com.mydata.userdata.entity;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Mutual Funds */
@Table
public record MutualFundBuyTransaction(
    @Id Integer id,
    Integer mfId,
    Double nav,
    Double units,
    Double charge,
    Instant buyDate,
    Double soldUnits,
    Boolean isSoldOut,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt) {}
