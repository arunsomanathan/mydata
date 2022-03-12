package com.mydata.userdata.integration;

import static com.mydata.userdata.common.ApiNames.*;
import static com.mydata.userdata.common.ApiUrls.INVESTMENT_BASE_URL;
import static com.mydata.userdata.common.ObjectProperties.ACCOUNT_ID;
import static com.mydata.userdata.common.TestConstants.API_NAME_URL_MAP;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.controller.InvestmentController;
import com.mydata.userdata.dto.*;
import com.mydata.userdata.utils.InvestmentParameterResolver;
import com.mydata.userdata.utils.NegativeBalance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"server.port=0", "server.ssl.enabled=false"})
@ExtendWith(InvestmentParameterResolver.class)
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class InvestmentTest extends IntegrationTestBase {

  private final BeanTransformer dtoToDtoSkipId =
      new BeanUtils().getTransformer().skipTransformationForField(ACCOUNT_ID);

  @Override
  public String getBaseUrl() {
    return INVESTMENT_BASE_URL;
  }

  @Override
  public String getApiUrlFromApiName(final String apiName) {
    return API_NAME_URL_MAP.get(apiName);
  }

  /** Test for {@link InvestmentController#getDepositAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Deposit Accounts")
  void getDepositAccounts() {
    verifyGetListResponse(GET_DEPOSIT_ACCOUNTS, AccountDto.class);
  }

  /**
   * Test for {@link InvestmentController#addDepositAccount(AccountDto)}
   *
   * @param depositAccount the input / expected deposit account
   */
  @Test
  @DisplayName("Happy Path: Add Deposit Account")
  void addDepositAccount(final AccountDto depositAccount) {
    verifyPostResponse(
        ADD_DEPOSIT_ACCOUNT,
        dtoToDtoSkipId.transform(depositAccount, AccountDto.class),
        AccountDto.class);
  }

  /** Test for {@link InvestmentController#getLoanAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Loan Accounts")
  void getLoanAccounts() {
    verifyGetListResponse(GET_LOAN_ACCOUNTS, AccountDto.class);
  }

  /**
   * Test for {@link InvestmentController#addLoanAccount(AccountDto)}
   *
   * @param loanAccount the input / expected loan account
   */
  @Test
  @DisplayName("Happy Path: Add Loan Account")
  void addLoanAccount(@NegativeBalance final AccountDto loanAccount) {
    verifyPostResponse(
        ADD_LOAN_ACCOUNT,
        dtoToDtoSkipId.transform(loanAccount, AccountDto.class),
        AccountDto.class);
  }

  /** Test for {@link InvestmentController#getMiscellaneousAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Miscellaneous Accounts")
  void getMiscellaneousAccounts() {
    verifyGetListResponse(GET_MISC_ACCOUNTS, MiscellaneousDto.class);
  }

  /**
   * Test for {@link InvestmentController#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param miscellaneousDto the input / expected miscellaneous account
   */
  @Test
  @DisplayName("Happy Path: Add Miscellaneous Account")
  void addMiscellaneousAccount(final MiscellaneousDto miscellaneousDto) {
    verifyPostResponse(
        ADD_MISC_ACCOUNT,
        dtoToDtoSkipId.transform(miscellaneousDto, MiscellaneousDto.class),
        MiscellaneousDto.class);
  }

  /** Test for {@link InvestmentController#getMutualFunds()} */
  @Test
  @DisplayName("Happy Path: Get Mutual Funds")
  void getMutualFunds() {
    verifyGetListResponse(GET_MUTUAL_FUNDS, MutualFundDto.class);
  }

  /**
   * Test for {@link InvestmentController#addMutualFund(MutualFundDto)}
   *
   * @param mutualFundDto the input / expected mutual fund dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund")
  void addMutualFund(final MutualFundDto mutualFundDto) {
    verifyPostResponse(
        ADD_MUTUAL_FUND,
        dtoToDtoSkipId.transform(mutualFundDto, MutualFundDto.class),
        MutualFundDto.class);
  }

  /** Test for {@link InvestmentController#getMutualFundBuyTransactions()} */
  @Test
  @DisplayName("Happy Path: Get Mutual Fund Buy Transactions")
  void getMutualFundBuyTransactions() {
    verifyGetListResponse(GET_MF_BUY_TRANSACTIONS, MutualFundBuyTransactionDto.class);
  }

  /**
   * Test for {@link InvestmentController#addMutualFundBuyTransaction(MutualFundBuyTransactionDto)}
   *
   * @param mfBuyTransactionDto the input / expected mutual fund buy transaction dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Buy Transaction")
  void addMutualFundBuyTransaction(final MutualFundBuyTransactionDto mfBuyTransactionDto) {
    verifyPostResponse(
        ADD_MF_BUY_TRANSACTIONS,
        dtoToDtoSkipId.transform(mfBuyTransactionDto, MutualFundBuyTransactionDto.class),
        MutualFundBuyTransactionDto.class);
  }

  /** Test for {@link InvestmentController#getMutualFundSellTransactions()} */
  @Test
  @DisplayName("Happy Path: Get Mutual Fund Sell Transactions")
  void getMutualFundSellTransactions() {
    verifyGetListResponse(GET_MF_SELL_TRANSACTIONS, MutualFundSellTransactionDto.class);
  }

  /**
   * Test for {@link
   * InvestmentController#addMutualFundSellTransaction(MutualFundSellTransactionDto)}
   *
   * @param mfSellTransactionDto the input / expected mutual fund buy transaction dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Sell Transaction")
  void addMutualFundSellTransaction(final MutualFundSellTransactionDto mfSellTransactionDto) {
    verifyPostResponse(
        ADD_MF_SELL_TRANSACTIONS,
        dtoToDtoSkipId.transform(mfSellTransactionDto, MutualFundSellTransactionDto.class),
        MutualFundSellTransactionDto.class);
  }
  /** Test for {@link InvestmentController#getSavingAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getSavingAccounts() {
    verifyGetListResponse(GET_SAVING_ACCOUNTS, AccountDto.class);
  }

  /**
   * Test for {@link InvestmentController#addSavingAccount(AccountDto)}
   *
   * @param savingAccountDto the input / expected saving account dto
   */
  @Test
  @DisplayName("Happy Path: Add Saving Account")
  void addSavingAccount(final AccountDto savingAccountDto) {
    verifyPostResponse(
        ADD_SAVING_ACCOUNT,
        dtoToDtoSkipId.transform(savingAccountDto, AccountDto.class),
        AccountDto.class);
  }

  /** Test for {@link InvestmentController#getStocks()} */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getStocks() {
    verifyGetListResponse(GET_STOCKS, StockDto.class);
  }

  /**
   * Test for {@link InvestmentController#addStock(StockDto)}
   *
   * @param stockDto the input / expected stock dto
   */
  @Test
  @DisplayName("Happy Path: Add Stock")
  void addStock(final StockDto stockDto) {
    verifyPostResponse(
        ADD_STOCK, dtoToDtoSkipId.transform(stockDto, StockDto.class), StockDto.class);
  }
}
