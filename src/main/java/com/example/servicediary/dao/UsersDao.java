package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import com.example.servicediary.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);
}
