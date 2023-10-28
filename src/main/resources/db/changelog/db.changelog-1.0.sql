--liquibase formatted sql
--changeset Alexander:1
-- Создание таблицы mentor
CREATE TABLE IF NOT EXISTS mentor
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    price  decimal

);
--changeset Alexander:2
-- Создание таблицы student
CREATE TABLE IF NOT EXISTS student
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    level  varchar(25),
    mentor_Id INT REFERENCES mentor (id) ON UPDATE CASCADE ON DELETE CASCADE
);