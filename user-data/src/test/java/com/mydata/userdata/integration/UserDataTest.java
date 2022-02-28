package com.mydata.userdata.integration;

import static com.mydata.userdata.utils.WebTestClientUtil.get;

import com.mydata.userdata.controller.InvestmentController;
import com.mydata.userdata.dto.AccountDto;
import com.mydata.userdata.utils.WebTestClientUtil;
import io.r2dbc.spi.ConnectionFactoryOptions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@MockitoSettings
@SpringBootTest
@AutoConfigureWebTestClient
@Testcontainers
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class UserDataTest {

  private static final String INVESTMENT_BASE_URL = "/investment";

  private static final String DEPOSIT_ACCOUNTS_URL = "depositaccounts";

  private static final String POSTGRESQL_IMAGE = "postgres:13.5";
  private static final String DB_NAME = "testDB";
  private static final String USER_NAME = "testUser";
  private static final String PASSWORD = "testPassword";

  @Container
  private static final PostgreSQLContainer<?> postgresql =
      new PostgreSQLContainer<>(POSTGRESQL_IMAGE)
          .withDatabaseName(DB_NAME)
          .withUsername(USER_NAME)
          .withPassword(PASSWORD)
          .withInitScript("sql/db-init.sql");

  @Autowired private WebTestClient webTestClient;

  @DynamicPropertySource
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void properties(final DynamicPropertyRegistry registry) {
    var options = PostgreSQLR2DBCDatabaseContainer.getOptions(postgresql);
    registry.add("db.driver", () -> options.getRequiredValue(ConnectionFactoryOptions.DRIVER));
    registry.add("db.host", () -> options.getRequiredValue(ConnectionFactoryOptions.HOST));
    registry.add("db.port", () -> options.getRequiredValue(ConnectionFactoryOptions.PORT));
    registry.add("db.user", () -> options.getRequiredValue(ConnectionFactoryOptions.USER));
    registry.add("db.password", () -> options.getRequiredValue(ConnectionFactoryOptions.PASSWORD));
    registry.add("db.database", () -> options.getRequiredValue(ConnectionFactoryOptions.DATABASE));
    registry.add("db.schema", () -> "public");
  }

  /** Test for {@link InvestmentController#getDepositAccounts()} */
  @Test
  @DisplayName("Integration Test: Get Deposit Accounts")
  void getDepositAccounts() {
    final List<AccountDto> accounts;
    getResponse(DEPOSIT_ACCOUNTS_URL)
        .expectAll(
            WebTestClientUtil::isOk, WebTestClientUtil::isContentTypeJson
            //                                responseSpec ->
            //                                        assertBodyEquals(responseSpec,
            // AccountDto.class, accounts,
            //             DEPOSIT_ACCOUNTS_URL)
            );
  }

  /** Get Request to given url */
  private WebTestClient.ResponseSpec getResponse(final String apiUrl) {
    return get(webTestClient, INVESTMENT_BASE_URL, apiUrl);
  }
}
