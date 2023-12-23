--changeset Alexander:1.7
-- Дозаполнение таблицы mentor


UPDATE mentor
set roles_id = 2
where id in (1, 2, 3);

-- Дозаполнение таблицы student
UPDATE student
set roles_id = 3
where id between 1 and 7;