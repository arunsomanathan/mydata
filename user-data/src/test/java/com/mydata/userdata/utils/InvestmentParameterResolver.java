package com.mydata.userdata.utils;

import com.expediagroup.beans.BeanUtils;
import com.mydata.userdata.dto.*;
import com.mydata.userdata.entity.*;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import net.datafaker.Finance;
import net.datafaker.Number;
import net.datafaker.Science;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/** Argument provider class for Investment Tests */
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class InvestmentParameterResolver implements ParameterResolver {
  private static final Science SCIENCE_DATA = new Faker().science();
  private static final Finance FINANCE_DATA = new Faker().finance();
  private static final Number NUMBER_DATA = new Faker().number();
  private static final net.datafaker.Stock STOCK_DATA = new Faker().stock();
  private static final Integer MIN_VALUE = Integer.MIN_VALUE;
  private static final Integer MAX_VALUE = Integer.MAX_VALUE;
  private static final Integer ZERO_VALUE = 0;
  private static final List<Class<?>> supportedParameters =
      List.of(
          AccountDto.class,
          MiscellaneousDto.class,
          MutualFundDto.class,
          MutualFundBuyTransactionDto.class,
          MutualFundSellTransactionDto.class,
          StockDto.class,
          DepositAccount.class,
          Loan.class,
          Miscellaneous.class,
          MutualFund.class,
          MutualFundBuyTransaction.class,
          MutualFundSellTransaction.class,
          SavingAccount.class,
          Stock.class);
  private static Integer id = 0;

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return supportedParameters.contains(getParameterType(parameterContext));
  }

  /**
   * Get the type of parameter. If the type is List then return the element type
   *
   * @param parameterContext the parameter context
   */
  private Class<?> getParameterType(final ParameterContext parameterContext) {
    var parameter = parameterContext.getParameter();
    if (List.class == parameter.getType()
        && parameter.getParameterizedType() instanceof ParameterizedType) {
      return (Class<?>)
          ((ParameterizedType) parameter.getParameterizedType()).getActualTypeArguments()[0];
    }
    return parameter.getType();
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {

    Object retObj;
    final var type = getParameterType(parameterContext);
    final var isList = List.class == parameterContext.getParameter().getType();
    final var listSize =
        isList
            ? parameterContext.findAnnotation(ListSize.class).map(ListSize::value).orElse(10)
            : 10;

    if (parameterContext.findAnnotation(GenerateFrom.class).isPresent()) {
      var genFromParam = parameterContext.findAnnotation(GenerateFrom.class).get();
      var refParameterName = genFromParam.value();
      var skipFiled = genFromParam.skipFields();
      var refParameterValue = getStore(extensionContext).get(refParameterName);
      var bt = new BeanUtils().getTransformer();
      if (isList) {
        retObj =
            ((List<?>) refParameterValue)
                .stream()
                    .map(arg -> bt.skipTransformationForField(skipFiled).transform(arg, type))
                    .toList();
      } else {
        retObj = bt.skipTransformationForField(skipFiled).transform(refParameterValue, type);
      }
    } else {
      var negativeFields = parameterContext.findAnnotation(NegativeValues.class).orElse(null);
      retObj = generateParameterValue(type, isList, listSize, negativeFields);
      getStore(extensionContext).put(parameterContext.getParameter().getName(), retObj);
    }
    return retObj;
  }

  private ExtensionContext.Store getStore(ExtensionContext extensionContext) {
    return extensionContext.getStore(
        ExtensionContext.Namespace.create(getClass(), extensionContext.getRequiredTestMethod()));
  }

  /**
   * Generate parameter value
   *
   * @param type the type of object
   * @param isList generate a list of objects?
   * @param listSize if isList is true, then size of list
   * @param negativeFields list of fields to be negative
   * @return the parameter value
   */
  private static Object generateParameterValue(
      Class<?> type, boolean isList, int listSize, final NegativeValues negativeFields) {
    if (AccountDto.class.equals(type)) {
      return isList
          ? getAccountsDto(listSize, negativeFields)
          : getSingleAccountDto(negativeFields);
    } else if (MiscellaneousDto.class.equals(type)) {
      return isList ? getMiscellaneousDto(listSize) : getSingleMiscellaneousDto();
    } else if (MutualFundDto.class.equals(type)) {
      return isList ? getMutualFundsDto(listSize) : getSingleMutualFundDto();
    } else if (MutualFundBuyTransactionDto.class.equals(type)) {
      return isList
          ? getMutualFundsBuyTransactionDto(listSize)
          : getSingleMutualFundBuyTransactionDto();
    } else if (MutualFundSellTransactionDto.class.equals(type)) {
      return isList
          ? getMutualFundsSellTransactionDto(listSize)
          : getSingleMutualFundSellTransactionDto();
    } else if (StockDto.class.equals(type)) {
      return isList ? getStocksDto(listSize) : getSingleStockDto();
    } else if (DepositAccount.class.equals(type)) {
      return isList ? getDepositAccounts(listSize) : getSingleDepositAccount();
    } else if (Loan.class.equals(type)) {
      return isList ? getLoanAccounts(listSize) : getSingleLoanAccount();
    } else if (Miscellaneous.class.equals(type)) {
      return isList ? getMiscellaneous(listSize) : getSingleMiscellaneous();
    } else if (MutualFund.class.equals(type)) {
      return isList ? getMutualFunds(listSize) : getSingleMutualFund();
    } else if (MutualFundBuyTransaction.class.equals(type)) {
      return isList ? getMutualFundBuyTransaction(listSize) : getSingleMutualFundBuyTransaction();
    } else if (MutualFundSellTransaction.class.equals(type)) {
      return isList ? getMutualFundSellTransaction(listSize) : getSingleMutualFundSellTransaction();
    } else if (SavingAccount.class.equals(type)) {
      return isList ? getSavingAccounts(listSize) : getSingleSavingAccount();
    } else if (Stock.class.equals(type)) {
      return isList ? getStocks(listSize) : getSingleStock();
    }
    return null;
  }

  /**
   * Generate List of Accounts
   *
   * @param count the count of accounts required in return list
   * @param negativeFields optional negative fields
   * @return {@link List<AccountDto>}
   */
  private static List<AccountDto> getAccountsDto(
      final Integer count, final NegativeValues negativeFields) {
    return IntStream.range(0, count).mapToObj(i -> getSingleAccountDto(negativeFields)).toList();
  }

  /**
   * Generate Account with random value
   *
   * @param negativeFields NegativeFields
   * @return {@link AccountDto}
   */
  private static AccountDto getSingleAccountDto(final NegativeValues negativeFields) {
    var converter = 1;
    if (null != negativeFields) {
      converter = -1;
    }
    return new AccountDto(
        ++id,
        SCIENCE_DATA.element(),
        SCIENCE_DATA.bosons(),
        FINANCE_DATA.iban(),
        BigDecimal.valueOf(converter * NUMBER_DATA.randomDouble(2, ZERO_VALUE, MAX_VALUE)));
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
   * Generate List of Mutual Funds Buy Transaction DTO
   *
   * @param size the size of mutual funds buy transactions required in the return list
   * @return {@link List<MutualFundBuyTransactionDto>}
   */
  private static List<MutualFundBuyTransactionDto> getMutualFundsBuyTransactionDto(
      final Integer size) {
    return IntStream.range(0, size).mapToObj(i -> getSingleMutualFundBuyTransactionDto()).toList();
  }

  /**
   * Generate Mutual Funds Buy Transactions with random value
   *
   * @return {@link MutualFundBuyTransactionDto}
   */
  private static MutualFundBuyTransactionDto getSingleMutualFundBuyTransactionDto() {
    return new MutualFundBuyTransactionDto(
        ++id,
        NUMBER_DATA.numberBetween(1, 5),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Instant.now(),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Boolean.TRUE);
  }

  /**
   * Generate List of Mutual Funds Sell Transaction DTO
   *
   * @param size the size of mutual funds sell transactions required in the return list
   * @return {@link List<MutualFundSellTransactionDto>}
   */
  private static List<MutualFundSellTransactionDto> getMutualFundsSellTransactionDto(
      final Integer size) {
    return IntStream.range(0, size).mapToObj(i -> getSingleMutualFundSellTransactionDto()).toList();
  }

  /**
   * Generate Mutual Funds Sell Transactions with random value
   *
   * @return {@link MutualFundSellTransactionDto}
   */
  private static MutualFundSellTransactionDto getSingleMutualFundSellTransactionDto() {
    return new MutualFundSellTransactionDto(
        ++id,
        NUMBER_DATA.numberBetween(1, 5),
        List.of(NUMBER_DATA.randomDigitNotZero(), NUMBER_DATA.randomDigitNotZero()),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Instant.now(),
        NUMBER_DATA.randomDouble(2, 1, 10000));
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
        Boolean.TRUE);
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
        Boolean.TRUE);
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
        Boolean.TRUE);
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
        Boolean.TRUE);
  }

  /**
   * Generate List of Mutual Funds Buy Transactions
   *
   * @param size the count of Mutual Funds Buy transactions required in the return list
   * @return {@link List<MutualFundBuyTransaction>}
   */
  private static List<MutualFundBuyTransaction> getMutualFundBuyTransaction(final Integer size) {
    return IntStream.range(0, size).mapToObj(i -> getSingleMutualFundBuyTransaction()).toList();
  }

  /**
   * Generate Mutual Fund Buy Transaction with random value
   *
   * @return {@link MutualFundBuyTransaction}
   */
  private static MutualFundBuyTransaction getSingleMutualFundBuyTransaction() {
    return new MutualFundBuyTransaction(
        ++id,
        NUMBER_DATA.randomDigitNotZero(),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Instant.now(),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Boolean.FALSE,
        Instant.now(),
        Instant.now());
  }

  /**
   * Generate List of Mutual Funds Sell Transactions
   *
   * @param size the count of Mutual Funds Sell transactions required in the return list
   * @return {@link List<MutualFundSellTransaction>}
   */
  private static List<MutualFundSellTransaction> getMutualFundSellTransaction(final Integer size) {
    return IntStream.range(0, size).mapToObj(i -> getSingleMutualFundSellTransaction()).toList();
  }

  /**
   * Generate Mutual Fund Sell Transaction with random value
   *
   * @return {@link MutualFundSellTransaction}
   */
  private static MutualFundSellTransaction getSingleMutualFundSellTransaction() {
    return new MutualFundSellTransaction(
        ++id,
        NUMBER_DATA.randomDigitNotZero(),
        List.of(NUMBER_DATA.randomDigitNotZero(), NUMBER_DATA.randomDigitNotZero()),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Instant.now(),
        NUMBER_DATA.randomDouble(2, 1, 10000),
        Instant.now(),
        Instant.now());
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
        Boolean.TRUE);
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
        Boolean.TRUE);
  }
}
