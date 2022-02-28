package com.mydata.userdata.entity;

import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Miscellaneous Investments / Accounts */
@Table
public record Miscellaneous(
    @Id Integer id,
    String investmentName,
    BigDecimal balance,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt,
    Boolean active) {}
