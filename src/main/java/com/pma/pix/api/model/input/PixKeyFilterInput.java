package com.pma.pix.api.model.input;

import com.pma.pix.api.validator.PixKeyFilterDateValidation;
import com.pma.pix.api.validator.PixKeyFilterOnlyIdValidation;
import com.pma.pix.api.validator.StringEnumeration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Getter
@Setter
@PixKeyFilterOnlyIdValidation
@PixKeyFilterDateValidation
public class PixKeyFilterInput {

  private String id;

  @StringEnumeration(enumClass = TipoChave.class)
  private String tipoChave;

  @Range(min = 1, max = 9999)
  private Integer agencia;

  @Range(min = 1, max = 99999999)
  private Integer conta;

  @Size(max = 30)
  private String nomeCorrentista;

  private String dataAtivacao;

  private String dataInativacao;
}
