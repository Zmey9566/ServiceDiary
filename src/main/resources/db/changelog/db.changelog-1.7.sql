--liquibase formatted sql
--changeset Alexander:1.7
-- Создание таблицы users

CREATE TABLE IF NOT EXISTS users
(
    id     SERIAL PRIMARY KEY,
    email varchar(40),
    password   varchar(100),
    roles_id INT references role(id)

);
