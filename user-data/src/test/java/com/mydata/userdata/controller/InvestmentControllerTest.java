package com.mydata.userdata.controller;

import static com.mydata.userdata.common.ObjectProperties.ACCOUNT_ID;
import static com.mydata.userdata.common.URLs.*;
import static org.mockito.Mockito.*;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.service.InvestmentService;
import com.mydata.userdata.utils.GenerateTestPojo;
import com.mydata.utilities.test.conroller.ControllerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
@DisplayName("Investment Controller Test")
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

  /** Executes after each test */
  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(investmentService);
  }

  /** Test for {@link InvestmentController#getDepositAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Controller: Get Deposit Accounts")
  void getDepositAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getDepositAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(DEPOSIT_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getDepositAccounts();
  }

  /** Test for {@link InvestmentController#addDepositAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Deposit Account")
  void addDepositAccount() {
    var depositAccount = GenerateTestPojo.getSingleAccountDto();
    when(investmentService.addDepositAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(depositAccount));
    verifyPostAndDocument(
        DEPOSIT_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(depositAccount, AccountDto.class),
        depositAccount,
        AccountDto.class);
    verify(investmentService, times(1)).addDepositAccount(any(AccountDto.class));
  }

  /** Test for {@link InvestmentController#getLoanAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Loan Accounts")
  void getLoanAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getLoanAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(LOAN_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getLoanAccounts();
  }

  /** Test for {@link InvestmentController#addLoanAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Loan Account")
  void addLoanAccount() {
    var loanAccount = GenerateTestPojo.getSingleAccountDto();
    when(investmentService.addLoanAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(loanAccount));
    verifyPostAndDocument(
        LOAN_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(loanAccount, AccountDto.class),
        loanAccount,
        AccountDto.class);
    verify(investmentService, times(1)).addLoanAccount(any(AccountDto.class));
  }

  /** Test for {@link InvestmentController#getMiscellaneousAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Miscellaneous Accounts")
  void getMiscellaneousAccounts(final int count) {
    var accounts = GenerateTestPojo.getMiscellaneousDto(count);
    when(investmentService.getMiscellaneousAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(MISCELLANEOUS_ACCOUNTS_URL, accounts, MiscellaneousDto.class);
    verify(investmentService, times(1)).getMiscellaneousAccounts();
  }

  /**
   * Test for {@link InvestmentController#addMiscellaneousAccount(MiscellaneousDto)} (AccountDto)}
   */
  @Test
  @DisplayName("Happy Path: Add Miscellaneous Account")
  void addMiscellaneousAccount() {
    var miscellaneousDto = GenerateTestPojo.getSingleMiscellaneousDto();
    when(investmentService.addMiscellaneousAccount(any(MiscellaneousDto.class)))
        .thenReturn(Mono.just(miscellaneousDto));
    verifyPostAndDocument(
        MISCELLANEOUS_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(miscellaneousDto, MiscellaneousDto.class),
        miscellaneousDto,
        MiscellaneousDto.class);
    verify(investmentService, times(1)).addMiscellaneousAccount(any(MiscellaneousDto.class));
  }

  /** Test for {@link InvestmentController#getMutualFunds()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Mutual Funds")
  void getMutualFunds(final int count) {
    var mutualFunds = GenerateTestPojo.getMutualFundsDto(count);
    when(investmentService.getMutualFunds()).thenReturn(Flux.fromIterable(mutualFunds));
    verifyGetAndDocument(MUTUAL_FUNDS_URL, mutualFunds, MutualFundDto.class);
    verify(investmentService, times(1)).getMutualFunds();
  }

  /** Test for {@link InvestmentController#addMutualFund(MutualFundDto)} */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund")
  void addMutualFund() {
    var mutualFundDto = GenerateTestPojo.getSingleMutualFundDto();
    when(investmentService.addMutualFund(any(MutualFundDto.class)))
        .thenReturn(Mono.just(mutualFundDto));
    verifyPostAndDocument(
        MUTUAL_FUNDS_URL,
        dtoToDtoSkipId.transform(mutualFundDto, MutualFundDto.class),
        mutualFundDto,
        MutualFundDto.class);
    verify(investmentService, times(1)).addMutualFund(any(MutualFundDto.class));
  }

  /** Test for {@link InvestmentController#getSavingAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Saving Accounts")
  void getSavingAccounts(final int count) {
    var accounts = GenerateTestPojo.getAccountsDto(count);
    when(investmentService.getSavingAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(SAVING_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getSavingAccounts();
  }

  /** Test for {@link InvestmentController#addSavingAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Saving Account")
  void addSavingAccount() {
    var savingAccountDto = GenerateTestPojo.getSingleAccountDto();
    when(investmentService.addSavingAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(savingAccountDto));
    verifyPostAndDocument(
        SAVING_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(savingAccountDto, AccountDto.class),
        savingAccountDto,
        AccountDto.class);
    verify(investmentService, times(1)).addSavingAccount(any(AccountDto.class));
  }

  /** Test for {@link InvestmentController#getStocks()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Saving Accounts")
  void getStocks(final int count) {
    var stocks = GenerateTestPojo.getStocksDto(count);
    when(investmentService.getStocks()).thenReturn(Flux.fromIterable(stocks));
    verifyGetAndDocument(STOCKS_URL, stocks, StockDto.class);
    verify(investmentService, times(1)).getStocks();
  }

  /** Test for {@link InvestmentController#addStock(StockDto)} */
  @Test
  @DisplayName("Happy Path: Add Stock")
  void addStock() {
    var stocksDto = GenerateTestPojo.getSingleStockDto();
    when(investmentService.addStock(any(StockDto.class))).thenReturn(Mono.just(stocksDto));
    verifyPostAndDocument(
        STOCKS_URL, dtoToDtoSkipId.transform(stocksDto, StockDto.class), stocksDto, StockDto.class);
    verify(investmentService, times(1)).addStock(any(StockDto.class));
  }
}
