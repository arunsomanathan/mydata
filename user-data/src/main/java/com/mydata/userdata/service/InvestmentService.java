package com.mydata.userdata.service;

import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
  private final BeanTransformer beanTransformer;

  /**
   * Get all Deposit Accounts
   *
   * @return {@link Flux<AccountDto>}
   */
  public Flux<AccountDto> getDepositAccounts() {
    log.info("Fetching all Active Deposit Accounts");
    return depositAccountRepository
        .findByActiveTrue()
        .map(da -> beanTransformer.transform(da, AccountDto.class));
  }

  /**
   * Get all Loan Accounts
   *
   * @return {@link Flux< AccountDto >}
   */
  public Flux<AccountDto> getLoanAccounts() {
    log.info("Fetching all Active Loan Accounts");
    return loanRepository
        .findByActiveTrue()
        .map(loan -> beanTransformer.transform(loan, AccountDto.class));
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
        .map(misc -> beanTransformer.transform(misc, MiscellaneousDto.class));
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
        .map(mf -> beanTransformer.transform(mf, MutualFundDto.class));
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
        .map(sa -> beanTransformer.transform(sa, AccountDto.class));
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
        .map(stock -> beanTransformer.transform(stock, StockDto.class));
  }
}
