package com.mydata.userdata.service;

import static com.mydata.userdata.utils.GenerateTestPojo.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.entity.*;
import com.mydata.userdata.repository.*;
import com.mydata.userdata.utils.GenerateTestPojo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoSettings;
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

  @Autowired private InvestmentService investmentService;

  @MockBean private DepositAccountRepository depositAccountRepository;
  @MockBean private LoanRepository loanRepository;
  @MockBean private MiscellaneousRepository miscellaneousRepository;
  @MockBean private MutualFundRepository mutualFundRepository;
  @MockBean private SavingAccountRepository savingAccountRepository;
  @MockBean private StockRepository stockRepository;

  /**
   * Test for {@link InvestmentService#getDepositAccounts()}
   *
   * @param count the number of Deposit Accounts to be returned
   */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Deposit Accounts")
  void getDepositAccounts(final int count) {
    var depositAccounts = GenerateTestPojo.getDepositAccounts(count);
    var accountsDto = getAccountsDto(depositAccounts);
    when(depositAccountRepository.findByActiveTrue())
        .thenReturn(Flux.fromIterable(depositAccounts));
    StepVerifier.create(investmentService.getDepositAccounts().log())
        .expectNext(accountsDto.toArray(new AccountDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addDepositAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Deposit Accounts")
  void addDepositAccount() {
    var depositAccount = GenerateTestPojo.getSingleDepositAccount();
    var accountsDto = getSingleAccountDto(depositAccount);
    when(depositAccountRepository.save(any(DepositAccount.class)))
        .thenReturn(Mono.just(depositAccount));
    StepVerifier.create(investmentService.addDepositAccount(accountsDto).log())
        .expectNext(accountsDto)
        .expectComplete()
        .verify();
  }

  /**
   * Test for {@link InvestmentService#getLoanAccounts()}
   *
   * @param count the number of Loan Accounts to be returned
   */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Loan Accounts")
  void getLoanAccounts(final int count) {
    var loanAccounts = GenerateTestPojo.getLoanAccounts(count);
    var accountsDto = getAccountsDto(loanAccounts);
    when(loanRepository.findByActiveTrue()).thenReturn(Flux.fromIterable(loanAccounts));
    StepVerifier.create(investmentService.getLoanAccounts().log())
        .expectNext(accountsDto.toArray(new AccountDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addLoanAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Loan Accounts")
  void addLoanAccount() {
    var loanAccount = GenerateTestPojo.getSingleLoanAccount();
    var accountsDto = getSingleAccountDto(loanAccount);
    when(loanRepository.save(any(Loan.class))).thenReturn(Mono.just(loanAccount));
    StepVerifier.create(investmentService.addLoanAccount(accountsDto).log())
        .expectNext(accountsDto)
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#getMiscellaneousAccounts()} */
  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Miscellaneous Accounts")
  void getMiscellaneousAccounts(final int count) {
    var miscellaneous = GenerateTestPojo.getMiscellaneous(count);
    var miscellaneousDto = getMiscellaneousDto(miscellaneous);
    when(miscellaneousRepository.findByActiveTrue()).thenReturn(Flux.fromIterable(miscellaneous));
    StepVerifier.create(investmentService.getMiscellaneousAccounts().log())
        .expectNext(miscellaneousDto.toArray(new MiscellaneousDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addMiscellaneousAccount(MiscellaneousDto)} */
  @Test
  @DisplayName("Happy Path: Add Miscellaneous Accounts")
  void addMiscellaneousAccount() {
    var miscellaneous = GenerateTestPojo.getSingleMiscellaneous();
    var miscellaneousDto = getSingleMiscellaneousDto(miscellaneous);
    when(miscellaneousRepository.save(any(Miscellaneous.class)))
        .thenReturn(Mono.just(miscellaneous));
    StepVerifier.create(investmentService.addMiscellaneousAccount(miscellaneousDto).log())
        .expectNext(miscellaneousDto)
        .expectComplete()
        .verify();
  }

  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Mutual Funds")
  void getMutualFund(final int count) {
    var mutualFunds = GenerateTestPojo.getMutualFunds(count);
    var mutualFundsDto = getMutualFundsDto(mutualFunds);
    when(mutualFundRepository.findByActiveTrue()).thenReturn(Flux.fromIterable(mutualFunds));
    StepVerifier.create(investmentService.getMutualFunds().log())
        .expectNext(mutualFundsDto.toArray(new MutualFundDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addMutualFund(MutualFundDto)} */
  @Test
  @DisplayName("Happy Path: Add Mutual Fund")
  void addMutualFund() {
    var mutualFund = GenerateTestPojo.getSingleMutualFund();
    var mutualFundDto = getSingleMutualFundDto(mutualFund);
    when(mutualFundRepository.save(any(MutualFund.class))).thenReturn(Mono.just(mutualFund));
    StepVerifier.create(investmentService.addMutualFund(mutualFundDto).log())
        .expectNext(mutualFundDto)
        .expectComplete()
        .verify();
  }

  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Saving Accounts")
  void getSavingAccounts(final int count) {
    var savingAccounts = GenerateTestPojo.getSavingAccounts(count);
    var accounts = getAccountsDto(savingAccounts);
    when(savingAccountRepository.findByActiveTrue()).thenReturn(Flux.fromIterable(savingAccounts));
    StepVerifier.create(investmentService.getSavingAccounts().log())
        .expectNext(accounts.toArray(new AccountDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addSavingAccount(AccountDto)} */
  @Test
  @DisplayName("Happy Path: Add Saving Account")
  void addSavingAccount() {
    var savingAccount = GenerateTestPojo.getSingleSavingAccount();
    var savingAccountDto = getSingleAccountDto(savingAccount);
    when(savingAccountRepository.save(any(SavingAccount.class)))
        .thenReturn(Mono.just(savingAccount));
    StepVerifier.create(investmentService.addSavingAccount(savingAccountDto).log())
        .expectNext(savingAccountDto)
        .expectComplete()
        .verify();
  }

  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("Happy Path: Get Active Stocks")
  void getStocks(final int count) {
    var stocks = GenerateTestPojo.getStocks(count);
    var stocksDto = getStocksDto(stocks);
    when(stockRepository.findByActiveTrue()).thenReturn(Flux.fromIterable(stocks));
    StepVerifier.create(investmentService.getStocks().log())
        .expectNext(stocksDto.toArray(new StockDto[0]))
        .expectComplete()
        .verify();
  }

  /** Test for {@link InvestmentService#addStock(StockDto)} */
  @Test
  @DisplayName("Happy Path: Add Stock")
  void addStock() {
    var stock = GenerateTestPojo.getSingleStock();
    var stockDto = getStockDto(stock);
    when(stockRepository.save(any(Stock.class))).thenReturn(Mono.just(stock));
    StepVerifier.create(investmentService.addStock(stockDto).log())
        .expectNext(stockDto)
        .expectComplete()
        .verify();
  }
}
