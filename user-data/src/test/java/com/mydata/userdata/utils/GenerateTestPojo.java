package com.mydata.userdata.utils;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.entity.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import net.datafaker.Finance;
import net.datafaker.Number;
import net.datafaker.Science;

/** Generate Entities for test */
public final class GenerateTestPojo {

  private static final BeanTransformer BT = new BeanUtils().getTransformer();
  private static final Science SCIENCE_DATA = new Faker().science();
  private static final Finance FINANCE_DATA = new Faker().finance();
  private static final Number NUMBER_DATA = new Faker().number();
  private static final net.datafaker.Stock STOCK_DATA = new Faker().stock();
  private static final Integer MIN_VALUE = Integer.MIN_VALUE;
  private static final Integer MAX_VALUE = Integer.MAX_VALUE;
  private static final Integer ZERO_VALUE = 0;
  private static Integer id = 0;

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
  public static AccountDto getSingleAccountDto() {
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
  public static List<MiscellaneousDto> getMiscellaneousDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMiscellaneousDto()).toList();
  }

  /**
   * Generate Miscellaneous account with random value
   *
   * @return {@link MiscellaneousDto}
   */
  public static MiscellaneousDto getSingleMiscellaneousDto() {
    return new MiscellaneousDto(
        ++id,
        SCIENCE_DATA.element(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)));
  }

  /**
   * Generate List of Mutual Funds
   *
   * @param count the count of mutual funds required in the return list
   * @return {@link MutualFundDto}
   */
  public static List<MutualFundDto> getMutualFundsDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMutualFundDto()).toList();
  }

  /**
   * Generate Mutual Funds with random value
   *
   * @return {@link MutualFundDto}
   */
  public static MutualFundDto getSingleMutualFundDto() {
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
  public static List<StockDto> getStocksDto(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleStockDto()).toList();
  }

  /**
   * Generate Account with random value
   *
   * @return {@link StockDto}
   */
  public static StockDto getSingleStockDto() {
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
  public static List<DepositAccount> getDepositAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleDepositAccount()).toList();
  }

  /**
   * Generate Deposit Account with random value
   *
   * @return {@link DepositAccount}
   */
  public static DepositAccount getSingleDepositAccount() {
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
   * Generate List of Accounts by copying from List of Objects
   *
   * @param accounts the list of accounts
   * @return {@link List<AccountDto>}
   */
  public static List<AccountDto> getAccountsDto(final List<?> accounts) {
    return accounts.stream().map(GenerateTestPojo::getSingleAccountDto).toList();
  }

  /**
   * Generate Account by copying values from an Object
   *
   * @return {@link AccountDto}
   */
  public static AccountDto getSingleAccountDto(Object account) {
    return BT.transform(account, AccountDto.class);
  }

  /**
   * Generate List of Loan Accounts
   *
   * @param count the count of loan accounts required in the return list
   * @return {@link List<Loan>}
   */
  public static List<Loan> getLoanAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleLoanAccount()).toList();
  }

  /**
   * Generate Loan Account with random value
   *
   * @return {@link Loan}
   */
  public static Loan getSingleLoanAccount() {
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
  public static List<SavingAccount> getSavingAccounts(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleSavingAccount()).toList();
  }

  /**
   * Generate Saving Account with random value
   *
   * @return {@link SavingAccount}
   */
  public static SavingAccount getSingleSavingAccount() {
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
  public static List<Miscellaneous> getMiscellaneous(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMiscellaneous()).toList();
  }

  /**
   * Generate Miscellaneous with random value
   *
   * @return {@link Miscellaneous}
   */
  public static Miscellaneous getSingleMiscellaneous() {
    return new Miscellaneous(
        ++id,
        SCIENCE_DATA.element(),
        BigDecimal.valueOf(NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)),
        Instant.now(),
        Instant.now(),
        false);
  }

  /**
   * Generate List of Miscellaneous DTO by copying from List of Miscellaneous
   *
   * @param miscellaneous {@link List<Miscellaneous>}
   * @return {@link List<MiscellaneousDto>}
   */
  public static List<MiscellaneousDto> getMiscellaneousDto(
      final List<Miscellaneous> miscellaneous) {
    return miscellaneous.stream().map(GenerateTestPojo::getSingleMiscellaneousDto).toList();
  }

  /**
   * Generate Miscellaneous DTO by copying from Miscellaneous object
   *
   * @return {@link MiscellaneousDto}
   */
  public static MiscellaneousDto getSingleMiscellaneousDto(Miscellaneous miscellaneous) {
    return BT.transform(miscellaneous, MiscellaneousDto.class);
  }

  /**
   * Generate List of MutualFunds
   *
   * @param count the count of Mutual Funds required in the return list
   * @return {@link List<MutualFund>}
   */
  public static List<MutualFund> getMutualFunds(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleMutualFund()).toList();
  }

  /**
   * Generate MutualFund with random value
   *
   * @return {@link MutualFund}
   */
  public static MutualFund getSingleMutualFund() {
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
   * Generate List of MutualFund DTO from List of MutualFund objects
   *
   * @param mutualFund {@link List<MutualFund>}
   * @return {@link List<MutualFundDto>}
   */
  public static List<MutualFundDto> getMutualFundsDto(final List<MutualFund> mutualFund) {
    return mutualFund.stream().map(GenerateTestPojo::getSingleMutualFundDto).toList();
  }

  /**
   * Generate MutualFund DTO by copying from MutualFund object
   *
   * @param mutualFund the {@link MutualFund}
   * @return {@link MutualFundDto}
   */
  public static MutualFundDto getSingleMutualFundDto(MutualFund mutualFund) {
    return BT.transform(mutualFund, MutualFundDto.class);
  }

  /**
   * Generate List of Stocks
   *
   * @param count the count of Stocks required in the return list
   * @return {@link List<Stock>}
   */
  public static List<Stock> getStocks(final Integer count) {
    return IntStream.range(0, count).mapToObj(i -> getSingleStock()).toList();
  }

  /**
   * Generate Stock with random value
   *
   * @return {@link Stock}
   */
  public static Stock getSingleStock() {
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

  /**
   * Generate List of Stock DTO from List of Stock objects
   *
   * @param stocks {@link List<Stock>}
   * @return {@link List<StockDto>}
   */
  public static List<StockDto> getStocksDto(final List<Stock> stocks) {
    return stocks.stream().map(GenerateTestPojo::getStockDto).toList();
  }

  /**
   * Generate Stock DTO by copying from Stock object
   *
   * @param stock the {@link Stock}
   * @return {@link StockDto}
   */
  public static StockDto getStockDto(Stock stock) {
    return BT.transform(stock, StockDto.class);
  }
}
