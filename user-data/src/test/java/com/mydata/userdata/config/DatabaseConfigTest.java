package com.mydata.userdata.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mydata.userdata.property.DatabaseProperties;
import com.mydata.utilities.spring.YamlPropertySourceFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/** Test class for {@link DatabaseConfig} */
@MockitoSettings
@SpringJUnitConfig
@Import({WebFluxConfig.class, DatabaseConfig.class})
@EnableConfigurationProperties(DatabaseProperties.class)
@PropertySource(
    value = "classpath:application-test.yaml",
    factory = YamlPropertySourceFactory.class)
@RequiredArgsConstructor
class DatabaseConfigTest {

  private final ApplicationContext context;

  /** Test to verify {@link DatabaseConfig} is available in Spring Application Context */
  @Test
  @DisplayName("Test Database Configuration")
  void databaseConfig() {
    assertNotNull(
        context.getBean(DatabaseConfig.class),
        "DatabaseConfig should be present in the Application Context");
  }
}
