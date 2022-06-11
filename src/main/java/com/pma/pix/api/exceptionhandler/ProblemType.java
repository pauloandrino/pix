package com.pma.pix.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  ENTIDADE_EM_USO("/entitdade-em-uso", "Entidade em uso"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
  DADOS_INVALIDOS("/dados-invalidos", "Dados Inválidos"),

  ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
  ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

  private String title;
  private String uri;

  ProblemType(String path, String title) {
    this.uri = "https://case.com.br" + path;
    this.title = title;
  }
}
