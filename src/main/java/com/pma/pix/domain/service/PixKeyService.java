package com.pma.pix.domain.service;

import com.pma.pix.domain.exception.EntidadeDuplicadaException;
import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.repository.PixKeyRepository;
import com.pma.pix.domain.validator.PixKeyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

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
      if (ex.getRootCause() instanceof SQLIntegrityConstraintViolationException) {
        throw new EntidadeDuplicadaException(
            String.format(MSG_PIX_KEY_DUPLICATED, pixKey.getChave()));
      }
      throw new RuntimeException("Um erro inesperado aconteceu");
    }
  }
}
