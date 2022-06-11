package com.pma.pix.domain.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.pma.pix.domain.exception.ValidationException;
import com.pma.pix.domain.model.PixKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfKeyValidator implements TypeKeyValidator {

  private final CPFValidator cpfValidator;

  @Override
  public void validate(PixKey pixKey) {

    try {
      cpfValidator.assertValid(pixKey.getChave());
    } catch (InvalidStateException e) {
      throw new ValidationException("CPF informado é inválido");
    }
  }
}
