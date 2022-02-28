package com.mydata.userdata.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
   * Method for Getting All Saving Accounts
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
   * Method for Getting All Saving Accounts
   *
   * @return {@link Flux<StockDto>}
   */
  @GetMapping(name = "GetStocks", path = "/stocks", produces = APPLICATION_JSON_VALUE)
  public Flux<StockDto> getStocks() {
    log.info("Get all Stocks");
    return investmentService.getStocks();
  }
}
