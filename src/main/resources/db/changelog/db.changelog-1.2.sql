--liquibase formatted sql
--changeset Alexander:1.2
-- Добавление поля password в таблицу mentor

ALTER TABLE mentor
ADD password VARCHAR(100);