--liquibase formatted sql
--changeset Alexander:1.1
-- Добавление полей e-mail и role в таблицу mentor

ALTER TABLE mentor
ADD email VARCHAR(45),
ADD role VARCHAR(20);