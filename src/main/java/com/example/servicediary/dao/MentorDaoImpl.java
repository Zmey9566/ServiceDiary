package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import jakarta.persistence.EntityManager;

public class MentorDaoImpl extends DaoAbstract<Integer, Mentor> {

//    private final EntityManager entityManager;
//    @Override
//    public void removeAll() {
//        // На нативе
//        entityManager.getTransaction().begin();
//        entityManager.createNativeQuery("DELETE FROM mentor")
//                .getResultList();
//        entityManager.getTransaction().commit();
//
//    }

    public MentorDaoImpl(Class<Mentor> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
//        this.entityManager = entityManager;
    }
}
