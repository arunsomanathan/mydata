package com.mydata.userdata.common;

import static com.mydata.userdata.common.ApiNames.*;
import static com.mydata.userdata.common.ApiUrls.*;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConstants {

  public static final Map<String, String> API_NAME_URL_MAP =
      Map.ofEntries(
          Map.entry(GET_DEPOSIT_ACCOUNTS, DEPOSIT_ACCOUNTS_URL),
          Map.entry(ADD_DEPOSIT_ACCOUNT, DEPOSIT_ACCOUNTS_URL),
          Map.entry(GET_LOAN_ACCOUNTS, LOAN_ACCOUNTS_URL),
          Map.entry(ADD_LOAN_ACCOUNT, LOAN_ACCOUNTS_URL),
          Map.entry(GET_MISC_ACCOUNTS, MISC_ACCOUNTS_URL),
          Map.entry(ADD_MISC_ACCOUNT, MISC_ACCOUNTS_URL),
          Map.entry(GET_MUTUAL_FUNDS, MUTUAL_FUNDS_URL),
          Map.entry(ADD_MUTUAL_FUND, MUTUAL_FUNDS_URL),
          Map.entry(GET_MF_BUY_TRANSACTIONS, MF_BUY_TRANSACTION_URL),
          Map.entry(ADD_MF_BUY_TRANSACTIONS, MF_BUY_TRANSACTION_URL),
          Map.entry(GET_MF_SELL_TRANSACTIONS, MF_SELL_TRANSACTION_URL),
          Map.entry(ADD_MF_SELL_TRANSACTIONS, MF_SELL_TRANSACTION_URL),
          Map.entry(GET_SAVING_ACCOUNTS, SAVING_ACCOUNTS_URL),
          Map.entry(ADD_SAVING_ACCOUNT, SAVING_ACCOUNTS_URL),
          Map.entry(GET_STOCKS, STOCKS_URL),
          Map.entry(ADD_STOCK, STOCKS_URL));

  public static final String POSTGRESQL_DB_PROPERTY_PREFIX = "db.postgresql.";
}
