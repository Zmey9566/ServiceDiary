--changeset Alexander:1.2
-- Дозаполнение таблицы mentor

update mentor
set
    password = case
                when id = 1 then '$2y$10$51ABYWy1u/EKuGIsmQCnxeOzE9sYBB/w8etyjl/ExyPoPL/c89k2u'
                when id = 2 then '$2y$10$Au92jRNKb4mqawJ4b8csb.bQuevG3jmn5HpZeeTTU5qbKPcomrFKi'
                when id = 3 then '$2y$10$JVqQ7i74Zsk2bs5GNvsy7OYDTGVM757cjT8pt5FhYR/ueSOwbmRZy'
        end
where id in('1', '2', '3');