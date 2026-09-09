CREATE TABLE funcionario (
    rowid BIGINT AUTO_INCREMENT,
    nm_funcionario VARCHAR(255)
);

CREATE TABLE agenda (
    rowid BIGINT AUTO_INCREMENT,
    nm_agenda VARCHAR(255),
    periodo VARCHAR(20)
);

CREATE TABLE compromisso (
    rowid BIGINT AUTO_INCREMENT,
    funcionario_id BIGINT,
    agenda_id BIGINT,
    dt_compromisso DATE,
    hr_compromisso TIME
);

INSERT INTO funcionario (nm_funcionario)
VALUES ('João'), ('Maria'), ('José'), ('Joana');