----------
--Create table Mentor
----------
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS mentor;

CREATE TABLE IF NOT EXISTS mentor
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    price  decimal,
    email varchar(50),
    password varchar(50),
    role varchar(20)

);

CREATE TABLE IF NOT EXISTS student
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    level  varchar(25),
    mentor_Id INT REFERENCES mentor (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT into mentor
(family, name, price, email, password, role)

values
    ('Sidorov', 'Semen', 1500000.34567800001241266727447509765625, 'Sidorov@mail.ru', 'qwerty11', 'ROLE_MENTOR'),
--     ('Sidorov', 'Ivan', 1500000.3),
    ('Almazov', 'Grigory', 1500000.34567800001241266727447509765625, 'Almazov@mail.ru', 'qwerty12', 'ROLE_MENTOR'),
    ('Evmenov', 'Anton', 1500000.34567800001241266727447509765625, 'Evmenov@mail.ru', 'qwerty13', 'ROLE_MENTOR');

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



----------
--Create table Student
----------



