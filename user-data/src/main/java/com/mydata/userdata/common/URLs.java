package com.mydata.userdata.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class URLs {

  // Base URLs
  public static final String INVESTMENT_BASE_URL = "/investment";

  // Investment URLs
  public static final String DEPOSIT_ACCOUNTS_URL = "depositaccounts";
  public static final String LOAN_ACCOUNTS_URL = "loanaccounts";
  public static final String MISCELLANEOUS_ACCOUNTS_URL = "miscellaneousaccounts";
  public static final String MUTUAL_FUNDS_URL = "mutualfunds";
  public static final String SAVING_ACCOUNTS_URL = "savingaccounts";
  public static final String STOCKS_URL = "stocks";
}
