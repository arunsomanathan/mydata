package com.mydata.userdata.contract;

import com.mydata.userdata.integration.IntegrationTestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "server.port=0")
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public abstract class InvestmentBase extends IntegrationTestBase {

  @LocalServerPort int port;

  @BeforeEach
  public void setup() {
    RestAssured.baseURI = "http://localhost:" + this.port;
  }
}
