package com.pma.pix.domain.validator;

import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.TipoChave;
import com.pma.pix.domain.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

@RequiredArgsConstructor
@Component
public class PixKeyValidator {

  private final CpfKeyValidator cpfKeyValidator;
  private final CnpjKeyValidator cnpjKeyValidator;
  private final EmailKeyValidator emailKeyValidator;
  private final CelularKeyValidator celularKeyValidator;
  private final AleatorioKeyValidator aleatorioKeyValidator;
  private final PessoaFisicaValidator pessoaFisicaValidator;
  private final PessoaJuridicaValidator pessoaJuridicaValidator;

  public void validate(PixKey pixKey) {
    getTipoChaveValidators().get(pixKey.getTipoChave()).validate(pixKey);
    getPessoaValidators().get(pixKey.getTipoPessoa()).validate(pixKey);
  }

  private EnumMap<TipoChave, Validators> getTipoChaveValidators() {

    EnumMap<TipoChave, Validators> validators = new EnumMap<>(TipoChave.class);

    validators.put(TipoChave.CPF, cpfKeyValidator);
    validators.put(TipoChave.CNPJ, cnpjKeyValidator);
    validators.put(TipoChave.EMAIL, emailKeyValidator);
    validators.put(TipoChave.CELULAR, celularKeyValidator);
    validators.put(TipoChave.ALEATORIO, aleatorioKeyValidator);

    return validators;
  }

  private EnumMap<TipoPessoa, Validators> getPessoaValidators() {

    EnumMap<TipoPessoa, Validators> validators = new EnumMap<>(TipoPessoa.class);

    validators.put(TipoPessoa.F, pessoaFisicaValidator);
    validators.put(TipoPessoa.J, pessoaJuridicaValidator);

    return validators;
  }
}
