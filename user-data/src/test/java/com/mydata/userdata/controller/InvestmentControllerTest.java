package com.mydata.userdata.controller;

import static com.mydata.userdata.utils.WebTestClientUtil.assertBodyEquals;
import static com.mydata.userdata.utils.WebTestClientUtil.get;
import static org.mockito.Mockito.*;

import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.service.InvestmentService;
import com.mydata.userdata.utils.GenerateTestPojo;
import com.mydata.userdata.utils.WebTestClientUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/** Test class for {@link InvestmentController} */
@MockitoSettings
@WebFluxTest(controllers = InvestmentController.class)
@AutoConfigureRestDocs
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class InvestmentControllerTest {

  private static final String INVESTMENT_BASE_URL = "/investment";

  private static final String DEPOSIT_ACCOUNTS_URL = "depositaccounts";
  private static final String LOAN_ACCOUNTS_URL = "loanaccounts";
  private static final String MISCELLANEOUS_ACCOUNTS_URL = "miscellaneousaccounts";
  private static final String MUTUAL_FUNDS_URL = "mutualfunds";
  private static final String SAVING_ACCOUNTS_URL = "savingaccounts";
  private static final String STOCKS_URL = "stocks";

  @Autowired private WebTestClient webTestClient;

  @MockBean private InvestmentService investmentService;

  /** Executes after each test */
  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(investmentService);
  }

  /** Test for {@link InvestmentController#getDepositAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Deposit Accounts")
  void getDepositAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getDepositAccounts()).thenReturn(Flux.fromIterable(accounts));
    getResponse(DEPOSIT_ACCOUNTS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec ->
                assertBodyEquals(responseSpec, AccountDto.class, accounts, DEPOSIT_ACCOUNTS_URL));
    verify(investmentService, times(1)).getDepositAccounts();
  }

  /** Get Request to given url */
  private WebTestClient.ResponseSpec getResponse(final String apiUrl) {
    return get(webTestClient, INVESTMENT_BASE_URL, apiUrl);
  }

  /** Test for {@link InvestmentController#getLoanAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Loan Accounts")
  void getLoanAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getLoanAccounts()).thenReturn(Flux.fromIterable(accounts));
    getResponse(LOAN_ACCOUNTS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec ->
                assertBodyEquals(responseSpec, AccountDto.class, accounts, LOAN_ACCOUNTS_URL));
    verify(investmentService, times(1)).getLoanAccounts();
  }

  /** Test for {@link InvestmentController#getMiscellaneousAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Miscellaneous Accounts")
  void getMiscellaneousAccounts(final int count) {
    var accounts = GenerateTestPojo.getMiscellaneousDto(count);
    when(investmentService.getMiscellaneousAccounts()).thenReturn(Flux.fromIterable(accounts));
    getResponse(MISCELLANEOUS_ACCOUNTS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec ->
                assertBodyEquals(
                    responseSpec, MiscellaneousDto.class, accounts, MISCELLANEOUS_ACCOUNTS_URL));
    verify(investmentService, times(1)).getMiscellaneousAccounts();
  }

  /** Test for {@link InvestmentController#getMiscellaneousAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Mutual Funds")
  void getMutualFunds(final int count) {
    var mutualFunds = GenerateTestPojo.getMutualFundsDto(count);
    when(investmentService.getMutualFunds()).thenReturn(Flux.fromIterable(mutualFunds));
    getResponse(MUTUAL_FUNDS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec ->
                assertBodyEquals(responseSpec, MutualFundDto.class, mutualFunds, MUTUAL_FUNDS_URL));
    verify(investmentService, times(1)).getMutualFunds();
  }

  /** Test for {@link InvestmentController#getSavingAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Saving Accounts")
  void getSavingAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getSavingAccounts()).thenReturn(Flux.fromIterable(accounts));
    getResponse(SAVING_ACCOUNTS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec ->
                assertBodyEquals(responseSpec, AccountDto.class, accounts, SAVING_ACCOUNTS_URL));
    verify(investmentService, times(1)).getSavingAccounts();
  }

  /** Test for {@link InvestmentController#getStocks()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Saving Accounts")
  void getStocks(final int count) {
    var stocks = GenerateTestPojo.getStocksDto(count);
    when(investmentService.getStocks()).thenReturn(Flux.fromIterable(stocks));
    getResponse(STOCKS_URL)
        .expectAll(
            WebTestClientUtil::isOk,
            WebTestClientUtil::isContentTypeJson,
            responseSpec -> assertBodyEquals(responseSpec, StockDto.class, stocks, STOCKS_URL));
    verify(investmentService, times(1)).getStocks();
  }
}
