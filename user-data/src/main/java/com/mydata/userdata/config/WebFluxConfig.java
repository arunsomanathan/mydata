package com.mydata.userdata.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/** This class is used for configuring the web flux */
@Configuration
@EnableWebFlux
@NoArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {

  @Override
  public void configureHttpMessageCodecs(final @NonNull ServerCodecConfigurer configurer) {
    configurer
        .defaultCodecs()
        .configureDefaultCodec(
            codec -> {
              if (codec instanceof Jackson2JsonDecoder || codec instanceof Jackson2JsonEncoder) {
                ((Jackson2CodecSupport) codec)
                    .setObjectMapper(
                        new Jackson2ObjectMapperBuilder()
                            .failOnUnknownProperties(true)
                            .dateFormat(new StdDateFormat())
                            .build());
              }
            });
    configurer.defaultCodecs().enableLoggingRequestDetails(true);
  }

  @Override
  public void addFormatters(final @NonNull FormatterRegistry registry) {
    final var registrar = new DateTimeFormatterRegistrar();
    registrar.setUseIsoFormat(true);
    registrar.registerFormatters(registry);
  }
}
