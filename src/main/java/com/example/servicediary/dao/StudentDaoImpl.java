package com.example.servicediary.dao;


import com.example.servicediary.entity.Student;
import jakarta.persistence.EntityManager;

public class StudentDaoImpl extends DaoAbstract<Integer, Student> {
    public StudentDaoImpl(Class<Student> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
    }
}
