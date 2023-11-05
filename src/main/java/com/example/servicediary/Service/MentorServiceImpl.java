package com.example.servicediary.Service;

import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MentorServiceImpl implements MentorService{

    MentorDao mentorDao;

    public MentorServiceImpl(MentorDao mentorDao) {
        this.mentorDao = mentorDao;
    }
    @Override
    public List<Mentor> findAll() {
        return mentorDao.findAll();
    }

    @Override
    public Optional<Mentor> findById(Integer id) {
        return mentorDao.findById(id);
    }

    @Override
    public void save(Mentor mentor) {
        mentorDao.save(mentor);
    }

    @Override
    public void deleteById(int id) {
        mentorDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        mentorDao.deleteAll();
    }
}
