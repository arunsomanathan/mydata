package com.mydata.userdata.service;

import static com.mydata.userdata.utils.GenerateTestPojo.*;
import static org.mockito.Mockito.when;

import com.mydata.userdata.config.UserDataConfig;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.repository.*;
import com.mydata.userdata.utils.GenerateTestPojo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@MockitoSettings
@SpringJUnitConfig(classes = {InvestmentService.class, UserDataConfig.class})
@RequiredArgsConstructor
class InvestmentServiceTest {

  @Autowired private InvestmentService investmentService;

  @MockBean private DepositAccountRepository depositAccountRepository;
  @MockBean private LoanRepository loanRepository;
  @MockBean private MiscellaneousRepository miscellaneousRepository;
  @MockBean private MutualFundRepository mutualFundRepository;
  @MockBean private SavingAccountRepository savingAccountRepository;
  @MockBean private StockRepository stockRepository;

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
}
