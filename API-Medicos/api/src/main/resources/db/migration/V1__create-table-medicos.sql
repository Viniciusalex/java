CREATE TABLE medicos
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    telefone      VARCHAR(20)  NOT NULL,
    crm           VARCHAR(6)   NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    ativo         TINYINT,
    logradouro    VARCHAR(100) NOT NULL,
    bairro        VARCHAR(100) NOT NULL,
    cep           VARCHAR(9)   NOT NULL,
    complemento   VARCHAR(100),
    numero        VARCHAR(20),
    uf            CHAR(2)      NOT NULL,
    cidade        VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE paciente
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    nome        VARCHAR(100) NOT NULL UNIQUE,
    email       VARCHAR(100) NOT NULL,
    telefone    VARCHAR(20)  NOT NULL,
    cpf         VARCHAR(14)  NOT NULL UNIQUE,  -- Adjusted length for CPF with formatting
    ativo       TINYINT,
    logradouro  VARCHAR(100) NOT NULL,
    bairro      VARCHAR(100) NOT NULL,
    cep         VARCHAR(9)   NOT NULL,
    complemento VARCHAR(100),
    numero      VARCHAR(20),
    uf          CHAR(2)      NOT NULL,
    cidade      VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);
