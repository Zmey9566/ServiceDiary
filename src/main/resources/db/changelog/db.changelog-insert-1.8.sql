--changeset Alexander:1.8
-- Заполнение таблицы users

insert into users(email, password, roles_id)
select email, password, roles_id from mentor;

insert into users(email, password, roles_id)
select email, password, roles_id from student;