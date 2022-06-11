package com.pma.pix.domain.validator;

import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.TipoChave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

@RequiredArgsConstructor
@Component
public class PixKeyValidator {

  private final CpfKeyValidator cpfKeyValidator;
  private final CnpjKeyValidator cnpjKeyValidator;

  public void validate(PixKey pixKey) {
    getValidators().get(pixKey.getTipoChave()).validate(pixKey);
  }

  private EnumMap<TipoChave, TypeKeyValidator> getValidators() {

    EnumMap<TipoChave, TypeKeyValidator> validators = new EnumMap<>(TipoChave.class);

    validators.put(TipoChave.CPF, cpfKeyValidator);
    validators.put(TipoChave.CNPJ, cnpjKeyValidator);

    return validators;
  }
}
