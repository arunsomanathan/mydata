package com.mydata.userdata.dto;

import java.math.BigDecimal;

/** Account DTO */
public record AccountDto(
    Integer id, String bankName, String branch, String accountNumber, BigDecimal balance) {}
