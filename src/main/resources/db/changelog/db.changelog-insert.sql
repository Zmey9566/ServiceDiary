--changeset Alexander:1
-- Заполнение таблицы mentor
INSERT into mentor
(family, name, price)

values
    ('Sidorov', 'Semen', 1500000),
    ('Almazov', 'Grigory', 1500000),
    ('Evmenov', 'Anton', 1500000);

--changeset Alexander:2
-- Заполнение таблицы student
INSERT into student
(family, name, level, mentor_Id)

values
    ('Kazakov', 'Ruslan', 'low', 1),
    ('Grigoryan', 'Artur', 'middle', 1),
    ('Adamov', 'Petr', 'high', 1),
    ('Kevorlov', 'Ignat', 'middle', 2),
    ('Karpenko', 'Dmitriy', 'low', 2),
    ('Ershov', 'Denis', 'middle',3),
    ('Potapov', 'Anton', 'high', 3);