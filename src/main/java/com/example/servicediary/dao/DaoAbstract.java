package com.example.servicediary.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public class DaoAbstract<K, V> implements Dao<K, V> {

    private final Class<V> clazz;
    final String tableName;
    private final EntityManager entityManager;

    public DaoAbstract(Class<V> clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
        tableName= clazz.getSimpleName().toLowerCase();
    }

    @Override
    public void save(V entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(V entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();

    }

    @Override
    public V findById(K id) {
        entityManager.getTransaction().begin();
        final var finded = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        return finded;
    }

    @Override
    public List<V> getAll() {
        entityManager.getTransaction().begin();
        final var allEntity = entityManager.createNativeQuery("SELECT * FROM " + tableName, clazz)
                .getResultList();
//        final var allEntity = entityManager.createNativeQuery("SELECT * FROM :tableName")
//                .setParameter("tableName", clazz.getName().toLowerCase()).getResultList();
        entityManager.getTransaction().commit();
        return allEntity;
    }

    @Override
    public void removeById(K id) {
        entityManager.getTransaction().begin();
        final var finded = entityManager.find(clazz, id);
        entityManager.remove(finded);
        entityManager.getTransaction().commit();

    }

    @Override
    public void removeAll() {
        entityManager.getTransaction().begin();
        final var allEntity = entityManager.createNativeQuery("SELECT * FROM " + tableName, clazz)
                .getResultList();
        allEntity.stream().forEach(entityManager::remove);
        entityManager.getTransaction().commit();

//        NOOP
    }
}
