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
    price  decimal

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
(family, name, price)

values
    ('Sidorov', 'Semen', 1500000.34000000008381903171539306640625),
    ('Almazov', 'Grigory', 1500000.34000000008381903171539306640625),
    ('Evmenov', 'Anton', 1500000.34000000008381903171539306640625);

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



