package com.pma.pix.domain.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoChave {
  CELULAR("celular"),
  EMAIL("email"),
  CPF("cpf"),
  CNPJ("cnpj"),
  ALEATORIO("aleatorio");

  private final String keyName;
}
