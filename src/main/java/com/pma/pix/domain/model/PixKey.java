package com.pma.pix.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity()
@Table(name = "chave_pix")
@Data
public class PixKey {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @NotNull
  private TipoChave tipoChave;

  @Size(max = 77)
  @NotBlank
  private String chave;

  @Enumerated(EnumType.STRING)
  @NotNull
  private TipoConta tipoConta;

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

  @Enumerated(EnumType.STRING)
  @NotNull
  private TipoPessoa tipoPessoa;
}
