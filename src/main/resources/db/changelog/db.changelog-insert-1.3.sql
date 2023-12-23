--changeset Alexander:1.3
-- Заполнение таблицы mentor_student

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