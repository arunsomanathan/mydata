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
import com.mydata.userdata.utils.InvestmentTestArguments;
import com.mydata.userdata.utils.InvestmentTestArguments.Mode;
import com.mydata.utilities.test.conroller.ControllerTest;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
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

  /**
   * Test for {@link InvestmentController#getDepositAccounts()}
   *
   * @param accounts list of expected deposit accounts
   */
  @ParameterizedTest(name = "Happy Path: Controller: Get Deposit Accounts")
  @InvestmentTestArguments(type = AccountDto.class, mode = Mode.LIST)
  void getDepositAccounts(final List<AccountDto> accounts) {
    when(investmentService.getDepositAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(DEPOSIT_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getDepositAccounts();
  }

  /**
   * Test for {@link InvestmentController#addDepositAccount(AccountDto)}
   *
   * @param depositAccount the expected deposit account
   */
  @ParameterizedTest(name = "Happy Path: Add Deposit Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addDepositAccount(final AccountDto depositAccount) {
    when(investmentService.addDepositAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(depositAccount));
    verifyPostAndDocument(
        DEPOSIT_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(depositAccount, AccountDto.class),
        depositAccount,
        AccountDto.class);
    verify(investmentService, times(1)).addDepositAccount(any(AccountDto.class));
  }

  /**
   * Test for {@link InvestmentController#getLoanAccounts()}
   *
   * @param accounts list of expected loan accounts
   */
  @ParameterizedTest(name = "Happy Path: Get Loan Accounts")
  @InvestmentTestArguments(type = AccountDto.class, mode = Mode.LIST)
  void getLoanAccounts(final List<AccountDto> accounts) {
    when(investmentService.getLoanAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(LOAN_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getLoanAccounts();
  }

  /**
   * Test for {@link InvestmentController#addLoanAccount(AccountDto)}
   *
   * @param loanAccount the expected loan account
   */
  @ParameterizedTest(name = "Happy Path: Add Loan Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addLoanAccount(final AccountDto loanAccount) {
    when(investmentService.addLoanAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(loanAccount));
    verifyPostAndDocument(
        LOAN_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(loanAccount, AccountDto.class),
        loanAccount,
        AccountDto.class);
    verify(investmentService, times(1)).addLoanAccount(any(AccountDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMiscellaneousAccounts()}
   *
   * @param accounts list of expected miscellaneous accounts
   */
  @ParameterizedTest(name = "Happy Path: Get Miscellaneous Accounts")
  @InvestmentTestArguments(type = MiscellaneousDto.class, mode = Mode.LIST)
  void getMiscellaneousAccounts(final List<MiscellaneousDto> accounts) {
    when(investmentService.getMiscellaneousAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(MISCELLANEOUS_ACCOUNTS_URL, accounts, MiscellaneousDto.class);
    verify(investmentService, times(1)).getMiscellaneousAccounts();
  }

  /**
   * Test for {@link InvestmentController#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param miscellaneousDto the expected miscellaneous dto
   */
  @ParameterizedTest(name = "Happy Path: Add Miscellaneous Account")
  @InvestmentTestArguments(type = MiscellaneousDto.class)
  void addMiscellaneousAccount(final MiscellaneousDto miscellaneousDto) {
    when(investmentService.addMiscellaneousAccount(any(MiscellaneousDto.class)))
        .thenReturn(Mono.just(miscellaneousDto));
    verifyPostAndDocument(
        MISCELLANEOUS_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(miscellaneousDto, MiscellaneousDto.class),
        miscellaneousDto,
        MiscellaneousDto.class);
    verify(investmentService, times(1)).addMiscellaneousAccount(any(MiscellaneousDto.class));
  }

  /**
   * Test for {@link InvestmentController#getMutualFunds()}
   *
   * @param mutualFunds list of expected mutual funds
   */
  @ParameterizedTest(name = "Happy Path: Get Mutual Funds")
  @InvestmentTestArguments(type = MutualFundDto.class, mode = Mode.LIST)
  void getMutualFunds(final List<MutualFundDto> mutualFunds) {
    when(investmentService.getMutualFunds()).thenReturn(Flux.fromIterable(mutualFunds));
    verifyGetAndDocument(MUTUAL_FUNDS_URL, mutualFunds, MutualFundDto.class);
    verify(investmentService, times(1)).getMutualFunds();
  }

  /**
   * Test for {@link InvestmentController#addMutualFund(MutualFundDto)}
   *
   * @param mutualFundDto the expected mutual fund dto
   */
  @ParameterizedTest(name = "Happy Path: Add Mutual Fund")
  @InvestmentTestArguments(type = MutualFundDto.class)
  void addMutualFund(final MutualFundDto mutualFundDto) {
    when(investmentService.addMutualFund(any(MutualFundDto.class)))
        .thenReturn(Mono.just(mutualFundDto));
    verifyPostAndDocument(
        MUTUAL_FUNDS_URL,
        dtoToDtoSkipId.transform(mutualFundDto, MutualFundDto.class),
        mutualFundDto,
        MutualFundDto.class);
    verify(investmentService, times(1)).addMutualFund(any(MutualFundDto.class));
  }

  /**
   * Test for {@link InvestmentController#getSavingAccounts()}
   *
   * @param accounts list of expected saving accounts
   */
  @ParameterizedTest(name = "Happy Path: Get Saving Accounts")
  @InvestmentTestArguments(type = AccountDto.class, mode = Mode.LIST)
  void getSavingAccounts(final List<AccountDto> accounts) {
    when(investmentService.getSavingAccounts()).thenReturn(Flux.fromIterable(accounts));
    verifyGetAndDocument(SAVING_ACCOUNTS_URL, accounts, AccountDto.class);
    verify(investmentService, times(1)).getSavingAccounts();
  }

  /**
   * Test for {@link InvestmentController#addSavingAccount(AccountDto)}
   *
   * @param savingAccountDto the expected saving account dto
   */
  @ParameterizedTest(name = "Happy Path: Add Saving Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addSavingAccount(final AccountDto savingAccountDto) {
    when(investmentService.addSavingAccount(any(AccountDto.class)))
        .thenReturn(Mono.just(savingAccountDto));
    verifyPostAndDocument(
        SAVING_ACCOUNTS_URL,
        dtoToDtoSkipId.transform(savingAccountDto, AccountDto.class),
        savingAccountDto,
        AccountDto.class);
    verify(investmentService, times(1)).addSavingAccount(any(AccountDto.class));
  }

  /**
   * Test for {@link InvestmentController#getStocks()}
   *
   * @param stocks list of expected stocks
   */
  @ParameterizedTest(name = "Happy Path: Get Saving Accounts")
  @InvestmentTestArguments(type = StockDto.class, mode = Mode.LIST)
  void getStocks(final List<StockDto> stocks) {
    when(investmentService.getStocks()).thenReturn(Flux.fromIterable(stocks));
    verifyGetAndDocument(STOCKS_URL, stocks, StockDto.class);
    verify(investmentService, times(1)).getStocks();
  }

  /**
   * Test for {@link InvestmentController#addStock(StockDto)}
   *
   * @param stockDto the expected stock dto
   */
  @ParameterizedTest(name = "Happy Path: Add Stock")
  @InvestmentTestArguments(type = StockDto.class)
  void addStock(final StockDto stockDto) {
    when(investmentService.addStock(any(StockDto.class))).thenReturn(Mono.just(stockDto));
    verifyPostAndDocument(
        STOCKS_URL, dtoToDtoSkipId.transform(stockDto, StockDto.class), stockDto, StockDto.class);
    verify(investmentService, times(1)).addStock(any(StockDto.class));
  }
}
