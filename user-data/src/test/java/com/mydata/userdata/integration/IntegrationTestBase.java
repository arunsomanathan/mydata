package com.mydata.userdata.integration;

import static com.mydata.userdata.common.TestConstants.API_NAME_URL_MAP;
import static com.mydata.userdata.common.TestConstants.POSTGRESQL_DB_PROPERTY_PREFIX;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mydata.utilities.test.conroller.ControllerTest;
import java.util.Objects;
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
public abstract class IntegrationTestBase implements ControllerTest {

  public static final int MIN_RECORDS_RESULT = 5;

  private static final String INIT_SQL = "db-init.sql";

  private static final String POSTGRESQL_IMAGE = "postgres:13.5";
  private static final String DB_NAME = "testDB";
  private static final String DB_USER_NAME = "testUser";
  private static final String DB_PASSWORD = "testPassword";

  @Container
  private static final PostgreSQLContainer<?> postgresql =
      new PostgreSQLContainer<>(POSTGRESQL_IMAGE)
          .withDatabaseName(DB_NAME)
          .withUsername(DB_USER_NAME)
          .withPassword(DB_PASSWORD)
          .withInitScript(INIT_SQL);

  @Autowired private WebTestClient webTestClient;

  @Override
  public WebTestClient getWebTestClient() {
    return webTestClient;
  }

  @Override
  public String getBaseUrl() {
    throw new IllegalStateException("getBaseUrl method should be overridden in the child class");
  }

  @Override
  public String getApiUrlFromApiName(String apiName) {
    throw new IllegalStateException(
        "getApiUrlFromApiName method should be overridden in the child class");
  }

  @DynamicPropertySource
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void properties(final DynamicPropertyRegistry registry) {
    var options = PostgreSQLR2DBCDatabaseContainer.getOptions(postgresql);
    registry.add(POSTGRESQL_DB_PROPERTY_PREFIX + "driver", () -> options.getRequiredValue(DRIVER));
    registry.add(POSTGRESQL_DB_PROPERTY_PREFIX + "host", () -> options.getRequiredValue(HOST));
    registry.add(POSTGRESQL_DB_PROPERTY_PREFIX + "port", () -> options.getRequiredValue(PORT));
    registry.add(POSTGRESQL_DB_PROPERTY_PREFIX + "user", () -> options.getRequiredValue(USER));
    registry.add(
        POSTGRESQL_DB_PROPERTY_PREFIX + "password", () -> options.getRequiredValue(PASSWORD));
    registry.add(
        POSTGRESQL_DB_PROPERTY_PREFIX + "database", () -> options.getRequiredValue(DATABASE));
    registry.add(POSTGRESQL_DB_PROPERTY_PREFIX + "schema", () -> "public");
  }

  /**
   * Verify the Get response containing a list
   *
   * @param apiName the API Name
   * @param respType the response Type
   */
  protected void verifyGetListResponse(final String apiName, final Class<?> respType) {
    get(API_NAME_URL_MAP.get(apiName))
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBodyList(respType)
                    .consumeWith(
                        r ->
                            assertTrue(
                                Objects.requireNonNull(r.getResponseBody()).size()
                                    >= MIN_RECORDS_RESULT)));
  }

  /**
   * Verify the Post response
   *
   * @param apiName the API Name
   * @param reqObject the request object
   * @param respType the response object type
   * @param nullCheckExcludedFields the fields to be excluded from null checks
   * @param <T> the response object type
   */
  protected <T> void verifyPostResponse(
      final String apiName,
      final T reqObject,
      final Class<T> respType,
      String... nullCheckExcludedFields) {
    post(API_NAME_URL_MAP.get(apiName), reqObject)
        .expectAll(
            ControllerTest::isOk,
            ControllerTest::isContentTypeJson,
            rSpec ->
                rSpec
                    .expectBody(respType)
                    .consumeWith(
                        r -> {
                          var response = r.getResponseBody();
                          assertNotNull(response);
                          assertThat(response)
                              .hasNoNullFieldsOrPropertiesExcept(nullCheckExcludedFields);
                        }));
  }
}
