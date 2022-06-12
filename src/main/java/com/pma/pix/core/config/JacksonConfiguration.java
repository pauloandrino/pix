package com.pma.pix.core.config;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
/*
  @Bean
  @Primary
  ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
    final var objectMapper = builder.createXmlMapper(false).build();
    objectMapper
        .configOverride(String.class)
        .setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.valueOf("")));
    return objectMapper;
  }

  */
}
