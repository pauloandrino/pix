package com.pma.pix.domain.validator;

import com.pma.pix.templates.PixKeyTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;

class EmailKeyValidatorTest {

  @InjectMocks EmailKeyValidator emailKeyValidator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void validateEmail() {
    emailKeyValidator.validate(PixKeyTemplate.getPixKeyTemplate());
  }

  @Test
  void shouldTrowValidationExceptionGivenInvalidEmail() {
    var invalidEmail = "teste.com";
    Assertions.assertThrows(
        RuntimeException.class,
        () -> emailKeyValidator.validate(getPixKeyTemplate().withChave(invalidEmail)));
  }
}
