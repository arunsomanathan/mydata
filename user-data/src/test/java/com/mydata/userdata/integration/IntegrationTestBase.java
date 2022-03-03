package com.mydata.userdata.integration;

import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.Getter;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@MockitoSettings
@Testcontainers
@Getter
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public abstract class IntegrationTestBase {

  private static final String INIT_SQL = "db-init.sql";

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
          .withInitScript(INIT_SQL);

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
}
