package com.example.servicediary.dao;

import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.MentorStudent;
import com.example.servicediary.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorStudentDao extends JpaRepository<MentorStudent, Integer> {

    List<MentorStudent> findByMentorId(int id);
    List<MentorStudent> findByStudentId(int id);
}
