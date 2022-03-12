package com.mydata.userdata.controller;

import static com.mydata.userdata.common.ApiNames.*;
import static com.mydata.userdata.common.ApiUrls.*;
import static com.mydata.userdata.common.CommonStrings.LOG_MSG_EXEC_API;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.mydata.userdata.dto.*;
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
@RequestMapping(path = INVESTMENT_BASE_URL)
@RequiredArgsConstructor
public class InvestmentController {

  private final InvestmentService investmentService;

  /**
   * Method for Getting All Deposit Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(
      name = GET_DEPOSIT_ACCOUNTS,
      path = DEPOSIT_ACCOUNTS_URL,
      produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getDepositAccounts() {
    log.info(LOG_MSG_EXEC_API, GET_DEPOSIT_ACCOUNTS);
    return investmentService.getDepositAccounts();
  }

  /**
   * Method for adding a Deposit Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = ADD_DEPOSIT_ACCOUNT,
      path = DEPOSIT_ACCOUNTS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addDepositAccount(@RequestBody final AccountDto depositAccount) {
    log.info(LOG_MSG_EXEC_API, ADD_DEPOSIT_ACCOUNT);
    return investmentService.addDepositAccount(depositAccount);
  }

  /**
   * Method for Getting All Loan Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(name = GET_LOAN_ACCOUNTS, path = LOAN_ACCOUNTS_URL, produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getLoanAccounts() {
    log.info(LOG_MSG_EXEC_API, GET_LOAN_ACCOUNTS);
    return investmentService.getLoanAccounts();
  }

  /**
   * Method for adding a Loan Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = ADD_LOAN_ACCOUNT,
      path = LOAN_ACCOUNTS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addLoanAccount(@RequestBody final AccountDto loanAccount) {
    log.info(LOG_MSG_EXEC_API, ADD_LOAN_ACCOUNT);
    return investmentService.addLoanAccount(loanAccount);
  }

  /**
   * Method for Getting All Miscellaneous Accounts
   *
   * @return {@link Flux<MiscellaneousDto>}
   */
  @GetMapping(name = GET_MISC_ACCOUNTS, path = MISC_ACCOUNTS_URL, produces = APPLICATION_JSON_VALUE)
  public Flux<MiscellaneousDto> getMiscellaneousAccounts() {
    log.info(LOG_MSG_EXEC_API, GET_MISC_ACCOUNTS);
    return investmentService.getMiscellaneousAccounts();
  }

