CREATE TABLE chave_pix
(
    id                    BINARY(16) NOT NULL,
    tipo_chave            VARCHAR(9)  NOT NULL,
    chave                 VARCHAR(77) NOT NULL,
    tipo_conta            VARCHAR(10) NOT NULL,
    agencia               BIGINT(4) NOT NULL,
    conta                 BIGINT(8) NOT NULL,
    nome_correntista      VARCHAR(30) NOT NULL,
    sobrenome_correntista VARCHAR(45) NOT NULL,
    tipo_pessoa           VARCHAR(1)  NOT NULL,

    PRIMARY KEY (id),
    unique key uk_chave (chave)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;