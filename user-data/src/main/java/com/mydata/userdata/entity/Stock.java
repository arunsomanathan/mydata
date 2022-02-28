package com.mydata.userdata.entity;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Stocks */
@Table
public record Stock(
    @Id Integer id,
    String stockCode,
    String stockName,
    String stockExchange,
    String broker,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt,
    Boolean active) {}
