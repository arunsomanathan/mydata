package com.mydata.userdata.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.entity.*;
import com.mydata.userdata.repository.*;
import com.mydata.userdata.utils.InvestmentTestArguments;
import com.mydata.userdata.utils.InvestmentTestArguments.Mode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.junit.jupiter.MockitoSettings;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@MockitoSettings
@SpringJUnitConfig(classes = {InvestmentService.class})
@RequiredArgsConstructor
class InvestmentServiceTest {

  @Autowired private InvestmentService investService;

  @MockBean private DepositAccountRepository daRepo;
  @MockBean private LoanRepository loanRepo;
  @MockBean private MiscellaneousRepository miscRepo;
  @MockBean private MutualFundRepository mfRepo;
  @MockBean private SavingAccountRepository saRepo;
  @MockBean private StockRepository stockRepo;

  /** Executes after each test */
  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(daRepo, loanRepo, miscRepo, mfRepo, saRepo, stockRepo);
  }

  /**
   * Step Verifier
   *
   * @param publisher the publisher to be tested
   * @param dto the expected dto objects
   */
  private static <T> void stepVerify(Publisher<T> publisher, T... dto) {
    StepVerifier.create(publisher).expectNext(dto).verifyComplete();
  }

  /**
   * Test for {@link InvestmentService#getDepositAccounts()}
   *
   * @param depositAccounts list of deposit accounts to set in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Deposit Accounts")
  @InvestmentTestArguments(
      type = DepositAccount.class,
      mode = Mode.LIST,
      companion = AccountDto.class)
  void getDepositAccounts(
      final List<DepositAccount> depositAccounts, final List<AccountDto> expectedResult) {
    when(daRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(depositAccounts));
    stepVerify(investService.getDepositAccounts().log(), expectedResult.toArray(new AccountDto[0]));
    verify(daRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addDepositAccount(AccountDto)}
   *
   * @param depositAccount the deposit account
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Deposit Accounts")
  @InvestmentTestArguments(type = DepositAccount.class, companion = AccountDto.class)
  void addDepositAccount(final DepositAccount depositAccount, final AccountDto expectedResult) {
    when(daRepo.save(any(DepositAccount.class))).thenReturn(Mono.just(depositAccount));
    stepVerify(investService.addDepositAccount(expectedResult).log(), expectedResult);
    verify(daRepo, times(1)).save(any(DepositAccount.class));
  }

  /**
   * Test for {@link InvestmentService#getLoanAccounts()}
   *
   * @param loanAccounts list of loan accounts in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Loan Accounts")
  @InvestmentTestArguments(type = Loan.class, mode = Mode.LIST, companion = AccountDto.class)
  void getLoanAccounts(final List<Loan> loanAccounts, final List<AccountDto> expectedResult) {
    when(loanRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(loanAccounts));
    stepVerify(investService.getLoanAccounts().log(), expectedResult.toArray(new AccountDto[0]));
    verify(loanRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addLoanAccount(AccountDto)}
   *
   * @param loanAccount the deposit account
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Loan Accounts")
  @InvestmentTestArguments(type = Loan.class, companion = AccountDto.class)
  void addLoanAccount(final Loan loanAccount, final AccountDto expectedResult) {
    when(loanRepo.save(any(Loan.class))).thenReturn(Mono.just(loanAccount));
    stepVerify(investService.addLoanAccount(expectedResult).log(), expectedResult);
    verify(loanRepo, times(1)).save(any(Loan.class));
  }

  /**
   * Test for {@link InvestmentService#getMiscellaneousAccounts()}
   *
   * @param miscAccounts list of miscellaneous accounts in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Miscellaneous Accounts")
  @InvestmentTestArguments(
      type = Miscellaneous.class,
      mode = Mode.LIST,
      companion = MiscellaneousDto.class)
  void getMiscellaneousAccounts(
      final List<Miscellaneous> miscAccounts, final List<MiscellaneousDto> expectedResult) {
    when(miscRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(miscAccounts));
    stepVerify(
        investService.getMiscellaneousAccounts(), expectedResult.toArray(new MiscellaneousDto[0]));
    verify(miscRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param miscAccounts the miscellaneous account
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Miscellaneous Accounts")
  @InvestmentTestArguments(type = Miscellaneous.class, companion = MiscellaneousDto.class)
  void addMiscellaneousAccount(
      final Miscellaneous miscAccounts, final MiscellaneousDto expectedResult) {
    when(miscRepo.save(any(Miscellaneous.class))).thenReturn(Mono.just(miscAccounts));
    stepVerify(investService.addMiscellaneousAccount(expectedResult).log(), expectedResult);
    verify(miscRepo, times(1)).save(any(Miscellaneous.class));
  }

  /**
   * Test for {@link InvestmentService#getMutualFunds()}
   *
   * @param mutualFunds list of mutual funds in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Mutual Funds")
  @InvestmentTestArguments(
      type = MutualFund.class,
      mode = Mode.LIST,
      companion = MutualFundDto.class)
  void getMutualFunds(
      final List<MutualFund> mutualFunds, final List<MutualFundDto> expectedResult) {
    when(mfRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(mutualFunds));
    stepVerify(investService.getMutualFunds(), expectedResult.toArray(new MutualFundDto[0]));
    verify(mfRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addMutualFund(MutualFundDto)}
   *
   * @param mutualFund the mutual fund
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Mutual Fund")
  @InvestmentTestArguments(type = MutualFund.class, companion = MutualFundDto.class)
  void addMutualFund(final MutualFund mutualFund, final MutualFundDto expectedResult) {
    when(mfRepo.save(any(MutualFund.class))).thenReturn(Mono.just(mutualFund));
    stepVerify(investService.addMutualFund(expectedResult).log(), expectedResult);
    verify(mfRepo, times(1)).save(any(MutualFund.class));
  }

  /**
   * Test for {@link InvestmentService#getSavingAccounts()}
   *
   * @param savingAccounts list of saving accounts in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Saving Accounts")
  @InvestmentTestArguments(
      type = SavingAccount.class,
      mode = Mode.LIST,
      companion = AccountDto.class)
  void getSavingAccounts(
      final List<SavingAccount> savingAccounts, final List<AccountDto> expectedResult) {
    when(saRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(savingAccounts));
    stepVerify(investService.getSavingAccounts(), expectedResult.toArray(new AccountDto[0]));
    verify(saRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addSavingAccount(AccountDto)}
   *
   * @param savingAccount the saving account
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Saving Account")
  @InvestmentTestArguments(type = SavingAccount.class, companion = AccountDto.class)
  void addSavingAccount(final SavingAccount savingAccount, final AccountDto expectedResult) {
    when(saRepo.save(any(SavingAccount.class))).thenReturn(Mono.just(savingAccount));
    stepVerify(investService.addSavingAccount(expectedResult).log(), expectedResult);
    verify(saRepo, times(1)).save(any(SavingAccount.class));
  }

  /**
   * Test for {@link InvestmentService#getStocks()}
   *
   * @param stocks list of stocks in mock response
   * @param expectedResult list of expected objects
   */
  @ParameterizedTest(name = "Happy Path: Get Active Stocks")
  @InvestmentTestArguments(type = Stock.class, mode = Mode.LIST, companion = StockDto.class)
  void getStocks(final List<Stock> stocks, final List<StockDto> expectedResult) {
    when(stockRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stocks));
    stepVerify(investService.getStocks(), expectedResult.toArray(new StockDto[0]));
    verify(stockRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addStock(StockDto)}
   *
   * @param stock the stock
   * @param expectedResult the expected result object
   */
  @ParameterizedTest(name = "Happy Path: Add Stock")
  @InvestmentTestArguments(type = Stock.class, companion = StockDto.class)
  void addStock(final Stock stock, final StockDto expectedResult) {
    when(stockRepo.save(any(Stock.class))).thenReturn(Mono.just(stock));
    stepVerify(investService.addStock(expectedResult).log(), expectedResult);
    verify(stockRepo, times(1)).save(any(Stock.class));
  }
}
