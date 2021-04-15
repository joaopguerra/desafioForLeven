CREATE TABLE IF NOT EXISTS student
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    registration varchar(100) NOT NULL UNIQUE,
    name         varchar(100) NOT NULL,
    last_Name    varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS phones
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
    student_id bigint       NOT NULL,
    number               varchar(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES student (id)
);