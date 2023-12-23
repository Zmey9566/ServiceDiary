--liquibase formatted sql
--changeset Alexander:1.4
-- Удаляем столбец mentor_id из students

alter table student
    drop column mentor_id;