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

class PessoaJuridicaValidatorTest {

  @Mock PixKeyRepository pixKeyRepository;
  @InjectMocks PessoaJuridicaValidator pessoaJuridicaValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void validatePessoaFisica() {
    when(pixKeyRepository.countByTipoPessoaAndAgenciaAndConta(any(), any(), any())).thenReturn(15L);
    pessoaJuridicaValidator.validate(getPixKeyTemplate());
  }

  @Test
  void shouldThrowNegationExceptionGivenExceedLimit() {
    when(pixKeyRepository.countByTipoPessoaAndAgenciaAndConta(any(), any(), any())).thenReturn(21L);
    Assertions.assertThrows(
        RuntimeException.class, () -> pessoaJuridicaValidator.validate(getPixKeyTemplate()));
  }
}
