package com.mydata.userdata.property;

import io.r2dbc.spi.Option;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/** This Record holds the property values for Postgresql connection */
@ConstructorBinding
@ConfigurationProperties(prefix = "db")
public record DatabaseProperties(
    String driver,
    String host,
    String database,
    String schema,
    Integer port,
    String user,
    CharSequence password,
    String protocol,
    Boolean ssl,
    Long connectionTimeout) {
  public static final Option<String> DB_SCHEMA = Option.valueOf("schema");
}
