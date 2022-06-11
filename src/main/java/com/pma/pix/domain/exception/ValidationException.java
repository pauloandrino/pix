package com.pma.pix.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {

  public ValidationException(String mensagem) {
    super(mensagem);
  }
}
