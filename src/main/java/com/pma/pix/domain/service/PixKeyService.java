package com.pma.pix.domain.service;

import com.pma.pix.domain.domain.PixKey;
import com.pma.pix.domain.repository.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PixKeyService {

  private final PixKeyRepository pixKeyRepository;

  public PixKey salvar(PixKey pixKey) {
    log.info("Salvando a chave pix [{}]", pixKey.toString());
    return pixKeyRepository.save(pixKey);
  }
}
