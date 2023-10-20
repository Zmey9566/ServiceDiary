package com.example.servicediary.dao;

import java.util.List;

public interface Dao<K, V> { // K - ключ, V - значение

    void save(V entity);
    void update(V entity);
    V findById(K id);
    List<V> getAll();
    void removeById(K id);
    void removeAll();
}
