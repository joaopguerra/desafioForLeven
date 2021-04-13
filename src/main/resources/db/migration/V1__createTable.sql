CREATE TABLE IF NOT EXISTS estudante
(
    matricula bigint       NOT NULL AUTO_INCREMENT,
    nome      varchar(100) NOT NULL,
    sobrenome varchar(100) NOT NULL,
    PRIMARY KEY (matricula)
);

CREATE TABLE IF NOT EXISTS telefones (
    id                  bigint       NOT NULL AUTO_INCREMENT,
    matricula_estudante bigint       NOT NULL,
    numero              varchar(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (matricula_estudante) REFERENCES estudante (matricula)
);