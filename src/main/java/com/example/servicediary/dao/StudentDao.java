package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);
}
