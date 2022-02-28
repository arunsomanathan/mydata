package com.mydata.userdata.service;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.transformer.model.FieldTransformer;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
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

  private final DepositAccountRepository depositAccountRepository;
  private final LoanRepository loanRepository;
  private final MiscellaneousRepository miscellaneousRepository;
  private final MutualFundRepository mutualFundRepository;
  private final SavingAccountRepository savingAccountRepository;
  private final StockRepository stockRepository;

  // TODO: These can be common constants
  private static final String ID_FIELD = "id";
  private static final String MODIFIED_AT_FIELD = "modifiedAt";
  private static final String CREATED_AT_FIELD = "createdAt";
  private static final String ACTIVE_FIELD = "createdAt";

  private final FieldTransformer<String, Boolean> activeFieldTransformer =
      new FieldTransformer<>("active", () -> Boolean.TRUE);
  private final BeanTransformer dtoToEntitySkipId =
      new BeanUtils()
          .getTransformer()
          .withFieldTransformer(activeFieldTransformer)
          .skipTransformationForField(CREATED_AT_FIELD)
          .skipTransformationForField(MODIFIED_AT_FIELD)
          .skipTransformationForField(ID_FIELD)
          .skipTransformationForField(ACTIVE_FIELD)
          .setDefaultValueForMissingField(true);
  private final BeanTransformer entityToDto = new BeanUtils().getTransformer();

  /**
   * Get all Deposit Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getDepositAccounts() {
    log.info("Fetching all Active Deposit Accounts");
    return depositAccountRepository
        .findByActiveTrue()
        .map(da -> entityToDto.transform(da, AccountDto.class));
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
        .save(dtoToEntitySkipId.transform(depositAccount, DepositAccount.class))
        .map(da -> entityToDto.transform(da, AccountDto.class));
  }

  /**
   * Get all Loan Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getLoanAccounts() {
    log.info("Fetching all Active Loan Accounts");
    return loanRepository
        .findByActiveTrue()
        .map(loan -> entityToDto.transform(loan, AccountDto.class));
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
        .save(dtoToEntitySkipId.transform(loanAccount, Loan.class))
        .map(loan -> entityToDto.transform(loan, AccountDto.class));
  }

  /**
   * Get all Miscellaneous Accounts
   *
   * @return {@link Flux<MiscellaneousDto>}
   */
  public Flux<MiscellaneousDto> getMiscellaneousAccounts() {
    log.info("Fetching all Active Miscellaneous Accounts");
    return miscellaneousRepository
        .findByActiveTrue()
        .map(misc -> entityToDto.transform(misc, MiscellaneousDto.class));
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
        .save(dtoToEntitySkipId.transform(miscellaneousAccount, Miscellaneous.class))
        .map(misc -> entityToDto.transform(misc, MiscellaneousDto.class));
  }

  /**
   * Get all Mutual Funds
   *
   * @return {@link Flux<MutualFundDto>}
   */
  public Flux<MutualFundDto> getMutualFunds() {
    log.info("Fetching all Active MutualFunds");
    return mutualFundRepository
        .findByActiveTrue()
        .map(mf -> entityToDto.transform(mf, MutualFundDto.class));
  }

  /**
   * Add a Mutual Fund
   *
   * @param mutualFund the mutual fund dto object
   * @return {@link Mono<MutualFundDto>}
   */
  public Mono<MutualFundDto> addMutualFund(final MutualFundDto mutualFund) {
    log.info("Add a Mutual Fund");
    return mutualFundRepository
        .save(dtoToEntitySkipId.transform(mutualFund, MutualFund.class))
        .map(mf -> entityToDto.transform(mf, MutualFundDto.class));
  }

  /**
   * Get all Saving Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getSavingAccounts() {
    log.info("Fetching all Active Saving Accounts");
    return savingAccountRepository
        .findByActiveTrue()
        .map(sa -> entityToDto.transform(sa, AccountDto.class));
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
        .save(dtoToEntitySkipId.transform(savingAccount, SavingAccount.class))
        .map(sa -> entityToDto.transform(sa, AccountDto.class));
  }

  /**
   * Get all Stocks
   *
   * @return {@link Flux<StockDto>}
   */
  public Flux<StockDto> getStocks() {
    log.info("Fetching all Active Stocks");
    return stockRepository
        .findByActiveTrue()
        .map(stock -> entityToDto.transform(stock, StockDto.class));
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
        .save(dtoToEntitySkipId.transform(stock, Stock.class))
        .map(sa -> entityToDto.transform(sa, StockDto.class));
  }
}
