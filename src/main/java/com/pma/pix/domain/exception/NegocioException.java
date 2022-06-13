package com.pma.pix.domain.exception;

public class NegocioException extends RuntimeException {

  public NegocioException(String mensagem) {
    super(mensagem);
  }
}
