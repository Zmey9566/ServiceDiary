----------
--Create table Mentor
----------
DROP TABLE IF EXISTS mentor_student;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS mentor;

CREATE TABLE IF NOT EXISTS mentor
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    price  decimal,
    email varchar(50),
    password varchar(50)
--     ,
--     role varchar(20)
);

CREATE TABLE IF NOT EXISTS student
(
    id     SERIAL PRIMARY KEY,
    family varchar(25),
    name   varchar(25),
    level  varchar(25)
);

INSERT into mentor
(family, name, price, email, password) /*, role*/

values
    ('Sidorov', 'Semen', 1500000.34567800001241266727447509765625, 'Sidorov@mail.ru', 'qwerty11'),
--     ('Sidorov', 'Ivan', 1500000.3),
    ('Almazov', 'Grigory', 1500000.34567800001241266727447509765625, 'Almazov@mail.ru', 'qwerty12'),
    ('Evmenov', 'Anton', 1500000.34567800001241266727447509765625, 'Evmenov@mail.ru', 'qwerty13');

INSERT into student
(family, name, level)

values
    ('Kazakov', 'Ruslan', 'low'),
    ('Grigoryan', 'Artur', 'middle'),
    ('Adamov', 'Petr', 'high'),
    ('Kevorlov', 'Ignat', 'middle'),
    ('Karpenko', 'Dmitriy', 'low'),
    ('Ershov', 'Denis', 'middle'),
    ('Potapov', 'Anton', 'high');

CREATE TABLE IF NOT EXISTS mentor_student
(
    id     SERIAL PRIMARY KEY,
    mentor_id INT references mentor(id) ON DELETE CASCADE,
    student_id INT references student(id) ON DELETE CASCADE

);

insert into mentor_student(student_id)
select id from student;

update mentor_student
set
    mentor_id = case
                    when id in (1, 2, 3) then 1
                    when id in (4, 5) then 2
                    when id in (6, 7, 3) then 3
        end
where student_id in(1, 2, 3, 4, 5, 6, 7);


----------
--Create table Student
----------

CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    role VARCHAR(40)
);

-- Добавление в таблицу mentor столбца roleId

ALTER TABLE mentor
    ADD roleId INT references roles (id);

-- Добавление в таблицу student столбца roleId

ALTER TABLE student
    ADD roleId INT references roles (id);

insert into roles(role)
values
    ('ROLE_ADMIN'),
    ('ROLE_MENTOR'),
    ('ROLE_STUDENT');

UPDATE mentor
set roleid = 2
where id in (1, 2, 3);

-- Дозаполнение таблицы student
UPDATE student
set roleid = 3
where id between 1 and 7;



