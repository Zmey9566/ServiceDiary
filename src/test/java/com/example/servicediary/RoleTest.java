package com.example.servicediary;

import com.example.servicediary.dao.RoleDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTest extends TestBase{
    RoleDao roleDao;

    @Autowired
    public RoleTest(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

//    @Test
//    @DisplayName("Поиск роли по e-mail")
//    void findRoleByEmailTest() {
//        System.out.println(roleDao.findByEmail("Sidorov@mail.ru"));
//    }
}
