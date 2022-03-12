package com.mydata.userdata.service;

import static com.mydata.userdata.common.ObjectProperties.*;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.transformer.model.FieldTransformer;
import com.mydata.userdata.dto.*;
import com.mydata.userdata.entity.*;
import com.mydata.userdata.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** This class the process the Investment data */
@Service
@Slf4j
@RequiredArgsConstructor
public class InvestmentService {

  private static final FieldTransformer<Boolean, Boolean> ACTIVE_TRUE_TRNSFMR =
      new FieldTransformer<>(ACCOUNT_ACTIVE, () -> Boolean.TRUE);
  private static final FieldTransformer<String, String> ID_NULL_TRNSFMR =
      new FieldTransformer<>(ACCOUNT_ID, () -> null);
  private final DepositAccountRepository depositAccountRepository;
  private final LoanRepository loanRepository;
  private final MiscellaneousRepository miscellaneousRepository;
  private final MutualFundRepository mutualFundRepository;
  private final MutualFundBuyTransactionRepository mutualFundBuyTransactionRepository;
  private final MutualFundSellTransactionRepository mutualFundSellTransactionRepository;
  private final SavingAccountRepository savingAccountRepository;
  private final StockRepository stockRepository;

  private <T> T dtoToEntitySkipId(Object dto, Class<T> entityType) {
    return new BeanUtils()
        .getTransformer()
        .withFieldTransformer(ID_NULL_TRNSFMR, ACTIVE_TRUE_TRNSFMR)
        .skipTransformationForField(ACCOUNT_MODIFIED_AT, ACCOUNT_CREATED_AT)
        .setDefaultValueForMissingField(false)
        .setDefaultValueForMissingPrimitiveField(false)
        .transform(dto, entityType);
  }

  /**
   * Get all Deposit Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getDepositAccounts() {
    log.info("Fetching all Active Deposit Accounts");
    return depositAccountRepository
        .findByActive(Boolean.TRUE)
        .map(da -> entityToDto(da, AccountDto.class));
  }

  private <T> T entityToDto(Object entity, Class<T> dtoType) {
    return BeanUtils.getTransformer(dtoType).apply(entity);
  }

  /**
   * Add a Deposit Account
   *
   * @param depositAccount the deposit account dto object
   * @return {@link Mono<AccountDto>}
   */
  public Mono<AccountDto> addDepositAccount(final AccountDto depositAccount) {
    log.info("Add a Deposit Account");
    return depositAccountRepository
        .save(dtoToEntitySkipId(depositAccount, DepositAccount.class))
        .map(da -> entityToDto(da, AccountDto.class));
  }

  /**
   * Get all Loan Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getLoanAccounts() {
    log.info("Fetching all Active Loan Accounts");
    return loanRepository
        .findByActive(Boolean.TRUE)
        .map(loan -> entityToDto(loan, AccountDto.class));
  }

  /**
   * Add a Loan Account
   *
   * @param loanAccount the loan account dto object
   * @return {@link Mono<AccountDto>}
   */
  public Mono<AccountDto> addLoanAccount(final AccountDto loanAccount) {
    log.info("Add a Loan Account");
    return loanRepository
        .save(dtoToEntitySkipId(loanAccount, Loan.class))
        .map(loan -> entityToDto(loan, AccountDto.class));
  }

  /**
   * Get all Miscellaneous Accounts
   *
   * @return {@link Flux<MiscellaneousDto>}
   */
  public Flux<MiscellaneousDto> getMiscellaneousAccounts() {
    log.info("Fetching all Active Miscellaneous Accounts");
    return miscellaneousRepository
        .findByActive(Boolean.TRUE)
        .map(misc -> entityToDto(misc, MiscellaneousDto.class));
  }

  /**
   * Add a Miscellaneous Account
   *
   * @param miscellaneousAccount the miscellaneous dto object
   * @return {@link Mono<MiscellaneousDto>}
   */
  public Mono<MiscellaneousDto> addMiscellaneousAccount(
      final MiscellaneousDto miscellaneousAccount) {
    log.info("Add a Miscellaneous Account");
    return miscellaneousRepository
        .save(dtoToEntitySkipId(miscellaneousAccount, Miscellaneous.class))
        .map(misc -> entityToDto(misc, MiscellaneousDto.class));
  }

  /**
   * Get all Mutual Funds
   *
   * @return {@link Flux<MutualFundDto>}
   */
  public Flux<MutualFundDto> getMutualFunds() {
    log.info("Fetching all Active MutualFunds");
    return mutualFundRepository
        .findByActive(Boolean.TRUE)
        .map(mf -> entityToDto(mf, MutualFundDto.class));
  }

