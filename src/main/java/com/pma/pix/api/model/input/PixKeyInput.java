package com.pma.pix.api.model.input;

import com.pma.pix.api.validator.StringEnumeration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class PixKeyInput {

  @StringEnumeration(enumClass = TipoChave.class)
  @NotBlank
  private String tipoChave;

  @Size(max = 77)
  @NotBlank
  private String chave;

  @StringEnumeration(enumClass = TipoConta.class)
  @NotBlank
  private String tipoConta;

  @Range(min = 1, max = 9999)
  @NotNull
  private Integer agencia;

  @Range(min = 1, max = 99999999)
  @NotNull
  private Integer conta;

  @Size(max = 30)
  @NotBlank
  private String nomeCorrentista;

  @Size(max = 45)
  private String sobrenomeCorrentista;

  @StringEnumeration(enumClass = TipoPessoa.class)
  @NotBlank
  private String tipoPessoa;
}
