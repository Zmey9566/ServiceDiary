package com.example.servicediary.Service;

import java.util.List;
import java.util.Optional;

public interface StudentService<R, S> {
    List<R> findAllOrdered();
    List<R> getAll();
    Optional<R> findById(Integer id);
    void save(S saveDto);
    void update(R readDto, Integer id);
    void deleteById(int id);
    void deleteAll();
}
