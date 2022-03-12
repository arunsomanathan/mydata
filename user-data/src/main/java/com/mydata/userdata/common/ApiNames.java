package com.mydata.userdata.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiNames {

  // Investment APIs
  public static final String GET_DEPOSIT_ACCOUNTS = "GetDepositAccounts";
  public static final String ADD_DEPOSIT_ACCOUNT = "AddDepositAccount";
  public static final String GET_LOAN_ACCOUNTS = "GetLoanAccounts";
  public static final String ADD_LOAN_ACCOUNT = "AddLoanAccount";
  public static final String GET_MISC_ACCOUNTS = "GetMiscellaneousAccounts";
  public static final String ADD_MISC_ACCOUNT = "AddMiscellaneousAccount";
  public static final String GET_MUTUAL_FUNDS = "GetMutualFunds";
  public static final String ADD_MUTUAL_FUND = "AddMutualFund";
  public static final String GET_MF_BUY_TRANSACTIONS = "GetMutualFundsBuyTransactions";
  public static final String ADD_MF_BUY_TRANSACTIONS = "AddMutualFundsBuyTransactions";
  public static final String GET_MF_SELL_TRANSACTIONS = "GetMutualFundsSellTransactions";
  public static final String ADD_MF_SELL_TRANSACTIONS = "AddMutualFundsSellTransactions";
  public static final String GET_SAVING_ACCOUNTS = "GetSavingAccounts";
  public static final String ADD_SAVING_ACCOUNT = "AddSavingAccount";
  public static final String GET_STOCKS = "GetStocks";
  public static final String ADD_STOCK = "AddStock";
}
