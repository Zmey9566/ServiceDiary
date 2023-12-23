--changeset Alexander:1.5
-- Дозаполнение таблицы student

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