package com.mydata.userdata.service;

import static org.junit.jupiter.api.Named.named;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.transformer.model.FieldTransformer;
import com.mydata.userdata.dto.*;
import com.mydata.userdata.entity.*;
import com.mydata.userdata.repository.*;
import com.mydata.userdata.utils.GenerateFrom;
import com.mydata.userdata.utils.InvestmentParameterResolver;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
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
@ExtendWith(InvestmentParameterResolver.class)
class InvestmentServiceTest {

  public static final String STUB_RESPONSE = "stubResponse";

  @Autowired private InvestmentService investService;

  @MockBean private DepositAccountRepository daRepo;
  @MockBean private LoanRepository loanRepo;
  @MockBean private MiscellaneousRepository miscRepo;
  @MockBean private MutualFundRepository mfRepo;
  @MockBean private MutualFundBuyTransactionRepository mfBuyTranRepo;
  @MockBean private MutualFundSellTransactionRepository mfSellTranRepo;
  @MockBean private SavingAccountRepository saRepo;
  @MockBean private StockRepository stockRepo;

  /** Executes after each test */
  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(daRepo, loanRepo, miscRepo, mfRepo, saRepo, stockRepo);
  }

  /**
   * Test for {@link InvestmentService#getDepositAccounts()}
   *
   * @param stubResponse list of deposit accounts to set in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Deposit Accounts")
  void getDepositAccounts(
      final List<DepositAccount> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<AccountDto> expectedResult) {
    when(daRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getDepositAccounts(), expectedResult);
    verify(daRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Step Verifier
   *
   * @param publisher the publisher to be tested
   * @param expectedList the expected list of objects
   */
  private static <T> void stepVerify(Publisher<T> publisher, Iterable<T> expectedList) {
    StepVerifier.create(publisher).expectNextSequence(expectedList).verifyComplete();
  }

  /**
   * Test for {@link InvestmentService#addDepositAccount(AccountDto)}
   *
   * @param stubResponse the deposit account
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Deposit Accounts")
  void addDepositAccount(
      final DepositAccount stubResponse,
      @GenerateFrom(STUB_RESPONSE) final AccountDto expectedResult) {
    when(daRepo.save(any(DepositAccount.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addDepositAccount(expectedResult), expectedResult);
    verify(daRepo, times(1)).save(any(DepositAccount.class));
  }

  /**
   * Step Verifier
   *
   * @param publisher the publisher to be tested
   * @param expected the expected object
   */
  private static <T> void stepVerify(Publisher<T> publisher, T expected) {
    stepVerify(publisher, List.of(expected));
  }

  /**
   * Test for {@link InvestmentService#getLoanAccounts()}
   *
   * @param stubResponse list of loan accounts in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Loan Accounts")
  void getLoanAccounts(
      final List<Loan> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<AccountDto> expectedResult) {
    when(loanRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getLoanAccounts(), expectedResult);
    verify(loanRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addLoanAccount(AccountDto)}
   *
   * @param stubResponse the deposit account
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Loan Accounts")
  void addLoanAccount(
      final Loan stubResponse, @GenerateFrom(STUB_RESPONSE) final AccountDto expectedResult) {
    when(loanRepo.save(any(Loan.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addLoanAccount(expectedResult), expectedResult);
    verify(loanRepo, times(1)).save(any(Loan.class));
  }

  /**
   * Test for {@link InvestmentService#getMiscellaneousAccounts()}
   *
   * @param stubResponse list of miscellaneous accounts in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Miscellaneous Accounts")
  void getMiscellaneousAccounts(
      final List<Miscellaneous> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<MiscellaneousDto> expectedResult) {
    when(miscRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getMiscellaneousAccounts(), expectedResult);
    verify(miscRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param stubResponse the miscellaneous account
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Miscellaneous Accounts")
  void addMiscellaneousAccount(
      final Miscellaneous stubResponse,
      @GenerateFrom(STUB_RESPONSE) final MiscellaneousDto expectedResult) {
    when(miscRepo.save(any(Miscellaneous.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addMiscellaneousAccount(expectedResult), expectedResult);
    verify(miscRepo, times(1)).save(any(Miscellaneous.class));
  }

  /**
   * Test for {@link InvestmentService#getMutualFunds()}
   *
   * @param stubResponse list of mutual funds in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Mutual Funds")
  void getMutualFunds(
      final List<MutualFund> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<MutualFundDto> expectedResult) {
    when(mfRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getMutualFunds(), expectedResult);
    verify(mfRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addMutualFund(MutualFundDto)}
   *
   * @param stubResponse the mutual fund
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund")
  void addMutualFund(
      final MutualFund stubResponse,
      @GenerateFrom(STUB_RESPONSE) final MutualFundDto expectedResult) {
    when(mfRepo.save(any(MutualFund.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addMutualFund(expectedResult), expectedResult);
    verify(mfRepo, times(1)).save(any(MutualFund.class));
  }

  /**
   * Test for {@link InvestmentService#getMutualFundBuyTransactions(Boolean)}
   *
   * @param stubRespTemp list of {@link MutualFundBuyTransaction} in mock response
   * @param expResTemp list of expected {@link MutualFundBuyTransactionDto} objects
   * @return {@link DynamicTest}
   */
  @TestFactory
  Stream<DynamicTest> getMutualFundBuyTransactions(
      final List<MutualFundBuyTransaction> stubRespTemp,
      @GenerateFrom("stubRespTemp") final List<MutualFundBuyTransactionDto> expResTemp) {

    final var disName = "Happy Path: Get Mutual Fund Buy Transaction by Is Sold Out value : ";

    var inputStream =
        Stream.of(
            named(disName + Boolean.TRUE, Boolean.TRUE),
            named(disName + Boolean.FALSE, Boolean.FALSE));

    ThrowingConsumer<Boolean> testExecutor =
        (isSoldOut) -> {
          @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
          final FieldTransformer<Boolean, Boolean> ft =
              new FieldTransformer<>("isSoldOut", () -> isSoldOut);
          var stubResponse =
              stubRespTemp.stream()
                  .map(
                      e ->
                          new BeanUtils()
                              .getTransformer()
                              .withFieldTransformer(ft)
                              .transform(e, MutualFundBuyTransaction.class))
                  .toList();
          var expectedResult =
              expResTemp.stream()
                  .map(
                      e ->
                          new BeanUtils()
                              .getTransformer()
                              .withFieldTransformer(ft)
                              .transform(e, MutualFundBuyTransactionDto.class))
                  .toList();
          when(mfBuyTranRepo.findByIsSoldOut(isSoldOut))
              .thenReturn(Flux.fromIterable(stubResponse));
          stepVerify(investService.getMutualFundBuyTransactions(isSoldOut), expectedResult);
          verify(mfBuyTranRepo, times(1)).findByIsSoldOut(isSoldOut);
        };
    return DynamicTest.stream(inputStream, testExecutor);
  }

  /**
   * Test for {@link InvestmentService#addMutualFundBuyTransaction(MutualFundBuyTransactionDto)}
   * (MutualFundBuyTransactionDto)}
   *
   * @param stubResponse list of {@link MutualFundBuyTransaction} in mock response
   * @param expectedResult list of expected {@link MutualFundBuyTransactionDto} objects
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Buy Transaction")
  void addMutualFundBuyTransactions(
      final MutualFundBuyTransaction stubResponse,
      @GenerateFrom(STUB_RESPONSE) final MutualFundBuyTransactionDto expectedResult) {
    when(mfBuyTranRepo.save(any(MutualFundBuyTransaction.class)))
        .thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addMutualFundBuyTransaction(expectedResult), expectedResult);
    verify(mfBuyTranRepo, times(1)).save(any(MutualFundBuyTransaction.class));
  }

  /**
   * Test for {@link InvestmentService#addMutualFundSellTransaction(MutualFundSellTransactionDto)}
   *
   * @param stubResponse list of {@link MutualFundSellTransaction} in mock response
   * @param expectedResult list of expected {@link MutualFundSellTransactionDto} objects
   */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund Sell Transaction")
  void addMutualFundSellTransactions(
      final MutualFundSellTransaction stubResponse,
      @GenerateFrom(STUB_RESPONSE) final MutualFundSellTransactionDto expectedResult) {
    when(mfSellTranRepo.save(any(MutualFundSellTransaction.class)))
        .thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addMutualFundSellTransaction(expectedResult), expectedResult);
    verify(mfSellTranRepo, times(1)).save(any(MutualFundSellTransaction.class));
  }

  /**
   * Test for {@link InvestmentService#getSavingAccounts()}
   *
   * @param stubResponse list of saving accounts in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Saving Accounts")
  void getSavingAccounts(
      final List<SavingAccount> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<AccountDto> expectedResult) {
    when(saRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getSavingAccounts(), expectedResult);
    verify(saRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addSavingAccount(AccountDto)}
   *
   * @param stubResponse the saving account
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Saving Account")
  void addSavingAccount(
      final SavingAccount stubResponse,
      @GenerateFrom(STUB_RESPONSE) final AccountDto expectedResult) {
    when(saRepo.save(any(SavingAccount.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addSavingAccount(expectedResult), expectedResult);
    verify(saRepo, times(1)).save(any(SavingAccount.class));
  }

  /**
   * Test for {@link InvestmentService#getStocks()}
   *
   * @param stubResponse list of stocks in mock response
   * @param expectedResult list of expected objects
   */
  @Test
  @DisplayName("Happy Path: Get Active Stocks")
  void getStocks(
      final List<Stock> stubResponse,
      @GenerateFrom(STUB_RESPONSE) final List<StockDto> expectedResult) {
    when(stockRepo.findByActive(Boolean.TRUE)).thenReturn(Flux.fromIterable(stubResponse));
    stepVerify(investService.getStocks(), expectedResult);
    verify(stockRepo, times(1)).findByActive(Boolean.TRUE);
  }

  /**
   * Test for {@link InvestmentService#addStock(StockDto)}
   *
   * @param stubResponse the stock
   * @param expectedResult the expected result object
   */
  @Test
  @DisplayName("Happy Path: Add Stock")
  void addStock(
      final Stock stubResponse, @GenerateFrom(STUB_RESPONSE) final StockDto expectedResult) {
    when(stockRepo.save(any(Stock.class))).thenReturn(Mono.just(stubResponse));
    stepVerify(investService.addStock(expectedResult), expectedResult);
    verify(stockRepo, times(1)).save(any(Stock.class));
  }
}
