--liquibase formatted sql
--changeset Alexander:1.3
-- Добавление таблицы mentor_student

CREATE TABLE IF NOT EXISTS mentor_student
(
    id     SERIAL PRIMARY KEY,
    mentor_id INT references mentor(id),
    student_id INT references student(id)

);