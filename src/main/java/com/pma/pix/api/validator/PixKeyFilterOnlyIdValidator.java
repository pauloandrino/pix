package com.pma.pix.api.validator;

import com.pma.pix.api.model.input.PixKeyFilterInput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PixKeyFilterOnlyIdValidator
    implements ConstraintValidator<PixKeyFilterOnlyIdValidation, PixKeyFilterInput> {
  @Override
  public void initialize(PixKeyFilterOnlyIdValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(
      PixKeyFilterInput pixKeyFilterInput, ConstraintValidatorContext constraintValidatorContext) {

    return pixKeyFilterInput.getId() == null
        || (pixKeyFilterInput.getTipoChave() == null
            && pixKeyFilterInput.getAgencia() == null
            && pixKeyFilterInput.getConta() == null
            && pixKeyFilterInput.getNomeCorrentista() == null
            && pixKeyFilterInput.getDataAtivacao() == null
            && pixKeyFilterInput.getDataInativacao() == null);
  }
}
