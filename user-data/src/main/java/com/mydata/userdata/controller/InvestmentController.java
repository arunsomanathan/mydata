package com.mydata.userdata.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class is the implementation rest controller exposing end points for collecting equity data
 */
@RestController
@Slf4j
@RequestMapping(path = "/investment")
@RequiredArgsConstructor
public class InvestmentController {

  private final InvestmentService investmentService;

  /**
   * Method for Getting All Deposit Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(
      name = "GetDepositAccounts",
      path = "/depositaccounts",
      produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getDepositAccounts() {
    log.info("Get all Deposit Accounts");
    return investmentService.getDepositAccounts();
  }

  /**
   * Method for adding a Deposit Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = "AddDepositAccount",
      path = "/depositaccounts",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addDepositAccount(@RequestBody final AccountDto depositAccount) {
    log.info("Add a Deposit Account");
    return investmentService.addDepositAccount(depositAccount);
  }

  /**
   * Method for Getting All Loan Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(name = "GetLoanAccounts", path = "/loanaccounts", produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getLoanAccounts() {
    log.info("Get all Loan Accounts");
    return investmentService.getLoanAccounts();
  }

  /**
   * Method for adding a Loan Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = "AddLoanAccount",
      path = "/loanaccounts",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addLoanAccount(@RequestBody final AccountDto loanAccount) {
    log.info("Add a Loan Account");
    return investmentService.addLoanAccount(loanAccount);
  }

  /**
   * Method for Getting All Miscellaneous Accounts
   *
   * @return {@link Flux<MiscellaneousDto>}
   */
  @GetMapping(
      name = "GetMiscellaneousAccounts",
      path = "/miscellaneousaccounts",
      produces = APPLICATION_JSON_VALUE)
  public Flux<MiscellaneousDto> getMiscellaneousAccounts() {
    log.info("Get all Miscellaneous Accounts");
    return investmentService.getMiscellaneousAccounts();
  }

  /**
   * Method for adding a Miscellaneous Accounts
   *
   * @return {@link Mono<MiscellaneousDto>}
   */
  @PostMapping(
      name = "AddMiscellaneousAccount",
      path = "/miscellaneousaccounts",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MiscellaneousDto> addMiscellaneousAccount(
      @RequestBody final MiscellaneousDto miscellaneous) {
    log.info("Add a Miscellaneous Account");
    return investmentService.addMiscellaneousAccount(miscellaneous);
  }

  /**
   * Method for Getting All Mutual Funds
   *
   * @return {@link Flux<MutualFundDto>}
   */
  @GetMapping(name = "GetMutualFunds", path = "/mutualfunds", produces = APPLICATION_JSON_VALUE)
  public Flux<MutualFundDto> getMutualFunds() {
    log.info("Get all Miscellaneous Accounts");
    return investmentService.getMutualFunds();
  }

  /**
   * Method for adding a Mutual Fund
   *
   * @return {@link Mono<MutualFundDto>}
   */
  @PostMapping(
      name = "AddMutualFund",
      path = "/mutualfunds",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MutualFundDto> addMutualFund(@RequestBody final MutualFundDto mutualFund) {
    log.info("Add a Mutual Fund");
    return investmentService.addMutualFund(mutualFund);
  }

  /**
   * Method for Getting All Saving Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(
      name = "GetSavingAccounts",
      path = "/savingaccounts",
      produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getSavingAccounts() {
    log.info("Get all Saving Accounts");
    return investmentService.getSavingAccounts();
  }

  /**
   * Method for adding a Saving Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = "AddSavingAccounts",
      path = "/savingaccounts",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addSavingAccount(@RequestBody final AccountDto savingAccount) {
    log.info("Add a Saving Account");
    return investmentService.addSavingAccount(savingAccount);
  }

  /**
   * Method for Getting All Stocks
   *
   * @return {@link Flux<StockDto>}
   */
  @GetMapping(name = "GetStocks", path = "/stocks", produces = APPLICATION_JSON_VALUE)
  public Flux<StockDto> getStocks() {
    log.info("Get all Stocks");
    return investmentService.getStocks();
  }

  /**
   * Method for adding a Stock
   *
   * @return {@link Mono<StockDto>}
   */
  @PostMapping(
      name = "AddStock",
      path = "/stocks",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<StockDto> addStock(@RequestBody final StockDto stock) {
    log.info("Add a Stock");
    return investmentService.addStock(stock);
  }
}
