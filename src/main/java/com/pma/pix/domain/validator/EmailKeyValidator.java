package com.pma.pix.domain.validator;

import com.pma.pix.domain.exception.ValidationException;
import com.pma.pix.domain.model.PixKey;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailKeyValidator implements Validators {

  private static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

  @Override
  public void validate(PixKey pixKey) {
    var isValidEMail = Pattern.compile(EMAIL_REGEX)
            .matcher(pixKey.getChave())
            .matches();

    if (!isValidEMail) {
      throw new ValidationException("EMAIL informado é inválido");
    }
  }
}
