package com.pma.pix.api.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
public class PixKeyModel {

  private String id;
  private String tipoChave;
  private String chave;
  private String tipoConta;
  private Integer agencia;
  private Integer conta;
  private String tipoPessoa;
  private String nomeCorrentista;
  private String sobrenomeCorrentista;
  private OffsetDateTime dataAtivacao;
  private String dataInativacao;

  public void setSobrenomeCorrentista(String sobrenomeCorrentista) {
    this.sobrenomeCorrentista = Objects.requireNonNullElse(sobrenomeCorrentista, "");

    // Fazendo essa lógica aqui, pois a configuração do Jackson está com BUG
    // https://github.com/FasterXML/jackson-databind/issues/2970
    // Poderiamos simplesmente anotar com @JsonSetter(contentNulls = Nulls.AS_EMPTY, nulls = Nulls.AS_EMPTY)
  }

  public void setDataInativacao(String dataInativacao) {
    this.dataInativacao = Objects.requireNonNullElse(dataInativacao, "");
    ;
  }
}
