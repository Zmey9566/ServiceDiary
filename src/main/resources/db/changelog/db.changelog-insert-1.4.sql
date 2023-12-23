--changeset Alexander:1.4
-- Заполнение таблицы mentor_student

update student
set
    email = case
                when id = 1 then 'Kazakov@mail.ru'
                when id = 2 then 'Grigoryan@mail.ru'
                when id = 3 then 'Adamov@mail.ru'
                when id = 4 then 'Kevorlov@mail.ru'
                when id = 5 then 'Karpenko@mail.ru'
                when id = 6 then 'Ershov@mail.ru'
                when id = 7 then 'Potapov@mail.ru'
        end
--     ,
--     role = 'ROLE_STUDENT'
where id in(1, 2, 3, 4, 5, 6, 7);