  /**
   * Method for adding a Miscellaneous Accounts
   *
   * @return {@link Mono<MiscellaneousDto>}
   */
  @PostMapping(
      name = ADD_MISC_ACCOUNT,
      path = MISC_ACCOUNTS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MiscellaneousDto> addMiscellaneousAccount(
      @RequestBody final MiscellaneousDto miscellaneous) {
    log.info(LOG_MSG_EXEC_API, ADD_MISC_ACCOUNT);
    return investmentService.addMiscellaneousAccount(miscellaneous);
  }

  /**
   * Method for Getting All Mutual Funds
   *
   * @return {@link Flux<MutualFundDto>}
   */
  @GetMapping(name = GET_MUTUAL_FUNDS, path = MUTUAL_FUNDS_URL, produces = APPLICATION_JSON_VALUE)
  public Flux<MutualFundDto> getMutualFunds() {
    log.info(LOG_MSG_EXEC_API, GET_MUTUAL_FUNDS);
    return investmentService.getMutualFunds();
  }

  /**
   * Method for adding a Mutual Fund
   *
   * @return {@link Mono<MutualFundDto>}
   */
  @PostMapping(
      name = ADD_MUTUAL_FUND,
      path = MUTUAL_FUNDS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MutualFundDto> addMutualFund(@RequestBody final MutualFundDto mutualFund) {
    log.info(LOG_MSG_EXEC_API, ADD_MUTUAL_FUND);
    return investmentService.addMutualFund(mutualFund);
  }

  /**
   * Method for Getting Mutual Funds Buy Transactions. Only fetched Buy transactions which are not
   * sold out.
   *
   * @return {@link Flux<MutualFundBuyTransactionDto>}
   */
  @GetMapping(
      name = GET_MF_BUY_TRANSACTIONS,
      path = MF_BUY_TRANSACTION_URL,
      produces = APPLICATION_JSON_VALUE)
  public Flux<MutualFundBuyTransactionDto> getMutualFundBuyTransactions() {
    log.info(LOG_MSG_EXEC_API, GET_MF_BUY_TRANSACTIONS);
    return investmentService.getMutualFundBuyTransactions(Boolean.FALSE);
  }

  /**
   * Method for Adding Mutual Funds Buy Transaction.
   *
   * @param mfBuyTransaction the mutual fund buy transaction
   * @return {@link Mono<MutualFundBuyTransactionDto>}
   */
  @PostMapping(
      name = ADD_MF_BUY_TRANSACTIONS,
      path = MF_BUY_TRANSACTION_URL,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MutualFundBuyTransactionDto> addMutualFundBuyTransaction(
      @RequestBody final MutualFundBuyTransactionDto mfBuyTransaction) {
    log.info(LOG_MSG_EXEC_API, ADD_MF_BUY_TRANSACTIONS);
    return investmentService.addMutualFundBuyTransaction(mfBuyTransaction);
  }

  /**
   * Method for Getting Mutual Funds Sell Transactions.
   *
   * @return {@link Flux<MutualFundSellTransactionDto>}
   */
  @GetMapping(
      name = GET_MF_SELL_TRANSACTIONS,
      path = MF_SELL_TRANSACTION_URL,
      produces = APPLICATION_JSON_VALUE)
  public Flux<MutualFundSellTransactionDto> getMutualFundSellTransactions() {
    log.info(LOG_MSG_EXEC_API, GET_MF_SELL_TRANSACTIONS);
    return investmentService.getMutualFundSellTransactions();
  }

  /**
   * Method for Adding Mutual Funds Sell Transaction.
   *
   * @param mfSellTransaction the mutual fund sell transaction
   * @return {@link Mono<MutualFundSellTransactionDto>}
   */
  @PostMapping(
      name = ADD_MF_SELL_TRANSACTIONS,
      path = MF_SELL_TRANSACTION_URL,
      produces = APPLICATION_JSON_VALUE)
  public Mono<MutualFundSellTransactionDto> addMutualFundSellTransaction(
      @RequestBody final MutualFundSellTransactionDto mfSellTransaction) {
    log.info(LOG_MSG_EXEC_API, ADD_MF_SELL_TRANSACTIONS);
    return investmentService.addMutualFundSellTransaction(mfSellTransaction);
  }

  /**
   * Method for Getting All Saving Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  @GetMapping(
      name = GET_SAVING_ACCOUNTS,
      path = SAVING_ACCOUNTS_URL,
      produces = APPLICATION_JSON_VALUE)
  public Flux<AccountDto> getSavingAccounts() {
    log.info(LOG_MSG_EXEC_API, GET_SAVING_ACCOUNTS);
    return investmentService.getSavingAccounts();
  }

  /**
   * Method for adding a Saving Accounts
   *
   * @return {@link Mono<AccountDto>}
   */
  @PostMapping(
      name = ADD_SAVING_ACCOUNT,
      path = SAVING_ACCOUNTS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<AccountDto> addSavingAccount(@RequestBody final AccountDto savingAccount) {
    log.info(LOG_MSG_EXEC_API, ADD_SAVING_ACCOUNT);
    return investmentService.addSavingAccount(savingAccount);
  }

  /**
   * Method for Getting All Stocks
   *
   * @return {@link Flux<StockDto>}
   */
  @GetMapping(name = GET_STOCKS, path = STOCKS_URL, produces = APPLICATION_JSON_VALUE)
  public Flux<StockDto> getStocks() {
    log.info(LOG_MSG_EXEC_API, GET_STOCKS);
    return investmentService.getStocks();
  }

  /**
   * Method for adding a Stock
   *
   * @return {@link Mono<StockDto>}
   */
  @PostMapping(
      name = ADD_STOCK,
      path = STOCKS_URL,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public Mono<StockDto> addStock(@RequestBody final StockDto stock) {
    log.info(LOG_MSG_EXEC_API, ADD_STOCK);
    return investmentService.addStock(stock);
  }
}
