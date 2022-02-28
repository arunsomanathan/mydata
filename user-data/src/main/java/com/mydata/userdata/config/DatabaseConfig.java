package com.mydata.userdata.config;

import static com.mydata.userdata.property.DatabaseProperties.DB_SCHEMA;

import com.mydata.userdata.property.DatabaseProperties;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@RequiredArgsConstructor
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

  private final DatabaseProperties dbProps;

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return ConnectionFactories.get(
        ConnectionFactoryOptions.builder()
            .option(ConnectionFactoryOptions.DRIVER, dbProps.driver())
            .option(ConnectionFactoryOptions.HOST, dbProps.host())
            .option(ConnectionFactoryOptions.PORT, dbProps.port())
            .option(ConnectionFactoryOptions.DATABASE, dbProps.database())
            .option(ConnectionFactoryOptions.USER, dbProps.user())
            .option(ConnectionFactoryOptions.PASSWORD, dbProps.password())
            .option(DB_SCHEMA, dbProps.schema())
            .build());
  }
}
