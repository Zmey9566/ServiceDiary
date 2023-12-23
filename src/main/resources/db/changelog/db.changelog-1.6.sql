--liquibase formatted sql
--changeset Alexander:1.6
-- Создание таблицы role

CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(40)
);

-- Добавление в таблицу mentor столбца roleId

ALTER TABLE mentor
    ADD roles_id INT references role(id);

-- Добавление в таблицу student столбца roleId

ALTER TABLE student
    ADD roles_id INT references role(id);