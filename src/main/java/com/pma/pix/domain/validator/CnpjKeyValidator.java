package com.pma.pix.domain.validator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.pma.pix.domain.exception.ValidationException;
import com.pma.pix.domain.model.PixKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CnpjKeyValidator implements TypeKeyValidator {

  private final CNPJValidator cnpjValidator;

  @Override
  public void validate(PixKey pixKey) {
    try {
      cnpjValidator.assertValid(pixKey.getChave());
    } catch (InvalidStateException e) {
      throw new ValidationException("CNPJ informado é inválido");
    }
  }
}
