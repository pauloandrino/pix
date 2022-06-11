package com.pma.pix.domain.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoConta {
  CORRENTE("corrente"),
  POUPANCA("poupanca");

  private final String tipoConta;
}