  /**
   * Add a Mutual Fund
   *
   * @param mutualFund the mutual fund dto
   * @return {@link Mono<MutualFundDto>}
   */
  public Mono<MutualFundDto> addMutualFund(final MutualFundDto mutualFund) {
    log.info("Add a Mutual Fund");
    return mutualFundRepository
        .save(dtoToEntitySkipId(mutualFund, MutualFund.class))
        .map(mf -> entityToDto(mf, MutualFundDto.class));
  }

  /**
   * Get Mutual Funds Buy Transaction
   *
   * @param fetchSoldOut if true fetch sold out buy transaction else un sold out transaction
   * @return {@link Flux<MutualFundBuyTransactionDto>}
   */
  public Flux<MutualFundBuyTransactionDto> getMutualFundBuyTransactions(Boolean fetchSoldOut) {
    log.info("Fetching all Mutual Fund Buy Transaction based on sold out value");
    return mutualFundBuyTransactionRepository
        .findByIsSoldOut(fetchSoldOut)
        .map(mf -> entityToDto(mf, MutualFundBuyTransactionDto.class));
  }

  /**
   * Add a Mutual Fund Buy Transactions
   *
   * @param mfBuyTransaction the mutual fund buy transaction dto
   * @return {@link Mono<MutualFundBuyTransactionDto>}
   */
  public Mono<MutualFundBuyTransactionDto> addMutualFundBuyTransaction(
      final MutualFundBuyTransactionDto mfBuyTransaction) {
    log.info("Add a Mutual Fund Buy Transaction");
    return mutualFundBuyTransactionRepository
        .save(dtoToEntitySkipId(mfBuyTransaction, MutualFundBuyTransaction.class))
        .map(mfBuy -> entityToDto(mfBuy, MutualFundBuyTransactionDto.class));
  }

  /**
   * Get Mutual Funds Sell Transactions
   *
   * @return {@link Flux<MutualFundSellTransactionDto>}
   */
  public Flux<MutualFundSellTransactionDto> getMutualFundSellTransactions() {
    log.info("Fetching all Mutual Fund Sell Transactions");
    return mutualFundSellTransactionRepository
        .findAll()
        .map(mf -> entityToDto(mf, MutualFundSellTransactionDto.class));
  }

  /**
   * Add a Mutual Fund Sell Transaction
   *
   * @param mfSellTransaction the mutual fund sell transaction dto
   * @return {@link Mono<MutualFundSellTransactionDto>}
   */
  public Mono<MutualFundSellTransactionDto> addMutualFundSellTransaction(
      final MutualFundSellTransactionDto mfSellTransaction) {
    log.info("Add a Mutual Fund Sell Transaction");
    return mutualFundSellTransactionRepository
        .save(dtoToEntitySkipId(mfSellTransaction, MutualFundSellTransaction.class))
        .map(mfSell -> entityToDto(mfSell, MutualFundSellTransactionDto.class));
  }

  /**
   * Get all Saving Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getSavingAccounts() {
    log.info("Fetching all Active Saving Accounts");
    return savingAccountRepository
        .findByActive(Boolean.TRUE)
        .map(sa -> entityToDto(sa, AccountDto.class));
  }

  /**
   * Add a Saving Account
   *
   * @param savingAccount the saving account dto object
   * @return {@link Mono<AccountDto>}
   */
  public Mono<AccountDto> addSavingAccount(final AccountDto savingAccount) {
    log.info("Add a Saving Account");
    return savingAccountRepository
        .save(dtoToEntitySkipId(savingAccount, SavingAccount.class))
        .map(sa -> entityToDto(sa, AccountDto.class));
  }

  /**
   * Get all Stocks
   *
   * @return {@link Flux<StockDto>}
   */
  public Flux<StockDto> getStocks() {
    log.info("Fetching all Active Stocks");
    return stockRepository
        .findByActive(Boolean.TRUE)
        .map(stock -> entityToDto(stock, StockDto.class));
  }

  /**
   * Add a Stock
   *
   * @param stock the stock dto object
   * @return {@link Mono<StockDto>}
   */
  public Mono<StockDto> addStock(final StockDto stock) {
    log.info("Add a Stock");
    return stockRepository
        .save(dtoToEntitySkipId(stock, Stock.class))
        .map(sa -> entityToDto(sa, StockDto.class));
  }
}
