package com.mydata.userdata.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/** Test class for {@link WebFluxConfig} */
@MockitoSettings
@SpringJUnitConfig
@Import(WebFluxConfig.class)
@RequiredArgsConstructor
class WebFluxConfigTest {

  private final ApplicationContext context;

  /** Test to verify {@link WebFluxConfig} is available in Spring Application Context */
  @Test
  @DisplayName("Test WebFlux Configurations")
  void webFluxConfig() {
    assertNotNull(
        context.getBean(WebFluxConfig.class),
        "WebFluxConfig should be present in the Application Context");
  }
}
