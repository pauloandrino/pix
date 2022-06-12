package com.pma.pix.domain.service;

import com.pma.pix.domain.exception.EntidadeDuplicadaException;
import com.pma.pix.domain.exception.EntidadeNaoEncontradaException;
import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.PixKeyFilter;
import com.pma.pix.domain.repository.PixKeyRepository;
import com.pma.pix.domain.specs.PixKeySpecs;
import com.pma.pix.domain.validator.PixKeyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PixKeyService {

  public static final String MSG_PIX_KEY_DUPLICATED = "Chave PIX (%s) já está cadastrada";

  private final PixKeyRepository pixKeyRepository;
  private final PixKeyValidator pixKeyValidator;

  public PixKey salvar(PixKey pixKey) {
    log.info("Salvando a chave pix [{}]", pixKey.toString());
    try {

      pixKeyValidator.validate(pixKey);
      return pixKeyRepository.save(pixKey);
    } catch (DataIntegrityViolationException ex) {
      if (ex.getRootCause() instanceof SQLIntegrityConstraintViolationException
          && ex.getMessage() != null
          && ex.getMessage().contains("uk_chave")) {

        throw new EntidadeDuplicadaException(
            String.format(MSG_PIX_KEY_DUPLICATED, pixKey.getChave()));
      }
      throw new RuntimeException("Um erro inesperado aconteceu");
    }
  }

  public List<PixKey> findAll(PixKeyFilter pixKeyFilter) {
    var pixKeys = pixKeyRepository.findAll(PixKeySpecs.querYFilter(pixKeyFilter));

    if (pixKeys.isEmpty()) {
      throw new EntidadeNaoEncontradaException(
          "Não doi encontrado nenhuma pix key com os filtros requisitados");
    }

    return pixKeys;
  }
}
