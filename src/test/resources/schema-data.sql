----------
--Create table Mentor
----------
DROP TABLE IF EXISTS mentor_student;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS mentor;
DROP TABLE IF EXISTS role;

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

ALTER TABLE student
    ADD email VARCHAR(45),
--     ADD role VARCHAR(20),
    ADD password VARCHAR(100);

update student
set
    password = case
                   when id = 1 then '$2y$10$51ABYWy1u/EKuGIsmQCnxeOzE9sYBB/w8etyjl/ExyPoPL/c89k2u' --qwerty11
                   when id = 2 then '$2y$10$igIYAbnjLwuN3RvKbRB/A.pY7YDpTr5Hv8Y0rAtgYvPPI8R5rmkru'
                   when id = 3 then '$2y$10$dc1oqrrBpQXN8AJd9qDOkeUowZOhcS96oItIas.HnV4ND19AocWf.'
                   when id = 4 then '$2y$10$y43fTfYp.S9X6dLvGo3MeuwaAVHM9Qtkf8sCfz0p5fKj9VrxfMf0S'
                   when id = 5 then '$2y$10$vzaDHIN4FVxzIBflxHOqkO3/nmZJ140Sk6UI4nH6dM6WCARECOSEK'
                   when id = 6 then '$2y$10$qUgu8dl7DE60i5JvQAfCl.EWmx0r6/xVWGN4PhLLSVMrgB3ouiZfy'
                   when id = 7 then '$2y$10$7QDO/EDXqE1jeDsKID/9pedvvsKeAkcnhoVLklEVlvaarr3pQKiSS'
        end
where id in(1, 2, 3, 4, 5, 6, 7);

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
    name VARCHAR(40)
);

-- Добавление в таблицу mentor столбца roleId

ALTER TABLE mentor
    ADD roles_id INT references role (id) ON DELETE CASCADE;

-- Добавление в таблицу student столбца roleId

ALTER TABLE student
    ADD roles_id INT references role (id) ON DELETE CASCADE;

insert into role(name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MENTOR'),
    ('ROLE_STUDENT');

UPDATE mentor
set roles_id = 2
where id in (1, 2, 3);

-- Дозаполнение таблицы student
UPDATE student
set roles_id = 3
where id between 1 and 7;



