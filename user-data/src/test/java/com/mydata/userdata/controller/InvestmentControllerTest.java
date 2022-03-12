package com.mydata.userdata.controller;

import static com.mydata.userdata.common.ApiNames.*;
import static com.mydata.userdata.common.ApiUrls.INVESTMENT_BASE_URL;
import static com.mydata.userdata.common.ObjectProperties.ACCOUNT_ID;
import static com.mydata.userdata.common.TestConstants.API_NAME_URL_MAP;
import static org.mockito.Mockito.*;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.dto.*;
import com.mydata.userdata.service.InvestmentService;
import com.mydata.userdata.utils.InvestmentParameterResolver;
import com.mydata.userdata.utils.NegativeBalance;
import com.mydata.utilities.test.conroller.ControllerTest;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Test class for {@link InvestmentController} */
@MockitoSettings
@WebFluxTest(controllers = InvestmentController.class)
@AutoConfigureRestDocs
@ExtendWith(InvestmentParameterResolver.class)
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class InvestmentControllerTest implements ControllerTest {

  private final BeanTransformer dtoToDtoSkipId =
      new BeanUtils().getTransformer().skipTransformationForField(ACCOUNT_ID);
  @Autowired private WebTestClient webTestClient;
  @MockBean private InvestmentService investmentService;

  @Override
  public WebTestClient getWebTestClient() {
    return webTestClient;
  }

  @Override
  public String getBaseUrl() {
    return INVESTMENT_BASE_URL;
  }

  @Override
  public String getApiUrlFromApiName(final String apiName) {
    return API_NAME_URL_MAP.get(apiName);
  }

  /** Executes after each test */
  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(investmentService);
  }

  /**
   * Test for {@link InvestmentController#getDepositAccounts()}
   *
   * @param accounts list of expected deposit accounts
   */
  @Test
  @DisplayName("Happy Path: Get Deposit Accounts")
  void getDepositAccounts(final List<AccountDto> accounts) {
    when(investmentService.getDepositAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(GET_DEPOSIT_ACCOUNTS, accounts, AccountDto.class);
    verify(investmentService, times(1)).getDepositAccounts();
  }

  /**
   * Test for {@link InvestmentController#addDepositAccount(AccountDto)}
   *
   * @param depositAccount the expected deposit account
   */
  @Test
  @DisplayName("Happy Path: Add Deposit Account")
  void addDepositAccount(final AccountDto depositAccount) {
    when(investmentService.addDepositAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(depositAccount));
    verifyPostAndDocument(ADD_DEPOSIT_ACCOUNT, depositAccount, AccountDto.class);
    verify(investmentService, times(1)).addDepositAccount(any(AccountDto.class));
  }

  /**
   * Verify Post request and document
   *
   * @param url the request url
   * @param expected the expected object
   * @param expectedType the expected object type
   * @param <T> Type of expected objet
   */
  private <T> void verifyPostAndDocument(String url, T expected, Class<T> expectedType) {
    verifyPostAndDocument(
        url, dtoToDtoSkipId.transform(expected, expectedType), expected, expectedType);
  }

  /**
   * Test for {@link InvestmentController#getLoanAccounts()}
   *
   * @param accounts list of expected loan accounts
   */
  @Test
  @DisplayName("Happy Path: Get Loan Accounts")
  void getLoanAccounts(@NegativeBalance final List<AccountDto> accounts) {
    when(investmentService.getLoanAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(GET_LOAN_ACCOUNTS, accounts, AccountDto.class);
    verify(investmentService, times(1)).getLoanAccounts();
  }

  /**
   * Test for {@link InvestmentController#addLoanAccount(AccountDto)}
   *
   * @param loanAccount the expected loan account
   */
  @Test
  @DisplayName("Happy Path: Add Loan Account")
  void addLoanAccount(@NegativeBalance final AccountDto loanAccount) {
    when(investmentService.addLoanAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(loanAccount));
    verifyPostAndDocument(ADD_LOAN_ACCOUNT, loanAccount, AccountDto.class);
    verify(investmentService, times(1)).addLoanAccount(any(AccountDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMiscellaneousAccounts()}
   *
   * @param accounts list of expected miscellaneous accounts
   */
  @Test
  @DisplayName("Happy Path: Get Miscellaneous Accounts")
  void getMiscellaneousAccounts(final List<MiscellaneousDto> accounts) {
    when(investmentService.getMiscellaneousAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(GET_MISC_ACCOUNTS, accounts, MiscellaneousDto.class);
    verify(investmentService, times(1)).getMiscellaneousAccounts();
  }

  /**
   * Test for {@link InvestmentController#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param miscellaneousDto the expected miscellaneous dto
   */
  @Test
  @DisplayName("Happy Path: Add Miscellaneous Account")
  void addMiscellaneousAccount(final MiscellaneousDto miscellaneousDto) {
    when(investmentService.addMiscellaneousAccount(any(MiscellaneousDto.class)))
        .thenReturn(Mono.just(miscellaneousDto));
    verifyPostAndDocument(ADD_MISC_ACCOUNT, miscellaneousDto, MiscellaneousDto.class);
    verify(investmentService, times(1)).addMiscellaneousAccount(any(MiscellaneousDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMutualFunds()}
   *
   * @param mutualFunds list of expected mutual funds
   */
  @Test
  @DisplayName("Happy Path: Get Mutual Funds")
  void getMutualFunds(final List<MutualFundDto> mutualFunds) {
    when(investmentService.getMutualFunds()).thenReturn(Flux.fromIterable(mutualFunds));
    verifyGetAndDocument(GET_MUTUAL_FUNDS, mutualFunds, MutualFundDto.class);
    verify(investmentService, times(1)).getMutualFunds();
  }

  /**
   * Test for {@link InvestmentController#addMutualFund(MutualFundDto)}
   *
   * @param mutualFundDto the expected mutual fund dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund")
  void addMutualFund(final MutualFundDto mutualFundDto) {
    when(investmentService.addMutualFund(any(MutualFundDto.class)))
        .thenReturn(Mono.just(mutualFundDto));
    verifyPostAndDocument(ADD_MUTUAL_FUND, mutualFundDto, MutualFundDto.class);
    verify(investmentService, times(1)).addMutualFund(any(MutualFundDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMutualFundBuyTransactions()}
   *
   * @param mutualFundBuyTransactions list of expected mutual fund buy transactions.
   */
  @Test
  @DisplayName("Happy Path: Get Mutual Fund Buy Transactions")
  void getMutualFundBuyTransactions(
      final List<MutualFundBuyTransactionDto> mutualFundBuyTransactions) {
    when(investmentService.getMutualFundBuyTransactions(Boolean.FALSE))
        .thenReturn(Flux.fromIterable(mutualFundBuyTransactions));
    verifyGetAndDocument(
        GET_MF_BUY_TRANSACTIONS, mutualFundBuyTransactions, MutualFundBuyTransactionDto.class);
    verify(investmentService, times(1)).getMutualFundBuyTransactions(any(Boolean.class));
  }

  /**
   * Test for {@link InvestmentController#addMutualFundBuyTransaction(MutualFundBuyTransactionDto)}
   *
   * @param mfBuyTransactionDto the expected mutual fund dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Buy Transaction")
  void addMutualFundBuyTransaction(final MutualFundBuyTransactionDto mfBuyTransactionDto) {
    when(investmentService.addMutualFundBuyTransaction(any(MutualFundBuyTransactionDto.class)))
        .thenReturn(Mono.just(mfBuyTransactionDto));
    verifyPostAndDocument(
        ADD_MF_BUY_TRANSACTIONS, mfBuyTransactionDto, MutualFundBuyTransactionDto.class);
    verify(investmentService, times(1))
        .addMutualFundBuyTransaction(any(MutualFundBuyTransactionDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMutualFundSellTransactions()}
   *
   * @param mfSellTransactions list of expected mutual fund sell transactions
   */
  @Test
  @DisplayName("Happy Path: Get Mutual Fund Sell Transactions")
  void getMutualFundSellTransactions(final List<MutualFundSellTransactionDto> mfSellTransactions) {
    when(investmentService.getMutualFundSellTransactions())
        .thenReturn(Flux.fromIterable(mfSellTransactions));
    verifyGetAndDocument(
        GET_MF_SELL_TRANSACTIONS, mfSellTransactions, MutualFundSellTransactionDto.class);
    verify(investmentService, times(1)).getMutualFundSellTransactions();
  }

  /**
   * Test for {@link
   * InvestmentController#addMutualFundSellTransaction(MutualFundSellTransactionDto)}
   *
   * @param mfSellTransactionDto the expected mutual fund dto
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Sell Transaction")
  void addMutualFundSellTransaction(final MutualFundSellTransactionDto mfSellTransactionDto) {
    when(investmentService.addMutualFundSellTransaction(any(MutualFundSellTransactionDto.class)))
        .thenReturn(Mono.just(mfSellTransactionDto));
    verifyPostAndDocument(
        ADD_MF_SELL_TRANSACTIONS, mfSellTransactionDto, MutualFundSellTransactionDto.class);
    verify(investmentService, times(1))
        .addMutualFundSellTransaction(any(MutualFundSellTransactionDto.class));
  }
  /**
   * Test for {@link InvestmentController#getSavingAccounts()}
   *
   * @param accounts list of expected saving accounts
   */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getSavingAccounts(final List<AccountDto> accounts) {
    when(investmentService.getSavingAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(GET_SAVING_ACCOUNTS, accounts, AccountDto.class);
    verify(investmentService, times(1)).getSavingAccounts();
  }

  /**
   * Test for {@link InvestmentController#addSavingAccount(AccountDto)}
   *
   * @param savingAccountDto the expected saving account dto
   */
  @Test
  @DisplayName("Happy Path: Add Saving Account")
  void addSavingAccount(final AccountDto savingAccountDto) {
    when(investmentService.addSavingAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(savingAccountDto));
    verifyPostAndDocument(ADD_SAVING_ACCOUNT, savingAccountDto, AccountDto.class);
    verify(investmentService, times(1)).addSavingAccount(any(AccountDto.class));
  }

  /**
   * Test for {@link InvestmentController#getStocks()}
   *
   * @param stocks list of expected stocks
   */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getStocks(final List<StockDto> stocks) {
    when(investmentService.getStocks()).thenReturn(Flux.fromIterable(stocks));
    verifyGetAndDocument(GET_STOCKS, stocks, StockDto.class);
    verify(investmentService, times(1)).getStocks();
  }

  /**
   * Test for {@link InvestmentController#addStock(StockDto)}
   *
   * @param stockDto the expected stock dto
   */
  @Test
  @DisplayName("Happy Path: Add Stock")
  void addStock(final StockDto stockDto) {
    when(investmentService.addStock(any(StockDto.class))).thenReturn(Mono.just(stockDto));
    verifyPostAndDocument(
        ADD_STOCK, dtoToDtoSkipId.transform(stockDto, StockDto.class), stockDto, StockDto.class);
    verify(investmentService, times(1)).addStock(any(StockDto.class));
  }
}
