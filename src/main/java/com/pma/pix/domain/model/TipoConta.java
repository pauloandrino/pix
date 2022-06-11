package com.pma.pix.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoConta {
  CORRENTE("corrente"),
  POUPANCA("poupanca");

  private final String tipoConta;
}
