package com.mydata.userdata.dto;

/** Stock DTO */
public record StockDto(
    Integer id, String stockCode, String stockName, String stockExchange, String broker) {}
