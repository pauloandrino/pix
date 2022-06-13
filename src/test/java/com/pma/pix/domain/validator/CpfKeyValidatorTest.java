package com.pma.pix.domain.validator;

import br.com.caelum.stella.SimpleValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.pma.pix.templates.PixKeyTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

class CpfKeyValidatorTest {

  @Mock private CPFValidator cpfValidator;

  @InjectMocks CpfKeyValidator cpfKeyValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldValidateCPF() {
    var validCpf = "36440298062";
    doNothing().when(cpfValidator).assertValid(validCpf);
    cpfKeyValidator.validate(PixKeyTemplate.getPixKeyTemplate().withChave(validCpf));
  }

  @Test
  void shouldTrowValidationExceptionGivenInvalidCPF() {
    var invalidCpf = "67628431000199";
    doThrow(new InvalidStateException(new SimpleValidationMessage("CPF Inválido")))
        .when(cpfValidator)
        .assertValid(invalidCpf);
    Assertions.assertThrows(
        RuntimeException.class,
        () -> cpfKeyValidator.validate(getPixKeyTemplate().withChave(invalidCpf)));
  }
}
