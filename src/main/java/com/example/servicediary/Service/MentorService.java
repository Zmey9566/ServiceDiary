package com.example.servicediary.Service;

import com.example.servicediary.dto.MentorReadDto;
import com.example.servicediary.dto.MentorSaveDto;

import java.util.List;
import java.util.Optional;

public interface MentorService {

    List<MentorReadDto> findAll();
    List<MentorReadDto> getAllById();

    Optional<MentorReadDto> findById(Integer id);

    void save(MentorSaveDto mentorSaveDto);
    void update(MentorReadDto mentorReadDto, Integer id);

    void deleteById(int id);

    void deleteAll();


}
