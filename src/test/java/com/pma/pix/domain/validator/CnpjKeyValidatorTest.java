package com.pma.pix.domain.validator;

import br.com.caelum.stella.SimpleValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.pma.pix.templates.PixKeyTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static org.mockito.Mockito.doThrow;

class CnpjKeyValidatorTest {

  @Mock private CNPJValidator cnpjValidator;

  @InjectMocks CnpjKeyValidator cnpjKeyValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldValidateCNPJ() {
    var validCnpj = "67628431000178";
    Mockito.doCallRealMethod().when(cnpjValidator).assertValid(validCnpj);
    cnpjKeyValidator.validate(PixKeyTemplate.getPixKeyTemplate().withChave(validCnpj));
  }

  @Test
  void shouldTrhowValidationExceptionGivenInvalidCNPJ() {
    var invalidCnpj = "67628431000199";
    doThrow(new InvalidStateException(new SimpleValidationMessage("CPF Inválido")))
            .when(cnpjValidator)
            .assertValid(invalidCnpj);
    Assertions.assertThrows(
        RuntimeException.class,
        () -> cnpjKeyValidator.validate(getPixKeyTemplate().withChave(invalidCnpj)));
  }
}
