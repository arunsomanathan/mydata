package com.mydata.userdata.entity;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/** Entity Record for Mutual Funds */
@Table
public record MutualFund(
    @Id Integer id,
    String mfCode,
    String mfName,
    String amc,
    String type,
    @CreatedDate Instant createdAt,
    @LastModifiedDate Instant modifiedAt,
    Boolean active) {}
