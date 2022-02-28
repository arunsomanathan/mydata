package com.mydata.userdata.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/** This class is used for configuring the web flux */
@Configuration
@EnableWebFlux
@NoArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {

  @Override
  public void addFormatters(final @NonNull FormatterRegistry registry) {
    final var registrar = new DateTimeFormatterRegistrar();
    registrar.setUseIsoFormat(true);
    registrar.registerFormatters(registry);
  }
}
