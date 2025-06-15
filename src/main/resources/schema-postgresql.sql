DROP TABLE IF EXISTS cliente CASCADE;

CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    cpf VARCHAR(11),
    email VARCHAR(100),
    senha VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS produto (
    id serial PRIMARY KEY,
    nome varchar(50) NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);