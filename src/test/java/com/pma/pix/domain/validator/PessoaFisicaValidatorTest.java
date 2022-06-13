package com.pma.pix.domain.validator;

import com.pma.pix.domain.repository.PixKeyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PessoaFisicaValidatorTest {

  @Mock PixKeyRepository pixKeyRepository;
  @InjectMocks PessoaFisicaValidator pessoaFisicaValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void validatePessoaFisica() {
    when(pixKeyRepository.countByTipoPessoaAndAgenciaAndConta(any(), any(), any())).thenReturn(3L);
    pessoaFisicaValidator.validate(getPixKeyTemplate());
  }

  @Test
  void shouldThrowNegationExceptionGivenExceedLimit() {
    when(pixKeyRepository.countByTipoPessoaAndAgenciaAndConta(any(), any(), any())).thenReturn(6L);
    Assertions.assertThrows(
        RuntimeException.class, () -> pessoaFisicaValidator.validate(getPixKeyTemplate()));
  }
}
