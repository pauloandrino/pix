package com.pma.pix.domain.service;

import com.pma.pix.domain.exception.EntidadeDuplicadaException;
import com.pma.pix.domain.exception.EntidadeNaoEncontradaException;
import com.pma.pix.domain.exception.NegocioException;
import com.pma.pix.domain.mapper.PixKeyMapper;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PixKeyService {

  public static final String MSG_PIX_KEY_DUPLICATED = "Chave PIX (%s) já está cadastrada";

  private final PixKeyRepository pixKeyRepository;
  private final PixKeyValidator pixKeyValidator;

  private final PixKeyMapper pixKeyMapper;

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

  public List<PixKey> buscar(PixKeyFilter pixKeyFilter) {
    log.info("Buscando chaves pix com o filtro [{}]", pixKeyFilter.toString());

    var pixKeys = pixKeyRepository.findAll(PixKeySpecs.querYFilter(pixKeyFilter));

    if (pixKeys.isEmpty()) {
      throw new EntidadeNaoEncontradaException(
          "Não foi encontrado nenhuma pix key com os filtros requisitados");
    }

    return pixKeys;
  }

  public PixKey alterar(PixKey pixKeyAtualizado) {

    var pixKeySalvo = buscarOuFalhar(pixKeyAtualizado.getId());

    if (pixKeySalvo.getDataInativacao() != null) {
      throw new NegocioException("Chave inativa não pode ser alterada");
    }

    pixKeyMapper.merge(pixKeyAtualizado, pixKeySalvo);

    log.info(
        "Atualizando chave pix id [{}], chave atualizada [{}]",
        pixKeySalvo.getId(),
        pixKeySalvo.toString());

    pixKeyRepository.save(pixKeySalvo);

    return pixKeySalvo;
  }

  public PixKey buscarOuFalhar(UUID pixKeyId) {
    return pixKeyRepository
        .findById(pixKeyId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Pix Key não foi encontrada"));
  }
}
