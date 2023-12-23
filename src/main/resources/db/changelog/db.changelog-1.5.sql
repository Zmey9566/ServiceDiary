--liquibase formatted sql
--changeset Alexander:1.5
-- Добавление полей e-mail role password в таблицу students

ALTER TABLE student
    ADD email VARCHAR(45),
--     ADD role VARCHAR(20),
    ADD password VARCHAR(100);