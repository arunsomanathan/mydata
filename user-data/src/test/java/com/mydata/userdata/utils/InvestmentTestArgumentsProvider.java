/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.mydata.userdata.utils;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.entity.*;
import com.mydata.userdata.utils.InvestmentTestArguments.Mode;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.datafaker.Faker;
import net.datafaker.Finance;
import net.datafaker.Number;
import net.datafaker.Science;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

/** Argument provider class for Investment Tests */
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
class InvestmentTestArgumentsProvider
    implements ArgumentsProvider, AnnotationConsumer<InvestmentTestArguments> {
  private static Integer id = 0;

  private static final Science SCIENCE_DATA = new Faker().science();
  private static final Finance FINANCE_DATA = new Faker().finance();
  private static final Number NUMBER_DATA = new Faker().number();
  private static final net.datafaker.Stock STOCK_DATA = new Faker().stock();
  private static final Integer MIN_VALUE = Integer.MIN_VALUE;
  private static final Integer MAX_VALUE = Integer.MAX_VALUE;
  private static final Integer ZERO_VALUE = 0;

  private transient Arguments argument;

  @Override
  public void accept(InvestmentTestArguments annotationArgs) {
    final BeanTransformer bt = new BeanUtils().getTransformer();
    final var firstArg = getFirstArgument(annotationArgs);

    final var companionClass = annotationArgs.companion();
    if (!void.class.equals(companionClass)) {
      Object companion = null;
      if (Mode.SINGLE.equals(annotationArgs.mode())) {
        companion =
            bt.skipTransformationForField(annotationArgs.skipFields())
                .transform(firstArg, companionClass);
      } else {
        if (firstArg instanceof List<?>) {
          companion =
              ((List<?>) firstArg)
                  .stream()
                      .map(
                          arg ->
                              bt.skipTransformationForField(annotationArgs.skipFields())
                                  .transform(arg, companionClass))
                      .toList();
        }
      }
      argument = Arguments.of(firstArg, companion);
    } else {
      argument = Arguments.of(firstArg);
    }
  }

  /**
   * Get the first Argument
   *
   * @param annotationArgs the annotation Arguments
   * @return the first argument
   */
  private Object getFirstArgument(final InvestmentTestArguments annotationArgs) {
    final var type = annotationArgs.type();
    final var mode = annotationArgs.mode();
    final var count = annotationArgs.count();
    if (AccountDto.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleAccountDto() : getAccountsDto(count);
    } else if (MiscellaneousDto.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleMiscellaneousDto() : getMiscellaneousDto(count);
    } else if (MutualFundDto.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleMutualFundDto() : getMutualFundsDto(count);
    } else if (StockDto.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleStockDto() : getStocksDto(count);
    } else if (DepositAccount.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleDepositAccount() : getDepositAccounts(count);
    } else if (Loan.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleLoanAccount() : getLoanAccounts(count);
    } else if (Miscellaneous.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleMiscellaneous() : getMiscellaneous(count);
    } else if (MutualFund.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleMutualFund() : getMutualFunds(count);
    } else if (SavingAccount.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleSavingAccount() : getSavingAccounts(count);
    } else if (Stock.class.equals(type)) {
      return (Mode.SINGLE.equals(mode)) ? getSingleStock() : getStocks(count);
    }
    return null;
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return Stream.of(argument);
  }

  /**
   * Generate List of Accounts
   *
   * @param count the count of accounts required in return list
   * @return {@link List<AccountDto>}
   */
  public static List<AccountDto> getAccountsDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleAccountDto()).toList();
  }

  /**
   * Generate Account with random value
   *
   * @return {@link AccountDto}
   */
  private static AccountDto getSingleAccountDto() {
    return new AccountDto(
        ++id,
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        FINANCE_DATA.iban(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, MIN_VALUE, MAX_VALUE)));
  }

  /**
   * Generate List of Miscellaneous Accounts
   *
   * @param count the count of MiscellaneousDto required in the return list
   * @return {@link List<MiscellaneousDto>}
   */
  private static List<MiscellaneousDto> getMiscellaneousDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMiscellaneousDto()).toList();
  }

  /**
   * Generate Miscellaneous account with random value
   *
   * @return {@link MiscellaneousDto}
   */
  private static MiscellaneousDto getSingleMiscellaneousDto() {
    return new MiscellaneousDto(
        ++id,
        SCIENCE_DATA.element(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)));
  }

  /**
   * Generate List of Mutual Funds
   *
   * @param count the count of mutual funds required in the return list
   * @return {@link List<MutualFundDto>}
   */
  private static List<MutualFundDto> getMutualFundsDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMutualFundDto()).toList();
  }

  /**
   * Generate Mutual Funds with random value
   *
   * @return {@link MutualFundDto}
   */
  private static MutualFundDto getSingleMutualFundDto() {
    return new MutualFundDto(
        ++id,
        STOCK_DATA.nsdqSymbol(),
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        SCIENCE_DATA.quark());
  }

  /**
   * Generate List of Stocks
   *
   * @param count the count of stocks required in the return list
   * @return {@link List<StockDto>}
   */
  private static List<StockDto> getStocksDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleStockDto()).toList();
  }

  /**
   * Generate Account with random value
   *
   * @return {@link StockDto}
   */
  private static StockDto getSingleStockDto() {
    return new StockDto(
        ++id,
        STOCK_DATA.nsdqSymbol(),
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        SCIENCE_DATA.quark());
  }

  /**
   * Generate List of Deposit Accounts
   *
   * @param count the count of deposit accounts required in the return list
   * @return {@link List<DepositAccount>}
   */
  private static List<DepositAccount> getDepositAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleDepositAccount()).toList();
  }

  /**
   * Generate Deposit Account with random value
   *
   * @return {@link DepositAccount}
   */
  private static DepositAccount getSingleDepositAccount() {
    return new DepositAccount(
        ++id,
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        FINANCE_DATA.iban(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of Loan Accounts
   *
   * @param count the count of loan accounts required in the return list
   * @return {@link List<Loan>}
   */
  private static List<Loan> getLoanAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleLoanAccount()).toList();
  }

  /**
   * Generate Loan Account with random value
   *
   * @return {@link Loan}
   */
  private static Loan getSingleLoanAccount() {
    return new Loan(
        ++id,
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        FINANCE_DATA.iban(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, MIN_VALUE, ZERO_VALUE)),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of Saving Accounts
   *
   * @param count the count of saving accounts required in the return list
   * @return {@link List<SavingAccount>}
   */
  private static List<SavingAccount> getSavingAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleSavingAccount()).toList();
  }

  /**
   * Generate Saving Account with random value
   *
   * @return {@link SavingAccount}
   */
  private static SavingAccount getSingleSavingAccount() {
    return new SavingAccount(
        ++id,
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        FINANCE_DATA.iban(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of Miscellaneous
   *
   * @param count the count of miscellaneous accounts required in the return list
   * @return {@link List<Miscellaneous>}
   */
  private static List<Miscellaneous> getMiscellaneous(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMiscellaneous()).toList();
  }

  /**
   * Generate Miscellaneous with random value
   *
   * @return {@link Miscellaneous}
   */
  private static Miscellaneous getSingleMiscellaneous() {
    return new Miscellaneous(
        ++id,
        SCIENCE_DATA.element(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of MutualFunds
   *
   * @param count the count of Mutual Funds required in the return list
   * @return {@link List<MutualFund>}
   */
  private static List<MutualFund> getMutualFunds(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMutualFund()).toList();
  }

  /**
   * Generate MutualFund with random value
   *
   * @return {@link MutualFund}
   */
  private static MutualFund getSingleMutualFund() {
    return new MutualFund(
        ++id,
        STOCK_DATA.nsdqSymbol(),
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        SCIENCE_DATA.quark(),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of Stocks
   *
   * @param count the count of Stocks required in the return list
   * @return {@link List<Stock>}
   */
  private static List<Stock> getStocks(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleStock()).toList();
  }

  /**
   * Generate Stock with random value
   *
   * @return {@link Stock}
   */
  private static Stock getSingleStock() {
    return new Stock(
        ++id,
        STOCK_DATA.nsdqSymbol(),
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        SCIENCE_DATA.quark(),
        Instant.now(),
        Instant.now(),
        false);
  }
}
