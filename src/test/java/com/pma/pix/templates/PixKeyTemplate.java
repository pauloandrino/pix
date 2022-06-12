package com.pma.pix.templates;

import com.pma.pix.api.model.input.PixKeyInput;
import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.TipoChave;
import com.pma.pix.domain.model.TipoConta;
import com.pma.pix.domain.model.TipoPessoa;

import java.util.UUID;

public class PixKeyTemplate {

    public static final UUID ID = UUID.fromString("79c9438f-d486-4fec-8388-f78c5e1fe0f4");
    public static final String TIPO_CHAVE = "EMAIL";
    public static final String CHAVE = "teste@email.com";

    public static final String TIPO_CONTA = "CORRENTE";
    public static final Integer AGENCIA = 1234;
    public static final Integer CONTA = 12345678;

    public static final String NOME_CORRENTISTA = "Carla";
    public static final String SOBRENOME_CORRENTISTA = "Silva";
    public static final String TIPO_PESSOA = "F";


    public static PixKeyInput getPixKeyInputTemplate() {

        return new PixKeyInput()
                .withTipoChave(TIPO_CHAVE)
                .withChave(CHAVE)
                .withTipoConta(TIPO_CONTA)
                .withAgencia(AGENCIA)
                .withConta(CONTA)
                .withNomeCorrentista(NOME_CORRENTISTA)
                .withSobrenomeCorrentista(SOBRENOME_CORRENTISTA)
                .withTipoPessoa(TIPO_PESSOA);


    }

    public static PixKey getPixKeyTemplate() {
        return new PixKey()
                .withId(ID)
                .withTipoChave(TipoChave.valueOf(TIPO_CHAVE))
                .withChave(CHAVE)
                .withTipoConta(TipoConta.valueOf(TIPO_CONTA))
                .withAgencia(AGENCIA)
                .withConta(CONTA)
                .withNomeCorrentista(NOME_CORRENTISTA)
                .withSobrenomeCorrentista(SOBRENOME_CORRENTISTA)
                .withTipoPessoa(TipoPessoa.valueOf(TIPO_PESSOA));

    }
}
