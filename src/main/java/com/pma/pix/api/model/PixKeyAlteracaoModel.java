package com.pma.pix.api.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
public class PixKeyAlteracaoModel {

  private String id;
  private String tipoChave;
  private String chave;
  private String tipoConta;
  private Integer agencia;
  private Integer conta;
  private String nomeCorrentista;
  private String sobrenomeCorrentista;
  private String tipoPessoa;
  private OffsetDateTime dataAtivacao;

  public void setSobrenomeCorrentista(String sobrenomeCorrentista) {
    this.sobrenomeCorrentista = Objects.requireNonNullElse(sobrenomeCorrentista, "");

    // Fazendo essa lógica aqui, pois a configuração do Jackson está com BUG
    // https://github.com/FasterXML/jackson-databind/issues/2970
    // Poderiamos simplesmente anotar com @JsonSetter(contentNulls = Nulls.AS_EMPTY, nulls =
    // Nulls.AS_EMPTY)
  }
}
