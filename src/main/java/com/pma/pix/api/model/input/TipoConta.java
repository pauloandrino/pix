package com.pma.pix.api.model.input;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoConta {
  CORRENTE("corrente"),
  POUPANCA("poupanca");

  private final String tipoConta;
}
