package com.pma.pix.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PixKeyFilterOnlyIdValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PixKeyFilterOnlyIdValidation {
  String message() default "Quando filtrar por ID, nenhum outro filtro é aceito";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
