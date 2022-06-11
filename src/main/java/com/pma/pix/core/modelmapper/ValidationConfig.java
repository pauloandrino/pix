package com.pma.pix.core.modelmapper;

import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ValidationConfig {



  @Bean
  public CPFValidator cpfValidator() {
    return new CPFValidator();
  }
}
