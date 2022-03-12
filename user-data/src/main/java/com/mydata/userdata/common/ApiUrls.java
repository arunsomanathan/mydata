package com.mydata.userdata.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiUrls {

  // Base URLs
  public static final String INVESTMENT_BASE_URL = "/investments";

  // Investment URLs
  public static final String DEPOSIT_ACCOUNTS_URL = "/depositaccounts";
  public static final String LOAN_ACCOUNTS_URL = "/loanaccounts";
  public static final String MISC_ACCOUNTS_URL = "/miscellaneousaccounts";
  public static final String MUTUAL_FUNDS_URL = "/mutualfunds";
  public static final String MF_BUY_TRANSACTION_URL = MUTUAL_FUNDS_URL + "/transactions/buy";
  public static final String MF_SELL_TRANSACTION_URL = MUTUAL_FUNDS_URL + "/transactions/sell";
  public static final String SAVING_ACCOUNTS_URL = "/savingaccounts";
  public static final String STOCKS_URL = "/stocks";
}
