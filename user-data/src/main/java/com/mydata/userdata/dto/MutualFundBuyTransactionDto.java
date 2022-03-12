package com.mydata.userdata.dto;

import java.time.Instant;

/** Mutual Fund Buy Transaction DTO */
public record MutualFundBuyTransactionDto(
    Integer id,
    Integer mfId,
    Double nav,
    Double units,
    Double charge,
    Instant buyDate,
    Double soldUnits,
    Boolean isSoldOut) {}
