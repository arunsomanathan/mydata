package com.mydata.userdata.dto;

import java.time.Instant;
import java.util.List;

/** Mutual Fund Sell Transaction DTO */
public record MutualFundSellTransactionDto(
    Integer id,
    Integer mfId,
    List<Object> buyIds,
    Double nav,
    Double units,
    Double charge,
    Instant soldDate,
    Double profitLoss) {}
