package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentorDao extends JpaRepository<Mentor, Integer> {

    List <Mentor> getAllByFamily(String family, Sort sort);

    List <Mentor> findAllByOrderByIdAsc();

    Mentor findByEmail(String email);

    @Query(value = "SELECT * FROM mentor WHERE name = :userName", nativeQuery = true)
    List <Mentor> get(String userName);

//    @Query(value = "DELETE FROM mentor WHERE id = :id", nativeQuery = true)
//    public void removeById(int id);

}
