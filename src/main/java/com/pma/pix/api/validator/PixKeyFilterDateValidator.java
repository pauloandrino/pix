package com.pma.pix.api.validator;

import com.pma.pix.api.model.input.PixKeyFilterInput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PixKeyFilterDateValidator
    implements ConstraintValidator<PixKeyFilterDateValidation, PixKeyFilterInput> {

  @Override
  public void initialize(PixKeyFilterDateValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(
      PixKeyFilterInput pixKeyFilterInput, ConstraintValidatorContext constraintValidatorContext) {

    return !(pixKeyFilterInput.getDataAtivacao() != null
        && pixKeyFilterInput.getDataInativacao() != null);
  }
}
