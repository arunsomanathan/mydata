package com.mydata.userdata.entity;

import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Saving Accounts */
@Table
public record SavingAccount(
    @Id Integer id,
    String bankName,
    String branch,
    String accountNumber,
    BigDecimal balance,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt,
    Boolean active) {}
