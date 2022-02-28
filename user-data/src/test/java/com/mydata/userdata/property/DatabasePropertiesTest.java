package com.mydata.userdata.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mydata.userdata.utils.YamlPropertySourceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@MockitoSettings
@SpringJUnitConfig
@EnableConfigurationProperties(DatabaseProperties.class)
@PropertySource(
    value = "classpath:application-test.yaml",
    factory = YamlPropertySourceFactory.class)
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class DatabasePropertiesTest {

  @Autowired private DatabaseProperties dbProps;

  /** Test for {{@link DatabaseProperties#driver()}} */
  @Test
  @DisplayName("Test Database Property: driver")
  void testDriver(@Value("${db.driver}") String driver) {
    assertEquals(driver, dbProps.driver());
  }

  @Test
  @DisplayName("Test Database Property: host")
  void host(@Value("${db.host}") String host) {
    assertEquals(host, dbProps.host());
  }

  @Test
  @DisplayName("Test Database Property: database")
  void database(@Value("${db.database}") String database) {
    assertEquals(database, dbProps.database());
  }

  @Test
  @DisplayName("Test Database Property: schema")
  void schema(@Value("${db.schema}") String schema) {
    assertEquals(schema, dbProps.schema());
  }

  @Test
  @DisplayName("Test Database Property: port")
  void port(@Value("${db.port}") Integer port) {
    assertEquals(port, dbProps.port());
  }

  @Test
  @DisplayName("Test Database Property: user")
  void user(@Value("${db.user}") String user) {
    assertEquals(user, dbProps.user());
  }

  @Test
  @DisplayName("Test Database Property: password")
  void password(@Value("${db.password}") CharSequence password) {
    assertEquals(password, dbProps.password());
  }

  @Test
  @DisplayName("Test Database Property: protocol")
  void protocol(@Value("${db.protocol}") String protocol) {
    assertEquals(protocol, dbProps.protocol());
  }

  @Test
  @DisplayName("Test Database Property: ssl")
  void ssl(@Value("${db.ssl}") Boolean ssl) {
    assertEquals(ssl, dbProps.ssl());
  }

  @Test
  @DisplayName("Test Database Property: connectionTimeout")
  void connectionTimeout(@Value("${db.connectionTimeout}") Long connectionTimeout) {
    assertEquals(connectionTimeout, dbProps.connectionTimeout());
  }
}
