package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorDao extends JpaRepository<Mentor, Integer> {
    public List <Mentor> getAllByFamily(String family, Sort sort);
    @Query(value = "SELECT * FROM mentor WHERE name = :userName", nativeQuery = true)
    public List <Mentor> get(String userName);

//    @Query(value = "DELETE FROM mentor WHERE id = :id", nativeQuery = true)
//    public void removeById(int id);
}
