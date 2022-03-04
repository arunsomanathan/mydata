package com.mydata.userdata.integration;

import static com.mydata.userdata.common.ObjectProperties.ACCOUNT_ID;
import static com.mydata.userdata.common.URLs.*;
import static org.junit.jupiter.api.Assertions.*;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.mydata.userdata.controller.InvestmentController;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.dto.MiscellaneousDto;
import com.mydata.userdata.dto.MutualFundDto;
import com.mydata.userdata.dto.StockDto;
import com.mydata.userdata.utils.InvestmentTestArguments;
import com.mydata.utilities.test.conroller.ControllerTest;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "server.port=0")
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class InvestmentTest extends IntegrationTestBase implements ControllerTest {

  public static final int MIN_RECORDS_RESULT = 5;

  private final BeanTransformer dtoToDtoSkipId =
      new BeanUtils().getTransformer().skipTransformationForField(ACCOUNT_ID);

  @Override
  public String getBaseUrl() {
    return INVESTMENT_BASE_URL;
  }

  /** Test for {@link InvestmentController#getDepositAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Deposit Accounts")
  void getDepositAccounts() {
    get(DEPOSIT_ACCOUNTS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(AccountDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addDepositAccount(AccountDto)}
   *
   * @param depositAccount the expected deposit account
   */
  @ParameterizedTest(name = "Happy Path: Add Deposit Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addDepositAccount(final AccountDto depositAccount) {
    post(DEPOSIT_ACCOUNTS_URL, dtoToDtoSkipId.transform(depositAccount, AccountDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(AccountDto.class)
                    .consumeWith(
                        r -> {
                          var account = r.getResponseBody();
                          assertNotNull(account);
                          assertAll(
                              () -> assertNotNull(account.id()),
                              () -> assertNotNull(account.bankName()),
                              () -> assertNotNull(account.branch()),
                              () -> assertNotNull(account.accountNumber()),
                              () -> assertNotNull(account.balance()));
                        }));
  }

  /** Test for {@link InvestmentController#getLoanAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Loan Accounts")
  void getLoanAccounts() {
    get(LOAN_ACCOUNTS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(AccountDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addLoanAccount(AccountDto)}
   *
   * @param loanAccount the expected loan account
   */
  @ParameterizedTest(name = "Happy Path: Add Loan Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addLoanAccount(final AccountDto loanAccount) {
    post(LOAN_ACCOUNTS_URL, dtoToDtoSkipId.transform(loanAccount, AccountDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(AccountDto.class)
                    .consumeWith(
                        r -> {
                          var account = r.getResponseBody();
                          assertNotNull(account);
                          assertAll(
                              () -> assertNotNull(account.id()),
                              () -> assertNotNull(account.bankName()),
                              () -> assertNotNull(account.branch()),
                              () -> assertNotNull(account.accountNumber()),
                              () -> assertNotNull(account.balance()));
                        }));
  }

  /** Test for {@link InvestmentController#getMiscellaneousAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Miscellaneous Accounts")
  void getMiscellaneousAccounts() {
    get(MISCELLANEOUS_ACCOUNTS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(MiscellaneousDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addMiscellaneousAccount(MiscellaneousDto)}
   *
   * @param miscellaneousDto the expected miscellaneous account
   */
  @ParameterizedTest(name = "Happy Path: Add Miscellaneous Account")
  @InvestmentTestArguments(type = MiscellaneousDto.class)
  void addMiscellaneousAccount(final MiscellaneousDto miscellaneousDto) {
    post(
            MISCELLANEOUS_ACCOUNTS_URL,
            dtoToDtoSkipId.transform(miscellaneousDto, MiscellaneousDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(MiscellaneousDto.class)
                    .consumeWith(
                        r -> {
                          var miscellaneous = r.getResponseBody();
                          assertNotNull(miscellaneous);
                          assertAll(
                              () -> assertNotNull(miscellaneous.id()),
                              () -> assertNotNull(miscellaneous.investmentName()),
                              () -> assertNotNull(miscellaneous.balance()));
                        }));
  }

  /** Test for {@link InvestmentController#getMutualFunds()} */
  @Test
  @DisplayName("Happy Path: Get Mutual Funds")
  void getMutualFunds() {
    get(MUTUAL_FUNDS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(MutualFundDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addMutualFund(MutualFundDto)}
   *
   * @param mutualFundDto the expected mutual fund dto
   */
  @ParameterizedTest(name = "Happy Path: Add Mutual Fund")
  @InvestmentTestArguments(type = MutualFundDto.class)
  void addMutualFund(final MutualFundDto mutualFundDto) {
    post(MUTUAL_FUNDS_URL, dtoToDtoSkipId.transform(mutualFundDto, MutualFundDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(MutualFundDto.class)
                    .consumeWith(
                        r -> {
                          var mutualFund = r.getResponseBody();
                          assertNotNull(mutualFund);
                          assertAll(
                              () -> assertNotNull(mutualFund.id()),
                              () -> assertNotNull(mutualFund.mfCode()),
                              () -> assertNotNull(mutualFund.mfName()),
                              () -> assertNotNull(mutualFund.amc()),
                              () -> assertNotNull(mutualFund.type()));
                        }));
  }

  /** Test for {@link InvestmentController#getSavingAccounts()} */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getSavingAccounts() {
    get(SAVING_ACCOUNTS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(AccountDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addSavingAccount(AccountDto)}
   *
   * @param savingAccountDto the expected saving account dto
   */
  @ParameterizedTest(name = "Happy Path: Add Saving Account")
  @InvestmentTestArguments(type = AccountDto.class)
  void addSavingAccount(final AccountDto savingAccountDto) {
    post(SAVING_ACCOUNTS_URL, dtoToDtoSkipId.transform(savingAccountDto, AccountDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(AccountDto.class)
                    .consumeWith(
                        r -> {
                          var account = r.getResponseBody();
                          assertNotNull(account);
                          assertAll(
                              () -> assertNotNull(account.id()),
                              () -> assertNotNull(account.bankName()),
                              () -> assertNotNull(account.branch()),
                              () -> assertNotNull(account.accountNumber()),
                              () -> assertNotNull(account.balance()));
                        }));
  }

  /** Test for {@link InvestmentController#getStocks()} */
  @Test
  @DisplayName("Happy Path: Get Saving Accounts")
  void getStocks() {
    get(STOCKS_URL)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(StockDto.class)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Test for {@link InvestmentController#addStock(StockDto)}
   *
   * @param stockDto the expected stock dto
   */
  @ParameterizedTest(name = "Happy Path: Add Stock")
  @InvestmentTestArguments(type = StockDto.class)
  void addStock(final StockDto stockDto) {
    post(STOCKS_URL, dtoToDtoSkipId.transform(stockDto, StockDto.class))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(StockDto.class)
                    .consumeWith(
                        r -> {
                          var stock = r.getResponseBody();
                          assertNotNull(stock);
                          assertAll(
                              () -> assertNotNull(stock.id()),
                              () -> assertNotNull(stock.stockCode()),
                              () -> assertNotNull(stock.stockName()),
                              () -> assertNotNull(stock.stockExchange()),
                              () -> assertNotNull(stock.broker()));
                        }));
  }
}