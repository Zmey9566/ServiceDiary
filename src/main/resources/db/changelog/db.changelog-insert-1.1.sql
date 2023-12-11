--changeset Alexander:1.1
-- Дозаполнение таблицы mentor

update mentor
set
    email = case
                when id = 1 then 'Sidorov@mail.ru'
                when id = 2 then 'Almazov@mail.ru'
                when id = 3 then 'Evmenov@mail.ru'
        end,
    role = 'ROLE_MENTOR'
where id in('1', '2', '3');