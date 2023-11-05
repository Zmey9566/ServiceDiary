package com.example.servicediary.Service;

import com.example.servicediary.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorService {
    List<Mentor> findAll();
    Optional<Mentor> findById(Integer id);
    void save(Mentor mentor);
    void deleteById(int id);
    void deleteAll();


}
