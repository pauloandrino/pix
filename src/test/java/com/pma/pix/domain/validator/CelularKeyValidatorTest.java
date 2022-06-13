package com.pma.pix.domain.validator;

import com.pma.pix.templates.PixKeyTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class CelularKeyValidatorTest {

  @InjectMocks CelularKeyValidator celularKeyValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void validateCelular() {
    celularKeyValidator.validate(PixKeyTemplate.getPixKeyTemplate());
  }
}
