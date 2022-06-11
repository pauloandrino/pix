package com.pma.pix.domain.validator;

import com.pma.pix.domain.exception.NegocioException;
import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.repository.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaFisicaValidator implements Validators {

  private final PixKeyRepository pixKeyRepository;

  @Override
  public void validate(PixKey pixKey) {
    var count =
        pixKeyRepository.countByTipoPessoaAndAgenciaAndConta(
            pixKey.getTipoPessoa(), pixKey.getAgencia(), pixKey.getConta());

    if (count > 5) {
      throw new NegocioException("Não é permitido cadastrar mais de 5 chaves para pessoa fisica");
    }
  }
}
