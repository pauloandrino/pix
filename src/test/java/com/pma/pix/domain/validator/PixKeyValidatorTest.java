package com.pma.pix.domain.validator;

import com.pma.pix.templates.PixKeyTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;

class PixKeyValidatorTest {

  @Mock private CpfKeyValidator cpfKeyValidator;
  @Mock private CnpjKeyValidator cnpjKeyValidator;
  @Mock private EmailKeyValidator emailKeyValidator;
  @Mock private CelularKeyValidator celularKeyValidator;
  @Mock private AleatorioKeyValidator aleatorioKeyValidator;
  @Mock private PessoaFisicaValidator pessoaFisicaValidator;
  @Mock private PessoaJuridicaValidator pessoaJuridicaValidator;

  @InjectMocks private PixKeyValidator pixKeyValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldValidate() {
    pixKeyValidator.validate(getPixKeyTemplate());
  }
}
