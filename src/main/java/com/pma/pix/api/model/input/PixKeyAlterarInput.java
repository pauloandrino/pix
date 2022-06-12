package com.pma.pix.api.model.input;

import com.pma.pix.api.validator.StringEnumeration;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class PixKeyAlterarInput {

  @NotNull private UUID id;

  @StringEnumeration(enumClass = TipoConta.class)
  @NotBlank
  private String tipoConta;

  @Range(min = 1, max = 9999)
  @NotNull
  private Integer agencia;

  @Range(min = 1, max = 99999999)
  @NotNull
  private Integer conta;

  @StringEnumeration(enumClass = TipoPessoa.class)
  @NotBlank
  private String tipoPessoa;

  @Size(max = 30)
  @NotBlank
  private String nomeCorrentista;

  @Size(max = 45)
  private String sobrenomeCorrentista;


}
