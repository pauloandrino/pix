package com.pma.pix.domain.exception;

public class ValidationException extends RuntimeException {

  public ValidationException(String mensagem) {
    super(mensagem);
  }
}
