package com.pma.pix.domain.validator;

import com.pma.pix.domain.model.PixKey;
import org.springframework.stereotype.Component;

@Component
public class CelularKeyValidator implements TypeKeyValidator {

  @Override
  public void validate(PixKey pixKey) {
    System.out.println("VALIDAR Celular");
  }
}
