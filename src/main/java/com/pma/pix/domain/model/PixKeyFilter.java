package com.pma.pix.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Data
public class PixKeyFilter {

  private String id;

  private TipoChave tipoChave;

  @Range(min = 1, max = 9999)
  private Integer agencia;

  @Range(min = 1, max = 99999999)
  private Integer conta;

  @Size(max = 30)
  private String nomeCorrentista;

  private String dataAtivacao;

  private String dataInativacao;
}
