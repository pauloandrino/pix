package com.pma.pix.core.config;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ValidationConfig {
  @Bean
  public CPFValidator cpfValidator() {
    return new CPFValidator();
  }

  @Bean
  public CNPJValidator cnpjValidator() {
    return new CNPJValidator();
  }
}
