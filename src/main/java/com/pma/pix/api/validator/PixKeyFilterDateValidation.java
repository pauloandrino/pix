package com.pma.pix.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PixKeyFilterDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PixKeyFilterDateValidation {
  String message() default "Somente uma data é aceita. Ou [dataAitvacao] ou [dataInativacao]";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
