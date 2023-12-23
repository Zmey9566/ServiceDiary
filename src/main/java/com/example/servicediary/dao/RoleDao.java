package com.example.servicediary.dao;

import com.example.servicediary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
//    Role findRoleNameByEmail(String email);
}
